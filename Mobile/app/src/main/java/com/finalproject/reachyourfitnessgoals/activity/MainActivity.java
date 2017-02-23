package com.finalproject.reachyourfitnessgoals.activity;

import android.app.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_calendar;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_home;

import static android.R.attr.fragment;
import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


                fragment_home home = new fragment_home();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_main, home);
                transaction.commit();

        Log.i("hello","hello");


    }


}
