package com.example.admin.kresnol;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";

    int button;
    int clickedButtonsTotal = 0;
    //int btnNumber;

    String symbolActiv = "x";
    String symbolNext = "o";
    String statusGames = "ready";

    String typeOfPlayerLeft = "livePlayer";
    String typeOfPlayerRight = "livePlayer";

    int totalWinLeft = 0;
    int totalWinRight = 0;

    int numOfRestart = 0;

    Button arrayOfButtons[] = new Button[9];

    Button symbolOfBtnLeft;
    Button symbolOfBtnRight;

    Player leftPlayer;
    Player rightPlayer;

    Spinner spinnerLeft;
    Spinner spinnerRight;
    Spinner spinnerLevel;

    String[] arrayOfPlayers = {"Player 1", "Player 2", "Android"};
    String[] arrayOfLevel = {"Easy", "Normal", "Hard"};

    boolean makeStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "64 statusGame-ready");

        //определение кнопки игрового поля
        arrayOfButtons[0] = (Button) findViewById(R.id.button0);
        arrayOfButtons[1] = (Button) findViewById(R.id.button1);
        arrayOfButtons[2] = (Button) findViewById(R.id.button2);
        arrayOfButtons[3] = (Button) findViewById(R.id.button3);
        arrayOfButtons[4] = (Button) findViewById(R.id.button4);
        arrayOfButtons[5] = (Button) findViewById(R.id.button5);
        arrayOfButtons[6] = (Button) findViewById(R.id.button6);
        arrayOfButtons[7] = (Button) findViewById(R.id.button7);
        arrayOfButtons[8] = (Button) findViewById(R.id.button8);

        //nameOfPlayerLeft = (TextView) findViewById(R.id.textViewLeftPlayer);
        //nameOfPlayerRight = (TextView) findViewById(R.id.textViewRightPlayer);

        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayOfPlayers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerLeft = (Spinner) findViewById(R.id.spinnerLeft);
        spinnerLeft.setAdapter(adapter);

        spinnerRight = (Spinner) findViewById(R.id.spinnerRight);
        spinnerRight.setAdapter(adapter);

        // заголовок
        //spinnerRight.setPrompt("Title");
        // выделяем элемент
        spinnerLeft.setSelection(0);
        spinnerRight.setSelection(1);

        // устанавливаем обработчик нажатия
        spinnerLeft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        spinnerRight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();

                //((TextView) spinnerRight.getSelectedView()).setGravity(Gravity.RIGHT);

                //видимость спинера уровня для игрока Андроид
                if (spinnerRight.getSelectedItem().toString().equals("Android")) {
                    spinnerLevel.setVisibility(View.VISIBLE);
                } else spinnerLevel.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        ArrayAdapter<String> adapterLevel = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayOfLevel);
        adapterLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerLevel = (Spinner) findViewById(R.id.spinnerLevel);
        spinnerLevel.setAdapter(adapterLevel);

        spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        //определение кнопки выбора символов
        symbolOfBtnLeft = (Button) findViewById(R.id.buttonSymbol);
        symbolOfBtnRight = (Button) findViewById(R.id.buttonSymbol2);

        //leftPlayer = new Player(nameOfPlayerLeft.getText());
        leftPlayer = new Player(spinnerLeft.getSelectedItem().toString());
        rightPlayer = new Player(spinnerRight.getSelectedItem().toString());

        // присвоить символы и другие значения по умолчанию
        leftPlayer.setSymbol("x");
        leftPlayer.setActive(true);
        rightPlayer.setSymbol("o");
        rightPlayer.setActive(false);
    }

    public void onclick(View v) {

        // TODO: 24.02.18 если клик на имени, дать или выбрать или ввести

        // TODO: 24.02.18 если клик на аватарке, дать выбрать тип игрока

        // TODO: 24.02.18 связать имя игрока с авой и наоборот

        // TODO: 20.04.18 сделать кнопки абсолютно квадратными

        // TODO: 20.04.18 сделать анимацию зачеркивания выигрышных кнопок

        // TODO: 12.03.18 заменить подсветку ходящего на анимацию

        // TODO: 13.05.18 добавить меню с настройками

        // TODO: 13.05.18 добавить создание нового игрока

        // TODO: 13.05.18 добавить бд с игроками

        // TODO: 13.05.18 добавить в бд результаты

        // TODO: 15.04.18 расставить паузы

        // TODO: 09.07.18 при смене игроков менять счет


        //изменение символов для игроков
        switch (v.getId()) {
            case R.id.buttonSymbol:

                if (statusGames == "ready") {
                    // меняет символ
                    invertVariables();

                    symbolOfBtnLeft.setText(leftPlayer.getSymbol());
                    symbolOfBtnRight.setText(rightPlayer.getSymbol());

                    if ((clickedButtonsTotal == 0) & (numOfRestart == 0)) {
                        Log.d(LOG_TAG, "128 clickedButtonsTotal=" + clickedButtonsTotal);

                        if (leftPlayer.getSymbol().equals("x")) {
                            leftPlayer.setActive(true);
                            makeNameActive("leftButton");
                            rightPlayer.setActive(false);
                        } else if (leftPlayer.getSymbol().equals("o")) {
                            leftPlayer.setActive(false);
                            makeNameActive("rightButton");
                            rightPlayer.setActive(true);
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
                            rightPlayer.setActive(true);
                            makeNameActive("rightButton");
                            leftPlayer.setActive(false);
                        } else if (rightPlayer.getSymbol().equals("o")) {
                            rightPlayer.setActive(false);
                            makeNameActive("leftButton");
                            leftPlayer.setActive(true);
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
                clickPlayFieldBtn(arrayOfButtons[0]);
                break;
            case R.id.button1:
                //clickPlayFieldBtn(1, btn1);
                clickPlayFieldBtn(arrayOfButtons[1]);

                break;
            case R.id.button2:
                clickPlayFieldBtn(arrayOfButtons[2]);
                break;
            case R.id.button3:
                clickPlayFieldBtn(arrayOfButtons[3]);
                break;
            case R.id.button4:
                clickPlayFieldBtn(arrayOfButtons[4]);
                break;
            case R.id.button5:
                clickPlayFieldBtn(arrayOfButtons[5]);
                break;
            case R.id.button6:
                clickPlayFieldBtn(arrayOfButtons[6]);
                break;
            case R.id.button7:
                clickPlayFieldBtn(arrayOfButtons[7]);
                break;
            case R.id.button8:
                clickPlayFieldBtn(arrayOfButtons[8]);
                break;
        }
    }

    public void clickPlayFieldBtn(Button btn) {

        // проверка нажатости кнопки и закончившесяй игры
        if ((statusGames != "finish") & (btn.getText().equals(""))) {
            statusGames = "inplay";
            Log.d(LOG_TAG, "277 clickPlayFieldBtn["+ btn + "]");
            disableChangeSymbol();

            if (leftPlayer.isActive()) {
                symbolActiv = leftPlayer.getSymbol();
            } else if (rightPlayer.isActive()) {
                symbolActiv = rightPlayer.getSymbol();
            }
            // рисование на кнопке активного символа
            btn.setText(symbolActiv);
            Log.d(LOG_TAG, "217 btn.setText(symbolActiv)" + symbolActiv);

            //счетчик нажатых кнопок
            clickedButtonsTotal++;
            Log.d(LOG_TAG, "223 clickedButtonsTotal " + clickedButtonsTotal);

            // занести в массив номер кнопки
            //playField[btnNumber] = symbolActiv;
            //Log.d(LOG_TAG, "playField[" + btnNumber + "] " + playField[btnNumber]);

            // проверка на выигрыш
            checkWin();

            //проверка числа нажатых кнопок
            if (clickedButtonsTotal == 9) {
                statusGames = "finish";
                Log.d(LOG_TAG, "235 clickedButtonsTotal == 9, statusGames-finish");
            }

            //передача ход 2му игроку если андроид, то ход по алгоритму, иначе обрабатывать нажатие
            // TODO: 13.04.18 заменить на имя из класса игрока
            if ((spinnerRight.getSelectedItem().toString().equals("Android")) & (leftPlayer.isActive())
                    & (statusGames.equals("inplay"))) {
                Log.d(LOG_TAG, "244 ход андроида");
                droidsStep();
            } else if (statusGames == "inplay") {
                //invertVariables();
                invertPlayersActivity();
            }

        } else if (statusGames == "finish") {
            restartGame();
        }
    }

    private void restartGame() {

        Log.d(LOG_TAG, "258 restart");
        numOfRestart++;

        clickedButtonsTotal = 0;

        arrayOfButtons[0].setTextColor(Color.BLACK);
        arrayOfButtons[0].setText("");
        arrayOfButtons[1].setTextColor(Color.BLACK);
        arrayOfButtons[1].setText("");
        arrayOfButtons[2].setTextColor(Color.BLACK);
        arrayOfButtons[2].setText("");
        arrayOfButtons[3].setTextColor(Color.BLACK);
        arrayOfButtons[3].setText("");
        arrayOfButtons[4].setTextColor(Color.BLACK);
        arrayOfButtons[4].setText("");
        arrayOfButtons[5].setTextColor(Color.BLACK);
        arrayOfButtons[5].setText("");
        arrayOfButtons[6].setTextColor(Color.BLACK);
        arrayOfButtons[6].setText("");
        arrayOfButtons[7].setTextColor(Color.BLACK);
        arrayOfButtons[7].setText("");
        arrayOfButtons[8].setTextColor(Color.BLACK);
        arrayOfButtons[8].setText("");

        statusGames = "ready";
        Log.d(LOG_TAG, "283 statusGames-ready");

        for (int i = 0; i < 9; i++) {
            arrayOfButtons[i].setText("");
        }

        enableChangeSymbol();
        invertPlayersActivity();

        if ((spinnerRight.getSelectedItem().toString().equals("Android")) & rightPlayer.isActive()) {
            invertPlayersActivity();
            droidsStep();
        }
    }

    public void disableChangeSymbol() {

        symbolOfBtnLeft.setEnabled(false);
        symbolOfBtnRight.setEnabled(false);
        spinnerLeft.setEnabled(false);
        spinnerRight.setEnabled(false);
        spinnerLevel.setEnabled(false);
    }

    public void enableChangeSymbol() {

        symbolOfBtnLeft.setEnabled(true);
        symbolOfBtnRight.setEnabled(true);
        spinnerLeft.setEnabled(true);
        spinnerRight.setEnabled(true);
        spinnerLevel.setEnabled(true);
    }

    public void checkWin() {

        // TODO: 10.03.18 заменить финиш на вин?
        if ((arrayOfButtons[0].getText().equals(symbolActiv))
                & (arrayOfButtons[1].getText().equals(symbolActiv))
                & (arrayOfButtons[2].getText().equals(symbolActiv))) {
            statusGames = "finish";
            Log.d(LOG_TAG, "320 statusGames-finish");
            arrayOfButtons[0].setTextColor(Color.RED);
            arrayOfButtons[1].setTextColor(Color.RED);
            arrayOfButtons[2].setTextColor(Color.RED);

            // TODO: 04.04.18 передавать не текст кнопки, а aктивный символ, проверить
            saveResult(arrayOfButtons[0].getText());
        }

        if ((arrayOfButtons[3].getText().equals(symbolActiv))
                & (arrayOfButtons[4].getText().equals(symbolActiv))
                & (arrayOfButtons[5].getText().equals(symbolActiv))) {

            statusGames = "finish";
            Log.d(LOG_TAG, "334 statusGames-finish");

            arrayOfButtons[3].setTextColor(Color.RED);
            arrayOfButtons[4].setTextColor(Color.RED);
            arrayOfButtons[5].setTextColor(Color.RED);

            saveResult(arrayOfButtons[3].getText());
        }

        if ((arrayOfButtons[6].getText().equals(symbolActiv))
                & (arrayOfButtons[7].getText().equals(symbolActiv))
                & (arrayOfButtons[8].getText().equals(symbolActiv))) {

            statusGames = "finish";
            Log.d(LOG_TAG, "348 statusGames-finish");

            arrayOfButtons[6].setTextColor(Color.RED);
            arrayOfButtons[7].setTextColor(Color.RED);
            arrayOfButtons[8].setTextColor(Color.RED);

            saveResult(arrayOfButtons[6].getText());

        }
        if ((arrayOfButtons[0].getText().equals(symbolActiv))
                & (arrayOfButtons[3].getText().equals(symbolActiv))
                & (arrayOfButtons[6].getText().equals(symbolActiv))) {
            statusGames = "finish";
            Log.d(LOG_TAG, "361 statusGames-finish");

            arrayOfButtons[0].setTextColor(Color.RED);
            arrayOfButtons[3].setTextColor(Color.RED);
            arrayOfButtons[6].setTextColor(Color.RED);

            saveResult(arrayOfButtons[0].getText());
        }

        if ((arrayOfButtons[1].getText().equals(symbolActiv))
                & (arrayOfButtons[4].getText().equals(symbolActiv))
                & (arrayOfButtons[7].getText().equals(symbolActiv))) {
            statusGames = "finish";
            Log.d(LOG_TAG, "374 statusGames-finish");

            arrayOfButtons[1].setTextColor(Color.RED);
            arrayOfButtons[4].setTextColor(Color.RED);
            arrayOfButtons[7].setTextColor(Color.RED);

            saveResult(arrayOfButtons[1].getText());
        }
        if ((arrayOfButtons[2].getText().equals(symbolActiv))
                & (arrayOfButtons[5].getText().equals(symbolActiv))
                & (arrayOfButtons[8].getText().equals(symbolActiv))) {
            statusGames = "finish";
            Log.d(LOG_TAG, "386 statusGames-finish");

            arrayOfButtons[2].setTextColor(Color.RED);
            arrayOfButtons[5].setTextColor(Color.RED);
            arrayOfButtons[8].setTextColor(Color.RED);

            saveResult(arrayOfButtons[2].getText());
        }
        if ((arrayOfButtons[0].getText().equals(symbolActiv))
                & (arrayOfButtons[4].getText().equals(symbolActiv))
                & (arrayOfButtons[8].getText().equals(symbolActiv))) {
            statusGames = "finish";
            Log.d(LOG_TAG, "398 statusGames-finish");

            arrayOfButtons[0].setTextColor(Color.RED);
            arrayOfButtons[4].setTextColor(Color.RED);
            arrayOfButtons[8].setTextColor(Color.RED);

            saveResult(arrayOfButtons[0].getText());
        }
        if ((arrayOfButtons[2].getText().equals(symbolActiv))
                & (arrayOfButtons[4].getText().equals(symbolActiv))
                & (arrayOfButtons[6].getText().equals(symbolActiv))) {
    /*    if ((playField[2] == symbolActiv) & (playField[4] == symbolActiv)
                & (playField[6] == symbolActiv)) {*/
            statusGames = "finish";
            Log.d(LOG_TAG, "412 statusGames-finish");

            arrayOfButtons[2].setTextColor(Color.RED);
            arrayOfButtons[4].setTextColor(Color.RED);
            arrayOfButtons[6].setTextColor(Color.RED);

            saveResult(arrayOfButtons[2].getText());
        }
    }

    public void saveResult(CharSequence winSimbol) {

        Log.d(LOG_TAG, "426 winSimbol=" + winSimbol);
        Log.d(LOG_TAG, "428 symbolOfBtnLeft=" + symbolOfBtnLeft.getText());
        Log.d(LOG_TAG, "429 symbolOfBtnRight=" + symbolOfBtnRight.getText());

        TextView winLeft = (TextView) findViewById(R.id.totalWinLeftPlayer);
        TextView winRight = (TextView) findViewById(R.id.totalWinRightPlayer);

        if (symbolOfBtnLeft.getText().equals(winSimbol)) {
            Log.d(LOG_TAG, "435 left win=");

            totalWinLeft++;
            Log.d(LOG_TAG, "438 totalWinLeft=" + totalWinLeft);

            winLeft.setText(Integer.toString(totalWinLeft));
        } else if (symbolOfBtnRight.getText().equals(winSimbol)) {
            Log.d(LOG_TAG, "442 right win=");

            totalWinRight++;
            winRight.setText(Integer.toString(totalWinRight));
            Log.d(LOG_TAG, "446 totalWinRight=" + totalWinRight);
        }
    }

    public void makeNameActive(String selectedSymbolsButton) {

        switch (selectedSymbolsButton) {
            case "leftButton":
/*                nameOfPlayerLeft.setTextColor(getResources().getColor(R.color.buttonsTextActive));
                nameOfPlayerLeft.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);*/

                ((TextView) spinnerLeft.getSelectedView()).setTextColor(getResources().getColor(R.color.buttonsTextActive));
                ((TextView) spinnerLeft.getSelectedView()).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);

                ((TextView) spinnerRight.getSelectedView()).setTextColor(getResources().getColor(R.color.buttonsText));
                ((TextView) spinnerRight.getSelectedView()).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
                break;
            case "rightButton":
/*                nameOfPlayerLeft.setTextColor(getResources().getColor(R.color.buttonsText));
                nameOfPlayerLeft.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);*/

                ((TextView) spinnerLeft.getSelectedView()).setTextColor(getResources().getColor(R.color.buttonsText));
                ((TextView) spinnerLeft.getSelectedView()).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);

                ((TextView) spinnerRight.getSelectedView()).setTextColor(getResources().getColor(R.color.buttonsTextActive));
                ((TextView) spinnerRight.getSelectedView()).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);

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

        buferActivity = leftPlayer.isActive();
        leftPlayer.setActive(rightPlayer.isActive());
        rightPlayer.setActive(buferActivity);

        if (leftPlayer.isActive()) {
            Log.d(LOG_TAG, "487 leftPlayer.isActive()=true");
            Log.d(LOG_TAG, "488 leftPlayer.getSymbol()=" + leftPlayer.getSymbol());
            makeNameActive("leftButton");
        } else if (rightPlayer.isActive()) {
            Log.d(LOG_TAG, "491 rightPlayer.isActive()=true");
            Log.d(LOG_TAG, "492 rightPlayer.getSymbol()=" + rightPlayer.getSymbol());
            makeNameActive("rightButton");
        }
    }

    public void droidsStep() {
        invertPlayersActivity();
        Log.d(LOG_TAG, "500 droidsStep");
        //int x;
        //int xx;
        //boolean makeStep = false;
        //TimeUnit.SECONDS.sleep(1);
        switch (spinnerLevel.getSelectedItem().toString()) {
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
                Log.d(LOG_TAG, "сделать логику для 2 уровня ");
                makeStep = false;

                //проверка возможности выигрыша
                checkWinStep();

                //проверка возможности проигрыша
                checkLooseStep();

                //проверка возможности поставить в центр
                makeCenterStep();

                //если раньше не было ходов то сделать случайный ход

                if (makeStep == false){
                    // рэндом нажатие кнопки андроидом
                   makeRandomStep();
                }

                break;

            case "Hard":
                // TODO: 03.04.18 сделать логику для 3 уровня
                // TODO: 09.07.18 делать угловой хода на 2-м шаге, доработать и на 5-м правильно составить треугольник

                Log.d(LOG_TAG, "сделать логику для 3 уровня ");
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
