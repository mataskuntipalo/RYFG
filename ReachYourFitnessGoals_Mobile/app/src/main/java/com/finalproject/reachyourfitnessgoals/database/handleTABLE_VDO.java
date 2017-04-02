package com.finalproject.reachyourfitnessgoals.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.finalproject.reachyourfitnessgoals.models.DateData;
import com.finalproject.reachyourfitnessgoals.models.vdoData;

import java.util.ArrayList;

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
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_CALORIE = "calorie";

    public handleTABLE_VDO(Context context) {
        objDBHelper = new DBHelper(context);
        writeSQLite = objDBHelper.getWritableDatabase();
        readSQLite = objDBHelper.getReadableDatabase();
    }

    public void addExercise(ArrayList<vdoData> vdoDataList){
        ContentValues values = new ContentValues();
        for (vdoData data: vdoDataList) {
            values.put(COLUMN_NAME, data.getName());
            values.put(COLUMN_TYPE, data.getType());
            values.put(COLUMN_POSITION , data.getPosition());
            values.put(COLUMN_DURATION , data.getDuration());
            values.put(COLUMN_CALORIE, data.getPosition());
        }
        writeSQLite.insert(TABLE_VDO, null, values);
    }

}
