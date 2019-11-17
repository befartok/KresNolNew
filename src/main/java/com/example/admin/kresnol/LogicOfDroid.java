package com.example.admin.kresnol;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Button;


/**
 * Created by admin on 20.07.18.
 */
//класс логики для игрока Андроид
class LogicOfDroid {
    LogicOfDroid(Resources res) {
        this.res=res;
    }
    private Resources res;
    private boolean makeStep;
    private int btnToDroidStep;
    private int btnToStep;
    private Button[] arrayOfBtn;
    private Player leftPlr;
    private Player rightPlr;

    final String LOG_TAG = "myLogs";
    private final String EMPTY_SYMBOL = "";

    private MainModel mainModel;

    int droidsStep(Button[] arr, Player lPlr, Player rPlr, MainModel model) {

        arrayOfBtn=arr;
        leftPlr = lPlr;
        rightPlr = rPlr;
        mainModel=model;

        // логика для 1 уровня
        if (mainModel.getSpinnerLevelValue().equals(res.getString(R.string.level_easy))){
            // рэндом нажатие кнопки андроидом
            btnToDroidStep = makeRandomStep();
        }

        //логика для 2 уровня;
        if (mainModel.getSpinnerLevelValue().equals(res.getString(R.string.level_normal))){
            makeStep = false;

            //проверка возможности выигрыша
            btnToDroidStep = checkWinStep();

            //проверка возможности проигрыша
            btnToDroidStep = checkLooseStep();

            //проверка возможности поставить в центр
            btnToDroidStep = makeCenterStep();
            Log.d(LOG_TAG, "74 test" );

            //если раньше не было ходов то сделать случайный ход
            if (makeStep == false) {
                // рэндом нажатие кнопки андроидом
                btnToDroidStep = makeRandomStep();
            }
        }

        // логику для 3 уровня

        if (mainModel.getSpinnerLevelValue().equals(res.getString(R.string.level_hard))){

            makeStep = false;

            //проверка возможности выигрыша
            btnToDroidStep = checkWinStep();

            //проверка возможности проигрыша
            btnToDroidStep = checkLooseStep();

            //проверка возможности поставить в центр
            btnToDroidStep = makeCenterStep();

            //ставить в угол
            btnToDroidStep = makeCornerStep();

            //если раньше не было ходов то сделать случайный ход
            if (makeStep == false) {
                // рэндом нажатие кнопки андроидом
                btnToDroidStep = makeRandomStep();
            }
        }

        return btnToDroidStep;
    }

    //проверка ячейки на незанятость, иначе брать следующую
    private int checkEmptyButton(int numberOfButton) {

        String x_SYMBOL = "x";
        String o_SYMBOL = "o";
        while ((arrayOfBtn[numberOfButton].getText().equals(x_SYMBOL))
                | (arrayOfBtn[numberOfButton].getText().equals(o_SYMBOL))) {
            numberOfButton = numberOfButton + 1;

            if (numberOfButton == 9) {
                numberOfButton = 0;
            }
        }
        return (numberOfButton);
    }

    private int makeCenterStep(){
        if ((makeStep == false) & (arrayOfBtn[4].getText().equals(EMPTY_SYMBOL))) {
            makeStep = true;
            btnToStep=4;
        }
        return btnToStep;
    }

    private int makeCornerStep(){
        if ((makeStep == false)&(arrayOfBtn[0].getText().equals(EMPTY_SYMBOL))) {
            makeStep = true;
            btnToStep = 0;
        }
        if ((makeStep == false)&(arrayOfBtn[2].getText().equals(EMPTY_SYMBOL))) {
            makeStep = true;
            btnToStep = 2;
        }
        if ((makeStep == false)&(arrayOfBtn[5].getText().equals(EMPTY_SYMBOL))) {
            makeStep = true;
            btnToStep = 5;
        }
        if ((makeStep == false)&(arrayOfBtn[8].getText().equals(EMPTY_SYMBOL))) {
            makeStep = true;
            btnToStep = 8;
        }

        return btnToStep;
    }

    // рэндом нажатие кнопки андроидом
    private int makeRandomStep(){

        int varRandom ;
        varRandom = (int) (Math.random() * 8);

        return  checkEmptyButton(varRandom);
            }

    private int checkWinStep() {

        String symbolOfDroid = rightPlr.getSymbol();

        int emptyButton=-1;


        Boolean isEmptyButton;
        int amountOfSymbolOfDroid=0;
        int j = 0;

        while ((makeStep==false) & (j < 8)) {

            isEmptyButton=false;
            amountOfSymbolOfDroid = 0;

            for (int i = 0; i < 3; i++) {

                if (arrayOfBtn[mainModel.winLines[j][i]].getText().equals(symbolOfDroid)) {

                    amountOfSymbolOfDroid++;

                } else if (arrayOfBtn[mainModel.winLines[j][i]].getText().equals("")){

                    isEmptyButton=true;

                    emptyButton = mainModel.winLines[j][i];
                }


                if ((amountOfSymbolOfDroid == 2) & (isEmptyButton)){
                    makeStep=true;
                    btnToStep = emptyButton;
                }
            }
            j++;
        }





        return btnToStep;
    }

    private int checkLooseStep(){

        String symbolOfPlayer = leftPlr.getSymbol();

        int emptyButton=-1;


        Boolean isEmptyButton;
        int amountOfSymbolOfPlayer=0;
        int j = 0;

        while ((makeStep==false) & (j < 8)) {

            isEmptyButton=false;
            amountOfSymbolOfPlayer = 0;


            for (int i = 0; i < 3; i++) {

                if (arrayOfBtn[mainModel.winLines[j][i]].getText().equals(symbolOfPlayer)) {

                    amountOfSymbolOfPlayer++;

                } else if (arrayOfBtn[mainModel.winLines[j][i]].getText().equals("")){

                    isEmptyButton=true;

                    emptyButton = mainModel.winLines[j][i];
                }

                if ((amountOfSymbolOfPlayer == 2) & (isEmptyButton)){
                    makeStep=true;
                    btnToStep = emptyButton;
                }
            }
            j++;
        }


        return btnToStep;
    }

}
