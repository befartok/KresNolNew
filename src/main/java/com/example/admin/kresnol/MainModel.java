package com.example.admin.kresnol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by admin on 26.07.18.
 */



public class MainModel {
    final String LOG_TAG = "myLogs";


    int clickedButtonsTotal = 0;

    public String getStatusGames() {
        return statusGames;
    }

    public void setStatusGames(String statusGames) {
        this.statusGames = statusGames;
    }

    String statusGames = "ready";

    // TODO: 25.07.18 после реализации бд вместо 0 брать значения из бд
    int totalWinLeft = 0;
    int totalWinRight = 0;

    int numOfRestart = 0;

    // TODO: 09.08.18 массивы игроков брать из разных списков без пересекающихся значений
// TODO: 29.09.18  "android" передавать сетером?
    // TODO: 24.10.18 брать из базы?
    String[] arrayOfPlayers = {"Player1", "Player2", "Android"};


    boolean makeStep;

    String spinnerLeftValue;
    String spinnerRightValue;
    String spinnerLevelValue;

    //массив уровней игры
    String[] arrayOfLevel = new String[3];

    public MainModel(String arrLv0, String arrLv1,String arrLv2) {
        arrayOfLevel[0]=arrLv0;
        arrayOfLevel[1]=arrLv1;
        arrayOfLevel[2]=arrLv2;

    }

    // TODO: 27.07.18 проверить гетеры и сеттеры насчет параметров и переменных

    public String getSpinnerLeftValue() {
        return spinnerLeftValue;
    }

    public void setSpinnerLeftValue(String spinnerLeftValue) {
        this.spinnerLeftValue = spinnerLeftValue;
    }

    public String getSpinnerRightValue() {
        return spinnerRightValue;
    }

    public void setSpinnerRightValue(String spinnerRightValue) {
        this.spinnerRightValue = spinnerRightValue;
    }
    public void setSpinnerLevelValue(String spinnerLevelValue) {
        this.spinnerLevelValue = spinnerLevelValue;
    }

    public String getSpinnerLevelValue() {
        return spinnerLevelValue;
    }





/*

    // создаем объект для создания и управления версиями БД
    // подключаемся к БД
    SQLiteDatabase db = dbHelper.getWritableDatabase();




    class DBHelper extends SQLiteOpenHelper {


        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "email text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }*/


}
