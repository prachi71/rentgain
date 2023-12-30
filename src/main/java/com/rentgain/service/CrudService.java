package com.rentgain.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.rentgain.cb.decentro.model.*;
import com.rentgain.model.*;
import com.rentgain.model.request.OnDemandRequest;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class CrudService {

    public static final String RG_BANK_ACCOUNT_VERIFICATION_COLLECTION = "rgBankAccountVerification";

    public static void handleSave(BaseModel object) {

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        findBankValidation();
        findBankValidation();
        findPendingBankValidation();

    }

    public static Collection findKycValidation() throws ExecutionException, InterruptedException {
        final Filter unverifiedKyc =
                Filter.equalTo("status", null);
        Query q = FireStoreUtil.getCollectionReference("rgPanVerification").where(unverifiedKyc);
        List<QueryDocumentSnapshot> data = q.get().get().getDocuments();
        return data;
    }

    public static Collection findBankValidation() throws ExecutionException, InterruptedException {
        final Filter unverifiedBank =
                Filter.equalTo("status", null);
        Query q = FireStoreUtil.getCollectionReference(RG_BANK_ACCOUNT_VERIFICATION_COLLECTION).where(unverifiedBank);
        List<QueryDocumentSnapshot> data = q.get().get().getDocuments();
        return data;
    }

    public static Collection findPendingBankValidation() {
        final Filter unverifiedBank = Filter.equalTo("status", "pending");
        Query q = FireStoreUtil.getCollectionReference(RG_BANK_ACCOUNT_VERIFICATION_COLLECTION).where(unverifiedBank);
        List<QueryDocumentSnapshot> data = null;
        try {
            data = q.get().get().getDocuments();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }


    public static TenantInvite saveTenant(String llMobile, BaseModel tenant) throws ExecutionException, InterruptedException {
        Tenant toSave = (Tenant) tenant;
        final DocumentReference llDoc = FireStoreUtil.getDb().collection("rgLandlords_" + llMobile).document(llMobile);

        final CollectionReference properties = llDoc
                .collection("properties");
        Landlord ll = llDoc.get().get().toObject(Landlord.class);
        Query query = properties.whereEqualTo("llp_nickname", toSave.getLlr_existing_property());
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        final DocumentSnapshot next = querySnapshot.get().iterator().next();
        Property property = next.toObject(Property.class);

        //Validate property matched nick name
        property.getTenants().add(toSave);
        //ApiFuture<DocumentReference> done = next.getReference().collection("tenants").add(toSave);


        ApiFuture<DocumentReference> done = llDoc.collection("tenants").add(tenant);
        String tenantId = done.get().getId();

        TenantInvite tenantInvite = new TenantInvite();
        tenantInvite.setTenantMobile(toSave.getLlp_tenantnumber());
        tenantInvite.setTenantId(tenantId);
        tenantInvite.setLandLordMobile(llMobile);
        tenantInvite.setLandLordName(ll.getProfile().getLl_fullname());
        tenantInvite.setSent(false);
        tenantInvite.setPropertyNickName(toSave.getLlr_existing_property());
        ApiFuture<DocumentReference> tenantInviteRef = FireStoreUtil.getDb().collection("rgTenantInvites").add(tenantInvite);
        tenantInvite.setInviteId(tenantInviteRef.get().getId());
        return tenantInvite;
    }

    public static void saveProperty(String ll_mobile, BaseModel property) throws ExecutionException, InterruptedException {
        final CollectionReference document = FireStoreUtil.getDb().collection("rgLandlords_" + ll_mobile).document(ll_mobile).collection("properties");
        document.add(property).get();
    }

    public static void updateProperty(String propertyID, String llMobile, BaseModel property) throws ExecutionException, InterruptedException {
        final CollectionReference document = FireStoreUtil.getDb().collection("rgLandlords_" + llMobile).document(llMobile).collection("properties");
        ApiFuture<WriteResult> f = document.document(propertyID).set(property, SetOptions.merge());
    }

    public static void savePanVerification(PanVerification panVerification) throws ExecutionException, InterruptedException {
        final CollectionReference document = FireStoreUtil.getDb().collection("rgPanVerification");
        document.document(panVerification.getPan()).set(panVerification, SetOptions.merge());
    }

    public static void saveBankAccountVerification(BankAccountValidation bankAccountValidation) throws ExecutionException, InterruptedException {
        final CollectionReference document = FireStoreUtil.getDb().collection(RG_BANK_ACCOUNT_VERIFICATION_COLLECTION);
        StringJoiner sj = new StringJoiner("_");
        final BeneficiaryDetails beneficiary_details = bankAccountValidation.getBankAccountValidationRequest().getBeneficiary_details();
        sj.add(bankAccountValidation.getMobile()).add(beneficiary_details.getIfsc()).add(beneficiary_details.getAccount_number());
        ApiFuture<WriteResult> f = document.document(sj.toString()).set(bankAccountValidation, SetOptions.merge());
    }

    public static List<Property> getProperties(String ll_mobile) throws ExecutionException, InterruptedException {
        final CollectionReference document = FireStoreUtil.getDb().collection("rgLandlords_" + ll_mobile).document(ll_mobile).collection("properties");
        return document.get().get().toObjects(Property.class);
    }

    public static List<Tenant> getTenants(String ll_mobile) throws ExecutionException, InterruptedException {
        final CollectionReference document = FireStoreUtil.getDb().collection("rgLandlords_" + ll_mobile).document(ll_mobile).collection("tenants");
        List<Tenant> tenants = document.get().get().toObjects(Tenant.class);
        return tenants;
    }

    public static Boolean isLandlordRegistered(String mobile) throws ExecutionException, InterruptedException {
        final CollectionReference document = FireStoreUtil.getDb().collection("rgLandlords_" + mobile);
        return document.get().get().isEmpty() == false;
    }

    public static DocumentReference saveOnDemandRequest(OnDemandRequest onDemandRequest, String ll_mobile) throws ExecutionException, InterruptedException {
        final CollectionReference document = FireStoreUtil.getDb().collection("rgLandlords_" + ll_mobile).document(ll_mobile).collection("requests");
        ApiFuture<DocumentReference> req = document.add(onDemandRequest);
        return req.get();
    }

    public static Landlord saveLandlord(Landlord ll) throws ExecutionException, InterruptedException {
        System.out.println("save landlord in crud service: " + ll);
        WriteBatch wb = FireStoreUtil.getDb().batch();
        final String ll_mobile = ll.getProfile().getLl_mobile();
        final DocumentReference document = FireStoreUtil.getDb().collection("rgLandlords_" + ll_mobile).document(ll_mobile);
        System.out.println("save landlord document: " + document);
        WriteBatch saveLandLord = wb.set(document, ll, SetOptions.merge());

        ApiFuture<List<WriteResult>> future = wb.commit();
        // ...
        // future.get() blocks on batch commit operation
        for (WriteResult result : future.get()) {
            System.out.println("Update time : " + result.getUpdateTime());
        }
        return ll;
    }

    public static boolean validateSession(String sid) throws ExecutionException, InterruptedException {
        final CollectionReference sessions = FireStoreUtil.getDb().collection("rgSession");
        Query query = sessions.whereIn(FieldPath.documentId(), Collections.singletonList(sid));
        ApiFuture<QuerySnapshot> result = query.get();
        return !result.get().isEmpty();
    }

    public static void update(Tenant tenant, TenantInvite ti) throws ExecutionException, InterruptedException {
        final DocumentReference llDoc = FireStoreUtil.getDb().collection("rgLandlords_" + ti.getLandLordMobile()).document(ti.getLandLordMobile());
        final CollectionReference tenants = llDoc
                .collection("tenants");
        Query query = tenants.whereEqualTo(FieldPath.documentId(), ti.getTenantId());
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        final DocumentSnapshot next = querySnapshot.get().iterator().next();
        Tenant tenantWithId = next.toObject(Tenant.class);
        ApiFuture f = next.getReference().set(tenant, SetOptions.mergeFields(Arrays.stream(new String[]{
                "llp_tenantaddress", "llp_tenantpan", "llp_tenantfullname", "llp_tenantemail",
                "llp_tenantunit"}).toList()));
        System.out.println(f.isDone());

    }

    public static TenantInvite getTenantInvite(String tid) throws ExecutionException, InterruptedException {
        return FireStoreUtil.getDb().collection("rgTenantInvites").whereEqualTo(FieldPath.documentId(), tid).get().get().iterator().next().toObject(TenantInvite.class);
    }

    public static Landlord getLandlord(String llMobile) throws ExecutionException, InterruptedException {
        final DocumentReference llDoc = FireStoreUtil.getDb().collection("rgLandlords_" + llMobile).document(llMobile);
        Landlord ll = llDoc.get().get().toObject(Landlord.class);
        return ll;
    }

    public static String getVirtualAccount(String mobile, String propertyNickName) throws ExecutionException, InterruptedException {
        final Optional<Property> propertyByNickName = CrudService.getProperties(mobile).stream().filter(property -> property.getLlp_nickname().equals(propertyNickName)).findFirst();
        Property property = propertyByNickName.get();
        VirtualAccountResponse.VirtualAccountResponseData data = property.getLlp_virtualAccountNumber().getData().get(0);
        return data.getAccountNumber();
    }

    public static void savePaymentLink(PaymentLink paymentLink) {
        final CollectionReference document = FireStoreUtil.getDb().collection("rgPaymentLinks");
        final ApiFuture<WriteResult> save = document.document(paymentLink.getPaymentLinkId()).set(paymentLink, SetOptions.merge());
        save.isDone();
    }

    public static void saveTransactionCallbackRequest(TransactionCallbackRequest transactionCallbackRequest) throws ExecutionException, InterruptedException {
        PaymentLink paymentLink = findPaymentLink(transactionCallbackRequest.getDecentroTxnId());
        paymentLink.setTransactionCallbackRequest(transactionCallbackRequest);
        savePaymentLink(paymentLink);
    }

    public static PaymentLink findPaymentLink(String paymentId) throws InterruptedException, ExecutionException {
        PaymentLink paymentLink = null;
        final CollectionReference document = FireStoreUtil.getDb().collection("rgPaymentLinks");
        Query q = document.whereEqualTo("paymentLinkId", paymentId);
        ApiFuture<QuerySnapshot> querySnapshot = q.get();
        final QuerySnapshot queryDocumentSnapshots = querySnapshot.get();
        if (queryDocumentSnapshots.size() > 0) {
            paymentLink = queryDocumentSnapshots.iterator().next().toObject(PaymentLink.class);
        } else {
            throw new RuntimeException("Cannnot find payment link for [" + paymentId + "]");
        }
        return paymentLink;
    }


}
