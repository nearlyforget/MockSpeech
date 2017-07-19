package com.gamecodeschool.mockspeech;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by nan.liu on 2017/07/06.
 */

public class SpeechDAO extends DataBaseHelper {

    protected static final String TAG = "DataAdapter";
    private ArrayList<SpeechItem> speechItem;
    private ArrayList<ArrayList<SpeechItem>> groupList;
    private final Context mContext;
    private SQLiteDatabase mDb;
    SpeechItem item;
    Speecher speecher;


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

    public ArrayList<SpeechItem> getSpeechByCategory(int speechCategory) {
        String sql = "SELECT * FROM tbl_speech where speecher_id =" + speechCategory;


        try {

            Cursor mCur = mDb.rawQuery(sql, null);
            for (mCur.moveToFirst(); mCur.isAfterLast(); mCur.moveToNext()) {
                SpeechItem item = new SpeechItem();
                item.setSpeech(mCur.getColumnName(5));
                item.setSpeechCategory(mCur.getInt(8));
                item.setSpeechDate(mCur.getColumnName(2));
                item.setSpeecher(mCur.getInt(7));
                item.setSpeechName(mCur.getColumnName(1));
                item.setSpeechPlace(mCur.getString(3));
                item.setSpeechUri(mCur.getColumnName(4));
                item.setTranslation(mCur.getColumnName(6));
                speechItem.add(item);
            }
            return speechItem;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public ArrayList<SpeechItem> getSpeechList() {
        String sql = "SELECT * FROM tbl_speech";


        try {

            Cursor mCur = mDb.rawQuery(sql, null);
            for (mCur.moveToFirst(); mCur.isAfterLast(); mCur.moveToNext()) {
                SpeechItem item = new SpeechItem();
                item.setSpeechId(mCur.getInt(0));
                item.setSpeechDate(mCur.getColumnName(2));
                item.setSpeecher(mCur.getInt(7));
                item.setSpeechName(mCur.getColumnName(1));
                item.setSpeechPlace(mCur.getString(3));
                speechItem.add(item);
            }
            return speechItem;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public ArrayList<ArrayList<SpeechItem>> getSpeechGroupList() {
        String sql = "SELECT * FROM tbl_speech";
        int i = 1;
        try {

            Cursor mCur = mDb.rawQuery(sql, null);
            for (mCur.moveToFirst(); mCur.isAfterLast(); mCur.moveToNext()) {
                SpeechItem item = new SpeechItem();
                ArrayList<SpeechItem> groupSpeechItem = new ArrayList<>();
                item.setSpeechId(mCur.getInt(0));
                item.setSpeechDate(mCur.getColumnName(2));
                item.setSpeecher(mCur.getInt(7));
                item.setSpeechName(mCur.getColumnName(1));
                item.setSpeechPlace(mCur.getString(3));
                if (i <= 3) {
                    groupSpeechItem.add(item);
                    i++;
                } else {
                    i = 1;
                    groupList.add(groupSpeechItem);
                }

            }
            return groupList;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public SpeechItem getSpeechById(int speechId) {
        String sql;
        if (speechId == 0) {
            sql = "SELECT * FROM tbl_speech";
        } else {
            sql = "SELECT * FROM tbl_speech where id =" + speechId;
        }

        try {

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur != null) {
                mCur.moveToNext();
                item = new SpeechItem();
                item.setSpeech(mCur.getColumnName(5));
                item.setSpeechCategory(mCur.getInt(8));
                item.setSpeechDate(mCur.getColumnName(2));
                item.setSpeecher(mCur.getInt(7));
                item.setSpeechName(mCur.getColumnName(1));
                item.setSpeechPlace(mCur.getString(3));
                item.setSpeechUri(mCur.getColumnName(4));
                item.setTranslation(mCur.getColumnName(6));
                return item;
            } else {
                return null;
            }
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public Speecher getSpeecherById(int speecherId) {
        String sql;
        if (speecherId == 0) {
            sql = "SELECT * FROM tbl_speecher";
        } else {
            sql = "SELECT * FROM tbl_speecher where id =" + speecherId;
        }

        try {

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur != null) {
                mCur.moveToNext();
                speecher = new Speecher();
                speecher.setSpeecharDisc(mCur.getColumnName(3));
                speecher.setSpeecher_photo(mCur.getColumnName(6));
                speecher.setSpeecherBirthday(mCur.getColumnName(2));
                speecher.setSpeecherCountry(mCur.getColumnName(4));
                speecher.setSpeecherId(mCur.getInt(0));
                speecher.setSpeecherName(mCur.getColumnName(1));
                speecher.setSpeecherSocial(mCur.getColumnName(5));

                return speecher;
            } else {
                return null;
            }
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

}
