package com.rentgain.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@Serdeable
public class LandlordVerification {
    private String ll_mobile, kyc_verified, bankaccount_verified;
}
