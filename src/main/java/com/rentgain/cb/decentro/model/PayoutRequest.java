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
public class PayoutRequest {


    private String reference_id;
    private String purpose_message;
    private String from_customer_id;
    private String to_customer_id;
    private String from_account;
    private String to_account;
    private String mobile_number;
    private String email_address;
    private String name;
    private String transfer_type;
    private String transfer_amount;
    private BeneficiaryDetails beneficiary_details;
    private String currency_code;

    @Getter
    @Setter
    @Introspected
    @Serdeable
    @ToString
    public static class BeneficiaryDetails {
        private String email_address;
        private String mobile_number;
        private String address;
        private String ifsc_code;
        private String country_code;
        private String payee_name;
    }

}
