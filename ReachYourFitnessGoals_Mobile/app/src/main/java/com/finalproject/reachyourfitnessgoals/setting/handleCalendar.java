package com.finalproject.reachyourfitnessgoals.setting;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Papang on 23/3/2560.
 */

public class handleCalendar {
    public final Calendar thaiTime;
    public int currentDay;

    public handleCalendar() {
        thaiTime = new GregorianCalendar(TimeZone.getTimeZone("GMT+07:00"));
    }

    public int getCurrentDay() {
        currentDay = thaiTime.get(Calendar.DAY_OF_WEEK);
        return currentDay;
    }
}
