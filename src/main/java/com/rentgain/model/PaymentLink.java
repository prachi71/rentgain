package com.rentgain.model;

import com.rentgain.cb.decentro.model.TransactionCallbackRequest;
import com.rentgain.cb.decentro.model.TransactionStatusResponse;
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
public class PaymentLink {
    private String propertyAddress;
    private String tenantName;

    private String tenantMobile;
    private String tenantPan;
    private String tenantEmail;

    private String landlordName;
    private String landlordMobile;
    private String landlordPan;
    private String landlordEmail;

    private String month;
    private String year;
    private String rent;
    private String dateIssued;
    private String dateDue;
    private String rentFor;


    private String invoiceNumber;
    private String paymentLinkId;
    private TransactionCallbackRequest transactionCallbackRequest;
}
