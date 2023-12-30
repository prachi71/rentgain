package com.rentgain.cb.decentro.model;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@Serdeable
public class SettlementAccount {
    private String reference_id;
    private String action;
    private String name;
    private String ifsc;
    // VA nbr
    private String sender_account_number;
    // BA nbr
    private String account_number;
}
