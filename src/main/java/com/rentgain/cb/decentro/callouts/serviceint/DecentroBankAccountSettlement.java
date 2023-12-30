package com.rentgain.cb.decentro.callouts.serviceint;

import com.rentgain.cb.decentro.callouts.error.InitiatePaymentErrorWrapper;
import com.rentgain.cb.decentro.model.*;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;

@Client(
        value = "${decentro.settlement.url}",
        path = "${decentro.settlement.path}",
        errorType = InitiatePaymentErrorWrapper.class)
public interface DecentroBankAccountSettlement {
    @Post(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public PayoutResponse initiatePayout(@Body @Valid PayoutRequest pr);
}
