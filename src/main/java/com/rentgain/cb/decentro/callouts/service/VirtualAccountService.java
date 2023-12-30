package com.rentgain.cb.decentro.callouts.service;

import com.rentgain.cb.decentro.callouts.serviceint.DecentroVirtualAccountInt;
import com.rentgain.cb.decentro.model.VirtualAccountRequest;
import com.rentgain.cb.decentro.model.VirtualAccountResponse;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import jakarta.inject.Inject;

import java.util.Optional;

public class VirtualAccountService {
    @Inject
    DecentroVirtualAccountInt virtualAccountInt;

    public VirtualAccountResponse createVirtualAccount(VirtualAccountRequest virtualAccount) {
        try {
            return virtualAccountInt.createVirtualAccount(virtualAccount);

        } catch (HttpClientResponseException e) {
            Optional<VirtualAccountResponse> error = e.getResponse()
                    .getBody(VirtualAccountResponse.class);
            e.printStackTrace();
        }
        return null;
    }

}
