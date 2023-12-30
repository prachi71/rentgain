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
public class VirtualAccountBalanceDetailsResponse {
        private String decentroTxnId;
        private String status;
        private String responseCode;
        private String type;
        private String accountNumber;
        private String presentBalance;
        private String upiId;
        private double transactionAmountLimit;
        private double minimumBalance;
}
