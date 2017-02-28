package com.finalproject.reachyourfitnessgoals.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.finalproject.reachyourfitnessgoals.models.GoalData;

/**
 * Created by Papang on 27/2/2560.
 */

public class handleTABLE_PROGRAM {
        private DBHelper objDBHelper;
        private SQLiteDatabase writeSQLite, readSQLite;

        public static final String TABLE_PROGRAM = "PROGRAM";
        public static final String COLUMN_WEIGHT_GOAL = "weightGoal";
        public static final String COLUMN_TOTAL_DURATION = "totalDuration";


        public static final String COLUMN_YEAR_BEGIN = "year_date_begin";
        public static final String COLUMN_MONTH_BEGIN = "month_date_begin";
        public static final String COLUMN_DAY_BEGIN = "day_date_begin";


        public static final String COLUMN_YEAR_END = "year_date_end";
        public static final String COLUMN_MONTH_END = "month_date_end";
        public static final String COLUMN_DAY_END = "day_date_end";

        public handleTABLE_PROGRAM(Context context) {
            objDBHelper = new DBHelper(context);
            writeSQLite = objDBHelper.getWritableDatabase();
            readSQLite = objDBHelper.getReadableDatabase();
        }


        public void addProgram(GoalData data){
            ContentValues values = new ContentValues();
            values.put(COLUMN_WEIGHT_GOAL, data.getWeightGoal());
            values.put(COLUMN_TOTAL_DURATION, data.getTotalDuration());


            values.put(COLUMN_YEAR_BEGIN , data.getYear_date_begin());
            values.put(COLUMN_MONTH_BEGIN, data.getMonth_date_begin());
            values.put(COLUMN_DAY_BEGIN, data.getDay_date_begin());


            values.put(COLUMN_YEAR_END, data.getYear_date_end());
            values.put(COLUMN_MONTH_END, data.getMonth_date_end());
            values.put(COLUMN_DAY_END, data.getDay_date_end());

            writeSQLite.insert(TABLE_PROGRAM, null, values);
        }


        public GoalData getDateToCalendar(){
            GoalData data = new GoalData();
            Cursor cursor = readSQLite.rawQuery("SELECT * FROM " + TABLE_PROGRAM ,null);
            cursor.moveToLast();

            data.setWeightGoal(cursor.getFloat(1));
            data.setTotalDuration(cursor.getInt(2));
            data.setYear_date_begin(cursor.getInt(3));
            data.setMonth_date_begin(cursor.getInt(4));
            data.setYear_date_begin(cursor.getInt(5));
            data.setYear_date_end(cursor.getInt(6));
            data.setMonth_date_end(cursor.getInt(7));
            data.setYear_date_end(cursor.getInt(8));
            return data;
        }
    }
