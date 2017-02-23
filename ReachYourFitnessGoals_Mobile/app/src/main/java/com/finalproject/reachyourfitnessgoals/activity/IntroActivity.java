package com.finalproject.reachyourfitnessgoals.activity;

import android.app.Activity;
import android.os.Bundle;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_intro_slideEnd;
import com.finalproject.reachyourfitnessgoals.setting.SampleSlide;
import com.github.paolorotolo.appintro.AppIntro;

public class IntroActivity extends AppIntro {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add your slide's fragments here
        // AppIntro will automatically generate the dots indicator and buttons.
        fragment_intro_slideEnd slideEnd = new fragment_intro_slideEnd();
        addSlide(SampleSlide.newInstance(R.layout.intro_slide1));
        addSlide(SampleSlide.newInstance(R.layout.fragment_intro_slide_end));




        // SHOW or HIDE the statusbar
        showStatusBar(true);

        // Hide Skip/Done button
        showSkipButton(false);




    }

    @Override
    public void onSkipPressed() {
        // Do something when users tap on Skip button.
        finish();
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        finish();
    }

    @Override
    public void onSlideChanged() {
        // Do something when slide is changed
    }
}

