package com.rentgain.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Introspected
@Serdeable
@Getter
@Setter
public class TenantDetail {
    public int id;
    public String name = "Waiting";
    public String property = "";
    public String email = "";
    public String phone = "";
    public String leaseStart = "";
    public String rent = "";
    public String leaseEnd = "";
    public String kyc = "";
    public int status = 1;
public Boolean panVerified = false;
    public TenantDetail(Tenant t) {
        phone = t.getLlp_tenantnumber();
        property = t.getLlr_existing_property();
    }

}
