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
public class Beneficiary {
        private String action;
        private String sender_account_number;
        private String account_number;
        private String ifsc;
        private String name;
        private String transfer_type;
        private String sender_mobile;

}
