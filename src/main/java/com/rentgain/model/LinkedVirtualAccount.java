package com.rentgain.model;

import java.util.ArrayList;
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
public class LinkedVirtualAccount {


    private ArrayList<AccountDetails> accounts;
    private String decentroTxnId;
    private String status;
    private String responseCode;

    @Getter
    @Setter
    @Introspected
    @Serdeable
    @ToString
    public static class AccountDetails {
        private String type;
        private String accountNumber;
        private String ifscCode;
        private ArrayList<String> allowedMethods;
        private String currency;
        private int transactionLimit;
        private int minimumBalance;
        private String upiId;
        private String upiOnboardingStatus;
        private UpiOnboardingDetails upiOnboardingDetails;
    }
    @Getter
    @Setter
    @Introspected
    @Serdeable
    @ToString
    public static class UpiOnboardingDetails {
        private int transactionCountLimitPerDay;
        private int transactionAmountLimitPerDay;
        private int transactionAmountLimitPerTransaction;
    }
}
