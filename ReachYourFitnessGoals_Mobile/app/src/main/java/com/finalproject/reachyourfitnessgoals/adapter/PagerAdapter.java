package com.finalproject.reachyourfitnessgoals.adapter;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.finalproject.reachyourfitnessgoals.fragment.fragment_calendar;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_home;
import com.finalproject.reachyourfitnessgoals.fragment.fragment_list;

/**
 * Created by Papang on 1/3/2560.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    public final int PAGE_NUM = 3;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new fragment_home();
            case 1:
                return new fragment_calendar();
            case 2:
                return new fragment_list();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }




}
