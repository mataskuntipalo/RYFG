package com.finalproject.reachyourfitnessgoals.models;

import java.sql.Date;

/**
 * Created by Papang on 23/2/2560.
 */

public class GoalData {
    int typeGoal;
    int kgPerWeek;
    int totalDuration;
    float weightGoal;
    int day_date_begin;
    int month_date_begin;
    int year_date_begin;
    int status;
    String programName;


    public int getTypeGoal() {
        return typeGoal;
    }

    public void setTypeGoal(int typeGoal) {
        this.typeGoal = typeGoal;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
