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
public class UpiPaymentLinkResponse {

    private String decentroTxnId;
    private String status;
    private String responseCode;
    private String message;
    private Data data;
    private String responseKey;

    @Getter
    @Setter
    @Introspected
    @Serdeable
    @ToString
    public static class Data {
        private String encodedDynamicQrCode;
        private String upiUri;
        private PspUri pspUri;
        private String generatedLink;
        private String transactionId;
        private String transactionStatus;
    }

    @Getter
    @Setter
    @Introspected
    @Serdeable
    @ToString
    public static class PspUri {
        private String commonUri;
        private String gpayUri;
        private String phonepeUri;
        private String paytmUri;
    }




}
