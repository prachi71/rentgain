package com.rentgain.scheduled;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.rentgain.service.FireStoreUtil;
import com.rentgain.cb.wa.callouts.WaGsRestClient;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Singleton
public class TenantInviter {

    private static String invite = "Dear tenant I have partnered with RentGain to manage rent payments. " +
            "Please complete your profile at https://wa.me/%s?text=%s.";
    @Inject
    WaGsRestClient waGsRestClient;

    //@Scheduled(fixedDelay = "10s")
    public void sendTenantInvite() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query = FireStoreUtil.getDb().collection("rgTenantInvites").whereEqualTo("sent", false).get();
        List<QueryDocumentSnapshot> invitesToSend = query.get().getDocuments();
        invitesToSend.forEach(data-> {
            Map<String, Object> inviteToSend = data.getData();

        });
    }
}
