package com.finalproject.reachyourfitnessgoals.models;

import java.util.Date;

/**
 * Created by Papang on 30/5/2560.
 */

public class PersonalData {
    String f_Name,l_Name;
    int gender,age;
    double weight,height;

    public PersonalData(String f_Name, String l_Name, int gender, int age, double weight, double height) {
        this.f_Name = f_Name;
        this.l_Name = l_Name;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public String getF_Name() {
        return f_Name;
    }

    public void setF_Name(String f_Name) {
        this.f_Name = f_Name;
    }

    public String getL_Name() {
        return l_Name;
    }

    public void setL_Name(String l_Name) {
        this.l_Name = l_Name;
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
