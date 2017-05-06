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

    public int setMaxCalorie(String keyType) {
        switch (keyType){
            case ExeType.TYPE_STRETCHING :
                maxCalorie = (maxCalorie*10)/100;
                break;
            case ExeType.TYPE_WARMUP :
                if(id == R.id.type_1){
                    maxCalorie = (maxCalorie*20)/100;
                }else{
                    maxCalorie = (maxCalorie*30)/100;
                }
                break;
            case ExeType.TYPE_STRENGTH :
                maxCalorie = (maxCalorie*30)/100;
                break;
        }
        return maxCalorie;
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
