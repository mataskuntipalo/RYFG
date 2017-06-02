package com.finalproject.reachyourfitnessgoals.models;

/**
 * Created by Papang on 2/6/2560.
 */

public class ExerciseFromServerData {
    int day,month,year;
    String vdo_id;
    int calorieInDay,calorieTotal;
    String note,time;
    int check_state_workout;

    public ExerciseFromServerData(int day, int month, int year, String vdo_id, int calorieInDay, int calorieTotal, String note, String time, int check_state_workout) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.vdo_id = vdo_id;
        this.calorieInDay = calorieInDay;
        this.calorieTotal = calorieTotal;
        this.note = note;
        this.time = time;
        this.check_state_workout = check_state_workout;
    }

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

    public int getCalorieInDay() {
        return calorieInDay;
    }

    public void setCalorieInDay(int calorieInDay) {
        this.calorieInDay = calorieInDay;
    }

    public int getCalorieTotal() {
        return calorieTotal;
    }

    public void setCalorieTotal(int calorieTotal) {
        this.calorieTotal = calorieTotal;
    }

    public int getCheck_state_workout() {
        return check_state_workout;
    }

    public void setCheck_state_workout(int check_state_workout) {
        this.check_state_workout = check_state_workout;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
