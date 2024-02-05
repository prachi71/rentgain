package com.rentgain.controller;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.SetOptions;
import com.rentgain.annotations.SendWA;
import com.rentgain.cb.decentro.callouts.service.BankAccountValidationService;
import com.rentgain.cb.decentro.callouts.service.DecentroKycVerificationService;
import com.rentgain.cb.decentro.callouts.serviceint.DecentroBankAccountValidationInt;
import com.rentgain.cb.decentro.callouts.serviceint.DecentroKYCVerification;
import com.rentgain.cb.decentro.callouts.serviceint.DecentroUpiLinkGeneratorInt;
import com.rentgain.cb.decentro.callouts.serviceint.DecentroVirtualAccountInt;
import com.rentgain.cb.decentro.model.*;
import com.rentgain.cb.wa.callouts.WaGsRestClient;
import com.rentgain.cb.wa.callouts.WaGsRestClientInt;
import com.rentgain.configuration.MessagesConfiguration;
import com.rentgain.model.*;
import com.rentgain.model.request.*;
import com.rentgain.service.CrudService;
import com.rentgain.service.MessageLookup;
import com.rentgain.utils.Utils;
import io.micronaut.core.naming.NameUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.netty.cookies.NettyCookie;
import jakarta.annotation.Nonnull;
import jakarta.inject.Inject;

import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static com.rentgain.cb.decentro.controller.Controller.getKycRequest;
import static io.micronaut.http.annotation.Controller.*;


@Controller("/rentgain")
public class RentgainController {


    public static final String DEFAULT = "default";
    public static final String RG_UI_URL = "https://rentgain.com";
    public static final String INVITE_MSG = "Dear tenant I have partnered with RentGain to manage my rental property %s including easier rent payments. Please click the link to complete your profile " + RG_UI_URL + "/rentgain/signupTenant";
    public static final String INVITE_STR = "https://wa.me/%s?text=%s?tid=%s";
    public static final String RENT_LINK = """
            Hello+%s.++%s
                    Your+residential+rental+invoice+for+the+month+%s+%s+is+generated.+
                            The+invoice+is+due+for+payment+by+%s+%s,+%s
                    Click+on+the+link+to+view+the+invoice+and+pay+it.++%s""";
    public static final String PENNY_DROP_AMOUNT = "1.5";
    @Inject
    MessageLookup messageLookup;

    @Inject
    BankAccountValidationService bankAccountValidationService;

    @Inject
    DecentroKycVerificationService decentroKYCVerification;

    @Inject
    DecentroVirtualAccountInt decentroVirtualAccountInt;

    @Inject
    DecentroUpiLinkGeneratorInt decentroUpiLinkGeneratorInt;

    @Inject
    WaGsRestClient waGsRestClient;

    @Inject
    WaGsRestClientInt waGsRestClientInt;


    @Get(uri = "/getProperties")
    public HttpResponse<?> getProperties(@CookieValue("ll_mobile") String llMobile) {
        try {
            return HttpResponse.ok(CrudService.getProperties(llMobile));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Get(uri = "/getTenants")
    public HttpResponse<?> getTenants(@CookieValue("ll_mobile") String llMobile) {
        try {
            return HttpResponse.ok(CrudService.getTenants(llMobile));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Get(uri = "/getTenantDetails")
    public HttpResponse<?> getTenantDetails(@CookieValue("ll_mobile") String llMobile) {
        try {
            List<TenantDetail> td = new ArrayList<TenantDetail>();
            List<Tenant> tenants = CrudService.getTenants(llMobile);
            tenants.forEach(t -> {
                td.add(new TenantDetail(t));
            });
            return HttpResponse.ok(td);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Post("/saveProperty")
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpResponse<?> saveProperty(@Body @Valid Property property, @CookieValue("ll_mobile") String llMobile) {
        // call decentro va service
        return save(llMobile, property);
    }


    @Post("/saveTenant")
    @Consumes(MediaType.APPLICATION_JSON)
    @SendWA
    public HttpResponse<?> saveTenant(@Body @Valid Tenant tenant, @CookieValue("ll_mobile") String llMobile) throws ExecutionException, InterruptedException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Calendar startLease = Calendar.getInstance();
        startLease.setTime(formatter.parse(tenant.getLlr_leasestartdate()));

        Calendar endLease = Calendar.getInstance();
        endLease.setTime(formatter.parse(tenant.getLlr_leaseenddate()));

        if (startLease.after(endLease)) {
            return getServerErrorMessage(messageLookup.getByName("lease-date"));
        }
        return save(llMobile, tenant);
    }

    @Post("/updateTenant")
    @Consumes(MediaType.APPLICATION_JSON)
    @SendWA
    public HttpResponse<?> updateTenant(@Body @Valid Tenant tenant) throws ExecutionException, InterruptedException {
        return updateTenantProfile(tenant);
    }


    @Post("/saveLandLord")
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpResponse<?> saveLandLord(@Body @Valid Landlord landlord, @Nonnull @QueryValue String sid) throws Exception {
        System.out.println("save landlord : " + landlord);

        PanDetails panDetails = new PanDetails();
        panDetails.setPan(landlord.getProfile().getLl_pan());
        panDetails.setLegalName(landlord.getProfile().getLl_fullname());
        panDetails.setMobile(landlord.getProfile().getLl_mobile());
        panDetails.setDob(landlord.getProfile().getDob());

        seedPanValidation(panDetails);

        seedBankAccountValidation(landlord, landlord.getBankAccount(), sid);

        // This will be used to create VA's
        seedCustomerId(landlord);

        CrudService.saveLandlord(landlord);
        // Validate bank account

        // after bank validated activate the lanalord
        return HttpResponse.status(HttpStatus.OK).body("Saved Successfully!").cookie(new NettyCookie(
                "ll_mobile", landlord.getProfile().getLl_mobile()));

    }

    private static Map<String, String> existingCids = new HashMap<String, String>();

    static {
        existingCids.put("6176207674", "PKRG93");
        existingCids.put("9845128580", "e32ab3f3-a451-4dc6-8c64-4a4ec999f781");
        existingCids.put("8310798566", "2a39d661-7f40-4a7a-9cf3-85fb14dbdd9b");
        existingCids.put("9845075065", "YMRG93");
    }

    private void seedCustomerId(Landlord landlord) {
        String telNo = Utils.getTenDigitTelNo(landlord.getProfile().getLl_mobile());
        String existingCid = existingCids.get(telNo);
        landlord.setLl_cust_id(existingCid != null ? existingCid : UUID.randomUUID().toString());
    }

    @Post("/saveLandLordBank")
    @Consumes(MediaType.APPLICATION_JSON)
    @SendWA
    public HttpResponse<?> saveBank(@QueryValue String mobile, @QueryValue String sid, @Body @Valid BankAccount bankAccount) throws ExecutionException, InterruptedException, ParseException {
        Landlord landlord = CrudService.getLandlord(mobile);
        if (landlord != null) {
            if (ValidationState.FAILURE.equals(landlord.getBankAccount().getState())) {
                seedBankAccountValidation(landlord, bankAccount, sid);
            }
        }
        return HttpResponse.ok();
    }

    @Post("/savePan")
    @Consumes(MediaType.APPLICATION_JSON)
    @SendWA
    public HttpResponse<?> savePan(@Body @Valid PanDetails panDetails) throws ExecutionException, InterruptedException, ParseException {
        seedPanValidation(panDetails);
        return HttpResponse.ok();
    }

    private void seedPanValidation(PanDetails panDetails) throws ExecutionException, InterruptedException {
        PanVerification panVerification = new PanVerification();
        panVerification.setMobile(panDetails.getMobile());
        panVerification.setPan(panDetails.getPan());
        panVerification.setKycRequest(getKycRequest(panVerification.getPan()));
        CrudService.savePanVerification(panVerification);
    }

    private void seedBankAccountValidation(Landlord landlord, BankAccount bankAccount, String sid) throws ExecutionException, InterruptedException {
        BankAccountValidationRequest bankAccountValidationRequest = new BankAccountValidationRequest();
        bankAccountValidationRequest.setPurpose_message("Validating " + landlord.getProfile().getLl_fullname() + " bank details");
        bankAccountValidationRequest.setReference_id(UUID.randomUUID().toString());
        BeneficiaryDetails beneficiaryDetails = new BeneficiaryDetails();
        beneficiaryDetails.setAccount_number(bankAccount.getLl_bacctn());
        beneficiaryDetails.setIfsc(bankAccount.getLl_ifsc());
        bankAccountValidationRequest.setBeneficiary_details(beneficiaryDetails);
        bankAccountValidationRequest.setTransfer_amount(PENNY_DROP_AMOUNT);


        BankAccountValidation bankAccountValidation = new BankAccountValidation();
        bankAccountValidation.setCreatedTime(System.currentTimeMillis());
        bankAccountValidation.setMobile(landlord.getProfile().getLl_mobile());
        bankAccountValidation.setName(landlord.getProfile().getLl_fullname());
        bankAccountValidation.setBankAccountValidationRequest(bankAccountValidationRequest);
        bankAccountValidation.setSid(sid);

        CrudService.saveBankAccountVerification(bankAccountValidation);
    }

    @Post("/requestReminder")
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpResponse<?> reminderRequest(@Body @Valid RequestReminder onDemandRequest, @CookieValue("ll_mobile") String llMobile) throws ExecutionException, InterruptedException {
        return saveOnDemandRequest(onDemandRequest, llMobile);
    }

    @Post("/requestVerification")
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpResponse<?> verificationRequest(@Body @Valid RequestVerification verify, @CookieValue("ll_mobile") String llMobile) {
        return saveOnDemandRequest(verify, llMobile);
    }

    @Post("/requestReceipt")
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpResponse<?> receiptRequest(@Body @Valid RequestReceipt getReceipt, @CookieValue("ll_mobile") String llMobile) {
        return saveOnDemandRequest(getReceipt, llMobile);
    }

    @Post("/requestDeposit")
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpResponse<?> depositRequest(@Body @Valid RequestDeposit depositRequest, @CookieValue("ll_mobile") String llMobile) {
        return saveOnDemandRequest(depositRequest, llMobile);
    }

    @Post("/requestRent")
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpResponse<?> rentRequest(@Body @Valid RequestRent requestRent, @CookieValue("ll_mobile") String llMobile) throws ExecutionException, InterruptedException {
        requestRent.setRequest_rent_ll(llMobile);
        Map returnMap = new HashMap();
        final HttpResponse<?> httpResponse = saveOnDemandRequest(requestRent, llMobile, returnMap);

        String virtualAccount = CrudService.getVirtualAccount(llMobile, requestRent.getRequest_rent_tenant());
        final Tenant tenant = CrudService.getTenants(llMobile).stream().filter(tenants -> tenants.getLlr_existing_property().equals(requestRent.getRequest_rent_tenant())).findFirst().get();
        String tenantMobile = tenant.getLlp_tenantnumber();
        String rent = tenant.getLlr_amount();

        UpiPaymentLinkRequest upiPaymentLinkRequest = new UpiPaymentLinkRequest();
        upiPaymentLinkRequest.setAmount(Double.parseDouble(rent));
        upiPaymentLinkRequest.setPayee_account(virtualAccount);
        upiPaymentLinkRequest.setGenerate_uri(1);
        final DocumentReference docRef = (DocumentReference) returnMap.get("docRef");
        upiPaymentLinkRequest.setReference_id(docRef.getId());
        upiPaymentLinkRequest.setPurpose_message(String.format("Rent for %s for %s %s", tenant.getLlr_existing_property(), requestRent.getRequest_rent_month(), requestRent.getRequest_rent_year()));
        upiPaymentLinkRequest.setGenerate_qr(0);
        upiPaymentLinkRequest.setGenerate_uri(1);
        upiPaymentLinkRequest.setExpiry_time(32400);
        upiPaymentLinkRequest.setCustomized_qr_with_logo(0);
        // UPI link
        UpiPaymentLinkResponse upiPaymentLinkResponse = decentroUpiLinkGeneratorInt.generateUpiLink(upiPaymentLinkRequest);
        Landlord ll = CrudService.getLandlord(llMobile);

        savePaymentLink(requestRent, tenant, rent, upiPaymentLinkResponse, ll);

        RequestRent rr = docRef.get().get().toObject(RequestRent.class);
        rr.setPaymentLink(upiPaymentLinkResponse);
        docRef.set(rr, SetOptions.merge());

        // Send via whatsapp
//        Hello %s %s" +
//        " Your residential rental invoice for the month %s 20%s is generated." +
//                "The invoice is due for payment by %s %s, 20%s." +
//                "Click on the link to view the invoice and pay it. %s"
        LocalDate ld = LocalDate.now();

        //ResponseWrapper rw = waGsRestClient.optIn(tenantMobile);

//        HsmRentReq req = new HsmRentReq();
//        req.setPhoneNbr("16176207674");//,tenant.getLlp_tenantnumber());
//        req.setName(tenant.getLlp_tenantfullname());
//        req.setRentMonth(requestRent.getRequest_rent_month());
//                req.setRentYear(requestRent.getRequest_rent_year());
//                req.setDueDay(String.valueOf(ld.getDayOfMonth()));
//                req.setDueMonth(ld.getMonth().toString());
//                req.setDueYear(String.valueOf(ld.getYear()));
//                req.setUpiLink(upiPaymentLinkResponse.getData().getGeneratedLink() );
        waGsRestClientInt.sendHSM(tenant.getLlp_tenantnumber(), tenant.getLlp_tenantfullname(),
                requestRent.getRequest_rent_month(),
                requestRent.getRequest_rent_year(),
                String.valueOf(ld.getDayOfMonth()), ld.getMonth().toString(), String.valueOf(ld.getYear()),
                upiPaymentLinkResponse.getData().getGeneratedLink());

//        new UpiLinkGenerator().queueHsm(req);
        return httpResponse;
    }

    private void savePaymentLink(RequestRent requestRent, Tenant tenant, String rent, UpiPaymentLinkResponse upiPaymentLinkResponse, Landlord ll) throws ExecutionException, InterruptedException {
        PaymentLink paymentLink = new PaymentLink();

        paymentLink.setPaymentLinkId(upiPaymentLinkResponse.getDecentroTxnId());

        //rent details
        paymentLink.setRent(rent);
        paymentLink.setMonth(requestRent.getRequest_rent_month());
        paymentLink.setYear(requestRent.getRequest_rent_year());
        paymentLink.setRentFor(String.format("%s, %s", paymentLink.getMonth(), paymentLink.getYear()));

        // property details
        Property property = CrudService.getProperties(ll.getProfile().getLl_mobile()).stream().filter(properties -> properties.getLlp_nickname().equals(tenant.getLlr_existing_property())).findFirst().get();
        paymentLink.setPropertyAddress(property.getLlp_address());

        // landlorddetails
        paymentLink.setLandlordName(ll.getProfile().getLl_fullname());
        paymentLink.setLandlordPan(ll.getProfile().getLl_pan());
        paymentLink.setLandlordEmail(ll.getProfile().getLl_email());
        paymentLink.setLandlordMobile(ll.getProfile().getLl_mobile());

        // tenant details
        paymentLink.setTenantName(tenant.getLlp_tenantfullname());
        paymentLink.setTenantMobile(tenant.getLlp_tenantnumber());
        paymentLink.setTenantPan(tenant.getLlp_tenantpan());
        paymentLink.setTenantEmail(tenant.getLlp_tenantemail());


        paymentLink.setLandlordMobile(ll.getProfile().getLl_mobile());

        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
        final String date = sdf.format(Calendar.getInstance().getTime());
        paymentLink.setDateIssued(date);
        // calculate actual due date
        paymentLink.setDateDue(date);

        int invoiceMonth = 0;
        switch (paymentLink.getMonth().substring(0, 3).toLowerCase()) {
            case "jan":
                invoiceMonth = Calendar.JANUARY;
                break;
            case "feb":
                invoiceMonth = Calendar.FEBRUARY;
                break;
            case "mar":
                invoiceMonth = Calendar.MARCH;
                break;
            case "apr":
                invoiceMonth = Calendar.APRIL;
                break;
            case "may":
                invoiceMonth = Calendar.MAY;
                break;
            case "jun":
                invoiceMonth = Calendar.JUNE;
                break;
            case "jul":
                invoiceMonth = Calendar.JULY;
                break;
            case "aug":
                invoiceMonth = Calendar.AUGUST;
                break;
            case "sep":
                invoiceMonth = Calendar.SEPTEMBER;
                break;
            case "oct":
                invoiceMonth = Calendar.OCTOBER;
                break;
            case "nov":
                invoiceMonth = Calendar.NOVEMBER;
                break;
            case "dec":
                invoiceMonth = Calendar.DECEMBER;
                break;

        }
        paymentLink.setInvoiceNumber(String.format("%s-%s", paymentLink.getYear(), (invoiceMonth + 1)));

        CrudService.savePaymentLink(paymentLink);
        System.err.println(paymentLink);
    }

    private HttpResponse<?> updateTenantProfile(Tenant tenant) throws ExecutionException, InterruptedException {
        final String name = NameUtils.hyphenate(TenantProfile.class.getSimpleName(), true);
        MessagesConfiguration mc = messageLookup.getByName(name);
        MessagesConfiguration defaultMc = messageLookup.getByName("Default");
        TenantInvite ti = CrudService.getTenantInvite(tenant.getLlp_tenantid());
        try {
            CrudService.update(tenant, ti);
            try {
                PanDetails panDetails = new PanDetails();
                panDetails.setPan(tenant.getLlp_tenantpan());
                panDetails.setMobile(ti.getTenantMobile());
                panDetails.setLegalName(tenant.getLlp_tenantfullname());
                seedPanValidation(panDetails);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Message message = getSuccessMessage(mc);
            return HttpResponse.ok(message).cookie(new NettyCookie(
                    "llt_mobile", ti.getTenantMobile()));
        } catch (Exception e) {
            return getServerErrorMessage(mc);
        }

    }

    public HttpResponse<?> saveOnDemandRequest(OnDemandRequest onDemandRequest, String llMobile, Object... returnHolder) {
        final String name = NameUtils.hyphenate(onDemandRequest.getClass().getSimpleName(), true);
        MessagesConfiguration mc = messageLookup.getByName(name);
        MessagesConfiguration defaultMc = messageLookup.getByName("Default");
        try {
            DocumentReference savedDoc = CrudService.saveOnDemandRequest(onDemandRequest, llMobile);
            if (returnHolder.length > 0) {
                Map returnMap = (Map) returnHolder[0];
                returnMap.put("docRef", savedDoc);
            }
        } catch (Exception e) {
            return getServerErrorMessage(mc);
        }
        Message message = getSuccessMessage(mc);
        return HttpResponse.ok(message);
    }

    private MutableHttpResponse<Message> save(String llMobile, BaseModel bm) {
        final String name = NameUtils.hyphenate(bm.getClass().getSimpleName(), true);
        MessagesConfiguration mc = messageLookup.getByName(name);
        Message message = getSuccessMessage(mc);
        try {
            switch (name) {
                case "property":
                    Property property = (Property) bm;
                    Landlord landlord = CrudService.getLandlord(llMobile);
//                    String customer_id = landlord.getProfile().getLl_fullname() + "_" + property.getLlp_nickname();
//                    customer_id = customer_id.replace(" ", "_");
//                    customer_id = "PKRG93";
//                    VirtualAccountRequest virtualAccountRequest = VirtualAccountFactory.getVirtualAccountRequest(landlord, customer_id);
//                    VirtualAccountResponse virtualAccountResponse = decentroVirtualAccountInt.createVirtualAccount(virtualAccountRequest);
//                    property.setLlp_virtualAccountNumber(virtualAccountResponse);
                    CrudService.saveProperty(llMobile, property);
                    break;
                case "tenant":
                    TenantInvite tenantInvite = CrudService.saveTenant(llMobile, bm);
                    message.setConfirmButtonText("Invite Tenant");
                    String inviteMsg = URLEncoder.encode(String.format(INVITE_MSG, tenantInvite.getPropertyNickName()), StandardCharsets.UTF_8);
                    String link = String.format(INVITE_STR, tenantInvite.getTenantMobile(), inviteMsg, tenantInvite.getInviteId());
                    message.setLink(link);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            return getServerErrorMessage(mc);
        }

        // message.setToast(true);
        return HttpResponse.ok(message);
    }

    private Message getSuccessMessage(MessagesConfiguration mc) {
        MessagesConfiguration defaultMc = messageLookup.getByName(DEFAULT);
        Message message = new Message();
        message.setOnSuccess(mc.getSuccess());
        message.setStatusTxt(mc.getStatusText());
        if (mc.getLink() != null)
            message.setLink(mc.getLink());
        else {
            message.setLink(defaultMc.getLink());
        }
        if (mc.getConfirmButtonText() != null)
            message.setConfirmButtonText(mc.getConfirmButtonText());
        else {
            message.setConfirmButtonText(defaultMc.getConfirmButtonText());
        }
        if (mc.getFooterMsg1() != null)
            message.setFooterMsg1(mc.getFooterMsg1());
        else {
            message.setFooterMsg1(defaultMc.getFooterMsg1());
        }
        if (mc.getFooterMsg2() != null)
            message.setFooterMsg2(mc.getFooterMsg2());
        else {
            message.setFooterMsg2(defaultMc.getFooterMsg2());
        }
        if (mc.getFooterMsg3() != null)
            message.setFooterMsg3(mc.getFooterMsg3());
        else {
            message.setFooterMsg3(defaultMc.getFooterMsg3());
        }
        return message;
    }

    private MutableHttpResponse<Message> getServerErrorMessage(MessagesConfiguration mc) {

        Message message = new Message();
        message.setOnError(mc.getError());
        message.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
        message.setStatusTxt(HttpStatus.INTERNAL_SERVER_ERROR.getReason());
        return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }


}