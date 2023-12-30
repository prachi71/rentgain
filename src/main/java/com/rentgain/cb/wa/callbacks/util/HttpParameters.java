package com.rentgain.cb.wa.callbacks.util;

import java.util.Map;
import java.util.StringJoiner;

public class HttpParameters {
    public static String getParameters(Map<String, String> parameters) {
        StringJoiner sjAmpersand = new StringJoiner("&");
        parameters.forEach((k, v) -> {
            StringJoiner sjEquals = new StringJoiner("=");
            sjEquals.add(k);
            sjEquals.add(v);
            sjAmpersand.add(sjEquals.toString());
        });
        return sjAmpersand.toString();
    }
}
