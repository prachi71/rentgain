package com.rentgain.cb.wa.callbacks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.rentgain.cb.wa.callbacks.constants.MessageType;
import com.rentgain.cb.wa.callbacks.model.ResponseWrapper;
import com.rentgain.cb.wa.callbacks.model.WaGsInboundMessage;
import com.rentgain.cb.wa.callouts.WaGsRestClient;
import com.rentgain.service.FireStoreUtil;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;

@Controller("/rentgain")
public class WaGsInboundMessageController {

    public static final String WELCOME = "Welcome to RentGain! To continue and confirm your service opt-in, please send this message";
    public static final String HELP = "help";

    private static String setupUrl = "rentgain/start";
    public static final String RG_UI_URL = "https://rentgain.com/";

    @Inject
    WaGsRestClient waGsRestClient;

    @Inject
    private ObjectMapper objectMapper;

    public static Boolean isLandlordRegistered(String mobile) throws ExecutionException, InterruptedException {
        final CollectionReference document = FireStoreUtil.getDb().collection("rgLandlords_" + mobile);
        return document.get().get().isEmpty() == false;
    }

    @Post("/processInbound")
    @Consumes(MediaType.ALL)
    public void processInbound(@Body String payload) throws Exception {
        Instant start = Instant.now();
        WaGsInboundMessage inbound = objectMapper.readValue(payload, WaGsInboundMessage.class);
        String message = inbound.getText();
        System.out.println("recieved : " + inbound);
        final MessageType messageType = MessageType.get(inbound.getType());

        switch (messageType) {
            case TEXT:
                switch (message) {
                    case WELCOME:
                        if (!isLandlordRegistered(inbound.getMobile())) {
                            final DocumentReference document = FireStoreUtil.getDb().collection("rgOptins").document(inbound.getMobile());
                            document.set(CollectionUtils.mapOf("optin_gs_call", "false"));
                            System.out.println("Opted:" + waGsRestClient.optIn(inbound.getMobile()));
                            String sessionId = createSession(inbound);
                            ResponseWrapper rw = waGsRestClient.sendMessage(
                                    "Successfully opted in to WhatsApp, please complete your profile at "
                                            + RG_UI_URL + setupUrl + "?sid=" + sessionId +"&mobile=" + inbound.getMobile(), inbound.getMobile(), false);
                            System.out.println("sent : " + rw);
                        } else {
                            String sessionId = createSession(inbound);
                            ResponseWrapper rw = waGsRestClient.sendMessage(
                                    "Already registered, click the link to engage with RentGain "
                                            + RG_UI_URL + setupUrl + "?sid=" + sessionId +"&mobile=" + inbound.getMobile(), inbound.getMobile(), false);
                        }
                        break;
                    case HELP:
                    default:
                        //System.out.println("sending interactive message");
                        //ResponseWrapper rw1 = waGsRestClient.sendList(inbound.getMobile());
                        String sessionId = createSession(inbound);
                        ResponseWrapper rw1 = waGsRestClient.sendMessage(
                                "Click to interact with RentGain "
                                        + RG_UI_URL + setupUrl + "?sid=" + sessionId +"&mobile=" + inbound.getMobile(), inbound.getMobile(), false);
                        return;
                }
                break;
            case INTERACTIVE:
                System.out.println(inbound.getInteractive().getType());
                if (MessageType.LIST_TYPE.equals(MessageType.get(inbound.getInteractive().getType()))) {
                    System.out.println(MessageType.LIST_TYPE.getType() + ":" + inbound.getInteractive());
                }
                //respond(inbound.getName(), inbound.getMobile(), "When fully integrated you will get a proper link until then https://www.rentgain.com");
                break;
            default:
                return;
        }
        Instant end = Instant.now();
        System.out.println("Finished request in : " + Duration.between(start, end));
    }

    private String createSession(WaGsInboundMessage inbound) {
        final DocumentReference document = FireStoreUtil.getDb().collection("rgSession").document();
        document.set(
        CollectionUtils.mapOf("mobile", inbound.getMobile(),"date", Instant.now()));
        String sessionId = document.getId();
        return sessionId;
    }





}
