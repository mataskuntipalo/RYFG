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
        Log.i("receiveHello","in");
        shared = context.getSharedPreferences(context.getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
        editor = shared.edit();
        editor.putBoolean(context.getString(R.string.sharedBoolSetExe), false);
        editor.commit();


        Intent intent1 = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://devahoy.com/posts/android-notification/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);

        Notification notification =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("สัปดาห์ใหม่เริ่มต้นขึ้นแล้ว")
                        .setContentText("มาออกกำลังกายกันเถอะ")
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .build();

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1000, notification);
    }
}
