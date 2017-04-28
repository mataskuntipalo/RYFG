package com.finalproject.reachyourfitnessgoals.setting;

import android.app.Application;

/**
 * Created by Papang on 28/4/2560.
 */

public class GlobalDate extends Application {

        private int day;
        private int month;
        private int year;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

