package com.finalproject.reachyourfitnessgoals.models;

import java.sql.Date;

/**
 * Created by Papang on 23/2/2560.
 */

public class GoalData {
    int kgPerWeek;
    int totalDuration;
    float weightGoal;
    int day_date_begin;
    int month_date_begin;
    int year_date_begin;
    int day_date_end;
    int month_date_end;
    int year_date_end;

    public int getDay_date_begin() {
        return day_date_begin;
    }

    public void setDay_date_begin(int day_date_begin) {
        this.day_date_begin = day_date_begin;
    }

    public int getMonth_date_begin() {
        return month_date_begin;
    }

    public void setMonth_date_begin(int month_date_begin) {
        this.month_date_begin = month_date_begin;
    }

    public int getYear_date_begin() {
        return year_date_begin;
    }

    public void setYear_date_begin(int year_date_begin) {
        this.year_date_begin = year_date_begin;
    }

    public int getDay_date_end() {
        return day_date_end;
    }

    public void setDay_date_end(int day_date_end) {
        this.day_date_end = day_date_end;
    }

    public int getMonth_date_end() {
        return month_date_end;
    }

    public void setMonth_date_end(int month_date_end) {
        this.month_date_end = month_date_end;
    }

    public int getYear_date_end() {
        return year_date_end;
    }

    public void setYear_date_end(int year_date_end) {
        this.year_date_end = year_date_end;
    }

    public float getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(float weightGoal) {
        this.weightGoal = weightGoal;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }



    public int getKgPerWeek() {
        return kgPerWeek;
    }

    public void setKgPerWeek(int kgPerWeek) {
        this.kgPerWeek = kgPerWeek;
    }





}
