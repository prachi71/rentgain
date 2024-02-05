package com.rentgain.scheduled;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Filter;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.rentgain.cb.decentro.callouts.service.DecentroBankAccountToVirtualAccountService;
import com.rentgain.cb.decentro.model.SettlementAccountMappingRequest;
import com.rentgain.cb.decentro.model.SettlementAccountMappingResponse;
import com.rentgain.cb.decentro.model.VirtualAccountResponse;
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
public class VirtualAccountToSettlementAccountMapper {


    @Inject
    DecentroBankAccountToVirtualAccountService decentroBankAccountToVirtualAccountService;


    public void mapVirtualBankAccountToSettlement() throws ExecutionException, InterruptedException {
        Query query = FireStoreUtil.getDb().collectionGroup("properties").
                where(
                        Filter.and(
                                Filter.equalTo("settlementAccountMappingResponse", null),
                                Filter.notEqualTo("llp_virtualAccountNumber", null)
                        )
                );

        List<QueryDocumentSnapshot> data = query.get().get().getDocuments();
        for (QueryDocumentSnapshot qds : data) {
            String propertyId = qds.getId();
            Property property = qds.toObject(Property.class);
            DocumentReference o = qds.getReference().getParent().getParent();
            Landlord landlord = o.get().get().toObject(Landlord.class);
            String customer_id = landlord.getLl_cust_id();
            VirtualAccountResponse virtualAccountResponse = property.getLlp_virtualAccountNumber();
            System.out.println("Creating settlement account for  VA  " + virtualAccountResponse);
            property.setLlp_virtualAccountNumber(virtualAccountResponse);
            mapToSettlementAccount(property, landlord, virtualAccountResponse);

            CrudService.updateProperty(propertyId, landlord.getProfile().getLl_mobile(), property);
            System.out.println("Created settlement account for  VA  " + virtualAccountResponse);
        }


    }
    private void mapToSettlementAccount(Property property, Landlord landlord, VirtualAccountResponse virtualAccountResponse) {
        try {
            SettlementAccountMappingRequest settlementAccountMappingRequest = new SettlementAccountMappingRequest();
            settlementAccountMappingRequest.setAccount_number(landlord.getBankAccount().getLl_bacctn());
            settlementAccountMappingRequest.setAction("ADD");
            settlementAccountMappingRequest.setReference_id(UUID.randomUUID().toString());
            settlementAccountMappingRequest.setIfsc(landlord.getBankAccount().getLl_ifsc());
            settlementAccountMappingRequest.setName(landlord.getProfile().getLl_fullname());
            settlementAccountMappingRequest.setSender_account_number(virtualAccountResponse.getData().get(0).getAccountNumber());
            System.out.println("Creating settlement account for  VA  " + settlementAccountMappingRequest);

            SettlementAccountMappingResponse settlementAccountMappingResponse = decentroBankAccountToVirtualAccountService.mapVirtualAccountToBankAccount(settlementAccountMappingRequest);

            System.out.println("Created settlement account for  VA  " + settlementAccountMappingResponse);

            if(ValidationState.SUCCESS.equals(landlord.getBankAccount().getState())) {
                property.setSettlementAccountMappingResponse(settlementAccountMappingResponse);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
