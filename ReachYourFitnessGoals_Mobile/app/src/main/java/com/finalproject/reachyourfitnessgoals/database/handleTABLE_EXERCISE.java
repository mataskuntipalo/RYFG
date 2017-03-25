package com.finalproject.reachyourfitnessgoals.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.finalproject.reachyourfitnessgoals.models.DateData;
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

    public void addExercise(DateData data){
        ContentValues values = new ContentValues();
        values.put(COLUMN_DAY, data.getDay());
        values.put(COLUMN_MONTH, data.getMonth());
        values.put(COLUMN_YEAR , data.getYear());
        values.put(COLUMN_VDO_ID,"Not add vdo");
        values.put(COLUMN_CALORIE, 0);
        values.put(COLUMN_NOTE, "Not add note");
        values.put(COLUMN_TIME, 0.0);

        writeSQLite.insert(TABLE_EXERCISE, null, values);
    }
}
