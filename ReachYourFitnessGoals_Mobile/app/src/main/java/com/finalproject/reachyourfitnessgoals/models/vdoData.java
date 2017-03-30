package com.finalproject.reachyourfitnessgoals.models;

/**
 * Created by Papang on 29/3/2560.
 */

public class vdoData {
    String name,type,position;
    int calorie;

    public vdoData(String name, String type, String position, int calorie) {
        this.name = name;
        this.type = type;
        this.position = position;
        this.calorie = calorie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }
}
