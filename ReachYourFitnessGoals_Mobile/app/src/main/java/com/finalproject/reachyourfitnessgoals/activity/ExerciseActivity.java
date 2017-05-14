package com.finalproject.reachyourfitnessgoals.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_customExe_list;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_signUp;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_sumExe;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ExerciseActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "AIzaSyAQmrgCPOJ8d2CaEpPb6SfmFFASPZRH2tM";
    public static final String VIDEO_CAMEL = "XX2dMSkWK5w";
    public static final String VIDEO_JUMP = "ZGXolV0Wbis";
    public static final String VIDEO_BICYCLE = "giSLDybrOmQ";

    YouTubePlayer player;
    Chronometer chronometer;
    long lastPause;
    ImageButton doneButton;
    int countExe;
    TextView exeName;
    String[] nameArray;
    String timeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        nameArray = new String[]{"Camel", "Jump On The Spot" , "Bicycle Crunch"};

        YouTubePlayerView playerView = (YouTubePlayerView)findViewById(R.id.video_view);
        playerView.initialize(API_KEY,this);

        chronometer = (Chronometer)findViewById(R.id.Chronometer);
        Button start = (Button) findViewById(R.id.start);
        final ImageButton stop = (ImageButton) findViewById(R.id.stop);
        exeName = (TextView)findViewById(R.id.exeName_TextView_exe);

        countExe = 0;
        lastPause = 0;


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.play();
                chronometer.start();
                v.setVisibility(View.GONE);
                doneButton.setVisibility(View.VISIBLE);
                stop.setVisibility(View.VISIBLE);
            }
        });

        doneButton = (ImageButton)findViewById(R.id.done_ImageButton_exe);
        doneButton.setOnClickListener(finishExe);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lastPause != 0) {
                    chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - lastPause);
                    chronometer.start();
                    player.play();
                    lastPause = 0;
                }else {
                    lastPause = SystemClock.elapsedRealtime();
                    chronometer.stop();
                    player.pause();
                }
                //chronometer.setBase(SystemClock.elapsedRealtime());
            }
        });
    }

    private View.OnClickListener finishExe = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(countExe == 0){
                player.loadVideo(VIDEO_JUMP);
                exeName.setText(nameArray[1]);
                countExe++;
            }else if(countExe == 1){
                player.loadVideo(VIDEO_BICYCLE);
                exeName.setText(nameArray[2]);
                countExe++;
            }else {
                chronometer.stop();
                setTimeToString();
                RelativeLayout layout = (RelativeLayout)findViewById(R.id.exeSummary_exe);
                layout.setVisibility(View.VISIBLE);
                fragment_sumExe a = fragment_sumExe.newInstance(timeString);
                getFragmentManager().beginTransaction().replace(R.id.exeSummary_exe, a).commit();
            }
        }
    };

    public void setTimeToString(){
        long timeElapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
        int hours = (int) (timeElapsed / 3600000);
        int minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        int seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;
        timeString = minutes + " : " + seconds;
        Toast.makeText(this, timeString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerState);
        youTubePlayer.setPlaybackEventListener(playBack);
        player = youTubePlayer;
        if(!b){
            youTubePlayer.cueVideo(VIDEO_CAMEL);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
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
            player.seekToMillis(0);
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

//    @Override // ต้องใส่อันนี้ถึงจะเปลี่ยนฟ้อน
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }
}
