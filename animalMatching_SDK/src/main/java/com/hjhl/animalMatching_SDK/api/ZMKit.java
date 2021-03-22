package com.hjhl.animalMatching_SDK.api;

import android.app.Application;
import android.content.Context;

import com.hjhl.animalMatching_SDK.base.Constants;

/**
 * Zodiac Match Kit
 */
public class ZMKit {

    private static volatile ZMKit sInstance = null;

    public static ZMKit getInstance() {
        if (sInstance == null) {
            synchronized (ZMKit.class) {
                if (sInstance == null) {
                    sInstance = new ZMKit();
                }
            }
        }
        return sInstance;
    }

    public synchronized void initSDK(Context context, String key) {

    }

}
