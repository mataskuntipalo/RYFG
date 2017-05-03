package com.finalproject.reachyourfitnessgoals.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_home;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_intro_slideEnd;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_list;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_signUp;


import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    RelativeLayout content1;
    RelativeLayout content2;
    Button buttonAbout;
    ProgressBar progress;
    TextView forgot;
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //showIntro();
        signUp = (TextView)findViewById(R.id.signUp_text);
        forgot = (TextView)findViewById(R.id.forgetPass);
        progress = ( ProgressBar ) this.findViewById ( R.id.login_progress );
        content1 = (RelativeLayout)findViewById ( R.id.content1 );
        content2 = (RelativeLayout)findViewById ( R.id.content2 );
        buttonAbout = (Button) findViewById(R.id.loginButton);
        showView();
//        fragment_signUp myFragment = new fragment_signUp();
//
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.layout_fragment, myFragment);
//        transaction.commit();

        fragment_intro_slideEnd intro = fragment_intro_slideEnd.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_login, intro);
        transaction.addToBackStack("fragment_signUp");
        transaction.commit();


        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_signUp signUp = new fragment_signUp();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_login, signUp);
                transaction.addToBackStack("fragment_signUp");
                transaction.commit();
            }
        });
    }




    private void showView() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // Hide your View after 3 seconds
                content1.setVisibility(View.VISIBLE);
                content2.setVisibility(View.VISIBLE);
                buttonAbout.setVisibility(View.VISIBLE);
                signUp.setVisibility(View.VISIBLE);
                forgot.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
            }
        }, 2000);

    }

//    private void showIntro(){
//        //  Declare a new thread to do a preference check
////        Thread t = new Thread(new Runnable() {
////            @Override
////            public void run() {
////                //  Initialize SharedPreferences
////                SharedPreferences getPrefs = PreferenceManager
////                        .getDefaultSharedPreferences(getBaseContext());
////
////                //  Create a new boolean and preference and set it to true
////                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);
////
////                //  If the activity has never started before...
////                if (isFirstStart) {
//
//        //  Launch app intro
//        Intent i = new Intent(this, IntroActivity.class);
//        startActivity(i);
//
//                 /*   //  Make a new preferences editor
//                    SharedPreferences.Editor e = getPrefs.edit();
//
//                    //  Edit preference to make it false because we don't want this to run again
//                    e.putBoolean("firstStart", false);
//
//                    //  Apply changes
//                    e.apply();
//                }
//            }
//        });
//
//        // Start the thread
//        t.start();*/
//    }



    @Override // ต้องใส่อันนี้ถึงจะเปลี่ยนฟ้อน
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
