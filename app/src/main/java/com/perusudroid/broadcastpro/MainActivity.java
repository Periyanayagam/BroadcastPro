package com.perusudroid.broadcastpro;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.perusudroid.broadcastpro.activitybroad.ActivityBroad;
import com.perusudroid.broadcastpro.alarmbroad.AlarmActivity;
import com.perusudroid.broadcastpro.batterybroad.BatteryActivity;
import com.perusudroid.broadcastpro.intentbroad.IntentActivity;
import com.perusudroid.broadcastpro.internetbroad.NetworkChangeReceiver;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    NetworkChangeReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void simpleClicked(View v) {
        startThisActivity(IntentActivity.class);
    }

    public void batteryClicked(View v) {
        startThisActivity(BatteryActivity.class);
    }

    public void alarmClicked(View view) {startThisActivity(AlarmActivity.class);}

    public void activityClicked(View view) {startActivity(new Intent(MainActivity.this, ActivityBroad.class));}

    public void internetClicked(View view) {
        showToast(""+isNetworkAvailable(this));
    }

    @Override
    protected void onResume() {
        registerReceiver(this.mReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo networkInfo : info) {
                    if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void startThisActivity(Class<?> myClass) {
        Intent intent = new Intent(this, myClass);
        startActivity(intent);
    }

}
