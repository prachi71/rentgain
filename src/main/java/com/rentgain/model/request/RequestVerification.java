package com.rentgain.model.request;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Introspected
@Serdeable
public class RequestVerification implements OnDemandRequest {
    @NotNull(message = "Tenant Full Name is required and cannot be null")
    @NotEmpty(message = "Tenant Full Name is required and cannot be empty")
    @NotBlank(message = "Tenant Full Name is required and cannot be blank")
    private String verify_tenant_fullname;
    @NotEmpty(message = "Tenant mobile number is required.")
    private String verify_tenant_mobile;
    @NotEmpty(message = "Tenant Email is required.")
    @Email(message = "Tenant Email should be valid")
    private String verify_tenant_email;
    @NotEmpty(message = "Tenant PAN is required.")
    private String verify_tenant_pan;
}
