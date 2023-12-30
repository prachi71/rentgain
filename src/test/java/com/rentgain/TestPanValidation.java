package com.rentgain;

import com.rentgain.scheduled.LandLordVerificationService;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@MicronautTest
public class TestPanValidation {
    @Inject
    LandLordVerificationService landLordVerificationService;

    //@Test
    public void testPanValidations() {
        landLordVerificationService.verifyLandlordPan();
    }
}
