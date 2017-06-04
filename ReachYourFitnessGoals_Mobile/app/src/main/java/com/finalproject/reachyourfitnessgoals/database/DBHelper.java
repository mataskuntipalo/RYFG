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
    private static final String TABLE_PERSONAL = "CREATE TABLE PERSONAL (personal_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "f_name TEXT NOT NULL, " +
            "l_name TEXT NOT NULL, " +
            "age INTEGER NOT NULL, " +
            "gender INTEGER NOT NULL, " +
            "weight REAL NOT NULL, " +
            "height REAL NOT NULL, " +
            "activity  REAL NOT NULL);";


    private static final String TABLE_PROGRAM = "CREATE TABLE PROGRAM (program_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "typeGoal INTEGER NOT NULL, " +
            "weightGoal INTEGER , " +
            "totalCalorie INTEGER DEFAULT 0 , " +
            "kgPerWeek INTEGER, " +
            "year_date_begin INTEGER NOT NULL, " +
            "month_date_begin INTEGER NOT NULL, " +
            "day_date_begin INTEGER NOT NULL, " +
            "status INTEGER NOT NULL, " +
            "percentFat INTEGER, " +
            "programName TEXT);";

    private static final String TABLE_VDO = "CREATE TABLE VDO (vdo_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "type TEXT NOT NULL, " +
            "position TEXT NOT NULL, " +
            "duration TEXT NOT NULL, " +
            "calorie INTEGER NOT NULL);";

    private static final String TABLE_EXERCISE = "CREATE TABLE EXERCISE  (exercise_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "day INTEGER NOT NULL, " +
            "month INTEGER NOT NULL, " +
            "year INTEGER NOT NULL, " +
            "vdo_id TEXT, " +
            "calorieInDay INTEGER, " +
            "calorieTotal INTEGER, " +
            "note TEXT, " +
            "time TEXT, " +
            "check_state_workout INTEGER DEFAULT 0);";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_PERSONAL + "encoding = \"UTF-8\"");
        db.execSQL(TABLE_PROGRAM + "encoding = \"UTF-8\"");

        db.execSQL(TABLE_VDO + "encoding = \"UTF-8\"");
        db.execSQL(TABLE_EXERCISE + "encoding = \"UTF-8\"");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS PERSONAL");
        db.execSQL("DROP TABLE IF EXISTS PROGRAM");
        db.execSQL("DROP TABLE IF EXISTS EXERCISE");
        onCreate(db);
    }
}
