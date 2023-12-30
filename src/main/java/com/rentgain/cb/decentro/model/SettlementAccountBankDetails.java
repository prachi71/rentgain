package com.rentgain.cb.decentro.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@Serdeable
public class SettlementAccountBankDetails {
        public String ifsc;
        public String accountNumber;
        public String beneficiaryCode;
        public String beneficiaryName;
}
