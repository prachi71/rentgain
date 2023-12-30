package com.rentgain.model.request;

import com.google.cloud.firestore.DocumentReference;
import com.rentgain.cb.decentro.model.UpiPaymentLinkResponse;
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
public class RequestRent implements OnDemandRequest {
    @NotEmpty(message = "Tenant is required.")
    private String request_rent_tenant;
    @NotEmpty(message = "Month is required.")
    private String request_rent_month;
    @NotEmpty(message = "Year is required.")
    private String request_rent_year;


    private String request_rent_ll;
    private Integer reminderCount;
    private UpiPaymentLinkResponse paymentLink;
}
