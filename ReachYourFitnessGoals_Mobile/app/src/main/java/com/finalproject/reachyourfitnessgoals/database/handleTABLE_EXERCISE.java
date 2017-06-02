package com.finalproject.reachyourfitnessgoals.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.finalproject.reachyourfitnessgoals.models.DateData;
import com.finalproject.reachyourfitnessgoals.models.ExeInWeekData;
import com.finalproject.reachyourfitnessgoals.models.ExeType;
import com.finalproject.reachyourfitnessgoals.models.ExerciseData;
import com.finalproject.reachyourfitnessgoals.models.ExerciseFromServerData;
import com.finalproject.reachyourfitnessgoals.models.GoalData;
import com.finalproject.reachyourfitnessgoals.models.vdoData;
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
    public static final String COLUMN_CALORIE_IN_DAY = "calorieInDay";
    public static final String COLUMN_CALORIE_TOTAL= "calorieTotal";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_CHECK = "check_state_workout";

    public handleTABLE_EXERCISE(Context context) {
        objDBHelper = new DBHelper(context);
        writeSQLite = objDBHelper.getWritableDatabase();
        readSQLite = objDBHelper.getReadableDatabase();
    }

    public void addExercise(ExeInWeekData data){
        ContentValues values = new ContentValues();
        values.put(COLUMN_DAY, data.getDay());
        values.put(COLUMN_MONTH, data.getMonth());
        values.put(COLUMN_YEAR , data.getYear());
        values.put(COLUMN_VDO_ID,"ยังไม่ได้เลือกท่าออกกำลังกาย");
        values.put(COLUMN_CALORIE_IN_DAY, 0);
        values.put(COLUMN_CALORIE_TOTAL, data.getCalorieTotal());
        values.put(COLUMN_NOTE, "Not add note");
        values.put(COLUMN_TIME, "-");
        values.put(COLUMN_CHECK, 0);
        writeSQLite.insert(TABLE_EXERCISE, null, values);
    }

    public void addExerciseList(ArrayList<ExerciseFromServerData> serverDatas){
        ContentValues values = new ContentValues();
        for (ExerciseFromServerData data: serverDatas) {
            values.put(COLUMN_DAY, data.getDay());
            values.put(COLUMN_MONTH, data.getMonth());
            values.put(COLUMN_YEAR, data.getYear());
            values.put(COLUMN_VDO_ID, data.getVdo_id());
            values.put(COLUMN_CALORIE_IN_DAY, data.getCalorieInDay());
            values.put(COLUMN_CALORIE_TOTAL, data.getCalorieTotal());
            values.put(COLUMN_NOTE, data.getNote());
            values.put(COLUMN_TIME, data.getTime());
            values.put(COLUMN_CHECK, data.getCheck_state_workout());

            writeSQLite.insert(TABLE_EXERCISE, null, values);
        }
    }

    public ArrayList<ExerciseFromServerData> getExerciseData(){
        ArrayList<ExerciseFromServerData> datas = new ArrayList<>();
        Cursor cursor = readSQLite.rawQuery("SELECT * FROM " + TABLE_EXERCISE ,null);

        if (cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                datas.add(new ExerciseFromServerData(cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4)
                ,cursor.getInt(5),cursor.getInt(6),cursor.getString(7),cursor.getString(8),cursor.getInt(9)));

                cursor.moveToNext();
            }
            return datas;
        }else {
            return null;
        }
    }

    public ArrayList<DateData> getDateExerciseToCalendar(){
        ArrayList<DateData> dateDataArrayList = new ArrayList<>();
        Cursor cursor = readSQLite.rawQuery("SELECT * FROM " + TABLE_EXERCISE ,null);

        if (cursor != null) {
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
        Cursor cursor = readSQLite.rawQuery("SELECT " + COLUMN_VDO_ID + "," + COLUMN_CALORIE_IN_DAY+ "," + COLUMN_NOTE + "," + COLUMN_TIME + "," + COLUMN_CHECK + " FROM " + TABLE_EXERCISE
                + " WHERE " + COLUMN_DAY + "=" + date.getDay() + " AND "
                + COLUMN_MONTH + "=" + date.getMonth() + " AND "
                + COLUMN_YEAR + "=" + date.getYear(), null);
        Log.i("cursor",cursor.getCount()+"");
        if(cursor.getCount() == 0){
            return null;
        }else{
            cursor.moveToFirst();
            return new ExerciseData(cursor.getString(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));
        }
    }

    public String getVdoInDay(DateData date){
        Cursor cursor = readSQLite.rawQuery("SELECT " + COLUMN_VDO_ID + " FROM " + TABLE_EXERCISE
                + " WHERE " + COLUMN_DAY + "=" + date.getDay() + " AND "
                + COLUMN_MONTH + "=" + date.getMonth() + " AND "
                + COLUMN_YEAR + "=" + date.getYear(), null);
        Log.i("cursor",cursor.getCount()+"");
        if(cursor.getCount() == 0){
            return null;
        }else{
            cursor.moveToFirst();
            return cursor.getString(0);
        }
    }

    public int getTotalCalorieInDay(DateData date){
        Cursor cursor = readSQLite.rawQuery("SELECT " + COLUMN_CALORIE_TOTAL  + " FROM " + TABLE_EXERCISE
                + " WHERE " + COLUMN_DAY + "=" + date.getDay() + " AND "
                + COLUMN_MONTH + "=" + date.getMonth() + " AND "
                + COLUMN_YEAR + "=" + date.getYear(), null);
        Log.i("cursor",cursor.getCount()+"");
        if(cursor.getCount() == 0){
            return -1;
        }else{
            cursor.moveToFirst();
            Log.i("cursor",cursor.getInt(0)+"");
            Log.i("cursor1",date.getDay()+"");
            Log.i("cursor1",date.getMonth()+"");
            Log.i("cursor1",date.getYear()+"");
            return cursor.getInt(0);
        }
    }

    public int getCalorieInDay(DateData date){
        Cursor cursor = readSQLite.rawQuery("SELECT " + COLUMN_CALORIE_IN_DAY  + " FROM " + TABLE_EXERCISE
                + " WHERE " + COLUMN_DAY + "=" + date.getDay() + " AND "
                + COLUMN_MONTH + "=" + date.getMonth() + " AND "
                + COLUMN_YEAR + "=" + date.getYear(), null);
        Log.i("cursor",cursor.getCount()+"");
        if(cursor.getCount() == 0){
            return -1;
        }else{
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
    }

    public void addExeInDay(String vdo_id , int calorie , DateData data){
        ContentValues args = new ContentValues();
        args.put(COLUMN_VDO_ID, vdo_id);
        args.put(COLUMN_CALORIE_IN_DAY, calorie);
        writeSQLite.update(TABLE_EXERCISE, args, COLUMN_DAY + "=" + data.getDay()
                + " AND " + COLUMN_MONTH + "=" + data.getMonth()
                + " AND " + COLUMN_YEAR + "=" + data.getYear() , null);

//        Cursor cursor = readSQLite.rawQuery("UPDATE " + TABLE_EXERCISE + " SET "
//                + COLUMN_VDO_ID + " = " + vdo_id + " , "
//                + COLUMN_CALORIE_IN_DAY + "=" + calorie
//                + " WHERE " + COLUMN_DAY + "=" + data.getDay()
//                + " AND " + COLUMN_MONTH + "=" + data.getMonth()
//                + " AND " + COLUMN_YEAR + "=" + data.getYear() + ";");
    }

    public void updateTimeAndCalorie(String time,int calorie, DateData data){
        ContentValues args = new ContentValues();
        args.put(COLUMN_TIME, time);
        args.put(COLUMN_CALORIE_IN_DAY, calorie);
        args.put(COLUMN_CHECK, ExerciseData.WORKOUT_FINISH);
        writeSQLite.update(TABLE_EXERCISE, args, COLUMN_DAY + "=" + data.getDay()
                + " AND " + COLUMN_MONTH + "=" + data.getMonth()
                + " AND " + COLUMN_YEAR + "=" + data.getYear() , null);
    }

    public void delete(){
        writeSQLite.execSQL("delete from "+ TABLE_EXERCISE);
    }
}
