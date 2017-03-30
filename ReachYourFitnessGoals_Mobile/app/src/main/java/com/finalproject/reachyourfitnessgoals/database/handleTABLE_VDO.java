package com.finalproject.reachyourfitnessgoals.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.finalproject.reachyourfitnessgoals.models.DateData;
import com.finalproject.reachyourfitnessgoals.models.vdoData;

/**
 * Created by Papang on 31/3/2560.
 */

public class handleTABLE_VDO {
    private DBHelper objDBHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public static final String TABLE_VDO = "VDO";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_CALORIE = "calorie";

    public handleTABLE_VDO(Context context) {
        objDBHelper = new DBHelper(context);
        writeSQLite = objDBHelper.getWritableDatabase();
        readSQLite = objDBHelper.getReadableDatabase();
    }

    public void addExercise(vdoData vdoData){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, vdoData.getName());
        values.put(COLUMN_TYPE, vdoData.getType());
        values.put(COLUMN_POSITION , vdoData.getPosition());
        values.put(COLUMN_CALORIE, vdoData.getPosition());

        writeSQLite.insert(TABLE_VDO, null, values);
    }

}
