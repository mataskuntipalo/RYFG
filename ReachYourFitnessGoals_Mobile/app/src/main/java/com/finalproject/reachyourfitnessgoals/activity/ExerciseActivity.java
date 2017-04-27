package com.finalproject.reachyourfitnessgoals.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_exercise;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_signUp;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        fragment_exercise exercise = new fragment_exercise();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_exercise, exercise);
        transaction.addToBackStack("fragment_exercise");
        transaction.commit();
    }
}
