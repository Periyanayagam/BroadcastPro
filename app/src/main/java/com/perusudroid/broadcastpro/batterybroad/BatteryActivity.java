package com.perusudroid.broadcastpro.batterybroad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.perusudroid.broadcastpro.R;
import com.perusudroid.broadcastpro.Constants;

public class BatteryActivity extends AppCompatActivity {

    private static final String TAG = BatteryActivity.class.getSimpleName();
    BatteryReceiver mReceiver = new BatteryReceiver();
    private TextView tvStatus, tvPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        tvStatus = findViewById(R.id.tvStatus);
        tvPower = findViewById(R.id.tvPower);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //the one below registers a global receiver.
        IntentFilter myIF = new IntentFilter(Constants.broadCasts.powerConnected);
        myIF.addAction(Constants.broadCasts.powerDisconnected);
        myIF.addAction(Constants.broadCasts.batteryLow);
        myIF.addAction(Constants.broadCasts.batteryOkay);
        myIF.addAction(Constants.broadCasts.batteryChanged);
        registerReceiver(mReceiver, myIF);
    }


	/*
     * To test this on the emulator, remember to telnet to the emulator
    * commands:
    * power ac off      phone is unplugged
    * power ac on       phone is plugged in/charging
    * power capacity  15    low (when unplugged)
    * power capacity 30     okay.. (when plugged in and was low)
    * power capacity 100   full charge.
    */

    public class BatteryReceiver extends BroadcastReceiver {
        public BatteryReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.v("myReceiver", "received an intent" + intent.getAction());

            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            tvPower.setText(String.valueOf(level) + "%");


            String info = "";
            if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {  //battery is low!
                info = info + "\n batter low. should shut down things.";
            } else if (intent.getAction().equals(Intent.ACTION_BATTERY_OKAY)) {  //battery is now ok!
                info = info + "\n battery Okay.  ";
            } else if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {  //battery is charging.
                info = info + "\n Power connected";
            } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {  //power has been disconnected.
                info = info + "\n Power disconnected.";
            }

            tvStatus.setText(info);
        }
    }

}
