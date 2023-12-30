package com.rentgain.cb.wa.callbacks.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@Serdeable
public class TextMessage {
    private String msg;
    private String header = "RentGain : Renting Made Easy";
    private String footer = "https://rentgain.com";
    private String buttonUrlParam;
}
