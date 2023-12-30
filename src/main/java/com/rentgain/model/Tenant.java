package com.rentgain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS;

@Serdeable
@Introspected
@Getter
@Setter
@JsonInclude(ALWAYS)
@ToString
public class Tenant extends BaseModel {

    private String llp_tenantid;
    private String llp_tenantnumber;
    private String llp_tenantaddress;
    private String llp_tenantpan;

    private String llp_tenantfullname;
    private String llp_tenantemail;
    private String llp_tenantunit;

    private String panVerified;
    private String llr_existing_property;
    private String name;
    private String email;
    private String llr_prepost;
    private String llr_amount;
    private String llr_rentdueby;
    private String llr_leaseenddate;
    private String llr_leasestartdate;
    private String llr_postpay;
    private String llr_prepay;

    private Lease lease;


}
