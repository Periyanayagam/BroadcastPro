package com.perusudroid.broadcastpro.activitybroad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.perusudroid.broadcastpro.Constants;

/**
 * Created by Intel on 04-01-2018.
 */

public class ActivityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null) {
            if (intent.getAction().equals(Constants.activity.BROAD_CAST)) {
                Toast.makeText(context, "Received in global broadcast", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
