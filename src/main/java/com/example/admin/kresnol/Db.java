package com.example.admin.kresnol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 07.10.18.
 */

public class Db {
    //private static final String LOG_TAG = "my_tag";
    final String LOG_TAG = "myLogs";

    DbHelper dbHelper;
    Context context;
    Cursor cursor;
    SQLiteDatabase db;
    List<RecordOfDb> mRecordOfDbList;

    public Db(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
    }
    // возвращает количество записей в таблице
    public int getItemCount() {

        db = dbHelper.getReadableDatabase();

        cursor = db.query(DbHelper.TABLE_NAME, null, null, null, null, null, null);
        int cnt = cursor.getCount();
        cursor.close();

        return cnt;
    }
    // метод для обновления email
/*    public void updateEmail(String name, String newEmail){
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.KEY_EMAIL, "newemail@newemail.com");
        String[] args = new String[]{name};
        db.update(DbHelper.TABLE_NAME, cv, "name = ?", args);
    }*/
    // метод для удаления строки по id
    public void deleteItem(int id) {
        db = dbHelper.getWritableDatabase();
        db.delete(DbHelper.TABLE_NAME, DbHelper.KEY_ID + "=" + id, null);
    }
    // метод возвращающий коллекцию всех данных
    public List<RecordOfDb> getRecordOfDb() {
        cursor = db.query(DbHelper.TABLE_NAME, null, null, null, null, null, null);
        mRecordOfDbList = new ArrayList<RecordOfDb>();

        if (cursor.moveToFirst()) {

            int idColInd = cursor.getColumnIndex(DbHelper.KEY_ID);
            int nameColInd = cursor.getColumnIndex(DbHelper.KEY_NAME);
            int totalPlayColInd = cursor.getColumnIndex(DbHelper.KEY_TOTAL_PLAY);
            int totalWinColInd = cursor.getColumnIndex(DbHelper.KEY_TOTAL_WIN);
            int totalLoseColInd = cursor.getColumnIndex(DbHelper.KEY_TOTAL_LOSE);

            do {
                RecordOfDb record = new RecordOfDb(cursor.getInt(idColInd),
                        cursor.getString(nameColInd), cursor.getInt(totalPlayColInd),
                        cursor.getInt(totalWinColInd), cursor.getInt(totalLoseColInd)
                );
                mRecordOfDbList.add(record);
            } while (cursor.moveToNext());

        } else {
            Log.d(LOG_TAG, "В базе нет данных!");
        }

        cursor.close();

        return mRecordOfDbList;

    }
    // здесь закрываем все соединения с базой и класс-помощник
    public void close() {
        dbHelper.close();
        db.close();
    }

}

/* class DbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "my_tag";

    //public static final String TABLE_NAME = "friends";
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
    }*/
