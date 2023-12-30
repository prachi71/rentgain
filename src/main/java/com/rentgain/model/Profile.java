package com.rentgain.model;

import com.rentgain.cb.decentro.model.KycResponse;
import com.rentgain.cb.decentro.model.PanVerification;
import io.micronaut.serde.annotation.Serdeable;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@Serdeable
public class Profile {
    private String ll_mobile;
    private String ll_pan;
    private String ll_fullname;
    private String ll_address;
    private String ll_email;
    private String ll_unit;
    private PanVerification panVerification;
}
