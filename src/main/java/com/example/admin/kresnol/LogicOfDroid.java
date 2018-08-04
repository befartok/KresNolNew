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
    MainModel model;

    /*// TODO: 03.08.18 проверить работоспособность, или же передовать экземпляр из презентера
    Player leftPlayer;
    Player rightPlayer;*/



    // TODO: 02.08.18 сделать функции, возвращать в презентер номер кнопки для хода
    public int droidsStep(Button[] arrayOfBtn, Player leftPlr, Player rightPlr, MainModel mainModel) {
        //убрал инвертирование из метода, вынес перед вызовом метода
        //invertPlayersActivity();

        //Log.d(LOG_TAG, "500 droidsStep");
        //int x;
        //int xx;
        //boolean makeStep = false;
        //TimeUnit.SECONDS.sleep(1);
        //switch (spinnerLevel.getSelectedItem().toString()) {
        // TODO: 03.08.18 подумать над созданием разных массивов с перезаписью их при вызове метода

        switch (mainModel.getSpinnerLeftValue()) {
            case "Easy":

                //// TODO: 06.07.18 вынести в класс логики
                // рэндом нажатие кнопки андроидом
                btnToDroidStep = makeRandomStep();
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
                btnToDroidStep = checkWinStep();

                //проверка возможности проигрыша
                btnToDroidStep = checkLooseStep();

                //проверка возможности поставить в центр
                btnToDroidStep = makeCenterStep();

                //если раньше не было ходов то сделать случайный ход

                if (makeStep == false) {
                    // рэндом нажатие кнопки андроидом
                    btnToDroidStep = makeRandomStep();
                }

                break;

            case "Hard":
                // логику для 3 уровня
                // TODO: 09.07.18 делать угловой хода на 2-м шаге, доработать и на 5-м правильно составить треугольник

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
        /*Log.d(LOG_TAG, "526 test checkEmptyButton ");
        Log.d(LOG_TAG, "527 numberOfButton= " + numberOfButton);
        Log.d(LOG_TAG, "535 arrayOfButtons[numberOfButton].getText()= " + arrayOfButtons[numberOfButton].getText());
*/
        while ((arrayOfBtn[numberOfButton].getText().equals("x"))
                | (arrayOfBtn[numberOfButton].getText().equals("o"))) {
            numberOfButton = numberOfButton + 1;
            //Log.d(LOG_TAG, "532 numberOfButton+1 = " + numberOfButton);

            if (numberOfButton == 9) {
                numberOfButton = 0;
            }
            //checkEmptyButton(numberOfButton);
           /* Log.d(LOG_TAG, "538 test checkEmptyButton ");
            Log.d(LOG_TAG, "539 numberOfButton= " + numberOfButton);
*/
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
        //Log.d(LOG_TAG, "510 x=(int)(Math.random()*8) = " + varRandom);

        return  checkEmptyButton(varRandom);
        //clickPlayFieldBtn(arrayOfButtons[xx]);

        //clickPlayFieldBtn(arrayOfButtons[checkEmptyButton(varRandom)]);
    }

    public int checkWinStep() {

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
        //Log.d(LOG_TAG, "850 makeStep =" + makeStep);
        return btnToStep;
    }

    public int checkLooseStep (){

       /* Log.d(LOG_TAG, "855 checkLooseStep ");
        Log.d(LOG_TAG, "850 makeStep =" + makeStep);
*/
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

        //Log.d(LOG_TAG, "1042 makeStep =" + makeStep);

        return btnToStep;

    }

}
