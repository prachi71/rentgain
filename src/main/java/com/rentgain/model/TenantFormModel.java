package com.rentgain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantFormModel extends BaseModel {
    private String llp_tenantnumber;
    private String llp_existing_property;
}
