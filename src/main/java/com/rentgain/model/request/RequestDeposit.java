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
public class RequestDeposit implements OnDemandRequest {
    @NotEmpty(message = "Tenant name is required to request deposit.")
    private  String deposit_tenant_fullname;
    @NotEmpty(message = "Tenant mobile number is required to request deposit.")
    private  String deposit_tenant_mobile;
    @NotEmpty(message = "Deposit type (Full or Token) is required to request deposit.")
    private  String deposit_type;
    @NotEmpty(message = "Deposit amount is required to request deposit.")
    private  String deposit_amount;
}
