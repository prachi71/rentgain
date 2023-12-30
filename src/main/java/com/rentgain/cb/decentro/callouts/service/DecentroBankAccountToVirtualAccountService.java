package com.rentgain.cb.decentro.callouts.service;

import com.rentgain.cb.decentro.callouts.serviceint.DecentroBankAccountToVirtualAccount;
import com.rentgain.cb.decentro.model.BankAccountValidationResponse;
import com.rentgain.cb.decentro.model.SettlementAccountMappingRequest;
import com.rentgain.cb.decentro.model.SettlementAccountMappingResponse;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import jakarta.inject.Inject;

import java.util.Optional;

public class DecentroBankAccountToVirtualAccountService  {
    @Inject
    DecentroBankAccountToVirtualAccount decentroBankAccountToVirtualAccount;

    public SettlementAccountMappingResponse mapVirtualAccountToBankAccount(SettlementAccountMappingRequest settlementAccountMappingRequest) {
        try {
            return decentroBankAccountToVirtualAccount.mapVirtualAccountToBankAccount(settlementAccountMappingRequest);
        }catch(HttpClientResponseException e) {
            Optional<SettlementAccountMappingResponse> error = e.getResponse().getBody(SettlementAccountMappingResponse.class);
            return error.get();
        }

    }
}
