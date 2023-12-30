package com.rentgain;

import com.rentgain.model.PaymentLink;
import com.rentgain.model.Property;
import com.rentgain.service.CrudService;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

@MicronautTest
public class TestPaymentLink {

    //@BeforeAll
    public static void createData() throws ExecutionException, InterruptedException {
        PaymentLink paymentLink = new PaymentLink();

        paymentLink.setPaymentLinkId("35BE50C75FA0473FADBDB1AEB1B76C51");
        // property details
        paymentLink.setPropertyAddress("1 any street, bangalore ka 560075");

        // landlorddetails
        paymentLink.setLandlordName("Pradeep Kumar");
        paymentLink.setLandlordPan("ADZPK6545E");
        paymentLink.setLandlordEmail("pradekk@gmail.com");
        paymentLink.setLandlordMobile("16176207674");

        // tenant details
        paymentLink.setTenantName("Yogesh Mokashi");
        paymentLink.setTenantMobile("919565622148");
        paymentLink.setTenantPan("ADZYM6545E");
        paymentLink.setTenantEmail("yogesh@mb83.tech");

        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
        paymentLink.setMonth("January");
        paymentLink.setYear("2023");
        final String date = sdf.format(Calendar.getInstance().getTime());
        paymentLink.setDateIssued(date);
        // calculate actual due date
        paymentLink.setDateDue(date);
        paymentLink.setRentFor("Jan, 2023");
        paymentLink.setInvoiceNumber("2023-01");

        paymentLink.setRent("100");
        CrudService.savePaymentLink(paymentLink);

        PaymentLink find = CrudService.findPaymentLink(paymentLink.getPaymentLinkId());
        System.out.println(find);
    }



   // @Test
    public void testPaymentLink() {

    }
}
