package com.rentgain.cb.wa.callbacks.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Introspected
@Serdeable
@ToString
public class WaGsInboundMessage {
    private Interactive interactive;
    private Context context;
    private String replyId;
    private String name;
    private String messageId;
    private String type;
    private String timestamp;
    private String waNumber;
    private String mobile;
    private String text;
}