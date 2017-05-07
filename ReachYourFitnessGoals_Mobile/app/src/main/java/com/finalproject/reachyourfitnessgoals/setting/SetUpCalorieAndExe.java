package com.finalproject.reachyourfitnessgoals.setting;

import android.app.Activity;

import com.finalproject.reachyourfitnessgoals.R;
import com.finalproject.reachyourfitnessgoals.database.handleTABLE_EXERCISE;
import com.finalproject.reachyourfitnessgoals.models.ExeType;
import com.finalproject.reachyourfitnessgoals.models.GlobalData;

/**
 * Created by Papang on 6/5/2560.
 */

public class SetUpCalorieAndExe {
    int id;
    int maxCalorie;
    int calorie;
    private handleTABLE_EXERCISE handleTABLE_exercise;

    public SetUpCalorieAndExe(Activity activity) {
        handleTABLE_exercise = new handleTABLE_EXERCISE(activity);
        maxCalorie = handleTABLE_exercise.getTotalCalorieInDay(((GlobalData)activity.getApplication()).getDateData());
    }

    public int getMaxCalorieForEachStep(String keyType , int id) {
        int tempMaxCalorie = maxCalorie;
        switch (keyType){
            case ExeType.TYPE_STRETCHING:
                tempMaxCalorie = (maxCalorie*10)/100;
                break;
            case ExeType.TYPE_WARMUP :
                if(id == R.id.type_1 || id == 1){
                    tempMaxCalorie = (maxCalorie*20)/100;
                }else{
                    tempMaxCalorie = (maxCalorie*30)/100;
                }
                break;
            case ExeType.TYPE_STRENGTH :
                tempMaxCalorie = (maxCalorie*30)/100;
                break;
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

    public int getMaxCalorie() {
        return maxCalorie;
    }
}
