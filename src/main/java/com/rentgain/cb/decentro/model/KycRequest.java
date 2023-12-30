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
public class KycRequest {

        private String reference_id;
        private String document_type;
        private String id_number;
        private String consent;
        private String consent_purpose;
}
