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
public class RequestReceipt implements OnDemandRequest {
    @NotEmpty(message = "Tenant is required.")
    private String receipt_tenant;
    @NotEmpty(message = "Month is required.")
    private String receipt_rent_month;
    @NotEmpty(message = "Year is required.")
    private String receipt_rent_year;
    @NotEmpty(message = "Deliver method is required to send receipt.")
    private String receipt_deliver_method;
}
