package com.rentgain.cb.decentro.callouts.serviceint;

import com.rentgain.cb.decentro.model.BankAccountValidationRequest;
import com.rentgain.cb.decentro.model.BankAccountValidationResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;

@Client(
        value = "${decentro.bankaccountvalidation.url}",
        path = "${decentro.bankaccountvalidation.path}",
        errorType = BankAccountValidationResponse.class)
public interface DecentroBankAccountValidationInt {
    @Post(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public BankAccountValidationResponse validateBankAccount(@Body @Valid BankAccountValidationRequest ba);
}
