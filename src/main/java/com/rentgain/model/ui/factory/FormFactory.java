package com.rentgain.model.ui.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentgain.model.ui.FormDiv;
import io.micronaut.context.annotation.Value;
import io.micronaut.core.io.Readable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Singleton
public class FormFactory {

    @Value("classpath:ui/saveProperty.json")
    private Readable savePropertyReadable;

    @Value("classpath:ui/requestVerification.json")
    private Readable requestVerification;

    @Value("classpath:ui/requestRent.json")
    private Readable requestRent;

    @Value("classpath:ui/saveTenant.json")
    private Readable saveTenant;

    @Value("classpath:ui/saveTenantProfile.json")
    private Readable saveTenantProfile;

    @Value("classpath:ui/requestReminder.json")
    private Readable requestReminder;

    @Value("classpath:ui/requestReceipt.json")
    private Readable requestReceipt;

    @Value("classpath:ui/requestDeposit.json")
    private Readable requestDeposit;

    @Value("classpath:ui/lease.json")
    private Readable lease;

    @Inject
    private ObjectMapper objectMapper;

    public FormDiv getPropertyFormDiv() throws IOException {
        return objectMapper.readValue(savePropertyReadable.asInputStream(), FormDiv.class);
    }

    public FormDiv getVerificationFormDiv() throws IOException {
        return objectMapper.readValue(requestVerification.asInputStream(), FormDiv.class);
    }

    public FormDiv getRequestRentView() throws IOException {
        return objectMapper.readValue(requestRent.asInputStream(), FormDiv.class);
    }

    public FormDiv getSaveTenant() throws IOException {
        return objectMapper.readValue(saveTenant.asInputStream(), FormDiv.class);
    }

    public FormDiv getSaveTenantProfile() throws IOException {
        return objectMapper.readValue(saveTenantProfile.asInputStream(), FormDiv.class);
    }

    public FormDiv getRequestReminder() throws IOException {
        return objectMapper.readValue(requestReminder.asInputStream(), FormDiv.class);
    }

    public FormDiv getRequestReceipt() throws IOException {
        return objectMapper.readValue(requestReceipt.asInputStream(), FormDiv.class);
    }

    public FormDiv getRequestDeposit() throws IOException {
        return objectMapper.readValue(requestDeposit.asInputStream(), FormDiv.class);
    }

    public FormDiv getLease() throws IOException {
        return objectMapper.readValue(lease.asInputStream(), FormDiv.class);
    }
}
