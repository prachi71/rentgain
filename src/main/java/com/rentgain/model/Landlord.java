package com.rentgain.model;

import com.rentgain.cb.decentro.model.PanVerification;
import io.micronaut.serde.annotation.Serdeable;

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
public class Landlord extends BaseModel {


    private String ll_pan;
    private String ll_mobile;
    private BankAccount bankAccount;
    private Profile profile;
    private List<Property> properties = new ArrayList<Property>();
    private String ll_cust_id;


}
