package com.rentgain.cb.decentro.callouts.serviceint;

import com.rentgain.cb.decentro.model.VirtualAccountBalanceDetailsResponse;
import com.rentgain.model.LinkedVirtualAccount;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import jakarta.annotation.Nonnull;

@Client(
        value = "${decentro.linkedaccount.url}",
        path = "${decentro.linkedaccount.path}",
        errorType = VirtualAccountBalanceDetailsResponse.class)
public interface DecentroVirtualAccountDetailsInt {
    @Get(produces = MediaType.APPLICATION_JSON)
    public LinkedVirtualAccount getLinkedVirtualAccount(@Nonnull @QueryValue(value = "mobile") String phone, @Nonnull @QueryValue(value = "type") String type);
}
