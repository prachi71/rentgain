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
public class SettlementAccountMappingRequest{
    private String reference_id;
    private String action;
    private String name;
    private String ifsc;
    private String sender_account_number;
    private String account_number;
}
