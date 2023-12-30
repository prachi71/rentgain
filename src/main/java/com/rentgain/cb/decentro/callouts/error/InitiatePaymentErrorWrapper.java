package com.rentgain.cb.decentro.callouts.error;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Introspected
@Serdeable
@ToString
public class InitiatePaymentErrorWrapper {

    private InitiatePaymentError error;
    private String decentroTxnId;


    @Getter
    @Setter
    @Introspected
    @Serdeable
    @ToString
    public static class InitiatePaymentError {
        private String message;
        private String responseCode;
        private String status;
    }
}