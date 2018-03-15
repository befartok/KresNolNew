package com.example.admin.kresnol;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";

    int button;
    int clickedButtonsTotal = 0;
    //int btnNumber;

    String SymbolOfStep = "x";
    String SymbolOfNextStep = "o";
    String statusGames = "ready";

    TextView playerLeft;
    TextView playerRight;

    String nameOfPlayerLeft = "Player";
    String nameOfPlayerRight = "Player2";
    String typeOfPlayerLeft = "livePlayer";
    String typeOfPlayerRight = "livePlayer";

    String playField[] = new String[9];

    int totalWinLeft = 0;
    int totalWinRight = 0;

    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;

    Button symbolOfBtnLeft;
    Button symbolOfBtnRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "statusGame-ready");


    }

    public void onclick(View v) {

        //button = v.getId();

        // TODO: 09.03.18 ???/?
        //LinearLayout layout0 = (LinearLayout) findViewById(R.id.layout0);

        //определение кнопки игрового поля
        // TODO: 09.03.18 обявить кнопки глобально, а найти в онкреате
        btn0 = (Button) findViewById(R.id.button0);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);

        playerLeft = (TextView) findViewById(R.id.textViewLeftPlayer);
        playerRight = (TextView) findViewById(R.id.textViewRightPlayer);


        // занести кнопки в массив
        //Button arrayOfButton[] = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8};


        //определение кнопки выбора символов
        symbolOfBtnLeft = (Button) findViewById(R.id.buttonSymbol);
        symbolOfBtnRight = (Button) findViewById(R.id.buttonSymbol2);

        // TODO: 24.02.18 если клик на имени, дать или выбрать или ввести

        // TODO: 24.02.18 если клик на аватарке, дать выбрать тип игрока

        // TODO: 24.02.18 связать имя игрока с авой и наоборот

        // TODO: 11.03.18 передавать ход игрокам, сохранять текущего игрока

        // TODO: 12.03.18 исправить первый ход ходит левый независимо от крестика

        // TODO: 12.03.18 подсветку ходящего распространить и на игру

        // TODO: 12.03.18 заменить подсветку ходящего на анимацию

        //изменение символов для игроков
        switch (v.getId()) {
            case R.id.buttonSymbol:

                if (statusGames == "ready") {
                    invertVariables();
                    symbolOfBtnLeft.setText(SymbolOfStep);
                    symbolOfBtnRight.setText(SymbolOfNextStep);

                    if (clickedButtonsTotal == 0){
                        makeNameActive("leftButton");
                    }
                }
                /*else {
                    symbolOfBtnLeft.setEnabled(false);
                }*/
                break;

            case R.id.buttonSymbol2:
                if (statusGames == "ready") {
                    invertVariables();
                    symbolOfBtnLeft.setText(SymbolOfStep);
                    symbolOfBtnRight.setText(SymbolOfNextStep);

                    if (clickedButtonsTotal == 0){
                        makeNameActive("rightButton");
                    }
                }

                /*else {
                    symbolOfBtnRight.setEnabled(false);
                }*/
                break;

            //перезапуск
            case R.id.layout0:
                if (statusGames == "finish") {
                    restartGame();
                }
                break;


            //нажатие кнопки игроком в игровом поле
            case R.id.button0:
                clickPlayFieldBtn(0, btn0);
                break;
            case R.id.button1:
                clickPlayFieldBtn(1, btn1);
                break;
            case R.id.button2:
                clickPlayFieldBtn(2, btn2);
                break;
            case R.id.button3:
                clickPlayFieldBtn(3, btn3);
                break;
            case R.id.button4:
                clickPlayFieldBtn(4, btn4);
                break;
            case R.id.button5:
                clickPlayFieldBtn(5, btn5);
                break;
            case R.id.button6:
                clickPlayFieldBtn(6, btn6);
                break;
            case R.id.button7:
                clickPlayFieldBtn(7, btn7);
                break;
            case R.id.button8:
                clickPlayFieldBtn(8, btn8);
                break;
        }

    }

    public void clickPlayFieldBtn(int btnNumber, Button btn) {

        // TODO: 13.03.18 если ходит правый, то после выигрыша ходящий не меняется. исправить

        if (clickedButtonsTotal == 0){
            if (symbolOfBtnLeft.getText() == "o"){
                invertVariables();
            }
        }
       // Log.d(LOG_TAG, "SymbolOfStep" + SymbolOfStep);

        // проверка нажатости кнопки и закончившей игры
        if ((statusGames != "finish") & (playField[btnNumber] == null)) {
            statusGames = "inplay";
            Log.d(LOG_TAG, "statusGames-inplay");
            disableChangeSymbol();


            //Log.d(LOG_TAG, "v.getId() - " + v.getId());

            //включаем нажатость кнопки
            //btn.setEnabled(false);

            //установка на кнопки игры выбранного символа
            //Button btn = (Button) findViewById(v.getId());
            btn.setText(SymbolOfStep);

            //счетчик нажатых кнопок
            clickedButtonsTotal ++;
            Log.d(LOG_TAG, "clickedButtonsTotal " + clickedButtonsTotal );


            // занести в массив номер кнопки
            playField[btnNumber] = SymbolOfStep;
            Log.d(LOG_TAG, "playField[" + btnNumber + "] " + playField[btnNumber]);

             // проверка на выигрыш
            checkWin();

            //проверка числа нажатых кнопок
            if (clickedButtonsTotal  == 9) {
                statusGames = "finish";
                Log.d(LOG_TAG, "statusGames-finish");

            }

            // TODO: 24.02.18 передать ход 2му игроку.
            //если андроид, то ход по алгоритму, иначе обрабатывать нажатие
            //???инверсия для следующего хода
            if (statusGames == "inplay") {
                invertVariables();
            }
            // TODO: 11.03.18 проверить правильность рестарта внутри нажатости кнопки
        } else if (statusGames == "finish") {

            restartGame();
        }


    }

    private void restartGame() {
        /*for (int i = 0; i < 9; i++) {
            Log.d(LOG_TAG, "playField[" + i + "]=" + playField[i]);
        }*/
        Log.d(LOG_TAG, "restart");

        clickedButtonsTotal = 0;


        btn0.setTextColor(Color.BLACK);
        btn0.setText("");
        btn1.setTextColor(Color.BLACK);
        btn1.setText("");
        btn2.setTextColor(Color.BLACK);
        btn2.setText("");
        btn3.setTextColor(Color.BLACK);
        btn3.setText("");
        btn4.setTextColor(Color.BLACK);
        btn4.setText("");
        btn5.setTextColor(Color.BLACK);
        btn5.setText("");
        btn6.setTextColor(Color.BLACK);
        btn6.setText("");
        btn7.setTextColor(Color.BLACK);
        btn7.setText("");
        btn8.setTextColor(Color.BLACK);
        btn8.setText("");

        statusGames = "ready";
        Log.d(LOG_TAG, "statusGames-ready");

        for (int i = 0; i < 9; i++) {
            playField[i] = null;
        }

        enableChangeSymbol();
        invertVariables();

    }

    public void disableChangeSymbol() {
        //определение кнопки выбора символов
        /*Button symbolOfBtnLeft = (Button) findViewById(R.id.buttonSymbol);
        Button symbolOfBtnRight = (Button) findViewById(R.id.buttonSymbol2);*/
        symbolOfBtnLeft.setEnabled(false);
        symbolOfBtnRight.setEnabled(false);
    }

    public void enableChangeSymbol() {

        symbolOfBtnLeft.setEnabled(true);
        symbolOfBtnRight.setEnabled(true);
    }

    public void checkWin() {


        // TODO: 10.03.18 заменить финиш на вин
        if ((playField[0] == SymbolOfStep) & (playField[1] == SymbolOfStep)
                & (playField[2] == SymbolOfStep)) {
            statusGames = "finish";
            Log.d(LOG_TAG, "statusGames-finish");
            btn0.setTextColor(Color.RED);
            btn1.setTextColor(Color.RED);
            btn2.setTextColor(Color.RED);

            saveResult(playField[0]);


        }

        if ((playField[3] == SymbolOfStep) & (playField[4] == SymbolOfStep)
                & (playField[5] == SymbolOfStep)) {
            statusGames = "finish";
            Log.d(LOG_TAG, "statusGames-finish");

            btn3.setTextColor(Color.RED);
            btn4.setTextColor(Color.RED);
            btn5.setTextColor(Color.RED);

            saveResult(playField[3]);


        }

        if ((playField[6] == SymbolOfStep) & (playField[7] == SymbolOfStep)
                & (playField[8] == SymbolOfStep)) {
            statusGames = "finish";
            Log.d(LOG_TAG, "statusGames-finish");

            btn6.setTextColor(Color.RED);
            btn7.setTextColor(Color.RED);
            btn8.setTextColor(Color.RED);

            saveResult(playField[6]);

        }

        if ((playField[0] == SymbolOfStep) & (playField[3] == SymbolOfStep)
                & (playField[6] == SymbolOfStep)) {
            statusGames = "finish";
            Log.d(LOG_TAG, "statusGames-finish");

            btn0.setTextColor(Color.RED);
            btn3.setTextColor(Color.RED);
            btn6.setTextColor(Color.RED);

            saveResult(playField[0]);


        }

        if ((playField[1] == SymbolOfStep) & (playField[4] == SymbolOfStep)
                & (playField[7] == SymbolOfStep)) {
            statusGames = "finish";
            Log.d(LOG_TAG, "statusGames-finish");

            btn1.setTextColor(Color.RED);
            btn4.setTextColor(Color.RED);
            btn7.setTextColor(Color.RED);

            saveResult(playField[1]);


        }

        if ((playField[2] == SymbolOfStep) & (playField[5] == SymbolOfStep)
                & (playField[8] == SymbolOfStep)) {
            statusGames = "finish";
            Log.d(LOG_TAG, "statusGames-finish");

            btn2.setTextColor(Color.RED);
            btn5.setTextColor(Color.RED);
            btn8.setTextColor(Color.RED);

            saveResult(playField[2]);

        }

        if ((playField[0] == SymbolOfStep) & (playField[4] == SymbolOfStep)
                & (playField[8] == SymbolOfStep)) {
            statusGames = "finish";
            Log.d(LOG_TAG, "statusGames-finish");

            btn0.setTextColor(Color.RED);
            btn4.setTextColor(Color.RED);
            btn8.setTextColor(Color.RED);

            saveResult(playField[0]);


        }

        if ((playField[2] == SymbolOfStep) & (playField[4] == SymbolOfStep)
                & (playField[6] == SymbolOfStep)) {
            statusGames = "finish";
            Log.d(LOG_TAG, "statusGames-finish");

            btn2.setTextColor(Color.RED);
            btn4.setTextColor(Color.RED);
            btn6.setTextColor(Color.RED);

            saveResult(playField[2]);


        }


    }

    public void saveResult(String winSimbol) {

        Log.d(LOG_TAG, "winSimbol=" + winSimbol);

        Log.d(LOG_TAG, "symbolOfBtnLeft=" + symbolOfBtnLeft.getText());
        Log.d(LOG_TAG, "symbolOfBtnRight=" + symbolOfBtnRight.getText());

        TextView winLeft = (TextView) findViewById(R.id.totalWinLeftPlayer);
        TextView winRight = (TextView) findViewById(R.id.totalWinRightPlayer);

        if (symbolOfBtnLeft.getText().equals(winSimbol)) {
            Log.d(LOG_TAG, "left win=");

            totalWinLeft++;
            Log.d(LOG_TAG, "totalWinLeft=" + totalWinLeft);

            winLeft.setText(Integer.toString(totalWinLeft));
        } else if (symbolOfBtnRight.getText().equals(winSimbol)) {
            Log.d(LOG_TAG, "right win=");

            totalWinRight++;
            winRight.setText(Integer.toString(totalWinRight));
            Log.d(LOG_TAG, "totalWinRight=" + totalWinRight);

        }


    }

    public void makeNameActive(String selectedSymbolsButton){

        switch (selectedSymbolsButton ) {
            case "leftButton":
                if (symbolOfBtnLeft.getText().equals("x")){
                    playerLeft.setTextColor(getResources().getColor(R.color.buttonsTextActive));
                    playerLeft.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);
                    playerRight.setTextColor(getResources().getColor(R.color.buttonsText));
                    playerRight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
                } else if (symbolOfBtnLeft.getText().equals("o")){
                    playerLeft.setTextColor(getResources().getColor(R.color.buttonsText));
                    playerLeft.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
                    playerRight.setTextColor(getResources().getColor(R.color.buttonsTextActive));
                    playerRight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);
                }
                break;
            case "rightButton":
                if (symbolOfBtnRight.getText().equals("x")){
                    playerLeft.setTextColor(getResources().getColor(R.color.buttonsText));
                    playerLeft.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
                    playerRight.setTextColor(getResources().getColor(R.color.buttonsTextActive));
                    playerRight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);
                } else if (symbolOfBtnRight.getText().equals("o")){
                    playerLeft.setTextColor(getResources().getColor(R.color.buttonsTextActive));
                    playerLeft.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);
                    playerRight.setTextColor(getResources().getColor(R.color.buttonsText));
                    playerRight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
                }
                break;
        }


    }

    public void invertVariables() {
        String buferVariable;

        buferVariable = SymbolOfStep;
        SymbolOfStep = SymbolOfNextStep;
        SymbolOfNextStep = buferVariable;

    }
}
