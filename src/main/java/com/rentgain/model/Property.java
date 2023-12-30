package com.rentgain.model;

import com.rentgain.cb.decentro.model.SettlementAccountMappingResponse;
import com.rentgain.cb.decentro.model.VirtualAccountResponse;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@Serdeable
@ToString
public class Property extends BaseModel {
    private String llp_nickname;
    private String llp_address;
    private String llp_unit;
    private String llr_prepost;
    private String llr_amount;
    private String llr_rentdueby;
    private String llr_leasestartdate;
    private String llr_leaseenddate;
    private VirtualAccountResponse llp_virtualAccountNumber;
    private SettlementAccountMappingResponse settlementAccountMappingResponse;

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(List<Tenant> tenants) {
        this.tenants = tenants;
    }

    public List<Tenant> tenants = new ArrayList<Tenant>();


}
