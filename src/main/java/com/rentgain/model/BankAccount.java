package com.rentgain.model;

import io.micronaut.serde.annotation.Serdeable;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jdk.security.jarsigner.JarSigner;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Introspected
@Serdeable

@ToString
public class BankAccount extends BaseModel{
    private String ll_bacctn;
    private String ll_ifsc;
    private String ll_vacctn;
    private ValidationState state;

    public static BankAccount builder() {
        return new BankAccount();
    }

    public BankAccount ll_bacctn(String s) {
        this.setLl_bacctn(s);
        return this;
    }

    public BankAccount ll_ifsc(String abc) {
        this.setLl_ifsc(abc);
        return this;
    }
}
