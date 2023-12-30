package com.rentgain.service;

import com.rentgain.configuration.MessagesConfiguration;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class MessageLookup {
    private final Map<String, MessagesConfiguration> messagesConfigurationMap = new ConcurrentHashMap<>();

    @Inject
    public MessageLookup(List<MessagesConfiguration> fileTypes) {
        fileTypes.forEach(m -> messagesConfigurationMap.put(m.getName(), m));
    }

    public MessagesConfiguration getByName(String name) {
        return messagesConfigurationMap.get(name);
    }
}
