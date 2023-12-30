package com.rentgain.cb.wa.gupshup;

import com.rentgain.cb.wa.callbacks.constants.RentGainConstants;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GupShupApiCaller {


    /*
    	HSM Account	2000219131	GluDlwJY0
	Two Way Account	2000219134	hIGQegwJF
     */
    public static final String OPTIN_HTTPS_MEDIA_SMSGUPSHUP_COM_GATEWAY_API_REST =
            "https://media.smsgupshup.com/GatewayAPI/rest?method=OPT_IN&format=json&userid=%s&password=%s&phone_number=%s&v=1.1&auth_scheme=plain&channel=WHATSAPP";

    public static void optIn(String mobile) throws IOException, URISyntaxException {

        String urlString = String.format(OPTIN_HTTPS_MEDIA_SMSGUPSHUP_COM_GATEWAY_API_REST, "2000219131", "GluDlwJY0", mobile);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlString))
                .header(RentGainConstants.CONTENT_TYPE, RentGainConstants.TEXT_PLAIN)
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);


    }

    public static void postMessage(String mobile, String message) {

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://media.smsgupshup.com/GatewayAPI/rest"))
                    .header(RentGainConstants.CONTENT_TYPE, RentGainConstants.APPLICATION_X_WWW_FORM_URLENCODED)
                    .POST(HttpRequest.BodyPublishers.ofString(message))
                    .build();

    }

    public static void main(String[] args) throws URISyntaxException, ExecutionException, InterruptedException {
        sendHelp("1");
    }
    public static void sendHelp(String mobile) throws URISyntaxException, ExecutionException, InterruptedException {
        String listMsg = "method=SendMessage" +
                "&msg=Welcome%20to%20RentGain%20please%20select%20one%20to%20avail%20our%20service." +
                "&msg_type=text" +
                "&userid=2000219134" +
                "&auth_scheme=plain" +
                "&password=hIGQegwJF" +
                "&interactive_type=list" +
                "&send_to=918310798566" +
                "&v=1.1" +
                "&format=JSON" +
                "&footer=RentGain&header=RentGain&action=";

        String json = URLEncoder.encode("{\n" +
                "  \"button\": \"Menu\",\n" +
                "  \"sections\": [\n" +
                "    {\n" +
                "      \"title\": \"Set Up\",\n" +
                "      \"rows\": [\n" +
                "        {\n" +
                "          \"id\": \"id1\",\n" +
                "          \"title\": \"Add Tenant\",\n" +
                "          \"description\": \"Select to add a tenant\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Maintain\",\n" +
                "      \"rows\": [\n" +
                "        {\n" +
                "          \"id\": \"id2\",\n" +
                "          \"title\": \"Edit Tenant\",\n" +
                "          \"description\": \"Click here to update/delete tenants\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Service\",\n" +
                "      \"rows\": [\n" +
                "        {\n" +
                "          \"id\": \"id4\",\n" +
                "          \"title\": \"Customer care\",\n" +
                "          \"description\": \"select to connect with Customer care\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": \"id5\",\n" +
                "          \"title\": \"Call Back\",\n" +
                "          \"description\": \"We will call you back in 10 min\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Account\",\n" +
                "      \"rows\": [\n" +
                "        {\n" +
                "          \"id\": \"id8\",\n" +
                "          \"title\": \"Account Details\",\n" +
                "          \"description\": \"Change account details including contact and banking information\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}", StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://media.smsgupshup.com/GatewayAPI/rest)"))
                .header(RentGainConstants.CONTENT_TYPE, RentGainConstants.APPLICATION_X_WWW_FORM_URLENCODED)
                .POST(HttpRequest.BodyPublishers.ofString(listMsg+json))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        Future f = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
        f.get();
    }
}