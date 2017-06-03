package com.finalproject.reachyourfitnessgoals.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.finalproject.reachyourfitnessgoals.models.ExerciseFromServerData;
import com.finalproject.reachyourfitnessgoals.models.GoalData;
import com.finalproject.reachyourfitnessgoals.models.vdoData;

import java.util.ArrayList;

/**
 * Created by Papang on 27/2/2560.
 */

public class handleTABLE_PROGRAM {
        private DBHelper objDBHelper;
        private SQLiteDatabase writeSQLite, readSQLite;

        public static final String TABLE_PROGRAM = "PROGRAM";
        public static final String COLUMN_TYPE_GOAL = "typeGoal";
        public static final String COLUMN_WEIGHT_GOAL = "weightGoal";
        public static final String COLUMN_TOTAL_CALORIE = "totalCalorie";
        public static final String COLUMN_KG_PER_WEEK = "kgPerWeek";


        public static final String COLUMN_YEAR_BEGIN = "year_date_begin";
        public static final String COLUMN_MONTH_BEGIN = "month_date_begin";
        public static final String COLUMN_DAY_BEGIN = "day_date_begin";

        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_PERCENT_FAT = "percentFat";
        public static final String COLUMN_PROGRAM_NAME = "programName";

        public handleTABLE_PROGRAM(Context context) {
            objDBHelper = new DBHelper(context);
            writeSQLite = objDBHelper.getWritableDatabase();
            readSQLite = objDBHelper.getReadableDatabase();
        }


        public void addProgram(GoalData data){
            ContentValues values = new ContentValues();
            values.put(COLUMN_TYPE_GOAL, 0);
            values.put(COLUMN_WEIGHT_GOAL, data.getWeightGoal());
            values.put(COLUMN_TOTAL_CALORIE, data.getTotalCalorie());
            values.put(COLUMN_KG_PER_WEEK, data.getKgPerWeek());


            values.put(COLUMN_YEAR_BEGIN , data.getYear_date_begin());
            values.put(COLUMN_MONTH_BEGIN, data.getMonth_date_begin());
            values.put(COLUMN_DAY_BEGIN, data.getDay_date_begin());

            values.put(COLUMN_STATUS, data.getStatus());
            values.put(COLUMN_PERCENT_FAT, data.getPercentFat());
            values.put(COLUMN_PROGRAM_NAME, data.getProgramName());

            writeSQLite.insert(TABLE_PROGRAM, null, values);
        }

    public void addProgramList(ArrayList<GoalData> goalDatas){
        ContentValues values = new ContentValues();
        for (GoalData data: goalDatas) {
            values.put(COLUMN_TYPE_GOAL, data.getTypeGoal());
            values.put(COLUMN_WEIGHT_GOAL, data.getWeightGoal());
            values.put(COLUMN_TOTAL_CALORIE, data.getTotalCalorie());
            values.put(COLUMN_KG_PER_WEEK, data.getKgPerWeek());

            values.put(COLUMN_YEAR_BEGIN, data.getYear_date_begin());
            values.put(COLUMN_MONTH_BEGIN, data.getMonth_date_begin());
            values.put(COLUMN_DAY_BEGIN, data.getDay_date_begin());

            values.put(COLUMN_STATUS, data.getStatus());
            values.put(COLUMN_PROGRAM_NAME, data.getProgramName());

            writeSQLite.insert(TABLE_PROGRAM, null, values);
        }
    }


        public GoalData getCurrentProgramDate(){
            GoalData data = new GoalData();
            Cursor cursor = readSQLite.rawQuery("SELECT * FROM " + TABLE_PROGRAM + " WHERE " + COLUMN_STATUS + "=" + 1,null);
            cursor.moveToFirst();

            if(cursor.getCount()>0){
                data.setTypeGoal(cursor.getInt(1));
                data.setWeightGoal(cursor.getFloat(2));
                data.setTotalCalorie(cursor.getInt(3));
                data.setKgPerWeek(cursor.getInt(4));
                data.setYear_date_begin(cursor.getInt(5));
                data.setMonth_date_begin(cursor.getInt(6));
                data.setYear_date_begin(cursor.getInt(7));
                data.setStatus(cursor.getInt(8));
                data.setPercentFat(cursor.getInt(9));
                data.setProgramName(cursor.getString(10));
                return data;
            }else {
                data.setTypeGoal(-1);
                return data;
            }
        }

    public ArrayList<GoalData> getProgramDateList(){
        ArrayList<GoalData> dataArrayList = new ArrayList<>();
        Cursor cursor = readSQLite.rawQuery("SELECT * FROM " + TABLE_PROGRAM ,null);

        if (cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                GoalData data = new GoalData();
                data.setTypeGoal(cursor.getInt(1));
                data.setWeightGoal(cursor.getFloat(2));
                data.setTotalCalorie(cursor.getInt(3));
                data.setKgPerWeek(cursor.getInt(4));
                data.setYear_date_begin(cursor.getInt(5));
                data.setMonth_date_begin(cursor.getInt(6));
                data.setYear_date_begin(cursor.getInt(7));
                data.setStatus(cursor.getInt(8));
                data.setPercentFat(cursor.getInt(9));
                data.setProgramName(cursor.getString(10));
                dataArrayList.add(data);

                cursor.moveToNext();
            }
            return dataArrayList;
        }else {
            return null;
        }
    }

    public void delete(){
        writeSQLite.execSQL("delete from "+ TABLE_PROGRAM);
    }
    }
