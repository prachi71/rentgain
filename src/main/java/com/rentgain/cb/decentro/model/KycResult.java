package com.rentgain.cb.decentro.model;
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
public class KycResult{
    private String idNumber;
    private String idStatus;
    private String panStatus;
    private String lastName;
    private String firstName;
    private String fullName;
    private String idHolderTitle;
    private String idLastUpdated;
    private String aadhaarSeedingStatus;
}


