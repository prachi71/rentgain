package com.rentgain.configuration;

import io.micronaut.context.annotation.EachProperty;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable
@EachProperty("messages")
@Getter
@Setter
public class MessagesConfiguration {
    private String success;
    private String error;
    private String name;
    private String statusText;
    private String confirmButtonText;
    private String footerMsg1;
    private String footerMsg2;
    private String footerMsg3;
    private String link;
    public MessagesConfiguration(@Parameter String name) {
        this.name = name;
    }
}
