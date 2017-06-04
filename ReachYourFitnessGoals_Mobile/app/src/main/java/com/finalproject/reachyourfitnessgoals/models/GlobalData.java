package com.finalproject.reachyourfitnessgoals.models;

import android.app.Application;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PROGRAM;
import com.finalproject.reachyourfitnessgoals.models.DateData;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Papang on 28/4/2560.
 */

public class GlobalData extends Application {

    private DateData dateData;
    private ArrayList<ExeForGlobalData> exeForGlobalData;
    public String[] type = {"Stretching","Warm Up","Strength","Cardio","Cool Down"};
    private String email;
    private String pass;


    @Override
    public void onCreate() {
        super.onCreate();

        email="";
        pass="";

        //init Font
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/thaisanslite.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


        exeForGlobalData = new ArrayList<>();
        for (int i = 0; i < type.length; i++) {
            addExeForGlobalData(new ExeForGlobalData(type[i], new ArrayList<userSelectData>()));
            exeForGlobalData.get(i).setId(i);
            int tempSize = exeForGlobalData.get(i).getUserSelectDatas().size();
            for (int j = 0; j < tempSize; j++) {
                exeForGlobalData.get(i).getUserSelectDatas().get(j).setId(j);
            }
        }
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
        exeForGlobalData.get(id).setMaxCalorieInType(maxCalorie);
        exeForGlobalData.get(id).setCalorieInType(calorie);
    }

    public void resetData(){
        for (int i = 0 ; i < type.length ; i++){
            exeForGlobalData.get(i).getUserSelectDatas().clear();
            exeForGlobalData.get(i).setMaxCalorieInType(0);
            exeForGlobalData.get(i).setCalorieInType(0);
        }
    }

}

