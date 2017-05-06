package com.finalproject.reachyourfitnessgoals.fragment;


import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import com.finalproject.reachyourfitnessgoals.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_exercise extends Fragment  {

    Chronometer chronometer;
    long lastPause;
    EMVideoView emVideoView;
    ProgressDialog pd;
    ImageButton doneButton;


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

        emVideoView = (EMVideoView)rootview.findViewById(R.id.video_view);
        setupVideoView();
        chronometer = (Chronometer) rootview.findViewById(R.id.Chronometer);
        Button start = (Button) rootview.findViewById(R.id.start);
        ImageButton stop = (ImageButton) rootview.findViewById(R.id.stop);

        doneButton = (ImageButton) rootview.findViewById(R.id.done_ImageButton_exe);
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

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_sumExe parQ = new fragment_sumExe();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
//                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
                        .replace(R.id.activity_exercise
                                , parQ, "fragment_parQ")
                        .addToBackStack("fragment_parQ")
                        .commit();
            }
        });




        return rootview;
    }

    private void setupVideoView() {

        emVideoView.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared() {
                emVideoView.start();
            }
        });

        //For now we just picked an arbitrary item to play.  More can be found at
        //https://archive.org/details/more_animation
        String part = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.vdo;
        emVideoView.setVideoURI(Uri.parse(part));
    }


}
