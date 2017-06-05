package com.finalproject.reachyourfitnessgoals.setting;

import android.app.Activity;
import android.util.Log;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_EXERCISE;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PROGRAM;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_VDO;
import com.finalproject.reachyourfitnessgoals.models.DateData;
import com.finalproject.reachyourfitnessgoals.models.ExeForGlobalData;
import com.finalproject.reachyourfitnessgoals.models.ExeInWeekData;
import com.finalproject.reachyourfitnessgoals.models.ExeType;
import com.finalproject.reachyourfitnessgoals.models.ExerciseData;
import com.finalproject.reachyourfitnessgoals.models.GlobalData;
import com.finalproject.reachyourfitnessgoals.models.GoalData;
import com.finalproject.reachyourfitnessgoals.models.vdoData;

import java.util.ArrayList;

/**
 * Created by Papang on 6/5/2560.
 */

public class SetUpCalorieAndExe {
    int id;
    int maxCalorieInDay;
    int calorie;
    DateData date;
    private handleTABLE_EXERCISE handleTABLE_exercise;
    private handleTABLE_VDO handleTABLE_vdo;
    private handleTABLE_PROGRAM handleTABLE_program;
    private GoalData goalData;
    private ArrayList<ExeForGlobalData> exeDataList;

    public SetUpCalorieAndExe(Activity activity) {
        date = ((GlobalData)activity.getApplication()).getDateData();
        handleTABLE_exercise = new handleTABLE_EXERCISE(activity);
        handleTABLE_vdo = new handleTABLE_VDO(activity);
        handleTABLE_program = new handleTABLE_PROGRAM(activity);
        goalData = handleTABLE_program.getCurrentProgramDate();
        maxCalorieInDay = handleTABLE_exercise.getTotalCalorieInDay(((GlobalData)activity.getApplication()).getDateData());
        exeDataList = ((GlobalData)activity.getApplication()).getExeForGlobalData();
    }

    public int getMaxCalorieForEachStep(String keyType , int id) {
        int tempMaxCalorie = maxCalorieInDay;

        if(goalData.getTypeGoal() == ExeType.TYPE_PROGRAM_WEIGHT) {
            switch (keyType) {
                case ExeType.TYPE_STRETCHING:
                    tempMaxCalorie = (maxCalorieInDay * 10) / 100;
                    break;
                case ExeType.TYPE_WARMUP:
                    if (id == R.id.type_1 || id == 1) {
                        tempMaxCalorie = (maxCalorieInDay * 20) / 100;
                    } else {
                        tempMaxCalorie = (maxCalorieInDay * 30) / 100;
                    }
                    break;
                case ExeType.TYPE_STRENGTH:
                    tempMaxCalorie = (maxCalorieInDay * 30) / 100;
                    break;
            }
        }else{
                switch (keyType){
                    case ExeType.TYPE_STRETCHING:
                        tempMaxCalorie = (maxCalorieInDay*10)/100;
                        break;
                    case ExeType.TYPE_WARMUP :
                        if(id == R.id.type_1 || id == 1){
                            tempMaxCalorie = (maxCalorieInDay*20)/100;
                        }else{
                            tempMaxCalorie = (maxCalorieInDay*30)/100;
                        }
                        break;
                    case ExeType.TYPE_STRENGTH :
                        tempMaxCalorie = (maxCalorieInDay*30)/100;
                        break;
                }
        }
        return tempMaxCalorie;
    }

    public int getExeForGlobalDataId(int keyId){
        switch (keyId) {
            case R.id.type_0:
                id = 0;
                break;
            case R.id.type_1:
                id = 1;
                break;
            case R.id.type_2:
                id = 2;
                break;
            case R.id.type_3:
                id = 3;
                break;
            case R.id.type_4:
                id = 4;
                break;
        }
        return id;
    }

    public int getMaxCalorieInDay() {
        return maxCalorieInDay;
    }

    public ArrayList<ExeForGlobalData> getExeDataList() {
        return exeDataList;
    }

    public String getVdoID(){
        String id = "";
        for (int i = 0 ; i<exeDataList.size(); i++){
            for(int j = 0 ; j < exeDataList.get(i).getUserSelectDatas().size(); j++){
                if(i == 0 & j == 0){
                    id = exeDataList.get(i).getUserSelectDatas().get(j).getVdo_id();
                }
                id = id + " " + exeDataList.get(i).getUserSelectDatas().get(j).getVdo_id();
                Log.i("ListDada0",id);
            }
        }
        return id;
    }

    public void addExeInDay(int calorie){
        handleTABLE_exercise.addExeInDay(getVdoID(),calorie,date);
    }


}
