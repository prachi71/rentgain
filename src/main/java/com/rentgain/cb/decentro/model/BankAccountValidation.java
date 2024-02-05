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
    private String name;
    private String mobile;
    private String status;
    private STATE_NOTIFICATION notification;
    private BankAccountValidationRequest bankAccountValidationRequest ;
    private BankAccountValidationResponse bankAccountValidationResponse ;
    private long createdTime;
    private String sid;

}
