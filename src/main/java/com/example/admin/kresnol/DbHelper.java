package com.example.admin.kresnol;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by admin on 09.10.18.
 */

class DbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "my_tag";

    static final String TABLE_NAME = "records";
    static final String KEY_ID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_TOTAL_PLAY = "totalPlay";
    static final String KEY_TOTAL_WIN = "totalWin";

    private final String playersOneName = "Player 1";
    private final String playersTwoName = "Player 2";
    private final String playersAndroidName = "Android";

    private static final String DATABASE_NAME = "recordsDB";
    private static final int DATABASE_VERSION = 1;

    private Context cnt;

    DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

/*        String playersOneName = cnt.getResources().getString(R.string.players1_name);
        String playersTwoName = cnt.getResources().getString(R.string.players2_name);
        String playersAndroidName = cnt.getResources().getString(R.string.droids_name);*/


        db.execSQL("create table " + TABLE_NAME + " ("
                + KEY_ID + " integer primary key autoincrement,"
                + KEY_NAME + " text,"
                + KEY_TOTAL_PLAY + " text,"
                + KEY_TOTAL_WIN + " text"

                + ");");

        ContentValues cv = new ContentValues();

        cv.put(KEY_NAME, playersOneName);
        cv.put(KEY_TOTAL_PLAY, "0");
        cv.put(KEY_TOTAL_WIN, "0");
        db.insert(TABLE_NAME, null, cv);

        cv.put(KEY_NAME, playersTwoName);
        cv.put(KEY_TOTAL_PLAY, "0");
        cv.put(KEY_TOTAL_WIN, "0");
        db.insert(TABLE_NAME, null, cv);

        cv.put(KEY_NAME, playersAndroidName);
        cv.put(KEY_TOTAL_PLAY, "0");
        cv.put(KEY_TOTAL_WIN, "0");
        db.insert(TABLE_NAME, null, cv);

        Log.d(LOG_TAG, "test onCreate");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

}