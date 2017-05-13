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
    float time;

    @ParcelConstructor
    public ExerciseData(String vdo_id, int calorie, String note, float time) {
        this.vdo_id = vdo_id;
        this.calorie = calorie;
        this.note = note;
        this.time = time;
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

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
