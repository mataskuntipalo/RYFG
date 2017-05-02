package com.finalproject.reachyourfitnessgoals.models;

/**
 * Created by Papang on 22/4/2560.
 */

public class userSelectData {
    String vdo_id,name;
    int calories;
    int id;

    public userSelectData(String name, int calories , String vdo_id) {
        this.name = name;
        this.calories = calories;
        this.vdo_id = vdo_id;
    }

    public String getVdo_id() {
        return vdo_id;
    }

    public void setVdo_id(String vdo_id) {
        this.vdo_id = vdo_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
