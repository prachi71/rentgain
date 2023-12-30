package com.rentgain.cb.decentro.callouts.serviceint;

import com.rentgain.cb.decentro.callouts.error.InitiatePaymentErrorWrapper;
import com.rentgain.cb.decentro.model.PayoutRequest;
import com.rentgain.cb.decentro.model.PayoutResponse;
import com.rentgain.cb.decentro.model.SettlementAccountMappingRequest;
import com.rentgain.cb.decentro.model.SettlementAccountMappingResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;

@Client(
        value = "${decentro.bankaccount-to-virtualaccount.url}",
        path = "${decentro.bankaccount-to-virtualaccount.path}",
        errorType = SettlementAccountMappingResponse.class)
public interface DecentroBankAccountToVirtualAccount {
    @Post(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public SettlementAccountMappingResponse mapVirtualAccountToBankAccount(@Body @Valid SettlementAccountMappingRequest settlementAccountMappingRequest);
}
