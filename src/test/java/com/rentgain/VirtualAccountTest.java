package com.rentgain;

import com.rentgain.cb.decentro.callouts.service.VirtualAccountService;
import com.rentgain.cb.decentro.model.VirtualAccountRequest;
import com.rentgain.cb.decentro.model.VirtualAccountResponse;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

//@MicronautTest
public class VirtualAccountTest {

    @Inject
    VirtualAccountService c;

    //@Test
    public void testCreateVirtualAccount() {
        VirtualAccountRequest virtualAccount = new VirtualAccountRequest();
        List<String> bCodes = Arrays.asList(new String[]{"YESB"});
        virtualAccount.setBank_codes(bCodes);
        virtualAccount.setName("Pradeep Kumar");
        virtualAccount.setPan("ADXPK1245E");
        virtualAccount.setEmail("any@any.com");
        final String mobile = "6176207674";

        virtualAccount.setMobile(mobile);
        virtualAccount.setAddress("Test address");
        virtualAccount.setKyc_verified(1);
        virtualAccount.setKyc_check_decentro(0);
        virtualAccount.setMinimum_balance(0);
        virtualAccount.setTransaction_limit(BigDecimal.valueOf(1000000).doubleValue());
        virtualAccount.setCustomer_id("PKRG93");
        virtualAccount.setVirtual_account_balance_settlement("enabled");
        virtualAccount.setMaster_account_alias("decentro_account_ybl_3");
        virtualAccount.setUpi_onboarding(true);
        virtualAccount.setState_code(1);
        virtualAccount.setPincode(560036);
        virtualAccount.setCity("Bangalore");

        VirtualAccountRequest.UpiOnboardingDetails upiOnboardingDetails = new VirtualAccountRequest.UpiOnboardingDetails();
        upiOnboardingDetails.setMerchant_category_code("4214");
        upiOnboardingDetails.setMerchant_business_type("4");
        upiOnboardingDetails.setTransaction_amount_limit_per_transaction(999);
        upiOnboardingDetails.setTransaction_count_limit_per_day(1000);
        upiOnboardingDetails.setTransaction_amount_limit_per_day(10000);

        virtualAccount.setUpi_onboarding_details(upiOnboardingDetails);
        final VirtualAccountResponse virtualAccount1 = c.createVirtualAccount(virtualAccount);
        System.out.println(virtualAccount1);
    }
}
