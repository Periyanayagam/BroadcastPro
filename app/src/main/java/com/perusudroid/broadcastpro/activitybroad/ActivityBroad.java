package com.perusudroid.broadcastpro.activitybroad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.perusudroid.broadcastpro.R;
import com.perusudroid.broadcastpro.Constants;

public class ActivityBroad extends AppCompatActivity {

    private static final String TAG = ActivityBroad.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad);
        startMyServices();
    }

    public void broadClicked(View view) {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(Constants.activity.BROAD_CAST);
        sendBroadcast(broadcastIntent);
        Log.d(TAG, "activityClicked: broadcast send");
    }

    public void localBroadClicked(View view) {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(Constants.activity.LOCAL_BROADCAST);
        sendBroadcast(broadcastIntent);
        Log.d(TAG, "activityClicked: broadcast send");
    }

    private void startMyServices() {
        Intent serviceIntent = new Intent(this, ActivityService.class);
        startService(serviceIntent);
    }

}
