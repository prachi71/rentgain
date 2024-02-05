package com.rentgain.scheduled;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.rentgain.cb.decentro.callouts.service.BankAccountValidationService;
import com.rentgain.cb.decentro.callouts.service.DecentroKycVerificationService;
import com.rentgain.cb.decentro.callouts.serviceint.DecentroKYCVerification;
import com.rentgain.cb.decentro.callouts.serviceint.DecentroTransactionStatusInt;
import com.rentgain.cb.decentro.model.*;
import com.rentgain.cb.wa.callouts.WaGsRestClient;
import com.rentgain.model.Landlord;
import com.rentgain.model.ValidationState;
import com.rentgain.service.CrudService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static com.rentgain.service.CrudService.savePanVerification;

@io.micronaut.http.annotation.Controller("/rentgain-scheduled")
public class LandLordVerificationService {

    @Inject
    WaGsRestClient waGsRestClient;

    @Inject
    DecentroKycVerificationService decentroKYCVerification;

    @Inject
    DecentroTransactionStatusInt decentroTransactionStatusInt;

    @Inject
    BankAccountValidationService bankAccountValidationService;
    private String status;

    @Inject
    VirtualAccountCreator virtualAccountCreator;

    @Inject
    VirtualAccountToSettlementAccountMapper virtualAccountToSettlementAccountMapper;

    @Get("/schedule")

    public HttpResponse doWork() {
        try {
            verifyLandlordPan();
            verifyLandlordBankAccount();
            checkPendingBankAccountValidations();
            virtualAccountCreator.createVirtualBankAccount();
            virtualAccountToSettlementAccountMapper.mapVirtualBankAccountToSettlement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpResponse.ok();
    }

    public void verifyLandlordPan() {
        try {
            Collection<QueryDocumentSnapshot> panVerification = CrudService.findKycValidation();
            panVerification.forEach(panV -> {
                validatePan(panV);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyLandlordBankAccount() {
        System.out.println("Running->verifyLandlordBankAccount:");
        BankAccountValidation bankAccountValidation = null;
        try {
            // No status.
            Collection<QueryDocumentSnapshot> bankValidation = CrudService.findBankValidation();
            System.out.println("Running->verifyLandlordBankAccount: bankValidation records " + bankValidation.size());
            for (QueryDocumentSnapshot bank : bankValidation) {
                bankAccountValidation = bank.toObject(BankAccountValidation.class);
                final BankAccountValidationRequest bankAccountValidationRequest = bankAccountValidation.getBankAccountValidationRequest();
                BankAccountValidationResponse bankAccountValidationResponse = bankAccountValidationService.validateBankAccount(bankAccountValidationRequest);
                bankAccountValidation.setStatus(bankAccountValidationResponse.getStatus());
                bankAccountValidation.setBankAccountValidationResponse(bankAccountValidationResponse);
                System.out.println("Running->verifyLandlordBankAccount: bankValidation  " + bankAccountValidationResponse);
                handleStatus(bankAccountValidation, bankAccountValidationResponse.getStatus());

            }
        } catch (HttpClientResponseException | ExecutionException | InterruptedException e) {
            if (e instanceof HttpClientResponseException) {
                Optional<BankAccountValidationResponse> error = ((HttpClientResponseException) e).getResponse()
                        .getBody(BankAccountValidationResponse.class);
                bankAccountValidation.setBankAccountValidationResponse(error.get());
                handleStatus(bankAccountValidation, error.get().getError().getStatus());
            } else {
                e.printStackTrace();
            }

        }
    }

    public void checkPendingBankAccountValidations() {
        try {
            // status = "pending"
            Collection<QueryDocumentSnapshot> pendingBankValidation = CrudService.findPendingBankValidation();
            pendingBankValidation.forEach(bank -> {
                BankAccountValidation bankAccountValidation = bank.toObject(BankAccountValidation.class);
                TransactionStatusResponse transactionStatusResponse = decentroTransactionStatusInt.getTransactionStatus(bankAccountValidation.getBankAccountValidationRequest().getReference_id());
                bankAccountValidation.setStatus(transactionStatusResponse.getStatus());
                handleStatus(bankAccountValidation, transactionStatusResponse.getStatus());
            });
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void handleStatus(BankAccountValidation bankAccountValidation, String transactionStatusResponse) {
        System.out.println("Running->verifyLandlordBankAccount: handleStatus  " + bankAccountValidation);
        try {
            Landlord landlord = CrudService.getLandlord(bankAccountValidation.getMobile());
            switch (transactionStatusResponse) {
                case "success":
                    if (STATE_NOTIFICATION.SUCCESS != bankAccountValidation.getNotification()) {
                        waGsRestClient.sendBankVerificationSuccessfull(bankAccountValidation.getName(), bankAccountValidation.getMobile(), bankAccountValidation.getSid());
                        updateStatus(bankAccountValidation, STATE_NOTIFICATION.SUCCESS, landlord, ValidationState.SUCCESS);
                    }
                    break;
                case "failure":
                    if (STATE_NOTIFICATION.FAILURE != bankAccountValidation.getNotification()) {
                        waGsRestClient.sendBankVerificationFailed(bankAccountValidation.getName(), bankAccountValidation.getMobile(), bankAccountValidation.getSid());
                        updateStatus(bankAccountValidation, STATE_NOTIFICATION.FAILURE, landlord, ValidationState.FAILURE);
                    }
                    break;
                case "pending":
                    if (STATE_NOTIFICATION.PENDING != bankAccountValidation.getNotification()) {
                        waGsRestClient.sendBankVerificationPending(bankAccountValidation.getName(), bankAccountValidation.getMobile());
                        updateStatus(bankAccountValidation, STATE_NOTIFICATION.PENDING, landlord, ValidationState.PENDING);
                    }
                    break;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStatus(BankAccountValidation bankAccountValidation, STATE_NOTIFICATION success, Landlord landlord, ValidationState success1) throws ExecutionException, InterruptedException {
        System.out.println("Running->verifyLandlordBankAccount: updateStatus  " + bankAccountValidation + ":" + success1);
        bankAccountValidation.setNotification(success);
        landlord.getBankAccount().setState(success1);
        CrudService.saveBankAccountVerification(bankAccountValidation);
        // Update bank account status on landlord
        CrudService.saveLandlord(landlord);
    }


    public void notifySuccessOrFailureBankValidations(BankAccountValidation bankAccountValidation) {
/*
https://media.smsgupshup.com/GatewayAPI/rest?userid=2000219131&password={{PASSWORD}}&send_to=16176207674&v=1.1&format=json&msg_type=TEXT&method=SENDMESSAGE&msg=Hello+Mr.++Kumar%0A%0AThank+you+for+signing+up+with+RentGain+.%0A%0ABank+account+verification+is+successfull+.%0A%0ARegards&isTemplate=true&header=RentGain+%3A+Renting+Made+Easy&footer=https%3A%2F%2Frentgain.com
 */
/*
https://media.smsgupshup.com/GatewayAPI/rest?userid=2000219131&password={{PASSWORD}}&send_to=16176207674&v=1.1&format=json&msg_type=TEXT&method=SENDMESSAGE&msg=Hello+Mr.++Kumar%0A%0AWe+are+unable+to+verify+your+bank+account.+%0A%0AIt+appears+that+the+provided+bank+details+are+incorrect.%0A%0ATo+proceed+further+please+update+your+bank+details.&isTemplate=true&header=RentGain+%3A+Renting+Made+Easy&footer=https%3A%2F%2Frentgain.com&buttonUrlParam=%3Fmb%3D16176207674
 */

        switch (bankAccountValidation.getStatus()) {
            case "success":
                if (STATE_NOTIFICATION.SUCCESS != bankAccountValidation.getNotification()) {
                    waGsRestClient.sendBankVerificationSuccessfull(bankAccountValidation.getName(), bankAccountValidation.getMobile(), bankAccountValidation.getSid());
                }
                break;
            case "failure":
                if (STATE_NOTIFICATION.FAILURE != bankAccountValidation.getNotification()) {
                    waGsRestClient.sendBankVerificationFailed(bankAccountValidation.getName(), bankAccountValidation.getMobile(), bankAccountValidation.getSid());
                }
                break;
            case "pending":
                if (STATE_NOTIFICATION.PENDING != bankAccountValidation.getNotification()) {
                    waGsRestClient.sendBankVerificationSuccessfull(bankAccountValidation.getName(), bankAccountValidation.getMobile(), bankAccountValidation.getSid());
                }
                break;
        }


    }

    private void validatePan(QueryDocumentSnapshot panV) {
        PanVerification panVerificationPojo = panV.toObject(PanVerification.class);
        final KycRequest kycRequest = panVerificationPojo.getKycRequest();
        String originalRefId = kycRequest.getReference_id();
        kycRequest.setReference_id(UUID.randomUUID().toString());
        KycResponse kycResponse = decentroKYCVerification.validatePANDetailed(kycRequest);
        panVerificationPojo.setOriginalReqId(originalRefId);
        panVerificationPojo.setStatus(kycResponse.getStatus());
        panVerificationPojo.setKycResponse(kycResponse);
        panVerificationPojo.setKycRequest(kycRequest);
        try {
            if ("SUCCESS".equals(kycResponse.getStatus())) {
               if (!STATE_NOTIFICATION.SUCCESS.equals(panVerificationPojo.getNState())) {
                    waGsRestClient.sendPanVerificationSuccessfull(panVerificationPojo.getMobile(), kycResponse.getKycResult().getFullName());
                    panVerificationPojo.setNState(STATE_NOTIFICATION.SUCCESS);
                }
            } else {
                if (!STATE_NOTIFICATION.FAILURE.equals(panVerificationPojo.getNState())) {
                    waGsRestClient.sendPanVarificationFailure(panVerificationPojo.getMobile(), kycResponse.getKycResult().getFullName());
                    panVerificationPojo.setNState(STATE_NOTIFICATION.FAILURE);
                }
            }
            savePanVerification(panVerificationPojo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
