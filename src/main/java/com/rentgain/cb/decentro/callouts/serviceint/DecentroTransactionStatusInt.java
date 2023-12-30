package com.rentgain.cb.decentro.callouts.serviceint;

import com.rentgain.cb.decentro.model.TransactionStatusResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import jakarta.annotation.Nonnull;

@Client(
        value = "${decentro.transaction.status.url}",
        path = "${decentro.transaction.status.path}",
        errorType = TransactionStatusResponse.class)
public interface DecentroTransactionStatusInt {
    @Get(produces = MediaType.APPLICATION_JSON)
    public TransactionStatusResponse getTransactionStatus(@Nonnull @QueryValue(value = "reference_id") String reference_id);

}

