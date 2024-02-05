package com.rentgain.cb.decentro.controller;

import com.rentgain.cb.decentro.callouts.service.BankAccountValidationService;
import com.rentgain.cb.decentro.callouts.service.DecentroBankAccountToVirtualAccountService;
import com.rentgain.cb.decentro.callouts.service.DecentroKycVerificationService;
import com.rentgain.cb.decentro.callouts.service.DecentroUpiLinkGeneratorService;
import com.rentgain.cb.decentro.callouts.serviceint.*;
import com.rentgain.cb.decentro.model.*;
import com.rentgain.cb.wa.callouts.WaGsRestClient;
import com.rentgain.model.BankAccount;
import com.rentgain.model.Landlord;
import com.rentgain.model.LinkedVirtualAccount;
import com.rentgain.model.PaymentLink;
import com.rentgain.service.CrudService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static com.rentgain.controller.RentgainController.PENNY_DROP_AMOUNT;

@io.micronaut.http.annotation.Controller("/rentgain-dcb")
public class Controller {

    public static final String PAN_VALID = "VALID";

    @Inject
    DecentroBankAccountToVirtualAccountService decentroBankAccountToVirtualAccountService;

    @Inject
    DecentroVirtualAccountDetailsInt decentroVirtualAccountDetailsInt;

    @Inject
    BankAccountValidationService bankAccountValidationService;

    @Inject
    DecentroUpiLinkGeneratorService decentroUpiLinkGeneratorService;

    @Inject
    DecentroVirtualAccountInt decentroVirtualAccountInt;

    @Inject
    DecentroVirtualAccountBalanceInt decentroVirtualAccountBalanceInt;

    @Inject
    DecentroBankAccountSettlement decentroBankAccountSettlement;

    @Inject
    DecentroKycVerificationService decentroKYCVerification;

    @Inject
    WaGsRestClient waGsRestClient;

    @Post("/bav")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BankAccountValidationResponse validateBankAccount(@Body BankAccountValidationRequest bav) {
        System.out.println("Incoming : " + bav.toString());
        return bankAccountValidationService.validateBankAccount(bav);
    }

    @Post("/upi")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UpiPaymentLinkResponse generatorUpiLink(@Body UpiPaymentLinkRequest upiPaymentLinkRequest) {
        System.out.println("Incoming : " + upiPaymentLinkRequest.toString());
        return decentroUpiLinkGeneratorService.generateUpiLink(upiPaymentLinkRequest);
    }

    @Post("/va")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public VirtualAccountResponse createVirtualAccount(@Body VirtualAccountRequest virtualAccountRequest) {
        System.out.println("Incoming : " + virtualAccountRequest.toString());
        return decentroVirtualAccountInt.createVirtualAccount(virtualAccountRequest);
    }
    @Get("/vad")
    @Produces(MediaType.APPLICATION_JSON)
    public LinkedVirtualAccount getLinkedVirtualAccount(@QueryValue(value = "mobile") String phone, @QueryValue(value = "type") String virtualAccountType) {
        return decentroVirtualAccountDetailsInt.getLinkedVirtualAccount(phone,virtualAccountType);
    }

    @Get("/vab")
    @Produces(MediaType.APPLICATION_JSON)
    public VirtualAccountBalanceDetailsResponse getBalance(@QueryValue(value = "phone_number") String phone, @QueryValue(value = "account_number") String virtualAccount) {
       return decentroVirtualAccountBalanceInt.getVirtualAccountBalance(phone, virtualAccount);
    }

    @Post("/vas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PayoutResponse initiateSettlement(@Body PayoutRequest payoutRequest) {
        System.out.println("Incoming : " + payoutRequest.toString());
        PayoutResponse por = decentroBankAccountSettlement.initiatePayout(payoutRequest);
        System.out.println("POR : " + por);
        return por;
    }

    @Post("/abcb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse accountBalanceCallBackHandler(@Body String accountCallbackResponse) {
        System.out.println("Incoming : " + accountCallbackResponse.toString());
        return HttpResponse.ok(accountCallbackResponse);
    }

    @Post("/transaction_status")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse transactionStatusUpdate(@Body TransactionCallbackRequest transactionCallbackRequest) {
        System.out.println(String.format("Received  transactionCallbackRequest [%s]", transactionCallbackRequest));
        if("success".equals(transactionCallbackRequest.getTransactionStatus())){
            try {
                CrudService.saveTransactionCallbackRequest(transactionCallbackRequest);

            PaymentLink paymentLink = CrudService.findPaymentLink(transactionCallbackRequest.getDecentroTxnId());
            waGsRestClient.sendPaymentSuccessFullMessage(transactionCallbackRequest.getDecentroTxnId(), paymentLink.getTenantMobile(),paymentLink.getTenantName(),paymentLink.getLandlordName());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else{
            System.out.println(String.format("Failed  transactionCallbackRequest [%s]", transactionCallbackRequest));
        }
        return HttpResponse.ok(transactionCallbackRequest);
    }

    @Post("/va2ba")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse mapVirtualAccountToBankAccount(@Body SettlementAccountMappingRequest settlementAccountMappingRequest) {
       return HttpResponse.ok(decentroBankAccountToVirtualAccountService.mapVirtualAccountToBankAccount(settlementAccountMappingRequest));
    }
    @Get("/PANDetailed")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse verifiedPanDetailed(@QueryValue String pan) throws ExecutionException, InterruptedException {
        KycRequest kycRequest = getKycRequest(pan);
        KycResponse kycResponse = decentroKYCVerification.validatePANDetailed(kycRequest);
        PanVerification panVerification = new PanVerification();
        panVerification.setPan(pan);
        panVerification.setKycRequest(kycRequest);
        panVerification.setKycResponse(kycResponse);
        final KycResult kycResult = kycResponse.getKycResult();
        panVerification.setStatus(kycResult != null ? kycResult.getPanStatus() : null);
        return HttpResponse.ok(panVerification);
    }

    public static KycRequest getKycRequest(String pan) {
        KycRequest kycRequest = new KycRequest();
        kycRequest.setId_number(pan);
        kycRequest.setConsent("Y");
        kycRequest.setDocument_type("PAN-Detailed");
        kycRequest.setConsent_purpose("Landlord pan verification for account creation");
        kycRequest.setReference_id(UUID.randomUUID().toString());
        return kycRequest;
    }

}
