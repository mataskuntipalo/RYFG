package com.finalproject.reachyourfitnessgoals.models;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by Papang on 25/3/2560.
 */
@Parcel
public class ExerciseData {
    int calorie;
    String vdo_id,note;
    String time;
    int checkState;
    public static final int WORKOUT_NOT_FINISH = 0;
    public static final int WORKOUT_FINISH = 1;

    @ParcelConstructor
    public ExerciseData(String vdo_id, int calorie, String note, String time,int checkState) {
        this.vdo_id = vdo_id;
        this.calorie = calorie;
        this.note = note;
        this.time = time;
        this.checkState = checkState;
    }

    public ExerciseData(int calorie, String vdo_id) {
        this.calorie = calorie;
        this.vdo_id = vdo_id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCheckState() {
        return checkState;
    }

    public void setCheckState(int checkState) {
        this.checkState = checkState;
    }
}
