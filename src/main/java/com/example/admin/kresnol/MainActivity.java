package com.example.admin.kresnol;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";

    int button;
    int clickedButtonsTotal = 0;
    //int btnNumber;

    String symbolActiv = "x";
    String symbolNext = "o";
    String statusGames = "ready";

    TextView nameOfPlayerLeft;
    TextView nameOfPlayerRight;

    //String nameOfPlayerLeft = "Player";
    //String nameOfPlayerRight = "Player2";
    String typeOfPlayerLeft = "livePlayer";
    String typeOfPlayerRight = "livePlayer";

    String level = "easy";

    // TODO: 04.04.18 заменить массив результата на массив кнопок
    String playField[] = new String[9];

    int totalWinLeft = 0;
    int totalWinRight = 0;

    int numOfRestart = 0;

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

    Player leftPlayer;
    Player rightPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "statusGame-ready");

        //определение кнопки игрового поля
        btn0 = (Button) findViewById(R.id.button0);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);

        Log.d(LOG_TAG, "btn0.getText()= " + btn0.getText());


        nameOfPlayerLeft = (TextView) findViewById(R.id.textViewLeftPlayer);
        nameOfPlayerRight = (TextView) findViewById(R.id.textViewRightPlayer);
        // TODO: 01.04.18 реализовать выбор андроида-игрока
        nameOfPlayerRight.setText("Android");

        // TODO: 04.04.18 использовать массив
        // занести кнопки в массив
        Button arrayOfButton[] = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8};

        //определение кнопки выбора символов
        symbolOfBtnLeft = (Button) findViewById(R.id.buttonSymbol);
        symbolOfBtnRight = (Button) findViewById(R.id.buttonSymbol2);

        leftPlayer = new Player(nameOfPlayerLeft.getText());
        rightPlayer = new Player(nameOfPlayerRight.getText());

        // присвоить символы и другие значения по умолчанию
        leftPlayer.setSymbol("x");
        leftPlayer.setActiv(true);
        rightPlayer.setSymbol("o");
        rightPlayer.setActiv(false);
    }

    public void onclick(View v) {

        //button = v.getId();
        //LinearLayout layout0 = (LinearLayout) findViewById(R.id.layout0);

        // TODO: 24.02.18 если клик на имени, дать или выбрать или ввести

        // TODO: 24.02.18 если клик на аватарке, дать выбрать тип игрока

        // TODO: 24.02.18 связать имя игрока с авой и наоборот

        // TODO: 12.03.18 заменить подсветку ходящего на анимацию


        //изменение символов для игроков
        switch (v.getId()) {
            case R.id.buttonSymbol:

                if (statusGames == "ready") {
                    // меняет символ
                    invertVariables();

                    symbolOfBtnLeft.setText(leftPlayer.getSymbol());
                    symbolOfBtnRight.setText(rightPlayer.getSymbol());

                    if ((clickedButtonsTotal == 0) & (numOfRestart == 0)) {
                        Log.d(LOG_TAG, "clickedButtonsTotal=" + clickedButtonsTotal);

                        if (leftPlayer.getSymbol().equals("x")) {
                            leftPlayer.setActiv(true);
                            makeNameActive("leftButton");
                            rightPlayer.setActiv(false);
                        } else if (leftPlayer.getSymbol().equals("o")) {
                            leftPlayer.setActiv(false);
                            makeNameActive("rightButton");
                            rightPlayer.setActiv(true);
                        }
                    }
                }
                break;

            case R.id.buttonSymbol2:
                if (statusGames == "ready") {
                    invertVariables();

                    symbolOfBtnRight.setText(rightPlayer.getSymbol());
                    symbolOfBtnLeft.setText(leftPlayer.getSymbol());

                    if ((clickedButtonsTotal == 0) & (numOfRestart == 0)) {
                        if (rightPlayer.getSymbol().equals("x")) {
                            rightPlayer.setActiv(true);
                            makeNameActive("rightButton");
                            leftPlayer.setActiv(false);
                        } else if (rightPlayer.getSymbol().equals("o")) {
                            rightPlayer.setActiv(false);
                            makeNameActive("leftButton");
                            leftPlayer.setActiv(true);
                        }
                    }
                }
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


        // проверка нажатости кнопки и закончившесяй игры
        if ((statusGames != "finish") & (playField[btnNumber] == null)) {
            statusGames = "inplay";
            Log.d(LOG_TAG, "statusGames-inplay");
            disableChangeSymbol();


            if (leftPlayer.isActiv()) {
                symbolActiv = leftPlayer.getSymbol();
            } else if (rightPlayer.isActiv()) {
                symbolActiv = rightPlayer.getSymbol();
            }
            btn.setText(symbolActiv);

            //счетчик нажатых кнопок
            clickedButtonsTotal++;
            Log.d(LOG_TAG, "clickedButtonsTotal " + clickedButtonsTotal);

            // занести в массив номер кнопки
            playField[btnNumber] = symbolActiv;
            Log.d(LOG_TAG, "playField[" + btnNumber + "] " + playField[btnNumber]);

            // проверка на выигрыш
            checkWin();

            //проверка числа нажатых кнопок
            if (clickedButtonsTotal == 9) {
                statusGames = "finish";
                Log.d(LOG_TAG, "statusGames-finish");
            }

            // TODO: 24.02.18 передать ход 2му игроку если андроид, то ход по алгоритму, иначе обрабатывать нажатие
            //инверсия активности игроков для следующего хода

            if (nameOfPlayerRight.getText().equals("Android")) {
                //todo сделать алгоритм хода андроида
                Log.d(LOG_TAG, "ход андроида");
            } else if (statusGames == "inplay") {
                //invertVariables();
                invertPlayersActivity();
            }

            // TODO: 01.04.18 проверить необходимость этого рестарта
        } else if (statusGames == "finish") {
            restartGame();
        }
    }

    private void restartGame() {

        Log.d(LOG_TAG, "restart");
        numOfRestart++;

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
        invertPlayersActivity();

        if ((nameOfPlayerRight.getText().equals("Android")) & rightPlayer.isActiv()) {
            //todo сделать алгоритм хода андроида
            droidsStep();
        }

    }

    public void disableChangeSymbol() {

        symbolOfBtnLeft.setEnabled(false);
        symbolOfBtnRight.setEnabled(false);
    }

    public void enableChangeSymbol() {

        symbolOfBtnLeft.setEnabled(true);
        symbolOfBtnRight.setEnabled(true);
    }

    public void checkWin() {

        // TODO: 10.03.18 заменить финиш на вин
        if ((playField[0] == symbolActiv) & (playField[1] == symbolActiv)
                & (playField[2] == symbolActiv)) {
            statusGames = "finish";
            Log.d(LOG_TAG, "statusGames-finish");
            btn0.setTextColor(Color.RED);
            btn1.setTextColor(Color.RED);
            btn2.setTextColor(Color.RED);

            saveResult(playField[0]);
        }

        if ((playField[3] == symbolActiv) & (playField[4] == symbolActiv)
                & (playField[5] == symbolActiv)) {
            statusGames = "finish";
            Log.d(LOG_TAG, "statusGames-finish");

            btn3.setTextColor(Color.RED);
            btn4.setTextColor(Color.RED);
            btn5.setTextColor(Color.RED);

            saveResult(playField[3]);
        }

        if ((playField[6] == symbolActiv) & (playField[7] == symbolActiv)
                & (playField[8] == symbolActiv)) {
            statusGames = "finish";
            Log.d(LOG_TAG, "statusGames-finish");

            btn6.setTextColor(Color.RED);
            btn7.setTextColor(Color.RED);
            btn8.setTextColor(Color.RED);

            saveResult(playField[6]);

        }

        if ((playField[0] == symbolActiv) & (playField[3] == symbolActiv)
                & (playField[6] == symbolActiv)) {
            statusGames = "finish";
            Log.d(LOG_TAG, "statusGames-finish");

            btn0.setTextColor(Color.RED);
            btn3.setTextColor(Color.RED);
            btn6.setTextColor(Color.RED);

            saveResult(playField[0]);
        }

        if ((playField[1] == symbolActiv) & (playField[4] == symbolActiv)
                & (playField[7] == symbolActiv)) {
            statusGames = "finish";
            Log.d(LOG_TAG, "statusGames-finish");

            btn1.setTextColor(Color.RED);
            btn4.setTextColor(Color.RED);
            btn7.setTextColor(Color.RED);

            saveResult(playField[1]);
        }

        if ((playField[2] == symbolActiv) & (playField[5] == symbolActiv)
                & (playField[8] == symbolActiv)) {
            statusGames = "finish";
            Log.d(LOG_TAG, "statusGames-finish");

            btn2.setTextColor(Color.RED);
            btn5.setTextColor(Color.RED);
            btn8.setTextColor(Color.RED);

            saveResult(playField[2]);
        }

        if ((playField[0] == symbolActiv) & (playField[4] == symbolActiv)
                & (playField[8] == symbolActiv)) {
            statusGames = "finish";
            Log.d(LOG_TAG, "statusGames-finish");

            btn0.setTextColor(Color.RED);
            btn4.setTextColor(Color.RED);
            btn8.setTextColor(Color.RED);

            saveResult(playField[0]);
        }

        if ((playField[2] == symbolActiv) & (playField[4] == symbolActiv)
                & (playField[6] == symbolActiv)) {
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

    public void makeNameActive(String selectedSymbolsButton) {

        switch (selectedSymbolsButton) {
            case "leftButton":
                nameOfPlayerLeft.setTextColor(getResources().getColor(R.color.buttonsTextActive));
                nameOfPlayerLeft.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);
                nameOfPlayerRight.setTextColor(getResources().getColor(R.color.buttonsText));
                nameOfPlayerRight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
                break;
            case "rightButton":
                nameOfPlayerLeft.setTextColor(getResources().getColor(R.color.buttonsText));
                nameOfPlayerLeft.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
                nameOfPlayerRight.setTextColor(getResources().getColor(R.color.buttonsTextActive));
                nameOfPlayerRight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);
                break;
        }
    }

    // меняет символ
    public void invertVariables() {
        String buferVariable;

        buferVariable = leftPlayer.getSymbol();
        leftPlayer.setSymbol(rightPlayer.getSymbol());
        rightPlayer.setSymbol(buferVariable);
    }

    public void invertPlayersActivity() {
        Boolean buferActivity;

        buferActivity = leftPlayer.isActiv();
        leftPlayer.setActiv(rightPlayer.isActiv());
        rightPlayer.setActiv(buferActivity);


        if (leftPlayer.isActiv()) {
            Log.d(LOG_TAG, "leftPlayer.isActiv()=true");
            Log.d(LOG_TAG, "leftPlayer.getSymbol()=" + leftPlayer.getSymbol());
            makeNameActive("leftButton");
        } else if (rightPlayer.isActiv()) {
            Log.d(LOG_TAG, "rightPlayer.isActiv()=true");
            Log.d(LOG_TAG, "rightPlayer.getSymbol()=" + rightPlayer.getSymbol());
            makeNameActive("rightButton");
        }

    }

    public void droidsStep() {
        Log.d(LOG_TAG, "ход андроида droidsStep");

        switch (level) {
            case "easy":

                // TODO: 03.04.18 сделать рэндом нажатие кнопки
                //x=Math.random()*8;




                break;
            case "normal":
                // TODO: 03.04.18 сделать логику для 2 уровня
                Log.d(LOG_TAG, "сделать логику для 2 уровня ");
                break;

            case "hard":
                // TODO: 03.04.18 сделать логику для 3 уровня
                Log.d(LOG_TAG, "сделать логику для 3 уровня ");
                break;
        }


    }
}
