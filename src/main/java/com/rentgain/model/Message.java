package com.rentgain.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Serdeable
public class Message {
    private String name;
    private String status;
    private String statusTxt;
    private String onSuccess;
    private String onError;
    private Boolean toast = false;
    private String confirmButtonText;
    private String footerMsg1;
    private String footerMsg2;
    private String footerMsg3;
    private String link;
}
