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

public class SpeechDAO {

    protected static final String TAG = "DataAdapter";
    private ArrayList<SpeechItem> speechItem;
    private ArrayList<ArrayList<SpeechItem>> groupList;
    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;
    private SpeechItem item;
    private Speecher speecher;
    private ArrayList<Words> wordsList;

    public SpeechDAO(Context context) {

        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public SpeechDAO createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public SpeechDAO open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
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
        String sql = "SELECT * FROM tbl_speech where category_id =" + speechCategory;


        try {

            Cursor mCur = mDb.rawQuery(sql, null);
            for (mCur.moveToFirst(); !mCur.isAfterLast(); mCur.moveToNext()) {
                SpeechItem item = new SpeechItem();
                item.setSpeech(mCur.getString(5));
                item.setSpeechCategory(mCur.getInt(8));
                item.setSpeechDate(mCur.getString(2));
                item.setSpeecher(mCur.getInt(7));
                item.setSpeechName(mCur.getString(1));
                item.setSpeechPlace(mCur.getString(3));
                item.setSpeechUri(mCur.getString(4));
                item.setTranslation(mCur.getString(6));
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
            for (mCur.moveToFirst(); !mCur.isAfterLast(); mCur.moveToNext()) {
                SpeechItem item = new SpeechItem();
                item.setSpeechId(mCur.getInt(0));
                item.setSpeechDate(mCur.getString(2));
                item.setSpeecher(mCur.getInt(7));
                item.setSpeechName(mCur.getString(1));
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
        groupList = new ArrayList<>();
        int i = 0;
        try {
            ArrayList<SpeechItem> groupSpeechItem = new ArrayList<>();
            Cursor mCur = mDb.rawQuery(sql, null);
            for (mCur.moveToFirst(); !mCur.isAfterLast(); mCur.moveToNext()) {
                if (i < 5) {
                    SpeechItem item = new SpeechItem();
                    item.setSpeechId(mCur.getInt(0));
                    item.setSpeechDate(mCur.getString(2));
                    item.setSpeecher(mCur.getInt(7));
                    item.setSpeechName(mCur.getString(1));
                    item.setSpeechPlace(mCur.getString(3));
                    groupSpeechItem.add(item);
                    i++;
                } else {
                    groupList.add(groupSpeechItem);
                    groupSpeechItem.clear();
                    i = 0;
                }
            }
            if (groupList.isEmpty()) {
                if (!groupSpeechItem.isEmpty()) {
                    groupList.add(groupSpeechItem);
                }
            }
            return groupList;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mDb.isDatabaseIntegrityOk() + mSQLException.toString());
            throw mSQLException;
        }
    }

    public SpeechItem getSpeechById(int speechId) {
        String sql;
        if (speechId == 0) {
            sql = "SELECT * FROM tbl_speech";
        } else {
            sql = "SELECT * FROM tbl_speech where _id =" + speechId;
        }

        try {

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur != null) {
                mCur.moveToNext();
                item = new SpeechItem();
                item.setSpeech(mCur.getString(5));
                item.setSpeechCategory(mCur.getInt(8));
                item.setSpeechDate(mCur.getString(2));
                item.setSpeecher(mCur.getInt(7));
                item.setSpeechName(mCur.getString(1));
                item.setSpeechPlace(mCur.getString(3));
                item.setSpeechUri(mCur.getString(4));
                item.setTranslation(mCur.getString(6));
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
            sql = "SELECT * FROM tbl_speecher where _id =" + speecherId;
        }

        try {

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur != null) {
                mCur.moveToNext();
                speecher = new Speecher();
                speecher.setSpeecharDisc(mCur.getString(3));
                speecher.setSpeecher_photo(mCur.getString(6));
                speecher.setSpeecherBirthday(mCur.getString(2));
                speecher.setSpeecherCountry(mCur.getString(4));
                speecher.setSpeecherId(mCur.getInt(0));
                speecher.setSpeecherName(mCur.getString(1));
                speecher.setSpeecherSocial(mCur.getString(5));

                return speecher;
            } else {
                return null;
            }
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    public ArrayList<Words> getWordsById(int speechId) {
        String sql;
        wordsList = new ArrayList<>();
        if (speechId == 0) {
            sql = "SELECT * FROM tlb_words";
        } else {
            sql = "SELECT * FROM tlb_words where speech_id =" + speechId;
        }

        try {

            Cursor mCur = mDb.rawQuery(sql, null);
            for (mCur.moveToFirst(); !mCur.isAfterLast(); mCur.moveToNext()) {
                if (mCur != null) {
                    mCur.moveToNext();
                    Words words = new Words();
                    words.setWords(mCur.getString(1));
                    words.setType(mCur.getString(2));
                    words.setIpa(mCur.getString(3));
                    words.setMean(mCur.getString(4));
                    words.setId(mCur.getInt(0));
                    wordsList.add(words);
                } else {
                    return null;
                }
            }
            return wordsList;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }


}
