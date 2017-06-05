package com.finalproject.reachyourfitnessgoals.setting;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.finalproject.reachyourfitnessgoals.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyReceiver extends BroadcastReceiver {

    public SharedPreferences shared;
    public SharedPreferences.Editor editor;

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        if(intent.getExtras().getInt("keyDay") == 0){
            shared = context.getSharedPreferences(context.getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
            editor = shared.edit();
            editor.putBoolean(context.getString(R.string.sharedBoolSetExe), false);
            editor.commit();

            Notification notification =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("สัปดาห์ใหม่เริ่มต้นขึ้นแล้ว")
                            .setContentText("มาออกกำลังกายกันเถอะ")
                            .setAutoCancel(true)
                            .setPriority(Notification.PRIORITY_HIGH)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .build();

            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(intent.getExtras().getInt("keyDay"), notification);

        }else{


            Notification notification =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("วันนี้เรามีนัดกันนะ")
                            .setContentText("มาออกกำลังกายกันเถอะ")
                            .setAutoCancel(true)
                            .setPriority(Notification.PRIORITY_HIGH)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .build();

            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(intent.getExtras().getInt("keyDay"), notification);

        }






    }
}
