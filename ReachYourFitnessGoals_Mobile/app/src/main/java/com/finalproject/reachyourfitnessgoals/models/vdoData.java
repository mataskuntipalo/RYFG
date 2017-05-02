package com.finalproject.reachyourfitnessgoals.models;

/**
 * Created by Papang on 29/3/2560.
 */

public class vdoData {
    String vdo_id,name,type,position,duration;
    int calorie;

    public vdoData(String name, String type, String position, String duration, int calorie) {
        this.name = name;
        this.type = type;
        this.position = position;
        this.duration = duration;
        this.calorie = calorie;
    }

    public vdoData(int vdo_id,String name, String type, String position, String duration, int calorie) {
        this.vdo_id = vdo_id+"";
        this.name = name;
        this.type = type;
        this.position = position;
        this.duration = duration;
        this.calorie = calorie;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }
}
