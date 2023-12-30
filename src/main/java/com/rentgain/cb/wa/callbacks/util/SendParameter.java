package com.rentgain.cb.wa.callbacks.util;

import com.rentgain.cb.wa.callbacks.constants.RentGainConstants;

import java.util.HashMap;
import java.util.Map;

public class SendParameter {
    private String msgType = "Text";
    private String userId = "userId";
    private String authScheme = "plain";
    private String password = "password";
    private String method = "method";
    private String version = "1.1";
    private String format = "json";
    private String message = "message";
    private String channel = "WHATSAPP";
    private Map<String, String> fields = new HashMap<>();

    public SendParameter(String userId, String password, String method, String message,String phNbr) {
        fields.put(RentGainConstants.MSG_TYPE, msgType);
        fields.put(RentGainConstants.AUTH_SCHEME, authScheme);
        fields.put(RentGainConstants.VERSION, version);
        fields.put(RentGainConstants.FORMAT, format);
        fields.put(RentGainConstants.CHANNEL, channel);

        fields.put(RentGainConstants.PASSWORD, password);
        fields.put(RentGainConstants.METHOD, method);
        fields.put(RentGainConstants.MESSAGE, message);
        fields.put(RentGainConstants.PHONE_NBR,phNbr);
        fields.put(RentGainConstants.USER_ID, userId);

    }

    public String getParametersAsString() {
        return HttpParameters.getParameters(fields);
    }



}
