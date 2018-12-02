package com.example.admin.kresnol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 07.10.18.
 */
// TODO: 15.10.18 удалить проигрыши в бд
public class Db {
    //private static final String LOG_TAG = "my_tag";
    final String LOG_TAG = "myLogs";

    int id;
    DbHelper dbHelper;
    Context context;
    Cursor cursor;
    SQLiteDatabase db;
    List<RecordOfDb> mRecordOfDbList;
    List<RecordOfDb> NamesFromDbList;

    String NameForCreateLeftPlayer;
    String NameForCreateRightPlayer;

    public Db(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    public Cursor getAllItems() {
        db = dbHelper.getReadableDatabase();
        return db.query(DbHelper.TABLE_NAME, null, null, null, null, null, null);
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
    public void deleteItem(long id) {
        db = dbHelper.getWritableDatabase();
        db.delete(DbHelper.TABLE_NAME, DbHelper.KEY_ID + "=" + id, null);
    }

    public void upgradeBase() {
        db = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(db, 1, 2);
    }

    public String getNameForCreateLeftPlayer() {
        db = dbHelper.getReadableDatabase();
        String[] idFirstRecord = new String[]{Integer.toString(1)};
        Log.d(LOG_TAG, "idFirstRecord = " + idFirstRecord);

        cursor = db.query(DbHelper.TABLE_NAME, null, "_id =?", idFirstRecord, null, null, null);
        int nameColInd = cursor.getColumnIndex(DbHelper.KEY_NAME);
        Log.d(LOG_TAG, "cursor = " + cursor);
        Log.d(LOG_TAG, "nameColInd = " + nameColInd);

        cursor.moveToFirst();
        NameForCreateLeftPlayer = cursor.getString(nameColInd);
        //Log.d(LOG_TAG, "NameForCreateLeftPlayer = " + NameForCreateLeftPlayer);

        cursor.close();
        return NameForCreateLeftPlayer;

    }

    public String getNameForCreateRightPlayer() {
        db = dbHelper.getReadableDatabase();
        String[] idSecondRecord = new String[]{Integer.toString(2)};

        cursor = db.query(DbHelper.TABLE_NAME, null, "_id =?", idSecondRecord, null, null, null);
        int nameColInd = cursor.getColumnIndex(DbHelper.KEY_NAME);

        cursor.moveToFirst();
        String NameForCreateRightPlayer = cursor.getString(nameColInd);
        cursor.close();
        return NameForCreateRightPlayer;

    }

    public String getNameFromRecordOfDb(long id) {
        db = dbHelper.getReadableDatabase();


        String idInString = String.valueOf(id);
        //String[] idFromRecord = new String[]{Integer.toString(id)};
        String[] idFromRecord = new String[]{idInString};

        cursor = db.query(DbHelper.TABLE_NAME, null, "_id =?", idFromRecord, null, null, null);
        int nameColInd = cursor.getColumnIndex(DbHelper.KEY_NAME);

        cursor.moveToFirst();
        String NameFromRecordOfDb = cursor.getString(nameColInd);
        cursor.close();
        return NameFromRecordOfDb;

    }


    public void addPlayer(String name) {
        db = dbHelper.getWritableDatabase();

        String nameOfPlayer = name;
        ContentValues cv = new ContentValues();

        cv.put(DbHelper.KEY_NAME, nameOfPlayer);
        cv.put(DbHelper.KEY_TOTAL_PLAY, "0");
        cv.put(DbHelper.KEY_TOTAL_WIN, "0");

        db.insert(DbHelper.TABLE_NAME, null, cv);


    }


    public void clearStats(long id) {

        db = dbHelper.getReadableDatabase();

        String idInString = String.valueOf(id);
        String[] idFromRecord = new String[]{idInString};

        ContentValues cv = new ContentValues();
        cv.put(DbHelper.KEY_TOTAL_PLAY, 0);
        cv.put(DbHelper.KEY_TOTAL_WIN, 0);
        db.update(DbHelper.TABLE_NAME, cv, "_id =?", idFromRecord);


    }

    public void editName(long id, String name) {

        db = dbHelper.getReadableDatabase();

        String idInString = String.valueOf(id);
        String[] idFromRecord = new String[]{idInString};

        ContentValues cv = new ContentValues();
        cv.put(DbHelper.KEY_NAME, name);
        db.update(DbHelper.TABLE_NAME, cv, "_id =?", idFromRecord);


    }

    public boolean checkName(String name) {

        db = dbHelper.getReadableDatabase();

        String[] nameToSearch = new String[]{name};

        cursor = db.query(DbHelper.TABLE_NAME, null, "name = ?", nameToSearch, null, null, null);

        if ((cursor != null) && (cursor.getCount() > 0)) {
            return true;
        }else return false;

    }

    public void addGame(String leftPlayer, String rightPlayer) {
        //       сделать 2 квери с игроками
        db = dbHelper.getReadableDatabase();

        String[] nameOfLeftPlayer = new String[]{leftPlayer};
        String[] nameOfRightPlayer = new String[]{rightPlayer};
        String[] totalColumn = new String[]{DbHelper.KEY_TOTAL_PLAY};

        cursor = db.query(DbHelper.TABLE_NAME, null, "name = ?", nameOfLeftPlayer, null, null, null);

        int totalPlayColInd = cursor.getColumnIndex(DbHelper.KEY_TOTAL_PLAY);
        Log.d(LOG_TAG, "totalPlayColInd = " + totalPlayColInd);

        int cnt = cursor.getCount();
        Log.d(LOG_TAG, "getCount  = " + cnt);

        cursor.moveToFirst();

        int totalPlayLeft = cursor.getInt(totalPlayColInd);
        Log.d(LOG_TAG, "totalPlayLeft from DB = " + totalPlayLeft);

        cursor.close();

        totalPlayLeft++;
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.KEY_TOTAL_PLAY, totalPlayLeft);
        db.update(DbHelper.TABLE_NAME, cv, "name = ?", nameOfLeftPlayer);


        cursor = db.query(DbHelper.TABLE_NAME, null, "name = ?", nameOfRightPlayer, null, null, null);

        totalPlayColInd = cursor.getColumnIndex(DbHelper.KEY_TOTAL_PLAY);
        Log.d(LOG_TAG, "totalPlayColInd = " + totalPlayColInd);

        cnt = cursor.getCount();
        Log.d(LOG_TAG, "getCount  = " + cnt);

        cursor.moveToFirst();

        int totalPlayRight = cursor.getInt(totalPlayColInd);
        Log.d(LOG_TAG, "totalPlayRight from DB = " + totalPlayRight);

        cursor.close();

        totalPlayRight++;
        cv.put(DbHelper.KEY_TOTAL_PLAY, totalPlayRight);
        //String[] args = new String[]{name};
        db.update(DbHelper.TABLE_NAME, cv, "name = ?", nameOfRightPlayer);
    }

    // метод возвращающий коллекцию всех данных
    public List<RecordOfDb> getRecordOfDb() {
        db = dbHelper.getReadableDatabase();

        cursor = db.query(DbHelper.TABLE_NAME, null, null, null, null, null, null);
        mRecordOfDbList = new ArrayList<RecordOfDb>();

        if (cursor.moveToFirst()) {

            int idColInd = cursor.getColumnIndex(DbHelper.KEY_ID);
            int nameColInd = cursor.getColumnIndex(DbHelper.KEY_NAME);
            int totalPlayColInd = cursor.getColumnIndex(DbHelper.KEY_TOTAL_PLAY);
            int totalWinColInd = cursor.getColumnIndex(DbHelper.KEY_TOTAL_WIN);
            //int totalLoseColInd = cursor.getColumnIndex(DbHelper.KEY_TOTAL_LOSE);

            do {
                RecordOfDb record = new RecordOfDb(cursor.getInt(idColInd),
                        cursor.getString(nameColInd), cursor.getInt(totalPlayColInd),
                        cursor.getInt(totalWinColInd));
                //, cursor.getInt(totalLoseColInd));
                mRecordOfDbList.add(record);
            } while (cursor.moveToNext());

        } else {
            Log.d(LOG_TAG, "В базе нет данных!");
        }

        cursor.close();

        return mRecordOfDbList;

    }

    public List<RecordOfDb> getNamesFromDb() {
        db = dbHelper.getReadableDatabase();

        cursor = db.query(DbHelper.TABLE_NAME, null, null, null, null, null, null);
        NamesFromDbList = new ArrayList<RecordOfDb>();

        if (cursor.moveToFirst()) {

            int nameColInd = cursor.getColumnIndex(DbHelper.KEY_NAME);

            do {
                RecordOfDb record = new RecordOfDb(cursor.getString(nameColInd));
                NamesFromDbList.add(record);
            } while (cursor.moveToNext());

        } else {
            Log.d(LOG_TAG, "В базе нет данных!");
        }

        cursor.close();

        return NamesFromDbList;

    }


    // здесь закрываем все соединения с базой и класс-помощник
    public void close() {
        dbHelper.close();
        db.close();
    }

    public void addWinToDb(String name) {
        db = dbHelper.getReadableDatabase();

        String[] nameOfWinner = new String[]{name};

        cursor = db.query(DbHelper.TABLE_NAME, null, "name = ?", nameOfWinner, null, null, null);

        int totalWinColInd = cursor.getColumnIndex(DbHelper.KEY_TOTAL_WIN);
        Log.d(LOG_TAG, "totalPlayColInd = " + totalWinColInd);

        int cnt = cursor.getCount();
        Log.d(LOG_TAG, "getCount  = " + cnt);

        cursor.moveToFirst();

        int totalWin = cursor.getInt(totalWinColInd);
        Log.d(LOG_TAG, "totalWin from DB = " + totalWin);

        cursor.close();

        totalWin++;
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.KEY_TOTAL_WIN, totalWin);
        db.update(DbHelper.TABLE_NAME, cv, "name = ?", nameOfWinner);
    }
}
