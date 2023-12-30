package com.rentgain.scheduled;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@Serdeable
public class HsmRentReq {
    private String phoneNbr;
    private String name;
    private String rentMonth;
    private String rentYear;
    private String dueDay;
    private String dueMonth;
    private String dueYear;
    private String upiLink;
}
