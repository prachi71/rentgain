package com.rentgain.model;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Introspected
@Serdeable
@ToString
public class TenantProfile {
    private String llp_tenantnumber;
    private String llp_tenantaddress;
    private String llp_tenantpan;
    private String llp_tenantfullname;
    private String llp_tenantemail;
    private String llp_tenantunit;
}
