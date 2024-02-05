package com.rentgain.cb.decentro.callouts.service;

import com.rentgain.cb.decentro.callouts.serviceint.DecentroBankAccountToVirtualAccount;
import com.rentgain.cb.decentro.callouts.serviceint.DecentroKYCVerification;
import com.rentgain.cb.decentro.model.KycRequest;
import com.rentgain.cb.decentro.model.KycResponse;
import com.rentgain.cb.decentro.model.SettlementAccountMappingRequest;
import com.rentgain.cb.decentro.model.SettlementAccountMappingResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import jakarta.inject.Inject;

import javax.validation.Valid;
import java.util.Optional;

public class DecentroKycVerificationService {
    @Inject
    DecentroKYCVerification decentroKYCVerification;

    public KycResponse validatePANDetailed(@Body @Valid KycRequest kycRequest) {
        try {
            return decentroKYCVerification.validatePANDetailed(kycRequest);
        } catch (HttpClientResponseException e) {
            Optional<KycResponse> error = e.getResponse().getBody(KycResponse.class);
            return error.get();
        }

    }
}
