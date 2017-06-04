package com.finalproject.reachyourfitnessgoals.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.finalproject.reachyourfitnessgoals.models.DateData;
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
    public static final String COLUMN_WEIGHT= "weight";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_ACTIVITY = "activity";


    public handleTABLE_PERSONAL(Context context) {
        objDBHelper = new DBHelper(context);
        writeSQLite = objDBHelper.getWritableDatabase();
        readSQLite = objDBHelper.getReadableDatabase();
    }

    public void addPersonal(PersonalData data){
        ContentValues values = new ContentValues();
        values.put(COLUMN_F_NAME, data.getF_name());
        values.put(COLUMN_L_NAME, data.getL_name());
        values.put(COLUMN_AGE , data.getAge());
        values.put(COLUMN_GENDER,data.getGender());
        values.put(COLUMN_WEIGHT, data.getWeight());
        values.put(COLUMN_HEIGHT, data.getHeight());
        values.put(COLUMN_ACTIVITY, data.getActivity());

        writeSQLite.insert(TABLE_PERSONAL, null, values);
    }

    public PersonalData getPersonal(){
        Cursor cursor = readSQLite.rawQuery("SELECT * FROM " + TABLE_PERSONAL ,null);
        if (cursor != null) {
            cursor.moveToFirst();
            Log.i("personal",cursor.getString(1));
            Log.i("personal",cursor.getString(2));
            Log.i("personal",cursor.getInt(3)+"");
            Log.i("personal",cursor.getInt(4)+"");
            Log.i("personal",cursor.getDouble(5)+"");
            Log.i("personal",cursor.getDouble(6)+"");
            return new PersonalData(cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getDouble(5),cursor.getDouble(6),cursor.getDouble(7));
        }else {
            return null;
        }
    }

    public void delete(){
        writeSQLite.execSQL("delete from "+ TABLE_PERSONAL);
    }
}
