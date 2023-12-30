package com.rentgain.model.ui;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Link{
    private String href;
    private String rel;
    private String type;
    private String crossOrigin;
    @JsonProperty("class")
    private String linkClass;
    private String linkClassName="class";
}
