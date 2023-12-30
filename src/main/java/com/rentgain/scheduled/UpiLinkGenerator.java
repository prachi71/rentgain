package com.rentgain.scheduled;

import com.rentgain.cb.wa.callouts.WaGsRestClientInt;
import jakarta.inject.Inject;

import java.util.concurrent.*;

public class UpiLinkGenerator {


    private static ScheduledExecutorService scheduledExecutorService;
    private static final BlockingDeque<HsmRentReq> waHsmQueue = new LinkedBlockingDeque<HsmRentReq>();

    public static void queueHsm(HsmRentReq req) {
        waHsmQueue.add(req);
    }

    private static class WhatsAppSendHSM implements Runnable {
        @Inject
        private WaGsRestClientInt waGsRestClientInt;

        @Override
        public void run() {
            try {
                HsmRentReq req = waHsmQueue.takeFirst();
                waGsRestClientInt.sendHSM(req.getPhoneNbr(), req.getName(), req.getRentMonth(), req.getRentYear(), req.getDueDay(), req.getDueMonth(), req.getDueYear(), req.getUpiLink());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public UpiLinkGenerator() {
        scheduledExecutorService = Executors.newScheduledThreadPool(15);
        scheduledExecutorService.scheduleAtFixedRate(new WhatsAppSendHSM(), 10, 10, TimeUnit.SECONDS);
    }
}
