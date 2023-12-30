package com.rentgain.cb.wa.callbacks.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Introspected
@Serdeable
public class ListInteractive {
    private String button;
    private ArrayList<Section> sections;




}
