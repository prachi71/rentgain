package com.rentgain.cb.decentro.callouts.serviceint;

import com.rentgain.cb.decentro.model.VirtualAccountRequest;
import com.rentgain.cb.decentro.model.VirtualAccountResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;

@Client(
        value = "${decentro.virtualaccount.url}",
        path = "${decentro.virtualaccount.path}",
        errorType = VirtualAccountResponse.class)
public interface DecentroVirtualAccountInt {
    @Post(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public VirtualAccountResponse createVirtualAccount(@Body @Valid VirtualAccountRequest va);
}
