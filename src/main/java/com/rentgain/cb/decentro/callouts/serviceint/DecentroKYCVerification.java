package com.rentgain.cb.decentro.callouts.serviceint;

import com.rentgain.cb.decentro.model.KycRequest;
import com.rentgain.cb.decentro.model.KycResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;

@Client(
        value = "${decentro.kyc.url}",
        path = "${decentro.kyc.path}",
        errorType = KycResponse.class)
public interface DecentroKYCVerification {
    @Post(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public KycResponse validatePANDetailed(@Body @Valid KycRequest kycRequest);
}
