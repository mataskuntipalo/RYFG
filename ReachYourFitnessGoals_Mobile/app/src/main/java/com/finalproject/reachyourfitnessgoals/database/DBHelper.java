package com.finalproject.reachyourfitnessgoals.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Papang on 12/2/2560.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ReachYourFitnessGoals";
    private static final int DB_VERSION = 1;
    private static final String TABLE_PERSONAL = "CREATE TABLE PERSONAL (user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "f_name TEXT NOT NULL, " +
            "l_name TEXT NOT NULL, " +
            "age INTEGER NOT NULL, " +
            "gender TEXT NOT NULL, " +
            "birthday TEXT NOT NULL, " +
            "weight INTEGER NOT NULL," +
            "height  INTEGER NOT NULL," +
            "pressure_min  INTEGER," +
            "pressure_max INTEGER);";

    private static final String TABLE_PROGRAM = "CREATE TABLE PROGRAM (program_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "weightGoal INTEGER NOT NULL, " +
            "totalDuration INTEGER NOT NULL, " +
            "year_date_begin INTEGER NOT NULL, " +
            "month_date_begin INTEGER NOT NULL, " +
            "day_date_begin INTEGER NOT NULL, " +
            "year_date_end INTEGER NOT NULL, " +
            "month_date_end INTEGER NOT NULL, " +
            "day_date_end INTEGER NOT NULL);";




    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_PERSONAL);
        db.execSQL(TABLE_PROGRAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS PERSONAL");
        db.execSQL("DROP TABLE IF EXISTS PROGRAM");
        onCreate(db);
    }
}
