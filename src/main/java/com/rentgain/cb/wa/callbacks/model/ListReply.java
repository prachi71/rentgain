package com.rentgain.cb.wa.callbacks.model;


import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@Serdeable
public class ListReply {
    private String description;
    private String id;
}
