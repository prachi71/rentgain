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
public class BankAccountValidationResponse {
        private String status;
        private String message;
        private String accountStatus;
        private String decentroTxnId;
        private String beneficiaryName;
        private String beneficiaryCode;
        private String bankReferenceNumber;
        private String responseCode;
        private BankAccountValidationError error;
}
