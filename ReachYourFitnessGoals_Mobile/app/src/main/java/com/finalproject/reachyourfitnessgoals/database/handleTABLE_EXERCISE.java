package com.finalproject.reachyourfitnessgoals.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.finalproject.reachyourfitnessgoals.models.GoalData;

/**
 * Created by Papang on 25/3/2560.
 */

public class handleTABLE_EXERCISE {
    private DBHelper objDBHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String TABLE_EXERCISE = "EXERCISE";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_MONTH = "month";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_VDO_ID = "vdo_id";
    public static final String COLUMN_CALORIE = "calorie";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_TIME = "time";

    public handleTABLE_EXERCISE(Context context) {
        objDBHelper = new DBHelper(context);
        writeSQLite = objDBHelper.getWritableDatabase();
        readSQLite = objDBHelper.getReadableDatabase();
    }

    public void addExercise(GoalData data){
        ContentValues values = new ContentValues();
        values.put(COLUMN_DAY, data.getWeightGoal());
        values.put(COLUMN_MONTH, data.getTotalDuration());
        values.put(COLUMN_YEAR , data.getYear_date_begin());
        values.put(COLUMN_VDO_ID, data.getMonth_date_begin());
        values.put(COLUMN_CALORIE, data.getDay_date_begin());
        values.put(COLUMN_NOTE, data.getYear_date_end());
        values.put(COLUMN_TIME, data.getMonth_date_end());

        writeSQLite.insert(TABLE_EXERCISE, null, values);
    }
}
