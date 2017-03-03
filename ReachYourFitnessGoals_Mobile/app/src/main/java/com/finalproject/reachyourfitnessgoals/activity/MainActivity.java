package com.finalproject.reachyourfitnessgoals.activity;



import android.app.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.adapter.PagerAdapter;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_calendar;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_home;

import static android.R.attr.fragment;
import static android.R.attr.id;



public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    ViewPager pager;
    PagerAdapter adapter;
    String PageName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        adapter = new PagerAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.pager_ViewPager_main);
        pager.setAdapter(adapter);

        for (int i = 0; i < adapter.PAGE_NUM; i++) {
            if(i == 0){
                PageName = "หน้าหลัก";
            }else if(i == 1){
                PageName = "การออกกำลังกาย";
            }else{
                PageName = "ท่าออกกำลังกาย";
            }
            actionBar.addTab(actionBar.newTab()
                    .setText(PageName)
                    .setTabListener(this));
        }

        // set tab bar change when swipe
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    // set page change when swipe
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }


//                fragment_home home = new fragment_home();
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.activity_main, home);
//                transaction.commit();




}
