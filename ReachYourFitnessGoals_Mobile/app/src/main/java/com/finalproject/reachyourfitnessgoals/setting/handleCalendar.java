package com.finalproject.reachyourfitnessgoals.setting;

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

}
