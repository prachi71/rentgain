package com.rentgain.cb.wa.callbacks.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum MessageType {
    LIST_TYPE("list_reply"),
    TEXT("text"),
    INTERACTIVE("interactive");

    private static final Map<String, MessageType> ENUM_MAP;


    static {
        Map<String, MessageType> map = new HashMap<String, MessageType>();
        for (MessageType instance : MessageType.values()) {
            map.put(instance.getType().toLowerCase(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static MessageType get(String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }

    public String getType() {
        return type;
    }

    private String type;

    MessageType(String type) {
        this.type = type;
    }
}
