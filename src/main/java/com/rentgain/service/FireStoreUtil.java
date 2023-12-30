package com.rentgain.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.rentgain.security.SecurityUtils;


import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class FireStoreUtil {

    private static final String projectId = "rentgain-370213";
    private static GoogleCredentials googleCredentials = null;

    private static Firestore db = null;

    public static final String RENTGAIN_DB_ID = "rentgain-test";

    static {
        try {
            googleCredentials = SecurityUtils.getGoogleCredentials();
            FirestoreOptions firestoreOptions =
                    FirestoreOptions.getDefaultInstance().toBuilder()
                            .setProjectId(projectId)
                            .setDatabaseId(RENTGAIN_DB_ID)
                            .setCredentials(googleCredentials)
                            .build();
            setDb(firestoreOptions.getService());

        } catch (IOException e) {
            throw new RuntimeException("Failed in initializing security context , this will fail in loading this function as this is static initializer", e);
        }
    }

    public static void main(String[] args) {
        System.out.println(getCollectionReference("test"));
    }
    public static CollectionReference getCollectionReference(String collectionName) {
        return getDb().collection(collectionName);
    }

    public static DocumentSnapshot getLandlordDoc(String llMobile) throws ExecutionException, InterruptedException {
        return FireStoreUtil.getDb().collection("rgLandlords_" + llMobile).document(llMobile).get().get();
    }

    public static Firestore getDb() {
        return db;
    }

    public static void setDb(Firestore db) {
        FireStoreUtil.db = db;
    }
}
