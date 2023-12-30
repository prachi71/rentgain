package com.rentgain.cb.wa.callbacks.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Introspected
@Serdeable
@Builder
public class InteractiveMessage {

    private String send_to;
    private String msg_type;
    private String userid;
    private String auth_scheme;
    private String password;
    private String method;
    private String v;
    private String format;
    private String msg;


    private String interactive_type;
    private String action;
    private String footer;
    private String header;

    public static InteractiveMessage buildDefault(String listInteractive, String mobile) {


        return InteractiveMessage.builder().interactive_type("list")
                .msg("Welcome to RentGain please select from our menu options.")
                .footer("for more details please visit https://www.rentgain.com")
                .header("RentGain-Renting Made Easy")
                .format("json")
                .msg_type("TEXT")
                .userid("2000219134")
                .auth_scheme("plain")
                .password("hIGQegwJF")
                .method("SendMessage")
                .v("1.1")
                .action(listInteractive)
                .send_to(mobile)
                .build();
    }
}
