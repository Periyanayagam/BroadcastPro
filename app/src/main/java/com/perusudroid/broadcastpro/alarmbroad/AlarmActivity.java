package com.perusudroid.broadcastpro.alarmbroad;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.perusudroid.broadcastpro.R;
import com.perusudroid.broadcastpro.Constants;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    public static String id = "test_channel_01";
    NotificationManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        createchannel();
    }

    public void setalarm(View view) {
        setalarm();
    }

    public void setalarm() {
        int NotID = 1;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 2);
        calendar.set(Calendar.SECOND, 0);
        Intent notificationIntent = new Intent(Constants.alarm.ACTION);
        notificationIntent.setAction(Constants.alarm.ACTION);
        notificationIntent.putExtra("NotifID", NotID);
        PendingIntent contentIntent = PendingIntent.getBroadcast(AlarmActivity.this, NotID, notificationIntent, 0);
        //---sets the alarm to trigger---
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), contentIntent);
        sendBroadcast(notificationIntent);//let's see if it works... without the alarm.
        // finish();  //exit the app.
    }

    /*
 * for API 26+ create notification channels
*/
    private void createchannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id,
                    getString(R.string.channel_name),  //name of the channel
                    NotificationManager.IMPORTANCE_DEFAULT);   //importance level
            //important level: default is is high on the phone.  high is urgent on the phone.  low is medium, so none is low?
            // Configure the notification channel.
            mChannel.setDescription(getString(R.string.channel_description));
            mChannel.enableLights(true);
            // Sets the notification light color for notifications posted to this channel, if the device supports this feature.
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setShowBadge(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            nm.createNotificationChannel(mChannel);

        }
    }


}
