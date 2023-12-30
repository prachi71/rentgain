package com.rentgain.model.ui;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Introspected
@Getter
@Setter
@Serdeable
public class FormDiv {
    public List<String> titles = new ArrayList<>();
    public String formId;
    public List<InputDiv> inputs = new ArrayList<>();

}
