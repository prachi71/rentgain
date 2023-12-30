package com.rentgain.cb.wa.callouts;


import com.rentgain.cb.wa.callbacks.model.InteractiveMessage;
import com.rentgain.cb.wa.callbacks.model.ResponseWrapper;
import com.rentgain.cb.wa.callbacks.model.SendMessage;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import jakarta.annotation.Nonnull;

@Client(
        value = "${wags.inbound.client.url}",
        path = "${wags.inbound.client.path}")
@Retryable(
        attempts = "${wags.inbound.client.retry.attempts}",
        delay = "${wags.inbound.client.retry.delay}")
public interface WaGsRestClientInt {
    @Post(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ResponseWrapper sendMessage(@Body SendMessage sm);

    @Post(produces = MediaType.APPLICATION_FORM_URLENCODED, consumes = MediaType.APPLICATION_JSON)
    public ResponseWrapper sendInteractiveMessage(@Body InteractiveMessage interactiveMessage);

    @Get("?method=OPT_IN&format=json&userid=2000219134&password=hIGQegwJF&v=1.1&auth_scheme=plain&channel=WHATSAPP")
    @Produces({"application/json"})
    ResponseWrapper optIn(
            @Nonnull
            @QueryValue(value = "phone_number") String phone

    );

    @Get("?userid=2000219131" +
            "&password=GluDlwJY0" +
            "&send_to={tenantPhone}" +
            "&v=1.1" +
            "&format=json" +
            "&msg_type=TEXT" +
            "&method=SENDMESSAGE" +
            "&msg=Hello+{tenantName}%0AYour+residential+rental+invoice+for+the+month+{rentMonth}+{rentYear}+is+generated.+%0AThe+invoice+is+due+for+payment+by+{dueDay}+{dueMonth}%2C+{dueYear}.%0AClick+on+the+link+to+view+the+invoice+and+pay+it.++{upiLink}" +
            "&isTemplate=true&header=RentGain+%3A+Renting+Made+Easy" +
            "&footer=https%3A%2F%2Fwww.rentgain.com")
    @Produces({"application/json"})
    ResponseWrapper sendHSM(
            @Nonnull String tenantPhone,
            @Nonnull String tenantName,
            @Nonnull String rentMonth,
            @Nonnull String rentYear,
            @Nonnull String dueDay,
            @Nonnull String dueMonth,
            @Nonnull String dueYear,
            @Nonnull String upiLink
            //@QueryValue(value = "send_to") String phone, @QueryValue(value="msg") String msq
    );

    @Get("?userid={userId}" +
            "&password={password}" +
            "&send_to={mobile}" +
            "&v=1.1" +
            "&format=json" +
            "&msg_type=TEXT" +
            "&method=SENDMESSAGE" +
            "&isTemplate=true" +
            "&msg=Hello+{name}%0A%0AWe+are+unable+to+verify+your+bank+account.+%0A%0AIt+appears+that+the+provided+bank+details+are+incorrect.%0A%0ATo+proceed+further+please+update+your+bank+details.&&header={header}&footer={footer}&buttonUrlParam={param}"

    )
    @Produces({"application/json"})
    ResponseWrapper sendBankVarificationFailure(
            @Nonnull String userId,
            @Nonnull String password,
            @Nonnull String header,
            @Nonnull String footer,
            @Nonnull String mobile,
            @Nonnull String name,
            @Nonnull String param

            //@QueryValue(value = "send_to") String phone, @QueryValue(value="msg") String msq
    );

    @Get("?userid={userId}" +
            "&password={password}" +
            "&send_to={mobile}" +
            "&v=1.1" +
            "&format=json" +
            "&msg_type=TEXT" +
            "&method=SENDMESSAGE" +
            "&isTemplate=true" +
            "&msg=Hello+{name}%0A%0AThank+you+for+signing+up+with+RentGain+.%0A%0ABank+account+verification+is+successfull+.%0A%0ARegards&header={header}&footer={footer}"

    )
    // https://media.smsgupshup.com/GatewayAPI/rest?userid=2000219131&password={{PASSWORD}}&send_to=16176207674&v=1.1&format=json&msg_type=TEXT&method=SENDMESSAGE&msg=Hello+Mr.++Kumar%0A%0AThank+you+for+signing+up+with+RentGain+.%0A%0ABank+account+verification+is+sucessfull+.%0A%0ARegards&isTemplate=true&header=RentGain+%3A+Renting+Made+Easy&footer=https%3A%2F%2Frentgain.com
   // https://media.smsgupshup.com/GatewayAPI/rest?userid=2000219131&password={{PASSWORD}}&send_to=16176207674&v=1.1&format=json&msg_type=TEXT&method=SENDMESSAGE&msg=Hello+Mr.++Kumar%0A%0AThank+you+for+signing+up+with+RentGain+.%0A%0ABank+account+verification+is+successfull+.%0A%0ARegards&isTemplate=true&header=RentGain+%3A+Renting+Made+Easy&footer=https%3A%2F%2Frentgain.com

    @Produces({"application/json"})
    ResponseWrapper sendBankVerificationSuccessfull(
            @Nonnull String userId,
            @Nonnull String password,
            @Nonnull String header,
            @Nonnull String footer,
            @Nonnull String mobile,
            @Nonnull String name


    );
    @Get("?userid={userId}" +
            "&password={password}" +
            "&send_to={mobile}" +
            "&v=1.1" +
            "&format=json" +
            "&msg_type=TEXT" +
            "&method=SENDMESSAGE" +
            "&isTemplate=true" +
            "&msg=Hello+{name}%0A%0AThank+you+for+signing+up+with+RentGain+.%0A%0ABank+account+verification+is+pending+.%0A%0ARegards&header={header}&footer={footer}"

    )
    @Produces({"application/json"})
    ResponseWrapper sendBankVerificationPending(
            @Nonnull String userId,
            @Nonnull String password,
            @Nonnull String header,
            @Nonnull String footer,
            @Nonnull String mobile,
            @Nonnull String name


    );
    @Get("?userid={userId}" +
            "&password={password}" +
            "&send_to={mobile}" +
            "&v=1.1" +
            "&format=json" +
            "&msg_type=TEXT" +
            "&method=SENDMESSAGE" +
            "&isTemplate=true" +
            "&msg=Hello+{name}%0A%0AThank+you+for+signing+up+with+RentGain+.%0A%0APan+card+verification+is+successfull+.%0A%0ARegards&header={header}&footer={footer}"

    )
    @Produces({"application/json"})
    ResponseWrapper sendPanVerificationSuccessfull(
            @Nonnull String userId,
            @Nonnull String password,
            @Nonnull String header,
            @Nonnull String footer,
            @Nonnull String mobile,
            @Nonnull String name


    );

    @Get("?userid={userId}" +
            "&password={password}" +
            "&send_to={mobile}" +
            "&v=1.1" +
            "&format=json" +
            "&msg_type=TEXT" +
            "&method=SENDMESSAGE" +
            "&isTemplate=true" +
            "&msg=Hello+{name}%0A%0AWe+are+unable+to+verify+your+pan+card+details.+%0A%0AIt+appears+that+the+provided+PAN+details+are+incorrect.%0A%0ATo+proceed+further+please+update+your+PAN+details.&&header={header}&footer={footer}&buttonUrlParam={param}"

    )
    @Produces({"application/json"})
    ResponseWrapper sendPanVarificationFailure(
            @Nonnull String userId,
            @Nonnull String password,
            @Nonnull String header,
            @Nonnull String footer,
            @Nonnull String mobile,
            @Nonnull String name,
            @Nonnull String param

            //@QueryValue(value = "send_to") String phone, @QueryValue(value="msg") String msq
    );
    //https://media.smsgupshup.com/GatewayAPI/rest?userid=2000219131&password={{PASSWORD}}&send_to=16176207674&v=1.1&format=json&msg_type=TEXT&method=SENDMESSAGE&msg=Hello+again+Mr.++Kumar%0A%0ARent+payment+processed+successfully.+%0A%0AYour+landlord+Jan+Landlord+thanks+you+for+the+timely+payment.&isTemplate=true&header=RentGain+%3A+Renting+Made+Easy&footer=https%3A%2F%2Fwww.rentgain.com&buttonUrlParam=%3Ftid%3D1
    @Get("?userid={userId}" +
            "&password={password}" +
            "&send_to={mobile}" +
            "&v=1.1" +
            "&format=json" +
            "&msg_type=TEXT" +
            "&method=SENDMESSAGE" +
            "&isTemplate=true" +
            "&msg=Hello+again+{name}%0A%0ARent+payment+processed+successfully.+%0A%0AYour+landlord+{llName}+thanks+you+for+the+timely+payment.%0A%0ARegards&header={header}&footer={footer}&buttonUrlParam={param}"
            //"&msg=Hello+again+Pradeep+Kumar%0A%0ARent+payment+processed+successfully.+%0A%0AYour+landlord+Pradeep+Kumar+thanks+you+for+the+timely+payment.%0A%0ARegards&isTemplate=true&header={header}&footer={footer}&buttonUrlParam=%3Ftid%3D16176207674"
    )
    @Produces({"application/json"})
    ResponseWrapper sendPaymentSuccessFullMessage(
            @Nonnull String userId,
            @Nonnull String password,
            @Nonnull String header,
            @Nonnull String footer,
            @Nonnull String mobile,
            @Nonnull String name,
            @Nonnull String llName,
            @Nonnull String param

    );
}
