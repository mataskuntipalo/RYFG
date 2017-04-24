package com.finalproject.reachyourfitnessgoals.models;

/**
 * Created by Papang on 22/4/2560.
 */

public class userSelectData {
    String name;
    int calories;

    public userSelectData(String name, int calories) {
        this.name = name;
        this.calories = calories;
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
}
