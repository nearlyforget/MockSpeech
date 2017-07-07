package com.gamecodeschool.mockspeech;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;

/**
 * Created by nan.liu on 2017/07/06.
 */

public class SpeechDAO extends DataBaseHelper {

    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;

    public SpeechDAO(Context context) {
        super(context);
        this.mContext = context;
    }

    public SpeechDAO createDatabase() throws SQLException {
        try {
            this.createDataBase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public SpeechDAO open() throws SQLException {
        try {
            this.openDataBase();
            this.close();
            mDb = this.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>" + mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        this.close();
    }

    public Cursor getTestData() {
        try {
            String sql = "SELECT * FROM SPEECH";

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur != null) {
                mCur.moveToNext();
            }
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

}
