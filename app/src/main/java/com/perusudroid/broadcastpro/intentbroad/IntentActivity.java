package com.perusudroid.broadcastpro.intentbroad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.perusudroid.broadcastpro.R;
import com.perusudroid.broadcastpro.Constants;

public class IntentActivity extends AppCompatActivity {

    private IntentFilter mIntentFilter;
    private TextView tvText;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction() != null) {
                if (intent.getAction().equals(Constants.Simple.mBroadcastStringAction)) {
                    tvText.setText(String.format("%s\n%s\n", tvText.getText().toString(), intent.getStringExtra(Constants.Simple.mBroadcastStringAction)));
                    showToast("String received");
                } else if (intent.getAction().equals(Constants.Simple.mBroadcastIntegerAction)) {
                    tvText.setText(String.format("%s%s", tvText.getText().toString(), intent.getStringExtra(Constants.Simple.mBroadcastIntegerAction)));
                    showToast("Integer received");
                } else if (intent.getAction().equals(Constants.Simple.mBroadcastArrayListAction)) {
                    showToast("List Received");
                }
            }

            Intent stopIntent = new Intent(IntentActivity.this,
                    IntentService.class);
            stopService(stopIntent);
        }
    };

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        tvText = findViewById(R.id.tvText);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(Constants.Simple.mBroadcastStringAction);
        mIntentFilter.addAction(Constants.Simple.mBroadcastIntegerAction);
        mIntentFilter.addAction(Constants.Simple.mBroadcastArrayListAction);

        Intent serviceIntent = new Intent(this, IntentService.class);
        startService(serviceIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mReceiver);
        super.onPause();
    }
}
