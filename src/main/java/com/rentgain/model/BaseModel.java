package com.rentgain.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.util.Map;

@Introspected
@Serdeable
public class BaseModel {
   public boolean status = true;

}
