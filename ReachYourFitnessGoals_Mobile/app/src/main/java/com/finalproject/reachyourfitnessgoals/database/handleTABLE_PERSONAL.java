package com.finalproject.reachyourfitnessgoals.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Papang on 27/2/2560.
 */

public class handleTABLE_PERSONAL {
    private DBHelper objDBHelper;
    private SQLiteDatabase writeSQLite, readSQLite;

    public handleTABLE_PERSONAL(Context context) {
        objDBHelper = new DBHelper(context);
        writeSQLite = objDBHelper.getWritableDatabase();
        readSQLite = objDBHelper.getReadableDatabase();
    }
}
