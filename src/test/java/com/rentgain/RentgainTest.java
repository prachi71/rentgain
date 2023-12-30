package com.rentgain;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class RentgainTest {

    @Inject
    EmbeddedApplication<?> application;
    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testItWorks() {
       // HttpRequest<String> r = HttpRequest.POST("/saveLandLord","");
       // client.toBlocking().retrieve(r);

    }

}
