package com.rentgain.cb.decentro.callouts.serviceint;

import com.rentgain.cb.decentro.model.VirtualAccountBalanceDetailsResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import jakarta.annotation.Nonnull;

@Client(
        value = "${decentro.accountbalance.url}",
        path = "${decentro.accountbalance.path}",
        errorType = VirtualAccountBalanceDetailsResponse.class)
public interface DecentroVirtualAccountBalanceInt {
    @Get(produces = MediaType.APPLICATION_JSON)
    public VirtualAccountBalanceDetailsResponse getVirtualAccountBalance(@Nonnull @QueryValue(value = "mobile_number") String phone,
                                                                         @Nonnull @QueryValue(value = "account_number") String virtualAccount);

}

