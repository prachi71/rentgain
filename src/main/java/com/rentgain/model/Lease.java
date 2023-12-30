package com.rentgain.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@Serdeable
public class Lease extends BaseModel {
    private String llr_prepay;
    private String llr_postpay;
    private String llr_amount;
    private String llr_rentdueby;
    private String llr_leaseenddate;
}
