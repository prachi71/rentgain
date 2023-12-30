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
public class TransactionStatusResponse {
        private String status;
        private String originalTxnId;
        private String originalDecentroTxnId;
        private String decentroTxnId;
        private String bankReferenceNumber;
        private String transferType;
        private String beneficiaryName;
}
