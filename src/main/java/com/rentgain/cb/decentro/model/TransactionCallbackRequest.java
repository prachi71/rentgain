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
public class TransactionCallbackRequest {
    private int attempt;
    private String timestamp;
    private String callbackTxnId;
    private String originalCallbackTxnId;
    private String transactionStatus;
    private String referenceId;
    private String decentroTxnId;
    private String transactionMessage;
    private String transferType;
    private String bankReferenceNumber;
    private String beneficiaryName;
    private int transactionAmount;
    private String providerMessage;
    private String errorKey;
    private String npciTxnId;
}
