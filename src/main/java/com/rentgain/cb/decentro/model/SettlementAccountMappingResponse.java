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
public class SettlementAccountMappingResponse {
    private String decentroTxnId;
    private String status;
    private String responseCode;
    private String message;
    private SettlementAccountBankDetails data;
}
