package com.finalproject.reachyourfitnessgoals.setting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;

import com.finalproject.reachyourfitnessgoals.R;

public class MyReceiver extends BroadcastReceiver {

    public SharedPreferences shared;
    public SharedPreferences.Editor editor;

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("receive","in");
        shared = context.getSharedPreferences(context.getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
        editor = shared.edit();
        editor.putBoolean(context.getString(R.string.sharedBoolSetExe), false);
        editor.commit();
    }
}
