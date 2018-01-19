package com.perusudroid.broadcastpro.activitybroad;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.perusudroid.broadcastpro.Constants;

/**
 * Created by Intel on 04-01-2018.
 */

public class ActivityService extends Service {

    private static final String TAG = ActivityService.class.getSimpleName();
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction() != null) {
                if (intent.getAction().equals(Constants.activity.LOCAL_BROADCAST)) {
                    Toast.makeText(context, "Received in local broadast in service", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        registerReceiver(mReceiver, new IntentFilter(Constants.activity.LOCAL_BROADCAST));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        unregisterReceiver(mReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
