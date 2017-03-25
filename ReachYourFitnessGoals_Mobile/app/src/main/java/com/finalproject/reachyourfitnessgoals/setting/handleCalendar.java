package com.finalproject.reachyourfitnessgoals.setting;

import android.util.Log;

import com.finalproject.reachyourfitnessgoals.models.DateData;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Papang on 23/3/2560.
 */

public class handleCalendar {
    public Calendar thaiTime;
    public int currentDay;
    public long currentTime;
    public int day,month,year;
    ArrayList<DateData> dateDataArrayList;

    public handleCalendar() {
        thaiTime = Calendar.getInstance();
        //thaiTime = new GregorianCalendar(TimeZone.getTimeZone("GMT+07:00"));
    }

    public int getCurrentDay() {
        currentDay = thaiTime.get(Calendar.DAY_OF_WEEK);
        return currentDay;
    }

    public long getTimeToMidnight() {
        Calendar temp = Calendar.getInstance();
        temp.set(Calendar.HOUR_OF_DAY, 0);
        temp.set(Calendar.MINUTE, 0);
        temp.set(Calendar.SECOND, 0);
        return (System.currentTimeMillis()-temp.getTimeInMillis());
    }

    public int getDay(){
        //day = thaiTime.get(Calendar.DAY_OF_MONTH);
        //day = thaiTime.set(Calendar.DAY_OF_MONTH,1);
        return day;
    }

    public int getMonth(){
        month = thaiTime.get(Calendar.MONTH)+1;
        return month;
    }

    public int getYear(){
        year = thaiTime.get(Calendar.YEAR);
        return year;
    }

    public ArrayList<DateData> getAllDayInWeek(){
        dateDataArrayList = new ArrayList<>();
        Calendar tempCalendar = Calendar.getInstance();
        int delta = -thaiTime.get(Calendar.DAY_OF_WEEK) + 2; //add 2 if your week start on monday
        tempCalendar.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 1; i <= 7; i++)
        {
            DateData dateData = new DateData();
            dateData.setDay(tempCalendar.get(Calendar.DAY_OF_MONTH));
            dateData.setMonth(tempCalendar.get(Calendar.MONTH)+1);
            dateData.setYear(tempCalendar.get(Calendar.YEAR));
            dateDataArrayList.add(dateData);
            tempCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return dateDataArrayList;
    }
}
