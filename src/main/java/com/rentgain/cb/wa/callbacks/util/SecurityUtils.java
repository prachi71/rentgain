package com.rentgain.cb.wa.callbacks.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.rentgain.cb.wa.callbacks.constants.RentGainConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SecurityUtils {
    public static GoogleCredentials getGoogleCredentials() throws IOException {
        String GOOGLE_APPLICATION_CREDENTIALS=System.getenv(RentGainConstants.GOOGLE_APPLICATION_CREDENTIALS);

        if (GOOGLE_APPLICATION_CREDENTIALS == null || !Utils.isFileAndExists(GOOGLE_APPLICATION_CREDENTIALS)) {
            throw new IllegalStateException("\"GOOGLE_APPLICATION_CREDENTIALS\" is required.");
        }

        return GoogleCredentials.fromStream(Files.newInputStream(Paths.get(GOOGLE_APPLICATION_CREDENTIALS)));



    }
}
