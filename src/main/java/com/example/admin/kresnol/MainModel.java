package com.example.admin.kresnol;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.ViewGroup;

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

    int totalWinLeft = 0;
    int totalWinRight = 0;

    int numOfRestart = 0;

    String[] arrayOfPlayers = {"Player 1", "Player 2", "Android"};

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




}
