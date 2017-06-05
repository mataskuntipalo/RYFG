package com.finalproject.reachyourfitnessgoals.setting;

import android.content.Context;
import android.util.Log;

import com.finalproject.reachyourfitnessgoals.database.handleTABLE_PERSONAL;
import com.finalproject.reachyourfitnessgoals.models.GlobalData;
import com.finalproject.reachyourfitnessgoals.models.PersonalData;

/**
 * Created by SIRI_LOOKPLA on 5/6/2560.
 */

public class CalculateShape {

    public static final int MAN = 0;
    public static final int WOMEN = 1;
    private double bmr;
    private double bmi;
    private double tdee;
    private int percentFat;
    private PersonalData personalData;
    private handleTABLE_PERSONAL tablePersonal;

    public CalculateShape(Context context) {
        personalData = ((GlobalData)context.getApplicationContext()).getPersonalData();
        bmr = calBMR();
        bmi = calBMI();
        tdee = calTdee();
        percentFat = calPercentFat();
    }

    public CalculateShape(PersonalData data) {
        personalData = data;
        bmr = calBMR();
        bmi = calBMI();
        tdee = calTdee();
        percentFat = calPercentFat();
    }

    private double calBMR(){
        double bmr;
        if(personalData.getGender() == CalculateShape.MAN){
            bmr = 66+(13.7*personalData.getWeight())+(5*personalData.getHeight())-(6.8*personalData.getAge());
            Log.i("tdee1",bmr+"");
        }else {
            bmr = 665+(9.6*personalData.getWeight())+(1.8*personalData.getHeight())-(4.7*personalData.getAge());
        }
        return bmr;
    }

    private double calBMI(){
        double temp = personalData.getHeight()/100;
        return Double.parseDouble(String.format("%.2f", personalData.getWeight()/Math.pow(temp,2)));
    }

    private int calTdee(){
        Log.i("tdee",(int) (bmr*personalData.getActivity())+"");
        return (int) (bmr*personalData.getActivity());
    }

    private int calPercentFat(){
        double temp;
        if(personalData.getGender() == CalculateShape.MAN){
            temp = (1.2*bmi)+(0.23*personalData.getAge())-16.2;
        }else {
            temp = (1.2*bmi)+(0.23*personalData.getAge())-5.4;
        }
        return (int) temp;
    }

    public double getBmr() {
        return bmr;
    }

    public double getTdee() {
        return tdee;
    }

    public double getBmi() {
        return bmi;
    }

    public int getPercentFat() {
        return percentFat;
    }
}
