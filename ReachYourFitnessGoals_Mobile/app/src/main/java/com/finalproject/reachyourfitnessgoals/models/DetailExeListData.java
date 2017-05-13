package com.finalproject.reachyourfitnessgoals.models;

import java.util.ArrayList;

/**
 * Created by Papang on 14/5/2560.
 */

public class DetailExeListData {
    String type;
    ArrayList<vdoData> vdoDataArrayList;

    public DetailExeListData(String type) {
        this.type = type;
        this.vdoDataArrayList = new ArrayList<>();
    }

    public void addItem(vdoData data){
        vdoDataArrayList.add(data);
    }

    public ArrayList<vdoData> getVdoDataArrayList() {
        return vdoDataArrayList;
    }

    public String getType() {
        return type;
    }
}
