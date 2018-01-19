package com.perusudroid.broadcastpro;

/**
 * Created by Intel on 26-12-2017.
 */

public class Constants {

   public interface Simple {
        String mBroadcastStringAction = "com.perusudroid.broadcast.string";
        String mBroadcastIntegerAction = "com.perusudroid.broadcast.integer";
        String mBroadcastArrayListAction = "com.perusudroid.broadcast.arraylist";
    }

    public interface broadCasts{
        String powerConnected = "android.intent.action.ACTION_POWER_CONNECTED";
        String powerDisconnected = "android.intent.action.ACTION_POWER_DISCONNECTED";
        String batteryLow = "android.intent.action.BATTERY_LOW";
        String batteryOkay = "android.intent.action.BATTERY_OKAY";
        String batteryChanged = "android.intent.action.BATTERY_CHANGED";
    }

    public interface alarm{
       String ACTION = "com.perusudroid.broadcastpro.alarm";
       String id = "test_channel_01";
    }
    
    public interface activity{
       String LOCAL_BROADCAST = "com.perusudroid.activiy.localbroadcast";
       String BROAD_CAST = "com.perusudroid.activiy.broadcast";
    }

}
