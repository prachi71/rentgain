package com.rentgain.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Introspected
@Serdeable
@AllArgsConstructor
@Getter
public class Results {
  private List<SelectResults> results;
}
