package com.rentgain.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Context;

@ConfigurationProperties("messages")
@Context
public class Properties {
    public Readable getProperties() {
        return properties;
    }

    public void setProperties(Readable properties) {
        this.properties = properties;
    }

    private Readable properties;
}
