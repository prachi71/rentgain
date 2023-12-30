package com.rentgain.cb.decentro.callouts.serviceint;

import com.rentgain.cb.decentro.model.UpiPaymentLinkRequest;
import com.rentgain.cb.decentro.model.UpiPaymentLinkResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;

@Client(
        value = "${decentro.upigen.url}",
        path = "${decentro.upigen.path}",
        errorType = UpiPaymentLinkResponse.class)
public interface DecentroUpiLinkGeneratorInt {
    @Post(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public UpiPaymentLinkResponse generateUpiLink(@Body @Valid UpiPaymentLinkRequest va);
}
