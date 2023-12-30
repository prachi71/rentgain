package com.rentgain;

import com.rentgain.scheduled.VirtualAccountCreator;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

@MicronautTest
public class TestVirtualAccountCreation {
    @Inject
    VirtualAccountCreator virtualAccountCreator;

    //@Test
    public void testVirtualAccountCreator() throws ExecutionException, InterruptedException {
        virtualAccountCreator.createVirtualBankAccount();
    }

}
