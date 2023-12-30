package com.rentgain.model.ui;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Href {

    public class Attr {
        @JsonProperty("class")
        public String attrClass;
        public String href;
        public String target;
    }

    public String text;
    public String href;
    public Attr attr;
}