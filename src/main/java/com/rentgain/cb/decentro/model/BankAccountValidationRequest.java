package com.rentgain.cb.decentro.model;

import com.rentgain.model.BankAccount;
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
public class BankAccountValidationRequest {
    private String reference_id;
    private String purpose_message;
    private String transfer_amount;
    private BeneficiaryDetails beneficiary_details;
}
