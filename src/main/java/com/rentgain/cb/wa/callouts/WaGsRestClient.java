package com.rentgain.cb.wa.callouts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentgain.cb.wa.callbacks.model.*;
import io.micronaut.context.annotation.Value;
import io.micronaut.core.io.Readable;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Inject;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class WaGsRestClient {
    @Inject
    WaGsRestClientInt waGsRestClientInt;

    @Inject
    ObjectMapper objectMapper;

    @Value("classpath:cb/list.json")
    private Readable list;
    @Value("${gupshup.wa.creds.hsm.user}")
    String hsmUser = null;

    @Value("${gupshup.wa.creds.hsm.password}")
    String password = null;

    @Value("${gupshup.wa.header}")
    String header = null;

    @Value("${gupshup.wa.footer}")
    String footer = null;

    public ResponseWrapper sendMessage(String message, String sendToMobile, Boolean hsm) {
        SendMessage sm = new SendMessage();
        if (hsm) {
            sm.setPassword("GluDlwJY0");
            sm.setUserid("2000219131");
        }
        sm.setSend_to(sendToMobile);
        sm.setMsg(message);
        ResponseWrapper rw = waGsRestClientInt.sendMessage(sm);
        System.out.println("Response after sending message via WA : " + rw.getResponse());
        return rw;
    }

    public ResponseWrapper sendList(String mobile) throws IOException {
        ListInteractive listInteractive = objectMapper.readValue(list.asInputStream(), ListInteractive.class);
        InteractiveMessage interactiveMessage = InteractiveMessage.buildDefault(objectMapper.writeValueAsString(listInteractive), mobile);
        return waGsRestClientInt.sendInteractiveMessage(interactiveMessage);
    }

    public ResponseWrapper optIn(String mobile) {
        return waGsRestClientInt.optIn(mobile);
    }

    public ResponseWrapper sendBankVerificationSuccessfull(String name, String mobile) {
        return waGsRestClientInt.sendBankVerificationSuccessfull(hsmUser, password, header, footer, mobile, name);
    }

    public ResponseWrapper sendBankVerificationPending(String name, String mobile) {
        return waGsRestClientInt.sendBankVerificationPending(hsmUser, password, header, footer, mobile, name);
    }

    public ResponseWrapper sendBankVerificationFailed(String name, String mobile) {
        return waGsRestClientInt.sendBankVarificationFailure(hsmUser, password, header, footer, mobile, name, "?bank=Y&mobile=" + mobile);
    }


    public ResponseWrapper sendPanVerificationSuccessfull(String mobile, String name) {
        return waGsRestClientInt.sendPanVerificationSuccessfull(hsmUser, password, header, footer, mobile, name);
    }

    public ResponseWrapper sendPanVarificationFailure(String mobile, String name) {
        return waGsRestClientInt.sendPanVarificationFailure(hsmUser, password, header, footer, mobile, name, "?pan=Y&mobile=" + mobile);
    }

    public ResponseWrapper sendPaymentSuccessFullMessage(String decentroTxnId, String mobile, String tenantName, String llName) {
        return waGsRestClientInt.sendPaymentSuccessFullMessage(hsmUser, password, header, "https://www.rentgain.com", mobile, tenantName, llName, "?tid=" + decentroTxnId);
    }
}
