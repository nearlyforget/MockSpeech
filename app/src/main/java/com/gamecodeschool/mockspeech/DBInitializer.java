package com.gamecodeschool.mockspeech;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by nan.liu on 2017/07/06.
 */

public class DBInitializer {

    protected SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context mContext;

    public DBInitializer(Context context) {
        this.mContext = context;
        dbHelper = DBHelper.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if (dbHelper == null)
            dbHelper = DBHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

}
