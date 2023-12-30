package com.rentgain.model.request;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@Introspected
@Serdeable
public class RequestReminder implements OnDemandRequest {
    @NotEmpty(message = "Tenant to send reminder is required.")
    private  String reminder_tenant;
    @NotEmpty(message = "Month is required to send reminder.")
    private  String reminder_rent_month;
    @NotEmpty(message = "Year is required to send reminder.")
    private  String reminder_rent_year;
}
