package com.finalproject.reachyourfitnessgoals.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.finalproject.reachyourfitnessgoals.models.PersonalData;

/**
 * Created by Papang on 27/2/2560.
 */

public class handleTABLE_PERSONAL {
    private DBHelper objDBHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String TABLE_PERSONAL = "PERSONAL";
    public static final String COLUMN_F_NAME= "f_name";
    public static final String COLUMN_L_NAME = "l_name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_WEIGHT= "weight";
    public static final String COLUMN_HEIGHT = "height";


    public handleTABLE_PERSONAL(Context context) {
        objDBHelper = new DBHelper(context);
        writeSQLite = objDBHelper.getWritableDatabase();
        readSQLite = objDBHelper.getReadableDatabase();
    }

    public void addPersonal(PersonalData data){
        ContentValues values = new ContentValues();
        values.put(COLUMN_F_NAME, data.getF_Name());
        values.put(COLUMN_L_NAME, data.getL_Name());
        values.put(COLUMN_AGE , data.getAge());
        values.put(COLUMN_GENDER,data.getGender());
        values.put(COLUMN_BIRTHDAY, data.getBirthday());
        values.put(COLUMN_WEIGHT, data.getWeight());
        values.put(COLUMN_HEIGHT, data.getHeight());

        writeSQLite.insert(TABLE_PERSONAL, null, values);
    }
}
