package com.rentgain.cb.decentro.callouts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentgain.cb.decentro.callouts.serviceint.DecentroBankAccountValidationInt;
import com.rentgain.cb.decentro.model.BankAccountValidationRequest;
import com.rentgain.cb.decentro.model.BankAccountValidationResponse;
import jakarta.inject.Inject;


public class BankAccountValidationService {
    @Inject
    DecentroBankAccountValidationInt bankAccountValidationInt;

    public BankAccountValidationResponse validateBankAccount(BankAccountValidationRequest bav)  {
         BankAccountValidationResponse bankAccountValidationResponse= bankAccountValidationInt.validateBankAccount(bav);
        System.out.println("response from dec : " + bankAccountValidationResponse);
        return bankAccountValidationResponse;
    }
}
