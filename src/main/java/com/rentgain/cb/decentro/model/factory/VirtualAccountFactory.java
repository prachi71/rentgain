package com.rentgain.cb.decentro.model.factory;

import com.rentgain.cb.decentro.model.VirtualAccountRequest;
import com.rentgain.model.Landlord;
import com.rentgain.model.Profile;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class VirtualAccountFactory {
    public static VirtualAccountRequest getVirtualAccountRequest(Landlord landlord, String customer_id) {
        VirtualAccountRequest virtualAccount = new VirtualAccountRequest();
        List<String> bCodes = Arrays.asList(new String[]{"YESB"});
        virtualAccount.setBank_codes(bCodes);
        final Profile profile = landlord.getProfile();
        virtualAccount.setName(profile.getLl_fullname());
        virtualAccount.setPan(profile.getLl_pan());
        virtualAccount.setEmail(profile.getLl_email());
        final String ll_mobile = landlord.getProfile().getLl_mobile();
        virtualAccount.setMobile(ll_mobile.length()>10?ll_mobile.substring(1):ll_mobile);
        virtualAccount.setAddress(profile.getLl_address());
        virtualAccount.setKyc_verified(1);
        virtualAccount.setKyc_check_decentro(0);
        virtualAccount.setMinimum_balance(0);
        virtualAccount.setTransaction_limit(BigDecimal.valueOf(1000000).doubleValue());
        virtualAccount.setCustomer_id(customer_id);
        virtualAccount.setVirtual_account_balance_settlement("enabled");
        virtualAccount.setMaster_account_alias("decentro_account_ybl_3");
        virtualAccount.setUpi_onboarding(true);
        virtualAccount.setState_code(1);
        virtualAccount.setPincode(560036);
        virtualAccount.setCity("Bangalore");


        VirtualAccountRequest.UpiOnboardingDetails upiOnboardingDetails = new VirtualAccountRequest.UpiOnboardingDetails();
        upiOnboardingDetails.setMerchant_category_code("4214");
        upiOnboardingDetails.setMerchant_business_type("4");
        upiOnboardingDetails.setTransaction_amount_limit_per_transaction(100000);
        upiOnboardingDetails.setTransaction_count_limit_per_day(1000);
        upiOnboardingDetails.setTransaction_amount_limit_per_day(100000);

        virtualAccount.setUpi_onboarding_details(upiOnboardingDetails);

        System.out.println("VAR_CREATED':" + virtualAccount);

        return virtualAccount;
    }
}
