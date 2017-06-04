package com.finalproject.reachyourfitnessgoals.models;

import java.util.Date;

/**
 * Created by Papang on 30/5/2560.
 */

public class PersonalData {
    String f_name,l_name;
    int gender,age;
    double weight,height,activity;

    public PersonalData() {

    }

    public PersonalData(String f_name, String l_name, int gender, int age, double weight, double height, double activity) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.activity = activity;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getActivity() {
        return activity;
    }

    public void setActivity(double activity) {
        this.activity = activity;
    }
}
