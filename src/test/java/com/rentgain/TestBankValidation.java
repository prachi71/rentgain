package com.rentgain;

import com.rentgain.cb.decentro.model.BankAccountValidation;
import com.rentgain.cb.decentro.model.BankAccountValidationRequest;
import com.rentgain.cb.decentro.model.BeneficiaryDetails;
import com.rentgain.scheduled.LandLordVerificationService;
import com.rentgain.service.CrudService;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@MicronautTest
public class TestBankValidation {

    @Inject
    LandLordVerificationService landLordVerificationService;

    //@BeforeAll
    public static void createBankDetails() throws ExecutionException, InterruptedException {
        BankAccountValidationRequest bankAccountValidationRequest = new BankAccountValidationRequest();
        bankAccountValidationRequest.setPurpose_message("This is a penny drop transaction");
        bankAccountValidationRequest.setReference_id(UUID.randomUUID().toString());
        bankAccountValidationRequest.setTransfer_amount("1.5");
        BeneficiaryDetails beneficiaryDetails = new BeneficiaryDetails();
        beneficiaryDetails.setAccount_number("50100003540646");
        beneficiaryDetails.setIfsc("HDFC0000009");

        bankAccountValidationRequest.setBeneficiary_details(beneficiaryDetails);


        BankAccountValidation bankAccountValidation = new BankAccountValidation();
        bankAccountValidation.createdTime = System.currentTimeMillis();
        bankAccountValidation.mobile = "16176207674";
        bankAccountValidation.name = "Pradeep Kumar";
        bankAccountValidation.bankAccountValidationRequest = bankAccountValidationRequest;

        CrudService.saveBankAccountVerification(bankAccountValidation);
    }

    //@Test
    public void testBankValidation() {
        // FireStoreUtil.db.collection("test");
        landLordVerificationService.verifyLandlordBankAccount();
    }

   // @Test
    public void testBankValidationSuccess() {
        landLordVerificationService.checkPendingBankAccountValidations();
    }
}
