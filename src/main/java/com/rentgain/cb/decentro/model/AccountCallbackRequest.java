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
public class AccountCallbackRequest {
        private int attempt;
        private String timestamp;
        private String callbackTxnId;
        private String originalCallbackTxnId;
        private String accountNumber;
        private int balance;
        private String transactionMessage;
        private String type;
        private String transferType;
        private int transactionAmount;
        private String decentroTxnId;
        private String payerAccountNumber;
        private String payerAccountIfsc;
        private String payerVpa;
}
