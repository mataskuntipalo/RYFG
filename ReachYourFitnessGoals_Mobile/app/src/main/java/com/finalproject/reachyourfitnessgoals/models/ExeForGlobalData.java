package com.finalproject.reachyourfitnessgoals.models;

import java.util.ArrayList;

/**
 * Created by Papang on 30/4/2560.
 */

public class ExeForGlobalData {
    int id;
    String type;
    ArrayList<userSelectData> userSelectDatas;

    public ExeForGlobalData(String type, ArrayList<userSelectData> userSelectDatas) {
        this.type = type;
        this.userSelectDatas = userSelectDatas;
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

    public ArrayList<userSelectData> getUserSelectDatas() {
        return userSelectDatas;
    }

    public void setUserSelectDatas(ArrayList<userSelectData> userSelectDatas) {
        this.userSelectDatas = userSelectDatas;
    }
}
