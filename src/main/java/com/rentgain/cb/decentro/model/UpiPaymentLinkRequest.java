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
public class UpiPaymentLinkRequest {
    private String reference_id;
    private String payee_account;
    private Double amount;
    private String purpose_message;
    private int generate_qr;
    private int generate_uri;
    private int expiry_time;
    private int customized_qr_with_logo;
}
