package com.rentgain.model;
import com.rentgain.cb.decentro.model.BankAccountValidationResponse;
import com.rentgain.cb.decentro.model.KycResponse;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Introspected
@Serdeable
@ToString
public class LandlordSaveResponse {
    private KycResponse panVerifed;
    private BankAccountValidationResponse bankAccountVerified;
}
