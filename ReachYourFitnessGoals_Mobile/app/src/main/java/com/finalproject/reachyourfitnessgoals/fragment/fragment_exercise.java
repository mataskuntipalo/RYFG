package com.finalproject.reachyourfitnessgoals.fragment;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;

import com.finalproject.reachyourfitnessgoals.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_exercise extends Fragment {

    Chronometer chronometer;
    long lastPause;

    public fragment_exercise() {
        // Required empty public constructor
    }

    public static fragment_exercise newInstance() {
        fragment_exercise fragment = new fragment_exercise();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_exercise, container, false);


        chronometer = (Chronometer) rootview.findViewById(R.id.Chronometer);
        Button start = (Button) rootview.findViewById(R.id.start);
        ImageButton stop = (ImageButton) rootview.findViewById(R.id.stop);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lastPause != 0){
                    chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
                }else{
                    chronometer.setBase(SystemClock.elapsedRealtime());
                }
                chronometer.start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPause = SystemClock.elapsedRealtime();
                chronometer.stop();
                //chronometer.setBase(SystemClock.elapsedRealtime());
            }
        });

        return rootview;
    }

}
