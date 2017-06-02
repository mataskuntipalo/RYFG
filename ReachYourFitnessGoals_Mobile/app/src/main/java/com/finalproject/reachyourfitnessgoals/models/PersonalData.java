package com.finalproject.reachyourfitnessgoals.models;

import java.util.Date;

/**
 * Created by Papang on 30/5/2560.
 */

public class PersonalData {
    String f_name,l_name;
    int gender,age;
    double weight,height;

    public PersonalData() {

    }

    public PersonalData(String f_name, String l_name, int age, int gender, double weight, double height) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public String getF_Name() {
        return f_name;
    }

    public void setF_Name(String f_Name) {
        this.f_name = f_Name;
    }

    public String getL_Name() {
        return l_name;
    }

    public void setL_Name(String l_Name) {
        this.l_name = l_Name;
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

}
