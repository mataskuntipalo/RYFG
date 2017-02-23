package com.finalproject.reachyourfitnessgoals.setting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Papang on 12/2/2560.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBTABLE_PERSONAL = "PERSONAL";


    public static final String USER_ID = "user_id";
    public static final String FIRST_NAME = "f_name";
    public static final String LAST_NAME= "l_name";
    public static final String AGE = "age";
    public static final String GENDER = "gender";
    public static final String BIRTHDAY = "birthday";
    public static final String WEIGHT= "weight";
    public static final String HEIGHT = "height";
    public static final String PREESURE_MIN= "pressure_min";
    public static final String PREESURE_MAX = "pressure_max";

    public DBHelper(Context context) {
        super(context, "ReachYourFitnessGoals.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + DBTABLE_PERSONAL +"(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIRST_NAME + " TEXT, " +
                LAST_NAME + " TEXT, " +
                AGE + " INTEGER, " +
                GENDER + " TEXT, " +
                BIRTHDAY + " TEXT, " +
                WEIGHT + " INTEGER," +
                HEIGHT + " INTEGER," +
                PREESURE_MIN + " INTEGER," +
                PREESURE_MAX + " INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DBTABLE_PERSONAL);
        onCreate(db);
    }
}
