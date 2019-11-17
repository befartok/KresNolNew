package com.example.admin.kresnol;


/**
 * Created by admin on 26.07.18.
 */

//класс модели
class MainModel {
    final String LOG_TAG = "myLogs";


    int getClickedButtonsTotal() {
        return clickedButtonsTotal;
    }

    void setClickedButtonsTotal(int clickedButtonsTotal) {
        this.clickedButtonsTotal = clickedButtonsTotal;
    }

    private int clickedButtonsTotal = 0;


    String getStatusGames() {
        return statusGames;
    }

    void setStatusGames(String statusGames) {
        this.statusGames = statusGames;
    }

     private String statusGames;

    int getTotalWinLeft() {
        return totalWinLeft;
    }

    void setTotalWinLeft(int totalWinLeft) {
        this.totalWinLeft = totalWinLeft;
    }

    int getTotalWinRight() {
        return totalWinRight;
    }

    void setTotalWinRight(int totalWinRight) {
        this.totalWinRight = totalWinRight;
    }

    //счетчики побед левого и правого игроков
    private int totalWinLeft = 0;
    private int totalWinRight = 0;

    int getNumOfRestart() {
        return numOfRestart;
    }

    void setNumOfRestart(int numOfRestart) {
        this.numOfRestart = numOfRestart;
    }

    //число перезапусков игры
    private int numOfRestart = 0;


    //спинеры игроков и уровня сложности
    private String spinnerLeftValue;
    private String spinnerRightValue;
    private String spinnerLevelValue;

    //массив уровней игры
    String[] arrayOfLevel = new String[3];

    //конструктор модели
    MainModel(String arrLv0, String arrLv1, String arrLv2, String statusGames) {
        arrayOfLevel[0] = arrLv0;
        arrayOfLevel[1] = arrLv1;
        arrayOfLevel[2] = arrLv2;

        setStatusGames(statusGames);

    }

    String getSpinnerLeftValue() {
        return spinnerLeftValue;
    }

    void setSpinnerLeftValue(String spinnerLeftValue) {
        this.spinnerLeftValue = spinnerLeftValue;
    }

    String getSpinnerRightValue() {
        return spinnerRightValue;
    }

    void setSpinnerRightValue(String spinnerRightValue) {
        this.spinnerRightValue = spinnerRightValue;
    }

    void setSpinnerLevelValue(String spinnerLevelValue) {
        this.spinnerLevelValue = spinnerLevelValue;
    }

    String getSpinnerLevelValue() {
        return spinnerLevelValue;
    }



    //массив линий выигрышей
    int[][] winLines = {
            { 0, 1, 2 },
            { 3, 4, 5 },
            { 6, 7, 8 },
            { 0, 3, 6 },
            { 1, 4, 7 },
            { 2, 5, 8 },
            { 0, 4, 8 },
            { 2, 4, 6 }

    };

}
