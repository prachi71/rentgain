package com.rentgain.cb.decentro.model;

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
public class KycResponse{
    private String kycStatus;
    private String status;
    private String message;
    private KycResult kycResult;
    private KycError kycError;
    private String responseKey;
    private String responseCode;
    private String requestTimestamp;
    private String responseTimestamp;
    private String decentroTxnId;
}
