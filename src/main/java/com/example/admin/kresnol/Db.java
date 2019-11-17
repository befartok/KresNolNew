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
class Db {
    final String LOG_TAG = "myLogs";

    private DbHelper dbHelper;
    private Cursor cursor;
    private SQLiteDatabase db;


    Db(Context context) {
        dbHelper = new DbHelper(context);
    }


    Cursor getAllItems() {
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


    // метод для удаления строки по id
    void deleteItem(long id) {
        db = dbHelper.getWritableDatabase();
        db.delete(DbHelper.TABLE_NAME, DbHelper.KEY_ID + "=" + id, null);
    }

    public void upgradeBase() {
        db = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(db, 1, 2);
    }


    //возвращаем имя по индексу
    String getNameFromRecordOfDb(long id) {
        db = dbHelper.getReadableDatabase();


        String idInString = String.valueOf(id);
        String[] idFromRecord = new String[]{idInString};

        cursor = db.query(DbHelper.TABLE_NAME, null, "_id =?", idFromRecord, null, null, null);
        int nameColInd = cursor.getColumnIndex(DbHelper.KEY_NAME);

        cursor.moveToFirst();
        String NameFromRecordOfDb = cursor.getString(nameColInd);
        cursor.close();
        return NameFromRecordOfDb;

    }

    //добавляем нового игрока в базу
    void addPlayer(String name) {
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.KEY_NAME, name);
        cv.put(DbHelper.KEY_TOTAL_PLAY, "0");
        cv.put(DbHelper.KEY_TOTAL_WIN, "0");

        db.insert(DbHelper.TABLE_NAME, null, cv);

    }

    //очистка статистики игр по индексу
    void clearStats(long id) {

        db = dbHelper.getReadableDatabase();

        String idInString = String.valueOf(id);
        String[] idFromRecord = new String[]{idInString};

        ContentValues cv = new ContentValues();
        cv.put(DbHelper.KEY_TOTAL_PLAY, 0);
        cv.put(DbHelper.KEY_TOTAL_WIN, 0);
        db.update(DbHelper.TABLE_NAME, cv, "_id =?", idFromRecord);

    }

    //редактирование имени
    void editName(long id, String name) {

        db = dbHelper.getReadableDatabase();

        String idInString = String.valueOf(id);
        String[] idFromRecord = new String[]{idInString};

        ContentValues cv = new ContentValues();
        cv.put(DbHelper.KEY_NAME, name);
        db.update(DbHelper.TABLE_NAME, cv, "_id =?", idFromRecord);

    }

    // проверка на уникальность имен
    boolean checkName(String name) {

        db = dbHelper.getReadableDatabase();

        String[] nameToSearch = new String[]{name};

        cursor = db.query(DbHelper.TABLE_NAME, null, "name = ?", nameToSearch, null, null, null);

        if ((cursor != null) && (cursor.getCount() > 0)) {
            return true;
        } else return false;

    }

    //добавляем запущенную игру в базу
    void addGame(String leftPlayer, String rightPlayer) {

        db = dbHelper.getReadableDatabase();

        String[] nameOfLeftPlayer = new String[]{leftPlayer};
        String[] nameOfRightPlayer = new String[]{rightPlayer};

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
        db.update(DbHelper.TABLE_NAME, cv, "name = ?", nameOfRightPlayer);
    }

    // метод возвращающий коллекцию всех данных
    public List<RecordOfDb> getRecordOfDb() {
        db = dbHelper.getReadableDatabase();

        cursor = db.query(DbHelper.TABLE_NAME, null, null, null, null, null, null);
        List<RecordOfDb> mRecordOfDbList = new ArrayList<RecordOfDb>();

        if (cursor.moveToFirst()) {

            int idColInd = cursor.getColumnIndex(DbHelper.KEY_ID);
            int nameColInd = cursor.getColumnIndex(DbHelper.KEY_NAME);
            int totalPlayColInd = cursor.getColumnIndex(DbHelper.KEY_TOTAL_PLAY);
            int totalWinColInd = cursor.getColumnIndex(DbHelper.KEY_TOTAL_WIN);

            do {
                RecordOfDb record = new RecordOfDb(cursor.getInt(idColInd),
                        cursor.getString(nameColInd), cursor.getInt(totalPlayColInd),
                        cursor.getInt(totalWinColInd));
                mRecordOfDbList.add(record);
            } while (cursor.moveToNext());

        } else {
            Log.d(LOG_TAG, "В базе нет данных!");
        }

        cursor.close();

        return mRecordOfDbList;

    }

    //получаем список имен игроков из БД
    List<RecordOfDb> getNamesFromDb() {
        db = dbHelper.getReadableDatabase();

        cursor = db.query(DbHelper.TABLE_NAME, null, null, null, null, null, null);
        List<RecordOfDb> namesFromDbList = new ArrayList<RecordOfDb>();

        if (cursor.moveToFirst()) {

            int nameColInd = cursor.getColumnIndex(DbHelper.KEY_NAME);

            do {
                RecordOfDb record = new RecordOfDb(cursor.getString(nameColInd));
                namesFromDbList.add(record);
            } while (cursor.moveToNext());

        } else {
            Log.d(LOG_TAG, "В базе нет данных!");
        }

        cursor.close();

        return namesFromDbList;

    }

    // закрываем все соединения
    void close() {
        dbHelper.close();
        db.close();
    }

    //добавляем игроку выигрыш
    void addWinToDb(String name) {
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
