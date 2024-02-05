package com.rentgain;

import com.rentgain.cb.wa.callbacks.model.Response;
import com.rentgain.cb.wa.callbacks.model.ResponseWrapper;
import com.rentgain.cb.wa.callbacks.model.Strings;
import com.rentgain.cb.wa.callouts.WaGsRestClient;
import com.rentgain.cb.wa.callouts.WaGsRestClientInt;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.nio.charset.Charset;

@MicronautTest
public class WaMessageTest {
    @Inject
    WaGsRestClient waGsRestClient;

    //@Test
    public void testBankVerficationSuccessfullMessage() {
        ResponseWrapper rw = waGsRestClient.sendBankVerificationSuccessfull("Pradeep Kumar", "16176207674", "1");
        System.out.println(rw);
    }

    //@Test
    public void testBankVerficationFailedMessage() {
        ResponseWrapper rw = waGsRestClient.sendBankVerificationFailed("Pradeep Kumar","16176207674", "1");
        System.out.println(rw);
    }

   // @Test
    public void testBankVerficationPendingMessage() {
        ResponseWrapper rw = waGsRestClient.sendBankVerificationPending("Pradeep Kumar", "16176207674");
        System.out.println(rw);
    }

   // @Test
    public void testPaymentLinkSuccess() {
        ResponseWrapper rw = waGsRestClient.sendPaymentSuccessFullMessage("35BE50C75FA0473FADBDB1AEB1B76C51","16176207674", "Pradeep Kumar", "Pradeep Kumar");
    }
    //https://media.smsgupshup.com/GatewayAPI/rest?userid=2000219131&password=GluDlwJY0&send_to=16176207674&v=1.1&format=json&msg_type=TEXT&method=SENDMESSAGE&isTemplate=true&msg=Hello+again+Pradeep%20Kumar%0A%0ARent+payment+processed+successfully.+%0A%0AYour+landlord+Pradeep%20Kumar+thanks+you+for+the+timely+payment.%0A%0ARegards&header=RentGain%20%3A%20Renting%20Made%20Easy&footer=https%3A%2F%2Frentgain.com&buttonUrlParam=%3Ftid%3D35BE50C75FA0473FADBDB1AEB1B76C51
}
