package com.finalproject.reachyourfitnessgoals.models;

import android.app.Application;

import com.finalproject.reachyourfitnessgoals.models.DateData;

import java.util.ArrayList;

/**
 * Created by Papang on 28/4/2560.
 */

public class GlobalData extends Application {

    private DateData dateData;
    private ArrayList<ExeForGlobalData> exeForGlobalData;

    @Override
    public void onCreate() {
        super.onCreate();
        exeForGlobalData = new ArrayList<>();
    }


    public DateData getDateData() {
        return dateData;
    }

    public void setDateData(DateData dateData) {
        this.dateData = dateData;
    }

    public ArrayList<ExeForGlobalData> getExeForGlobalData() {
        return exeForGlobalData;
    }

    public void addExeForGlobalData(ExeForGlobalData exeForGlobalData) {
        this.exeForGlobalData.add(exeForGlobalData);
    }
}

