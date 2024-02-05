package com.rentgain.scheduled;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.rentgain.annotations.TO_IMPLEMENT;
import com.rentgain.cb.decentro.callouts.serviceint.DecentroUpiLinkGeneratorInt;
import com.rentgain.cb.decentro.model.UpiPaymentLinkRequest;
import com.rentgain.model.Tenant;
import com.rentgain.service.FireStoreUtil;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Singleton
public class RentInvoicer {

    @Inject
    DecentroUpiLinkGeneratorInt decentroUpiLinkGeneratorInt;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new RentInvoicer().scheduleOnDemandRentRequest();
    }

    //@Scheduled(fixedDelay = "60s")
    public void scheduleOnDemandRentRequest() throws ExecutionException, InterruptedException {

        /*
          1) Get the tenants
          2) calculate rent month
            1) IF regular scheduler
                2) if pre pay , current month is the rent month
                3) if its post pay, current month - 1 is the rent month
          3) Check if we have an outstanding invoice, if so then do nothing
          4) No outstanding invoice for the rent month, create invoice
         */
//
//        String ll_mobile = args[0];
//        final CollectionGroup next = FireStoreUtil.db.collectionGroup("tenants");
//        List docs = next.get().get().getDocuments();
//        System.out.println(docs);

        //onDemand();
        Query query = FireStoreUtil.getDb().collectionGroup("tenants").whereEqualTo("status", true);
        ApiFuture<QuerySnapshot> results = query.get();
        List<QueryDocumentSnapshot> tenants = results.get().getDocuments();
        for (QueryDocumentSnapshot tennant: tenants)       {
            Tenant t = tennant.toObject(Tenant.class);
            LocalDate currentdate = LocalDate.now();
            Month month = currentdate.getMonth();
            //validate that the month and year are withing a lease period
            Query invoiceQuery = FireStoreUtil.getDb().collection("rent-invoices").whereEqualTo("status","unpaid").whereEqualTo("tenant-id", t.getLlp_tenantid());
            ApiFuture<QuerySnapshot> invoiceQuerySnap = query.get();
            List<QueryDocumentSnapshot> invoices = invoiceQuerySnap.get().getDocuments();
            if(invoices.isEmpty()) {
                //decentroUpiLinkGeneratorInt.generateUpiLink()
                //save the request and response
                //send wa mesassage with the payment link
            }
        }

    }

    private void onDemand() throws InterruptedException, ExecutionException {
        Query query = FireStoreUtil.getDb().collectionGroup("requests").whereEqualTo("request_type", "rent")
                .whereEqualTo("request_status", "0");
        ApiFuture<QuerySnapshot> results = query.get();
        List<QueryDocumentSnapshot> rentRequests = results.get().getDocuments();
        for (QueryDocumentSnapshot qds : rentRequests) {
            String id =  qds.getId();
            String rentMonth = (String) qds.get(FieldPath.of("request_rent_month"));
            String rentYear = (String) qds.get(FieldPath.of("request_rent_year"));

            String tenantMobile = (String) qds.get(FieldPath.of("request_rent_tenant"));
            String llMobile = (String) qds.get(FieldPath.of(("request_rent_ll")));
            final DocumentReference landlordDoc = FireStoreUtil.getDb().collection("rgLandlords_" + llMobile).document(llMobile);
            final Query tenantByMobileQuery = landlordDoc
                    .collection("tenants").whereEqualTo("llp_tenantnumber", tenantMobile);
            List<QueryDocumentSnapshot> data = tenantByMobileQuery.get().get().getDocuments();
            if (data == null || data.isEmpty()) {
                System.err.println(String.format("Cannot schedule rent payment as no data found for tenant [%s] and landlord [%s]", tenantMobile, llMobile));
            }
            if (data.size() > 1) {
                System.err.println(String.format("Cannot schedule rent payment as more than tenants found for tenant [%s] and landlord [%s]", tenantMobile, llMobile));
                //return;
            }
            Tenant tenant= data.get(0).toObject(Tenant.class);



            UpiPaymentLinkRequest upiPaymentLinkRequest = new UpiPaymentLinkRequest();
            upiPaymentLinkRequest.setReference_id(id);
            upiPaymentLinkRequest.setAmount(Double.parseDouble(tenant.getLlr_amount()));
            upiPaymentLinkRequest.setPurpose_message(String.format("Rent for %s month %s year for %s", rentMonth, rentYear, tenant.getLlr_existing_property()));
            upiPaymentLinkRequest.setGenerate_qr(0);
            upiPaymentLinkRequest.setGenerate_uri(1);

            final String bankAccount = (String) landlordDoc.get().get().get(FieldPath.of("ll_bacctn"));

          validateBankAccount(bankAccount);

          upiPaymentLinkRequest.setPayee_account(bankAccount);
        }
    }


    @TO_IMPLEMENT
    private boolean validateBankAccount(String accountNumber) {
        return true;
    }
}
