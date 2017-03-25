package com.finalproject.reachyourfitnessgoals.models;

/**
 * Created by Papang on 25/3/2560.
 */

public class ExerciseData {
    int day,month,year,calorie;
    String vdo_id,note;
    float time;

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

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public String getVdo_id() {
        return vdo_id;
    }

    public void setVdo_id(String vdo_id) {
        this.vdo_id = vdo_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
