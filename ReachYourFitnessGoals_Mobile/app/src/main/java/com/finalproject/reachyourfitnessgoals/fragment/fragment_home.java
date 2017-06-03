package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.activity.LoginActivity;
import com.finalproject.reachyourfitnessgoals.activity.MainActivity;
import com.finalproject.reachyourfitnessgoals.activity.SetExerciseInWeekActivity;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_EXERCISE;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PROGRAM;
import com.finalproject.reachyourfitnessgoals.models.DateData;
import com.finalproject.reachyourfitnessgoals.models.GlobalData;
import com.finalproject.reachyourfitnessgoals.models.GoalData;
import com.finalproject.reachyourfitnessgoals.setting.handleCalendar;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.google.gson.Gson;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_home extends Fragment {

    SharedPreferences shared;
    SharedPreferences.Editor editor;
    Button setExe;
    LinearLayout displayDay;
    TextView cancelSetExe;
    NestedScrollView nestedScrollView;
    int currentProgramType;
    ImageView bgPic;
    GoalData programData;

    public fragment_home() {
        // Required empty public constructor
    }

    public static fragment_home newInstance() {
        fragment_home fragment = new fragment_home();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);


        programData = new handleTABLE_PROGRAM(getContext()).getCurrentProgramDate();
        currentProgramType = programData.getTypeGoal();

        //testJson(rootview);
        Button notiBtn = (Button)rootview.findViewById(R.id.notification_Button_home);
        notiBtn.setOnClickListener(showNotification);


        //Init value
        nestedScrollView = (NestedScrollView)rootview.findViewById(R.id.scrollView_home);
        MaterialViewPagerHelper.registerScrollView(getActivity(), nestedScrollView);
        shared = getContext().getSharedPreferences(getResources().getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
        editor = shared.edit();
        setExe = (Button)rootview.findViewById(R.id.setExd_button_home);
        displayDay = (LinearLayout) rootview.findViewById(R.id.displayDay_include_home);
        cancelSetExe = (TextView)  rootview.findViewById(R.id.cancelSetExe_Text_home);
        //bgPic = (ImageView)rootview.findViewById(R.id.bg_ImageView_home);
        //Glide.with(this).load("http://192.168.1.35/ryfg/image/1.jpg").into(bgPic);

        // Make layoutTotalSelectDay to invisible
        LinearLayout layout = (LinearLayout) displayDay.findViewById(R.id.layoutTotalSelectDay_LinearLayout_dayOfWork);
        layout.setVisibility(View.INVISIBLE);


        // Begin circle process
        DecoView arcView = (DecoView)rootview.findViewById(R.id.dynamicArcView);

        // Create background track
        arcView.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)
                .setInitialVisibility(false)
                .setLineWidth(32f)
                .build());


            //Create data series track
        final SeriesItem  seriesItem1 = new SeriesItem.Builder(Color.argb(255, 0, 0, 0))
                    .setRange(0,100,0)
                    .setLineWidth(32f)
                    .build();




        final int series1Index = arcView.addSeries(seriesItem1);


        arcView.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDelay(1000)
                .setDuration(2000)
                .build());


        if(currentProgramType == 0) {
            arcView.addEvent(new DecoEvent.Builder(calPercentWeight()).setIndex(series1Index).setDelay(4000).build());
        }else{
            arcView.addEvent(new DecoEvent.Builder(programData.getPercentFat()).setIndex(series1Index).setDelay(4000).build());
        }

        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(3000);
        final String format = "%.0f%%";
        final TextView view = (TextView) rootview.findViewById(R.id.test);
        view.startAnimation(in);
        seriesItem1.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                if (format.contains("%%")) {
                    float percentFilled = ((currentPosition - seriesItem1.getMinValue()) / (seriesItem1.getMaxValue() - seriesItem1.getMinValue()));
                    view.setText(String.format(format, percentFilled * 100f));
                } else {
                    view.setText(String.format(format, currentPosition));
                }
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });
        // End circle process


        checkSetExeWeekly();

        Button reset = (Button)rootview.findViewById(R.id.reset_button_home);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared = getActivity().getSharedPreferences(getResources().getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
                editor.putBoolean(getResources().getString(R.string.sharedBoolSetExe), false);
                editor.commit();

                new handleTABLE_EXERCISE(getContext()).delete();
            }
        });

        return rootview;

    }

    private int calPercentWeight(){
        double temp = (programData.getTotalCalorie()/(programData.getWeightGoal()*7700));
        int percent = (int) (temp*100);
        Log.i("percent1",programData.getTotalCalorie()+"");
        Log.i("percent1",programData.getWeightGoal()+"");
        Log.i("percent1",(programData.getWeightGoal()*7700)+"");
        Log.i("percent1",(programData.getTotalCalorie()/(programData.getWeightGoal()*7700))+"");
        Log.i("percent1",percent+"");
        return percent;
    }

    private void testJson(View rootview) {
        ArrayList<DateData> datas = new handleTABLE_EXERCISE(getContext()).getDateExerciseToCalendar();
        Gson gson = new Gson();
        String jsonString = gson.toJson(datas);
        TextView view = (TextView) rootview.findViewById(R.id.testJson);
        view.setText(jsonString);
    }


    private void checkSetExeWeekly(){
        Toast.makeText(getActivity(), shared.getBoolean(getResources().getString(R.string.sharedBoolSetExe), false)+"", Toast.LENGTH_SHORT).show();
        if(shared.getBoolean(getResources().getString(R.string.sharedBoolSetExe), false) == false){
            displayDay.setVisibility(View.INVISIBLE);
            setExe.setVisibility(View.VISIBLE);
            setExe.setOnClickListener(buttonSetExe);
        }else{
            setExe.setVisibility(View.INVISIBLE);
            displayDay.setVisibility(View.VISIBLE);
            setDayHighLight();
        }
    }


    private View.OnClickListener buttonSetExe = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), SetExerciseInWeekActivity.class);
            startActivityForResult(intent, 12345);

        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 12345 && data != null) {
            Log.i("onActivityResult", data.getStringExtra("key"));
            if(data.getStringExtra("key").equals("weekEnd")){
                displayDay.setVisibility(View.INVISIBLE);
                setExe.setVisibility(View.INVISIBLE);
                cancelSetExe.setVisibility(View.VISIBLE);
            }else{
                displayDay.setVisibility(View.VISIBLE);
                setExe.setVisibility(View.INVISIBLE);
                setDayHighLight();
            }
        }else {
            displayDay.setVisibility(View.INVISIBLE);
            setExe.setVisibility(View.VISIBLE);
        }
    }

    private void setDayHighLight(){
        for(int i = 1 ; i<=7 ; i++){
            LinearLayout dayOfWorkLayout = getSelectDayLayout(i);
            if( shared.getBoolean(getResources().getString(R.string.sharedBoolDayHighLight)+ i, false) == true) {
                dayOfWorkLayout.setBackground(getResources().getDrawable(R.drawable.layout_circle));
                TextView dayText = (TextView) dayOfWorkLayout.findViewById(R.id.day_text_dayOfWork);
                dayText.setAlpha(1);
            }
        }
    }

    private LinearLayout getSelectDayLayout(int i) {
        int id = displayDay.getResources().getIdentifier(
                "dayOfWork_" + i, "id",getContext().getPackageName());
        return (LinearLayout) displayDay.findViewById(id);
    }

    // test Notification
    View.OnClickListener showNotification = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.co.th"));
            PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);

            Notification notification =
                    new NotificationCompat.Builder(getContext())
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("วันนี้เรามีนัดกันนะ")
                            .setContentText("มาออกกำลังกายกันเถอะ")
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setPriority(Notification.PRIORITY_HIGH)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .build();

            NotificationManager notificationManager =
                    (NotificationManager) getContext().getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(1000, notification);
        }
    };


}
