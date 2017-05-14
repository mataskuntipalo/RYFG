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
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_exercise extends YouTubePlayerSupportFragment implements YouTubePlayer.OnInitializedListener {

    Chronometer chronometer;
    long lastPause;
//    EMVideoView emVideoView;
VideoView emVideoView;
    ProgressDialog pd;
    ImageButton doneButton;

    public static final String API_KEY = "AIzaSyAQmrgCPOJ8d2CaEpPb6SfmFFASPZRH2tM";
    public static final String VIDEO_ID = "aJ7BoNG-r2c";


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

        //emVideoView = (VideoView)rootview.findViewById(R.id.video_view);
        YouTubePlayerView playerView = (YouTubePlayerView)rootview.findViewById(R.id.video_view);
        playerView.initialize(API_KEY,this);
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
//                fragment_sumExe parQ = new fragment_sumExe();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                fragmentManager
//                        .beginTransaction()
////                        .setCustomAnimations(R.anim.slide_up,R.anim.slide_down,R.anim.slide_up,R.anim.slide_down)
//                        .replace(R.id.activity_exercise
//                                , parQ, "fragment_parQ")
//                        .addToBackStack("fragment_parQ")
//                        .commit();
            }
        });




        return rootview;
    }

    private void setupVideoView() {

//        emVideoView.setOnPreparedListener(new OnPreparedListener() {
//            @Override
//            public void onPrepared() {
//                emVideoView.start();
//            }
//        });

        //For now we just picked an arbitrary item to play.  More can be found at
        //https://archive.org/details/more_animation
        //String part = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.vdo;


        //emVideoView.setVideoURI(Uri.parse("http://192.168.1.35:32400/library/parts/1/1490626588/file.mp4"));
        //emVideoView.setVideoURI(Uri.parse("https://d1swr4916zvh4g.cloudfront.net/media/bbb-360p.mp4"));
        //emVideoView.start();
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerState);
        youTubePlayer.setPlaybackEventListener(playBack);

        if(!b){
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    private YouTubePlayer.PlayerStateChangeListener playerState = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };

    private YouTubePlayer.PlaybackEventListener playBack = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };
}
