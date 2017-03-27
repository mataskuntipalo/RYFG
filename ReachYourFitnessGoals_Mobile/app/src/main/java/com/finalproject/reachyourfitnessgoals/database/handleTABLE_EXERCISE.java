package com.finalproject.reachyourfitnessgoals.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.finalproject.reachyourfitnessgoals.models.DateData;
import com.finalproject.reachyourfitnessgoals.models.ExerciseData;
import com.finalproject.reachyourfitnessgoals.models.GoalData;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;

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
        values.put(COLUMN_VDO_ID,"ยังไม่ได้เลือกท่าออกกำลังกาย");
        values.put(COLUMN_CALORIE, 0);
        values.put(COLUMN_NOTE, "Not add note");
        values.put(COLUMN_TIME, 0.0);

        writeSQLite.insert(TABLE_EXERCISE, null, values);
    }

    public ArrayList<DateData> getDateExerciseToCalendar(){
        ArrayList<DateData> dateDataArrayList = new ArrayList<>();
        Cursor cursor = readSQLite.rawQuery("SELECT * FROM " + TABLE_EXERCISE ,null);

        if (cursor != null) {
            Log.i("cursor","cursor!=null");
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                DateData data = new DateData(cursor.getInt(1),cursor.getInt(2),cursor.getInt(3));
                dateDataArrayList.add(data);

                cursor.moveToNext();
            }
            return dateDataArrayList;
        }else {
            return null;
        }
    }

    public ExerciseData getDetailExercise(CalendarDay date){
        Cursor cursor = readSQLite.rawQuery("SELECT " + COLUMN_VDO_ID + "," + COLUMN_CALORIE+ "," + COLUMN_NOTE + "," + COLUMN_TIME + " FROM " + TABLE_EXERCISE
                + " WHERE " + COLUMN_DAY + "=" + date.getDay() + " AND "
                + COLUMN_MONTH + "=" + date.getMonth() + " AND "
                + COLUMN_YEAR + "=" + date.getYear(), null);
        cursor.moveToFirst();
         return new ExerciseData(cursor.getString(0),cursor.getInt(1),cursor.getString(2),cursor.getFloat(3));
    }
}
