package com.finalproject.reachyourfitnessgoals.setting;

import android.app.Application;

import com.finalproject.reachyourfitnessgoals.models.DateData;

/**
 * Created by Papang on 28/4/2560.
 */

public class GlobalDate extends Application {

        private DateData dateData;

    public DateData getDateData() {
        return dateData;
    }

    public void setDateData(DateData dateData) {
        this.dateData = dateData;
    }
}

