package com.example.admin.kresnol;

import android.util.Log;
import android.widget.Button;


/**
 * Created by admin on 20.07.18.
 */

public class LogicOfDroid {

    boolean makeStep;
    int btnToDroidStep;
    int btnToStep;
    Button[] arrayOfBtn;
    Player leftPlr;
    Player rightPlr;
    //MainModel model;

    final String LOG_TAG = "myLogs";

    public int droidsStep(Button[] arr, Player lPlr, Player rPlr, MainModel mainModel) {

        // TODO: 03.08.18 подумать над созданием разных массивов с перезаписью их при вызове метода
        arrayOfBtn=arr;
        leftPlr = lPlr;
        rightPlr = rPlr;
        Log.d(LOG_TAG, "42 mainModel.getSpinnerLevelValue()" + mainModel.getSpinnerLevelValue());

        switch (mainModel.getSpinnerLevelValue()) {
            case "Easy":

                // рэндом нажатие кнопки андроидом
                btnToDroidStep = makeRandomStep();

                break;
            case "Normal":
                //логика для 2 уровня;
                makeStep = false;
                Log.d(LOG_TAG, "62 test" );

                //проверка возможности выигрыша
                btnToDroidStep = checkWinStep();
                Log.d(LOG_TAG, "66 test" );

                //проверка возможности проигрыша
                btnToDroidStep = checkLooseStep();
                Log.d(LOG_TAG, "70 test" );

                //проверка возможности поставить в центр
                btnToDroidStep = makeCenterStep();
                Log.d(LOG_TAG, "74 test" );

                //если раньше не было ходов то сделать случайный ход
                if (makeStep == false) {
                    // рэндом нажатие кнопки андроидом
                    btnToDroidStep = makeRandomStep();
                }
                Log.d(LOG_TAG, "82 test" );

                break;

            case "Hard":
                // логику для 3 уровня

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
                break;
        }
        return btnToDroidStep;
    }

    //проверка ячейки на незанятость, иначе брать следующую
    public int checkEmptyButton(int numberOfButton) {

        Log.d(LOG_TAG, " 115 test  checkEmptyButton");

        while ((arrayOfBtn[numberOfButton].getText().equals("x"))
                | (arrayOfBtn[numberOfButton].getText().equals("o"))) {
            numberOfButton = numberOfButton + 1;
            Log.d(LOG_TAG, "120 numberOfButton+1 = " + numberOfButton);

            if (numberOfButton == 9) {
                numberOfButton = 0;
            }

        }
        return (numberOfButton);
    }

    public int makeCenterStep(){
        if ((makeStep == false) & (arrayOfBtn[4].getText().equals(""))) {
            makeStep = true;
            btnToStep=4;
        }
        return btnToStep;
    }

    public int makeCornerStep(){
        if ((makeStep == false)&(arrayOfBtn[0].getText().equals(""))) {
            makeStep = true;
            btnToStep = 0;
        }
        if ((makeStep == false)&(arrayOfBtn[2].getText().equals(""))) {
            makeStep = true;
            btnToStep = 2;
        }
        if ((makeStep == false)&(arrayOfBtn[5].getText().equals(""))) {
            makeStep = true;
            btnToStep = 5;
        }
        if ((makeStep == false)&(arrayOfBtn[8].getText().equals(""))) {
            makeStep = true;
            btnToStep = 8;
        }

        return btnToStep;
    }

    // рэндом нажатие кнопки андроидом
    public int makeRandomStep(){

        int varRandom ;
        varRandom = (int) (Math.random() * 8);
        //Log.d(LOG_TAG, "164 varRandom = " + varRandom);

        return  checkEmptyButton(varRandom);
            }

    public int checkWinStep() {
        Log.d(LOG_TAG, "181 test" );
        Log.d(LOG_TAG, "182 arrayOfBtn[4].getText()=" + arrayOfBtn[4].getText() );
        Log.d(LOG_TAG, "184 rightPlr.getSymbol=" + rightPlr.getSymbol() );

        if ((makeStep == false)
                & (arrayOfBtn[0].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[1].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[2].getText().equals(""))) {
            makeStep = true;
            btnToStep = 2;
        }
        if ((makeStep == false)
                & (arrayOfBtn[0].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[2].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[1].getText().equals(""))) {
            makeStep = true;
            btnToStep = 1;
        }
        if ((makeStep == false)
                & (arrayOfBtn[1].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[2].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[0].getText().equals(""))) {
            makeStep = true;
            btnToStep = 0;
        }

        if ((makeStep == false)
                & (arrayOfBtn[3].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[5].getText().equals(""))) {
            makeStep = true;
            btnToStep = 5;
        }
        if ((makeStep == false)
                & (arrayOfBtn[3].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[5].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(""))) {
            makeStep = true;
            btnToStep = 4;
        }
        if ((makeStep == false)
                & (arrayOfBtn[4].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[5].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[3].getText().equals(""))) {
            makeStep = true;
            btnToStep = 3;
        }


        if ((makeStep == false)
                & (arrayOfBtn[6].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[7].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(""))) {
            makeStep = true;
            btnToStep = 8;
        }
        if ((makeStep == false)
                & (arrayOfBtn[6].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[7].getText().equals(""))) {
            makeStep = true;
            btnToStep = 7;
        }
        if ((makeStep == false)
                & (arrayOfBtn[7].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[6].getText().equals(""))) {
            makeStep = true;
            btnToStep = 6;
        }


        if ((makeStep == false)
                & (arrayOfBtn[0].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[3].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[6].getText().equals(""))) {
            makeStep = true;
            btnToStep = 6;
        }
        if ((makeStep == false)
                & (arrayOfBtn[0].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[6].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[3].getText().equals(""))) {
            makeStep = true;
            btnToStep = 3;
        }
        if ((makeStep == false)
                & (arrayOfBtn[3].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[6].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[0].getText().equals(""))) {
            makeStep = true;
            btnToStep = 0;
        }


        if ((makeStep == false)
                & (arrayOfBtn[1].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[7].getText().equals(""))) {
            makeStep = true;
            btnToStep = 7;
        }
        if ((makeStep == false)
                & (arrayOfBtn[1].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[7].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(""))) {
            makeStep = true;
            btnToStep = 4;
        }
        if ((makeStep == false)
                & (arrayOfBtn[4].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[7].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[1].getText().equals(""))) {
            makeStep = true;
            btnToStep = 1;
        }


        if ((makeStep == false)
                & (arrayOfBtn[2].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[5].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(""))) {
            makeStep = true;
            btnToStep = 8;
        }
        if ((makeStep == false)
                & (arrayOfBtn[2].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[5].getText().equals(""))) {
            makeStep = true;
            btnToStep = 5;
        }
        if ((makeStep == false)
                & (arrayOfBtn[5].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[2].getText().equals(""))) {
            makeStep = true;
            btnToStep = 2;
        }


        if ((makeStep == false)
                & (arrayOfBtn[0].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(""))) {
            makeStep = true;
            btnToStep = 8;
        }
        if ((makeStep == false)
                & (arrayOfBtn[0].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(""))) {
            makeStep = true;
            btnToStep = 4;
        }
        if ((makeStep == false)
                & (arrayOfBtn[4].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[0].getText().equals(""))) {
            makeStep = true;
            btnToStep = 0;
        }


        if ((makeStep == false)
                & (arrayOfBtn[2].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[6].getText().equals(""))) {
            makeStep = true;
            btnToStep = 6;
        }
        if ((makeStep == false)
                & (arrayOfBtn[2].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[6].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(""))) {
            makeStep = true;
            btnToStep = 4;
        }
        if ((makeStep == false)
                & (arrayOfBtn[4].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[6].getText().equals(rightPlr.getSymbol()))
                & (arrayOfBtn[2].getText().equals(""))) {
            makeStep = true;
            btnToStep = 2;
        }
        Log.d(LOG_TAG, " test" );
        return btnToStep;
    }

    public int checkLooseStep (){

        if ((makeStep == false)
                & (arrayOfBtn[0].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[1].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[2].getText().equals(""))) {
            makeStep = true;
            btnToStep = 2;
        }

        if ((makeStep == false)
                & (arrayOfBtn[0].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[2].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[1].getText().equals(""))) {
            makeStep = true;
            btnToStep = 1;

        }
        if ((makeStep == false)
                & (arrayOfBtn[1].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[2].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[0].getText().equals(""))) {
            makeStep = true;
            btnToStep = 0;
        }

        if ((makeStep == false)
                & (arrayOfBtn[3].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[5].getText().equals(""))) {
            makeStep = true;
            btnToStep = 5;
        }
        if ((makeStep == false)
                & (arrayOfBtn[3].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[5].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(""))) {
            makeStep = true;
            btnToStep = 4;
        }
        if ((makeStep == false)
                & (arrayOfBtn[4].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[5].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[3].getText().equals(""))) {
            makeStep = true;
            btnToStep = 3;
        }


        if ((makeStep == false)
                & (arrayOfBtn[6].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[7].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(""))) {
            makeStep = true;
            btnToStep = 8;
        }
        if ((makeStep == false)
                & (arrayOfBtn[6].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[7].getText().equals(""))) {
            makeStep = true;
            btnToStep = 7;
        }
        if ((makeStep == false)
                & (arrayOfBtn[7].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[6].getText().equals(""))) {
            makeStep = true;
            btnToStep = 6;
        }


        if ((makeStep == false)
                & (arrayOfBtn[0].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[3].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[6].getText().equals(""))) {
            makeStep = true;
            btnToStep = 6;
        }
        if ((makeStep == false)
                & (arrayOfBtn[0].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[6].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[3].getText().equals(""))) {
            makeStep = true;
            btnToStep = 3;
        }
        if ((makeStep == false)
                & (arrayOfBtn[3].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[6].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[0].getText().equals(""))) {
            makeStep = true;
            btnToStep = 0;
        }


        if ((makeStep == false)
                & (arrayOfBtn[1].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[7].getText().equals(""))) {
            makeStep = true;
            btnToStep = 7;
        }
        if ((makeStep == false)
                & (arrayOfBtn[1].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[7].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(""))) {
            makeStep = true;
            btnToStep = 4;
        }
        if ((makeStep == false)
                & (arrayOfBtn[4].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[7].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[1].getText().equals(""))) {
            makeStep = true;
            btnToStep = 1;
        }


        if ((makeStep == false)
                & (arrayOfBtn[2].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[5].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(""))) {
            makeStep = true;
            btnToStep = 8;
        }
        if ((makeStep == false)
                & (arrayOfBtn[2].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[5].getText().equals(""))) {
            makeStep = true;
            btnToStep = 5;
        }
        if ((makeStep == false)
                & (arrayOfBtn[5].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[2].getText().equals(""))) {
            makeStep = true;
            btnToStep = 2;
        }


        if ((makeStep == false)
                & (arrayOfBtn[0].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(""))) {
            makeStep = true;
            btnToStep = 8;
        }
        if ((makeStep == false)
                & (arrayOfBtn[0].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(""))) {
            makeStep = true;
            btnToStep = 4;
        }
        if ((makeStep == false)
                & (arrayOfBtn[4].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[8].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[0].getText().equals(""))) {
            makeStep = true;
            btnToStep = 0;
        }


        if ((makeStep == false)
                & (arrayOfBtn[2].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[6].getText().equals(""))) {
            makeStep = true;
            btnToStep = 6;
        }
        if ((makeStep == false)
                & (arrayOfBtn[2].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[6].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[4].getText().equals(""))) {
            makeStep = true;
            btnToStep = 4;
        }
        if ((makeStep == false)
                & (arrayOfBtn[4].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[6].getText().equals(leftPlr.getSymbol()))
                & (arrayOfBtn[2].getText().equals(""))) {
            makeStep = true;
            btnToStep = 2;
        }

        return btnToStep;
    }

}
