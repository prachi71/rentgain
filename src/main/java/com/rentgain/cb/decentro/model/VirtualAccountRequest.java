package com.rentgain.cb.decentro.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Introspected
@Serdeable
@ToString
public class VirtualAccountRequest {
    private List<String> bank_codes;
    private String name;
    private String pan;
    private String email;
    private String mobile;
    private String address;
    private int kyc_verified;
    private int kyc_check_decentro;
    private double minimum_balance;
    private double transaction_limit;
    private String customer_id;
    private String virtual_account_balance_settlement;
    private MasterAccount master_account;
    private String master_account_alias;
    private boolean upi_onboarding;
    private int state_code;
    private String city;
    private int pincode;
    private String custom_vpa;
    private UpiOnboardingDetails upi_onboarding_details;


    @Getter
    @Setter
    @Introspected
    @Serdeable
    @ToString
    public static class MasterAccount {
        private String number;
        private String ifsc;
    }


    @Getter
    @Setter
    @Introspected
    @Serdeable
    @ToString
    public static class UpiOnboardingDetails {
        private String merchant_category_code;
        private String merchant_business_type;
        private int transaction_count_limit_per_day;
        private int transaction_amount_limit_per_day;
        private int transaction_amount_limit_per_transaction;
    }
}

