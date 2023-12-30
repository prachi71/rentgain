package com.rentgain.cb.decentro.callouts.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@Serdeable
@ConfigurationProperties("decentro.credentials")
public class DecentroConfig {
    private String client_id;
    private String client_secret;
    private String accounts_module_secret;
}
