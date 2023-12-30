package com.rentgain.cb.decentro.model;

import lombok.AllArgsConstructor;
import lombok.ToString;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Getter
@Setter
@Introspected
@Serdeable
@ToString
public class PanVerification {
    private String pan;
    private String originalReqId;
    private KycRequest kycRequest;
    private KycResponse kycResponse;
    private String mobile;
    private String status;
    private STATE_NOTIFICATION nState;
}
