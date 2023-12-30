package com.rentgain.model.ui;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InputDiv {
    private String col;
    private String invalidFeedBack;
    private String labelId;
    private String label;
    private String inputType;
    private String inputId;
    private String placeHolder;
    private List<String> options = new ArrayList<>();
}
