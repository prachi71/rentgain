package com.rentgain.cb.decentro.callouts.service;

import com.rentgain.cb.decentro.callouts.serviceint.DecentroUpiLinkGeneratorInt;
import com.rentgain.cb.decentro.model.UpiPaymentLinkRequest;
import com.rentgain.cb.decentro.model.UpiPaymentLinkResponse;
import jakarta.inject.Inject;


public class DecentroUpiLinkGeneratorService {
    @Inject
    DecentroUpiLinkGeneratorInt decentroUpiLinkGeneratorInt;

    public UpiPaymentLinkResponse generateUpiLink(UpiPaymentLinkRequest upiPaymentLinkRequest) {
       UpiPaymentLinkResponse upiPaymentLinkResponse = decentroUpiLinkGeneratorInt.generateUpiLink(upiPaymentLinkRequest);
        System.out.println(upiPaymentLinkResponse);
       return upiPaymentLinkResponse;
    }
}
