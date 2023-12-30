package com.rentgain.cb.decentro.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Introspected
@Serdeable
@ToString
public class VirtualAccountResponse {
    private String decentroTxnId;
    private String status;
    private String responseCode;
    private String message;
    private ArrayList<VirtualAccountResponseData> data;


    @Getter
    @Setter
    @Introspected
    @Serdeable
    @ToString
    public static class VirtualAccountResponseData {
        private String bank;
        private String status;
        private String message;
        private String accountNumber;
        private String ifsc;
        private List<String> allowedMethods;
        private String currency;
        private double transactionLimit;
        private double minimumBalance;
        private String upiId;
        private String upiQrCode;
        private String upiOnboardingStatus;
        private String upiOnboardingStatusDescription;
        private String virtualAccountBalanceSettlement;
    }
}


