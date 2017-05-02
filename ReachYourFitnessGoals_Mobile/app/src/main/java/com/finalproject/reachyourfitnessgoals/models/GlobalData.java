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
    private String[] type = {"Stretching","Warm Up","Strength","Cardio","Cool Down"};

    @Override
    public void onCreate() {
        super.onCreate();
        exeForGlobalData = new ArrayList<>();
        for (int i = 0; i < type.length; i++) {
            //MyGroupItem group = new MyGroupItem(i, type[i]);
            addExeForGlobalData(new ExeForGlobalData(type[i], new ArrayList<userSelectData>()));
            exeForGlobalData.get(i).setId(i);
            int tempSize = exeForGlobalData.get(i).getUserSelectDatas().size();
            for (int j = 0; j < tempSize; j++) {
                exeForGlobalData.get(i).getUserSelectDatas().get(j).setId(j);
                //group.children.add(new MyChildItem(j, "child " + j));
            }
        }
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

    public void updataData(int id , ArrayList<userSelectData> dataArrayList , int maxCalorie , int calorie){
        exeForGlobalData.get(id).setUserSelectDatas(dataArrayList);
        exeForGlobalData.get(id).setMaxCalorie(maxCalorie);
        exeForGlobalData.get(id).setCalorie(calorie);
    }

    public String getVdoID(){
        String id = "";
        for (int i = 0 ; i<exeForGlobalData.size(); i++){
            for(int j = 0 ; j < exeForGlobalData.get(i).getUserSelectDatas().size() ; j++){
                id = id + exeForGlobalData.get(i).getUserSelectDatas().get(j).vdo_id;
            }
        }
        return id;
    }
}

