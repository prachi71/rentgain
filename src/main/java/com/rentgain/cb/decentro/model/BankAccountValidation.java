package com.rentgain.cb.decentro.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

@Getter
@Setter
@Introspected
@Serdeable
@ToString

public class BankAccountValidation {
    public String name;
    public String mobile;
    public String status;
    public STATE_NOTIFICATION notification;
    public BankAccountValidationRequest bankAccountValidationRequest ;
    public BankAccountValidationResponse bankAccountValidationResponse ;
    public long createdTime;

}
