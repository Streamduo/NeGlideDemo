package com.example.wake.demo.neglide;

import java.util.concurrent.LinkedBlockingDeque;

public class RequestManager {

    private static volatile RequestManager requestManager = new RequestManager();
    private LinkedBlockingDeque<BitmapRequest> requests = new LinkedBlockingDeque<>();
    private BitmapDispather[] bitmapDispathers;


    private RequestManager() {
        start();
    }

    public void start() {
        stop();
        startAllDispather();
    }

    public void startAllDispather() {
        int threadCount = Runtime.getRuntime().availableProcessors();
        bitmapDispathers = new BitmapDispather[threadCount];
        for (int i = 0; i < threadCount; i++) {
            BitmapDispather dispather = new BitmapDispather(requests);
            dispather.start();
            bitmapDispathers[i] = dispather;
        }
    }

    public void stop() {
        if (bitmapDispathers != null && bitmapDispathers.length > 0) {
            for (BitmapDispather bitmapDispather : bitmapDispathers) {
                if (!bitmapDispather.isInterrupted()) {
                    bitmapDispather.interrupt();
                }
            }
        }
    }

    public static RequestManager getInstance() {
        return requestManager;
    }

    public void addBitmapRequest(BitmapRequest bitmapRequest) {
        if (bitmapRequest == null) {
            return;
        }

        if (!requests.contains(bitmapRequest)) {
            requests.add(bitmapRequest);
        }
    }

}
