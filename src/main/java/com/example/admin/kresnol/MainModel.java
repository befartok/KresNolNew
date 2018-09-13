package com.example.admin.kresnol;

/**
 * Created by admin on 26.07.18.
 */

public class MainModel {

    int clickedButtonsTotal = 0;

    // TODO: 30.08.18 проверить необходимость переменной и ее инициализации
    String statusGames = "ready";

    // TODO: 25.07.18 после реализации бд вместо 0 брать значения из бд
    int totalWinLeft = 0;
    int totalWinRight = 0;

    int numOfRestart = 0;

    // TODO: 09.08.18 массивы игроков брать из разных списков без пересекающихся значений

    String[] arrayOfPlayers = {"Player 1", "Player 2", "Android"};
    String[] arrayOfLevel = {"Easy", "Normal", "Hard"};

    boolean makeStep;

    String spinnerLeftValue;
    String spinnerRightValue;
    String spinnerLevelValue;

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
