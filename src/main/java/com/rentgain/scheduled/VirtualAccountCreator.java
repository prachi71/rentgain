package com.rentgain.scheduled;

import com.google.cloud.firestore.*;
import com.rentgain.cb.decentro.callouts.service.DecentroBankAccountToVirtualAccountService;
import com.rentgain.cb.decentro.callouts.serviceint.DecentroVirtualAccountInt;
import com.rentgain.cb.decentro.model.SettlementAccountMappingRequest;
import com.rentgain.cb.decentro.model.SettlementAccountMappingResponse;
import com.rentgain.cb.decentro.model.VirtualAccountRequest;
import com.rentgain.cb.decentro.model.VirtualAccountResponse;
import com.rentgain.cb.decentro.model.factory.VirtualAccountFactory;
import com.rentgain.model.Landlord;
import com.rentgain.model.Property;
import com.rentgain.model.ValidationState;
import com.rentgain.service.CrudService;
import com.rentgain.service.FireStoreUtil;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Singleton
public class VirtualAccountCreator {

    @Inject
    DecentroVirtualAccountInt decentroVirtualAccountInt;

    @Inject
    DecentroBankAccountToVirtualAccountService decentroBankAccountToVirtualAccountService;
    public void createVirtualBankAccount() throws ExecutionException, InterruptedException {
        Query query = FireStoreUtil.getDb().collectionGroup("properties").whereEqualTo("llp_virtualAccountNumber", null);
        List<QueryDocumentSnapshot> data = query.get().get().getDocuments();
        System.out.println("createVirtualBankAccount " + data.size());
        for (QueryDocumentSnapshot qds : data) {
            String propertyId = qds.getId();
            Property property = qds.toObject(Property.class);
            DocumentReference o = qds.getReference().getParent().getParent();
            Landlord landlord = o.get().get().toObject(Landlord.class);
            String customer_id = landlord.getLl_cust_id();
            System.out.println("createVirtualBankAccount creating VA for " + property);

            VirtualAccountRequest virtualAccountRequest = VirtualAccountFactory.getVirtualAccountRequest(landlord, customer_id);
            VirtualAccountResponse virtualAccountResponse = decentroVirtualAccountInt.createVirtualAccount(virtualAccountRequest);

            SettlementAccountMappingRequest settlementAccountMappingRequest = new SettlementAccountMappingRequest();
            settlementAccountMappingRequest.setAccount_number(landlord.getBankAccount().getLl_bacctn());
            settlementAccountMappingRequest.setAction("ADD");
            settlementAccountMappingRequest.setReference_id(UUID.randomUUID().toString());
            settlementAccountMappingRequest.setIfsc(landlord.getBankAccount().getLl_ifsc());
            settlementAccountMappingRequest.setName(landlord.getProfile().getLl_fullname());
            settlementAccountMappingRequest.setSender_account_number(virtualAccountResponse.getData().get(0).getAccountNumber());
            SettlementAccountMappingResponse settlementAccountMappingResponse = decentroBankAccountToVirtualAccountService.mapVirtualAccountToBankAccount(settlementAccountMappingRequest);

            System.out.println("createVirtualBankAccount created VA  " + settlementAccountMappingResponse);

            property.setLlp_virtualAccountNumber(virtualAccountResponse);

           if(ValidationState.SUCCESS.equals(landlord.getBankAccount().status)) {
               property.setSettlementAccountMappingResponse(settlementAccountMappingResponse);
           }
            CrudService.updateProperty(propertyId, landlord.getProfile().getLl_mobile(), property);
        }


    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new VirtualAccountCreator().createVirtualBankAccount();
    }
}
