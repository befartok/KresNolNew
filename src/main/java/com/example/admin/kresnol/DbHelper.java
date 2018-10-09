package com.example.admin.kresnol;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 09.10.18.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "my_tag";

    public static final String TABLE_NAME = "records";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_TOTAL_PLAY = "totalPlay";
    public static final String KEY_TOTAL_WIN = "totalWin";
    public static final String KEY_TOTAL_LOSE = "totalLose";

    private static final String DATABASE_NAME = "recordsDB";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_NAME + " text,"
                + KEY_TOTAL_PLAY + " text,"
                + KEY_TOTAL_WIN + " text,"
                + KEY_TOTAL_LOSE + " text" + ");");

        ContentValues cv = new ContentValues();

        cv.put(KEY_NAME, "Player1");
        cv.put(KEY_TOTAL_PLAY, "0");
        cv.put(KEY_TOTAL_WIN, "0");
        cv.put(KEY_TOTAL_LOSE, "0");
        db.insert(TABLE_NAME, null, cv);

        cv.put(KEY_NAME, "Player2");
        cv.put(KEY_TOTAL_PLAY, "0");
        cv.put(KEY_TOTAL_WIN, "0");
        cv.put(KEY_TOTAL_LOSE, "0");
        db.insert(TABLE_NAME, null, cv);

        cv.put(KEY_NAME, "Android");
        cv.put(KEY_TOTAL_PLAY, "0");
        cv.put(KEY_TOTAL_WIN, "0");
        cv.put(KEY_TOTAL_LOSE, "0");
        db.insert(TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

}