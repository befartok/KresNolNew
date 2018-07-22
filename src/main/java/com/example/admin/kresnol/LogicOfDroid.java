package com.example.admin.kresnol;

import android.util.Log;

/**
 * Created by admin on 20.07.18.
 */

public class LogicOfDroid {

    boolean makeStep;

    public void droidsStep() {
        invertPlayersActivity();
        //Log.d(LOG_TAG, "500 droidsStep");
        //int x;
        //int xx;
        //boolean makeStep = false;
        //TimeUnit.SECONDS.sleep(1);
        //switch (spinnerLevel.getSelectedItem().toString()) {
        switch (MainActivity.getSpinnerLevel()) {
            case "Easy":

                //// TODO: 06.07.18 вынести в класс логики

                // рэндом нажатие кнопки андроидом
                makeRandomStep();
               /* x = (int) (Math.random() * 8);
                Log.d(LOG_TAG, "510 x=(int)(Math.random()*8) = " + x);

                xx = checkEmptyButton(x);
                clickPlayFieldBtn(arrayOfButtons[xx]);*/
                //clickPlayFieldBtn(arrayOfButtons[checkEmptyButton(x)]);

                break;
            case "Normal":
                //Log.d(LOG_TAG, "сделать логику для 2 уровня ");
                makeStep = false;

                //проверка возможности выигрыша
                checkWinStep();

                //проверка возможности проигрыша
                checkLooseStep();

                //проверка возможности поставить в центр
                makeCenterStep();

                //если раньше не было ходов то сделать случайный ход

                if (makeStep == false) {
                    // рэндом нажатие кнопки андроидом
                    makeRandomStep();
                }

                break;

            case "Hard":
                // TODO: 03.04.18 сделать логику для 3 уровня
                // TODO: 09.07.18 делать угловой хода на 2-м шаге, доработать и на 5-м правильно составить треугольник

                Log.d(LOG_TAG, "сделать логику для 3 уровня ");

                makeStep = false;

                //проверка возможности выигрыша
                checkWinStep();

                //проверка возможности проигрыша
                checkLooseStep();

                //проверка возможности поставить в центр
                makeCenterStep();

                //ставить в угол
                makeCornerStep();

                //если раньше не было ходов то сделать случайный ход
                if (makeStep == false) {
                    // рэндом нажатие кнопки андроидом
                    makeRandomStep();
                }
                break;
        }
    }

    //проверка ячейки на незанятость, иначе брать следующую
    public int checkEmptyButton(int numberOfButton) {
        Log.d(LOG_TAG, "526 test checkEmptyButton ");
        Log.d(LOG_TAG, "527 numberOfButton= " + numberOfButton);

        Log.d(LOG_TAG, "535 arrayOfButtons[numberOfButton].getText()= " + arrayOfButtons[numberOfButton].getText());

        while ((arrayOfButtons[numberOfButton].getText().equals("x"))
                | (arrayOfButtons[numberOfButton].getText().equals("o"))) {
            numberOfButton = numberOfButton + 1;
            Log.d(LOG_TAG, "532 numberOfButton+1 = " + numberOfButton);

            if (numberOfButton == 9) {
                numberOfButton = 0;
            }
            //checkEmptyButton(numberOfButton);
            Log.d(LOG_TAG, "538 test checkEmptyButton ");
            Log.d(LOG_TAG, "539 numberOfButton= " + numberOfButton);

        }
        return (numberOfButton);
    }

    public void makeCenterStep(){
        if ((makeStep == false) & (arrayOfButtons[4].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[4]);
        }
    }

    public void makeCornerStep(){
        if ((makeStep == false)&(arrayOfButtons[0].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[0]);
        }
        if ((makeStep == false)&(arrayOfButtons[2].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[2]);
        }
        if ((makeStep == false)&(arrayOfButtons[5].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[5]);
        }
        if ((makeStep == false)&(arrayOfButtons[8].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[8]);
        }


    }

    // рэндом нажатие кнопки андроидом
    public void makeRandomStep(){

        int varRandom ;

        varRandom = (int) (Math.random() * 8);
        Log.d(LOG_TAG, "510 x=(int)(Math.random()*8) = " + varRandom);

        //xx = checkEmptyButton(x);
        //clickPlayFieldBtn(arrayOfButtons[xx]);
        clickPlayFieldBtn(arrayOfButtons[checkEmptyButton(varRandom)]);
    }

    public void checkWinStep() {

        if ((makeStep == false)
                & (arrayOfButtons[0].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[1].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[2].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[2]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[0].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[2].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[1].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[1]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[1].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[2].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[0].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[0]);
        }

        if ((makeStep == false)
                & (arrayOfButtons[3].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[5].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[5]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[3].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[5].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[4]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[4].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[5].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[3].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[3]);
        }


        if ((makeStep == false)
                & (arrayOfButtons[6].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[7].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[8]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[6].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[7].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[7]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[7].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[6].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[6]);
        }


        if ((makeStep == false)
                & (arrayOfButtons[0].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[3].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[6].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[6]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[0].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[6].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[3].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[3]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[3].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[6].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[0].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[0]);
        }


        if ((makeStep == false)
                & (arrayOfButtons[1].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[7].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[7]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[1].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[7].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[4]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[4].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[7].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[1].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[1]);
        }


        if ((makeStep == false)
                & (arrayOfButtons[2].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[5].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[8]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[2].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[5].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[5]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[5].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[2].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[2]);
        }


        if ((makeStep == false)
                & (arrayOfButtons[0].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[8]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[0].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[4]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[4].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[0].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[0]);
        }


        if ((makeStep == false)
                & (arrayOfButtons[2].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[6].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[6]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[2].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[6].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[4]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[4].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[6].getText().equals(rightPlayer.getSymbol()))
                & (arrayOfButtons[2].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[2]);
        }
        Log.d(LOG_TAG, "850 makeStep =" + makeStep);
    }

    public void checkLooseStep (){

        Log.d(LOG_TAG, "855 checkLooseStep ");
        Log.d(LOG_TAG, "850 makeStep =" + makeStep);

        if ((makeStep == false)
                & (arrayOfButtons[0].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[1].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[2].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[2]);
        }

        if ((makeStep == false)
                & (arrayOfButtons[0].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[2].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[1].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[1]);

        }
        if ((makeStep == false)
                & (arrayOfButtons[1].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[2].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[0].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[0]);
        }

        if ((makeStep == false)
                & (arrayOfButtons[3].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[5].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[5]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[3].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[5].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[4]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[4].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[5].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[3].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[3]);
        }


        if ((makeStep == false)
                & (arrayOfButtons[6].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[7].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[8]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[6].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[7].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[7]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[7].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[6].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[6]);
        }


        if ((makeStep == false)
                & (arrayOfButtons[0].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[3].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[6].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[6]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[0].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[6].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[3].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[3]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[3].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[6].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[0].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[0]);
        }


        if ((makeStep == false)
                & (arrayOfButtons[1].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[7].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[7]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[1].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[7].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[4]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[4].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[7].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[1].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[1]);
        }


        if ((makeStep == false)
                & (arrayOfButtons[2].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[5].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[8]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[2].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[5].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[5]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[5].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[2].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[2]);
        }


        if ((makeStep == false)
                & (arrayOfButtons[0].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[8]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[0].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[4]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[4].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[8].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[0].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[0]);
        }


        if ((makeStep == false)
                & (arrayOfButtons[2].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[6].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[6]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[2].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[6].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[4].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[4]);
        }
        if ((makeStep == false)
                & (arrayOfButtons[4].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[6].getText().equals(leftPlayer.getSymbol()))
                & (arrayOfButtons[2].getText().equals(""))) {
            makeStep = true;
            clickPlayFieldBtn(arrayOfButtons[2]);
        }

        Log.d(LOG_TAG, "1042 makeStep =" + makeStep);

    }

}
