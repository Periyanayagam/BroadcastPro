package com.perusudroid.broadcastpro.intentbroad;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.perusudroid.broadcastpro.Constants;

import java.util.ArrayList;

/**
 * Created by Intel on 26-12-2017.
 */

public class IntentService extends Service {

    private String TAG = IntentService.class.getSimpleName();
    private ArrayList<String> mList;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "In onCreate");
        mList = new ArrayList<String>();
        mList.add("Sample 1");
        mList.add("Sample 2");
        mList.add("Sample 3");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Intent broadcastIntent = new Intent();
                        broadcastIntent.setAction(Constants.Simple.mBroadcastStringAction);
                        broadcastIntent.putExtra(Constants.Simple.mBroadcastStringAction, "Broadcast Data");
                        sendBroadcast(broadcastIntent);

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        broadcastIntent.setAction(Constants.Simple.mBroadcastIntegerAction);
                        broadcastIntent.putExtra(Constants.Simple.mBroadcastIntegerAction, "10");
                        sendBroadcast(broadcastIntent);

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        broadcastIntent
                                .setAction(Constants.Simple.mBroadcastArrayListAction);
                        broadcastIntent.putExtra("Data", mList);
                        sendBroadcast(broadcastIntent);
                    }
                }
        ).start();

        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
