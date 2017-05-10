package com.finalproject.reachyourfitnessgoals.models;

import java.util.ArrayList;

/**
 * Created by Papang on 30/4/2560.
 */

public class ExeForGlobalData {
    int id;
    String type;
    int calorieInType , maxCalorieInType;
    ArrayList<userSelectData> userSelectDatas;

    public ExeForGlobalData(String type, ArrayList<userSelectData> userSelectDatas) {
        this.type = type;
        this.userSelectDatas = userSelectDatas;
        this.calorieInType = 0;
        this.maxCalorieInType = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCalorieInType() {
        return calorieInType;
    }

    public void setCalorieInType(int calorieInDay) {
        this.calorieInType = calorieInDay;
    }

    public int getMaxCalorieInType() {
        return maxCalorieInType;
    }

    public void setMaxCalorieInType(int maxCalorieInType) {
        this.maxCalorieInType = maxCalorieInType;
    }

    public ArrayList<userSelectData> getUserSelectDatas() {
        return userSelectDatas;
    }

    public void setUserSelectDatas(ArrayList<userSelectData> userSelectDatas) {
        this.userSelectDatas = userSelectDatas;
    }
}
