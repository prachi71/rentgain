package com.rentgain.cb.wa.callbacks.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Introspected
@Serdeable
public class SendMessage {
    private String send_to;
    private String msg_type = "text";
    private String userid = "2000219134";
    private String auth_scheme = "plain";
    private String password = "hIGQegwJF";
    private String method = "SendMessage";
    private String v = "1.1";
    private String format = "json";
    private String msg;
}
