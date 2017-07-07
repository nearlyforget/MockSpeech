package com.gamecodeschool.mockspeech;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nan.liu on 2017/07/06.
 */

public class DBHelper extends SQLiteOpenHelper {

    //Database name and version
    private static final String DATABASE_NAME = "speechDB";
    private static final int DATABASE_VERSION = 1;

    // Each table's name
    public static final String CATEGORY_TABLE = "tbl_category";
    public static final String SPEECH_TABLE = "tbl_speech";
    public static final String SPEECHER_TABLE = "tbl_speecher";
    public static final String WORDS_TABLE = "tlb_words";

    // Primary key in common
    public static final String ID_COLUMN = "id";

    // Foreign keys
    public static final String CATEGORY_ID = "category_id";
    public static final String SPEECHER_ID = "speecher_id";
    public static final String SPEECH_ID = "speech_id";

    // Speech Table
    public static final String SPEECH_NAME = "speech_name";
    public static final String SPEECH_DATE = "speech_date";
    public static final String SPEECH_PALCE = "speech_place";
    public static final String SPEECH_URI = "speech_uri";
    public static final String SPEECH = "speech";
    public static final String TRANSLATION = "translation";

    //Words Table
    public static final String WORDS = "words";
    public static final String TYPE = "type";
    public static final String IPA = "ipa";
    public static final String MEAN = "mean";

    //Category Table
    public static final String CATEGORY_NAME = "category_name";

    // Speecher table
    public static final String SPEECHER_NAME = "speecher_name";
    public static final String SPEECHER_BIRTHDAY = "speecher_birthday";
    public static final String SPEECHER_DISC = "speecher_disc";
    public static final String SPEECHER_COUNTRY = "speecher_country";
    public static final String SPEECHER_SOCIAL = "speecher_social";
    public static final String SPEECHER_PHOTO = "speecher_photo";

    public static final String CREATE_CATEGORY_TABLE = "CREATE TABLE "
            + CATEGORY_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY,"
            + CATEGORY_NAME + " TEXT)";

    public static final String CREATE_SPEECH_TABLE = "CREATE TABLE "
            + SPEECH_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY,"
            + SPEECH_NAME + " TEXT, " + SPEECH_DATE + " TEXT, "
            + SPEECH_PALCE + " TEXT, " + SPEECH_URI + " TEXT, "
            + SPEECH + " TEXT, " + TRANSLATION + " TEXT, "
            + CATEGORY_ID + " INTEGER , " + SPEECHER_ID + " INTEGER,  FOREIGN KEY("
            + CATEGORY_ID + ") REFERENCES " + CATEGORY_TABLE + "(" + ID_COLUMN + "))";

    public static final String CREATE_SPEECHER_TABLE = "CREATE TABLE "
            + SPEECHER_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY," + SPEECHER_NAME
            + " TEXT, " + SPEECHER_BIRTHDAY + " TEXT, " + SPEECHER_DISC + " TEXT, " + SPEECHER_COUNTRY
            + " TEXT, " + SPEECHER_SOCIAL + " TEXT, " + SPEECHER_PHOTO + " TEXT)";

    public static final String CREATE_WORDS_TABLE = "CREATE TABLE "
            + WORDS_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY," + WORDS
            + " TEXT, " + TYPE + " TEXT, " + IPA + " TEXT, "
            + MEAN + " TEXT, " + SPEECH_ID + " INTEGER, FOREIGN KEY("
            + SPEECH_ID + ") REFERENCES " + SPEECH_TABLE + "(" + ID_COLUMN + "))";

    private static DBHelper instance;

    public static synchronized DBHelper getHelper(Context context) {
        if (instance == null)
            instance = new DBHelper(context);
        return instance;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_SPEECHER_TABLE);
        db.execSQL(CREATE_SPEECH_TABLE);
        db.execSQL(CREATE_WORDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
