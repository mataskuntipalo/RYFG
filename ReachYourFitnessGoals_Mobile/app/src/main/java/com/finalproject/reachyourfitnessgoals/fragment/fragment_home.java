package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.activity.LoginActivity;
import com.finalproject.reachyourfitnessgoals.activity.MainActivity;
import com.finalproject.reachyourfitnessgoals.activity.SetExerciseInWeekActivity;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_home extends Fragment {

    public SharedPreferences shared;
    SharedPreferences.Editor editor;
    Button setExe;
    LinearLayout displayDay;

    public fragment_home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);

        //Init value
        shared = getContext().getSharedPreferences(getResources().getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE);
        editor = shared.edit();
        setExe = (Button)rootview.findViewById(R.id.setExd_button_home);
        displayDay = (LinearLayout) rootview.findViewById(R.id.displayDay_include_home);

        // check first time used
        boolean tempCheckTime = shared.getBoolean(getResources().getString(R.string.sharedBoolFirstTime), false);
        if(tempCheckTime == false){
            firstTimeUsed();
        }

        // Begin circle process
        DecoView arcView = (DecoView)rootview.findViewById(R.id.dynamicArcView);

        // Create background track
        arcView.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)
                .setInitialVisibility(false)
                .setLineWidth(32f)
                .build());

        //Create data series track
        final SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
                .setRange(0, 25, 0)
                .setLineWidth(32f)
                .build();

        final int series1Index = arcView.addSeries(seriesItem1);

        final SeriesItem seriesItem2 = new SeriesItem.Builder(Color.argb(255, 0, 0, 0))
                .setRange(0, 25, 0)
                .setLineWidth(32f)
                .setInset(new PointF(20f, 20f))
                .build();

        int series2Index = arcView.addSeries(seriesItem2);

        arcView.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDelay(1000)
                .setDuration(2000)
                .build());

        arcView.addEvent(new DecoEvent.Builder(25).setIndex(series1Index).setDelay(4000).build());
        arcView.addEvent(new DecoEvent.Builder(25).setIndex(series2Index).setDelay(6000).build());

        final String format = "%.0f%%";
        final TextView view = (TextView) rootview.findViewById(R.id.test);
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


        checkSetExe();



        return rootview;
    }

    private void firstTimeUsed(){
        editor.putBoolean(getResources().getString(R.string.sharedBoolFirstTime), true);
        editor.putBoolean(getResources().getString(R.string.sharedBoolSetExe), false);
        editor.commit();
    }

    private void checkSetExe(){
        boolean tempCheckSetExe = shared.getBoolean(getResources().getString(R.string.sharedBoolSetExe), false);
        setExe.setOnClickListener(buttonSetExe);
//        if(tempCheckSetExe == false){
//            setExe.setVisibility(View.VISIBLE);
//            displayDay.setVisibility(View.GONE);
//            setExe.setOnClickListener(buttonSetExe);
//            editor.putBoolean(getResources().getString(R.string.sharedBoolSetExe), true);
//            editor.commit();
//        }else {
//            setExe.setVisibility(View.GONE);
//            displayDay.setVisibility(View.VISIBLE);
//        }
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
            Log.d("onActivityResult", "requestCode = " + requestCode);
            setExe.setVisibility(View.GONE);
            displayDay.setVisibility(View.VISIBLE);
        }
    }


}
