package com.example.admin.kresnol;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


//import static com.example.admin.kresnol.R.id.group1;


/**
 * Created by admin on 26.07.18.
 */

class MainPresenter {
    private MainActivity view;
    private final MainModel model;
    private final LogicOfDroid logic;


    private Player leftPlayer;
    private Player rightPlayer;

    private Db db;
    Context context;


    final String LOG_TAG = "myLogs";


    //флаги того, что при выборе игрока в спинере он равен игроку в другом спинере
    private boolean equalsPlayersLeft = false;
    private boolean equalsPlayersRight = false;


    //константы для запоминания в настройках выбора спинеров
    private final String LASTLEFTSPINN = "lastLeftSpinn";
    private final String LASTRIGHTSPINN = "lastRightSpinn";
    private final String LASTLEVELSPINN = "pref_level";

    private final String X_SYMBOL = "x";
    private final String O_SYMBOL = "o";
    private final String EMPTY_SYMBOL = "";


    private final String LEFT_NAME = "leftName";
    private final String RIGHT_NAME = "rightName";


    private final String PREF_NEW_PLAYER = "prefNewPlayer";
    private final String PREF_RENAME_PLAYER = "renamePlayer";
    private final String PREF_DELETE_PLAYER = "deletePlayer";


    MainPresenter(MainActivity mainActivity) {

        view = mainActivity;

        //в модель передаю уровни игры из ресурсов
        model = new MainModel(view.getResources().getString(R.string.level_easy),
                view.getResources().getString(R.string.level_normal),
                view.getResources().getString(R.string.level_hard), view.getResources().getString(R.string.statusGamesReady));

        logic = new LogicOfDroid(view.getResources());

        initLeftPlayer();
        initRightPlayer();

    }

    //создание левого игрока
    private Player initLeftPlayer() {


        leftPlayer = new Player(view.getResources().getString(R.string.players1_name));

        leftPlayer.setSymbol(X_SYMBOL);
        leftPlayer.setActive(true);

        return leftPlayer;

    }


    //создание правого игрока
    private Player initRightPlayer() {

        rightPlayer = new Player(view.getResources().getString(R.string.players2_name));

        rightPlayer.setSymbol(O_SYMBOL);
        rightPlayer.setActive(false);

        return rightPlayer;

    }


    //создание массива игроков
    List<String> arrayOfPlayer = new ArrayList<String>();


    //заполнение массива игроков из БД

    List<RecordOfDb> getArrayOfPlayer() {
        db = new Db(view);

        List<RecordOfDb> records = db.getNamesFromDb();
        for (RecordOfDb record : records) {
            arrayOfPlayer.add(record.getName());

        }
        db.close();
        return records;
    }

    //создание массива для спинера левого без игрока Андроид

    List<String> arrayOfPlayerForLeft = new ArrayList<String>();

    //заполнение массива для левого игрока из БД

    List<RecordOfDb> getArrayOfPlayerForLeft() {
        db = new Db(view);

        List<RecordOfDb> records = db.getNamesFromDb();
        for (RecordOfDb record : records) {

            if (!record.getName().equals(view.getResources().getString(R.string.droids_name))) {
                arrayOfPlayerForLeft.add(record.getName());

            }
        }
        db.close();
        return records;
    }

    // TODO: 14.05.19 вынести тексты в константы
    //проверка создания или изменения игроков
    void checkChangePlayer() {

        if ((view.prefs.getBoolean(PREF_NEW_PLAYER, false) == true)
                | ((view.prefs.getBoolean(PREF_RENAME_PLAYER, false) == true))
                | ((view.prefs.getBoolean(PREF_DELETE_PLAYER, false) == true))) {
            updateSpinner();
            clearNewPlayerPreferences();

        }
    }

    //очистка преференсес создания нового игрока
    private void clearNewPlayerPreferences() {
        SharedPreferences.Editor ed = view.prefs.edit();
        ed.putBoolean(PREF_NEW_PLAYER, false);
        ed.putBoolean(PREF_RENAME_PLAYER, false);
        ed.putBoolean(PREF_DELETE_PLAYER, false);
        //ed.commit();
        ed.apply();


    }

    //обновление массивов игроков и адаптеров спинеров после создания или изменения игроков
    private void updateSpinner() {
        arrayOfPlayer.clear();
        arrayOfPlayerForLeft.clear();
        getArrayOfPlayer();
        getArrayOfPlayerForLeft();
        view.updateAdapters();
    }

    //установка в спиннеры созданных игроков
    void checkSpinnerToNewPlayer() {

        if (view.prefs.getBoolean("setPlayerToGame", false) == true
        ) {

            if (view.prefs.getBoolean("leftPositionToSet", false) == true
            ) {

                //находим в адаптере номер позиции созданного игрока
                int positionForLeft = view.adapterForLeft.getPosition(view.prefs.getString("nameLeftPlayerToSet", EMPTY_SYMBOL));

                // если позиция удалялась и ее нет в адаптере, то ставим спиннеры по умолчанию
                if (positionForLeft < 0) {
                    setDefaultSpinners();

                }
                //иначе ставим созданного игрока
                else {

                    view.spinnerLeft.setSelection(positionForLeft);
                    setSpinnerLeft(view.spinnerLeft.getSelectedItem().toString());
                }
            }

            if (view.prefs.getBoolean("rightPositionToSet", false) == true) {

                //находим в адаптере номер позиции созданного игрока
                int position = view.adapter.getPosition(view.prefs.getString("nameRightPlayerToSet", EMPTY_SYMBOL));
                Log.d(LOG_TAG, "position = " + position);//-1

                // если позиция удалялась и ее нет в адаптере, то ставим спиннеры по умолчанию
                if (position < 0) {
                    setDefaultSpinners();

                }
                //иначе ставим созданного игрока
                else {

                    view.spinnerRight.setSelection(position);
                    Log.d(LOG_TAG, "view.spinnerRight.getSelectedItem().toString() = "
                            + view.spinnerRight.getSelectedItem().toString());

                    setSpinnerRight(view.spinnerRight.getSelectedItem().toString());
                }
            }
            //после установки созанных играков очищаем соответствующие преференсес
            clearNewPlayerSelectionPreferences();
        }
    }

    //очистка преференсес установки нового игрока в игру
    private void clearNewPlayerSelectionPreferences() {

        SharedPreferences.Editor ed = view.prefs.edit();

        ed.putBoolean("setPlayerToGame", false);
        ed.putBoolean("leftPositionToSet", false);
        ed.putBoolean("rightPositionToSet", false);


        ed.putString("nameLeftPlayerToSet", EMPTY_SYMBOL);
        ed.putString("nameRightPlayerToSet", EMPTY_SYMBOL);
        //ed.commit();
        ed.apply();


    }


    String[] getArrayOfLevel() {
        return model.arrayOfLevel;
    }

    //установка спинеров из настроек
    void setSpinnersFromPreferences() {

        String lastSpinLeft = view.prefs.getString(LASTLEFTSPINN, EMPTY_SYMBOL);
        String lastSpinRight = view.prefs.getString(LASTRIGHTSPINN, EMPTY_SYMBOL);

        //проверяем, если удалялись игроки из настроек для запуска, то устанавливаем спинеры по умолчанию
        if ((view.adapter.getPosition(lastSpinRight) < 0)
                | (view.adapterForLeft.getPosition(lastSpinLeft) < 0)) {

            setDefaultSpinners();
        }

        //иначе устанавливаем спинеры из настроек
        else {
            int positionLastSpinLeft = view.adapterForLeft.getPosition(lastSpinLeft);
            view.spinnerLeft.setSelection(positionLastSpinLeft);
            setSpinnerLeft(view.spinnerLeft.getSelectedItem().toString());

            int positionLastSpinRight = view.adapter.getPosition(lastSpinRight);
            view.spinnerRight.setSelection(positionLastSpinRight);
            setSpinnerRight(view.spinnerRight.getSelectedItem().toString());

        }

    }


    void setSpinnerLeft(String spinLeft) {

        model.setSpinnerLeftValue(spinLeft);
        leftPlayer.setName(spinLeft);
    }

    void setSpinnerRight(String spinRight) {
        model.setSpinnerRightValue(spinRight);
        rightPlayer.setName(spinRight);

        setImageRight();
    }

    private String getSpinnerLeft() {
        return model.getSpinnerLeftValue();
    }

    private String getSpinnerRight() {
        return model.getSpinnerRightValue();
    }

    //установка иконки андроида или человека правому игроку
    private void setImageRight() {
        if (model.getSpinnerRightValue().equals(view.getResources().getString(R.string.droids_name))) {
            if (rightPlayer.isActive()) {
                view.imageOfRightPlayer.setImageResource(R.drawable.ic_android_black_24dp);

            } else view.imageOfRightPlayer.setImageResource(R.drawable.ic_android_gray_24dp);
        } else if (rightPlayer.isActive()) {
            view.imageOfRightPlayer.setImageResource(R.drawable.ic_accessibility_black_24dp);

        } else view.imageOfRightPlayer.setImageResource(R.drawable.ic_human_gray_24dp);


    }

    void setSpinnerLevel(String spinLevel) {

        model.setSpinnerLevelValue(spinLevel);
    }

    //установка видимости спинера уровня сложности игры
    void checkVisibilitySpinnerLevel() {

        if (model.getSpinnerRightValue().equals(view.getResources().getString(R.string.droids_name))) {


            view.spinnerLevel.setVisibility(View.VISIBLE);

        } else view.spinnerLevel.setVisibility(View.INVISIBLE);
    }

    //обработка нажатий
    void click(Integer id) {

        switch (id) {

            //тап по полям символов игроков "х" или "o"
            case R.id.buttonSymbolRightPlayer:
            case R.id.buttonSymbolLeftPlayer:

                if (model.getStatusGames().equals(view.getResources().getString(R.string.statusGamesReady))) {

                    // меняет символ
                    invertVariables();

                    //первый выбор символа, установка активности
                    if ((model.getClickedButtonsTotal() == 0) & (model.getNumOfRestart() == 0)) {

                        //если левый "х", то устанавливаем ему активность
                        if (leftPlayer.getSymbol().equals(X_SYMBOL)) {
                            leftPlayer.setActive(true);
                            makeIconActive(LEFT_NAME);
                            rightPlayer.setActive(false);

                            //если левый "o", то устанавливаем активность правому
                        } else if (leftPlayer.getSymbol().equals(O_SYMBOL)) {
                            leftPlayer.setActive(false);
                            makeIconActive(RIGHT_NAME);
                            rightPlayer.setActive(true);
                        }
                    }
                }
                break;

            //перезапуск
            case R.id.layout0:

                if (model.getStatusGames().equals(view.getResources().getString(R.string.statusGamesFinish))) {
                    restartGame();
                }
                break;

            //нажатие кнопки игроком в игровом поле
            case R.id.button0:
                clickPlayFieldBtn(view.arrayOfButtons[0]);
                break;
            case R.id.button1:
                clickPlayFieldBtn(view.arrayOfButtons[1]);
                break;
            case R.id.button2:
                clickPlayFieldBtn(view.arrayOfButtons[2]);
                break;
            case R.id.button3:
                clickPlayFieldBtn(view.arrayOfButtons[3]);
                break;
            case R.id.button4:
                clickPlayFieldBtn(view.arrayOfButtons[4]);
                break;
            case R.id.button5:
                clickPlayFieldBtn(view.arrayOfButtons[5]);
                break;
            case R.id.button6:
                clickPlayFieldBtn(view.arrayOfButtons[6]);
                break;
            case R.id.button7:
                clickPlayFieldBtn(view.arrayOfButtons[7]);
                break;
            case R.id.button8:
                clickPlayFieldBtn(view.arrayOfButtons[8]);
                break;
        }
    }

    //нажатия в меню
    boolean clickMenu(Integer idMenu) {

        Log.d(LOG_TAG, "clickMenu ");

        switch (idMenu) {

            case R.id.menu_settings:

                view.menuSettings();
                return true;

            case R.id.menu_records:

                view.menuHighScore();
                return true;

            case R.id.menu_about:

                view.menuAbout();
                return true;

            case R.id.menu_exit:

                view.finish();
                return true;

            default:
                return false;
        }
    }


    //меняем символ у игроков
    private void invertVariables() {
        String bufferVariable;

        bufferVariable = leftPlayer.getSymbol();
        leftPlayer.setSymbol(rightPlayer.getSymbol());
        rightPlayer.setSymbol(bufferVariable);

        view.symbolOfBtnLeftPlayer.setText(leftPlayer.getSymbol());
        view.symbolOfBtnRightPlayer.setText(rightPlayer.getSymbol());

    }

    //выделение картинки активного игрока анимацией
    void makeIconActive(String selectedSymbolsButton) {

        stopOfAnimation();

        switch (selectedSymbolsButton) {

            case LEFT_NAME:

                view.imageOfLeftPlayer.startAnimation(view.animation);

                view.imageOfLeftPlayer.setImageResource(R.drawable.ic_accessibility_black_24dp);

                if (model.getSpinnerRightValue().equals(view.getResources().getString(R.string.droids_name))) {

                    view.imageOfRightPlayer.setImageResource(R.drawable.ic_android_gray_24dp);
                } else

                    view.imageOfRightPlayer.setImageResource(R.drawable.ic_human_gray_24dp);

                break;

            case RIGHT_NAME:

                view.imageOfRightPlayer.startAnimation(view.animation);

                if (model.getSpinnerRightValue().equals(view.getResources().getString(R.string.droids_name))) {
                    view.imageOfRightPlayer.setImageResource(R.drawable.ic_android_black_24dp);
                } else {
                    view.imageOfRightPlayer.setImageResource(R.drawable.ic_accessibility_black_24dp);
                }
                view.imageOfLeftPlayer.setImageResource(R.drawable.ic_human_gray_24dp);

                break;
        }
    }

    //перезапуск игры
    private void restartGame() {

        Log.d(view.LOG_TAG, "258 restart");

        //увеличить счетчик рестартов на 1
        model.setNumOfRestart(model.getNumOfRestart() + 1);

        //обнулить счетчик кликнутых кнопок
        model.setClickedButtonsTotal(0);

        //очистка символов на игровых кнопках
        for (int i = 0; i < 9; i++) {
            view.arrayOfButtons[i].setTextColor(Color.BLACK);
            view.arrayOfButtons[i].setText(EMPTY_SYMBOL);
        }

        model.setStatusGames(view.getResources().getString(R.string.statusGamesReady));

        enableChangeSymbol();
        invertPlayersActivity();

        checkToFirstDroidsStep();


    }

    //проверка на необходимость сделать первый ход игроком-дроидом
    private void checkToFirstDroidsStep() {
        if ((view.spinnerRight.getSelectedItem().toString().equals(view.getResources().getString(R.string.droids_name))) & rightPlayer.isActive()) {
            clickPlayFieldBtn(view.arrayOfButtons[logic.droidsStep(view.arrayOfButtons, leftPlayer, rightPlayer, model)]);
        }
    }

    //меняем активность игроков
    private void invertPlayersActivity() {
        Boolean tempActive;

        tempActive = leftPlayer.isActive();
        leftPlayer.setActive(rightPlayer.isActive());
        rightPlayer.setActive(tempActive);

        if (leftPlayer.isActive()) {
            makeIconActive(LEFT_NAME);
        } else if (rightPlayer.isActive()) {
            makeIconActive(RIGHT_NAME);
        }
    }

    //тап в игровом поле
    private void clickPlayFieldBtn(SquareButton btn) {

        //константа числа ячеек поля
        final int NUM_OF_ALL_BUTTONS = 9;

        //запоминаем спинеры в преференсес, когда нажата кнопка в поле
        saveSpinnersPreferences();

        // проверка нажатости кнопки и закончившесяй игры
        if ((!model.getStatusGames().equals(view.getResources().getString(R.string.statusGamesFinish)))
                & (btn.getText().equals(EMPTY_SYMBOL))) {

            model.setStatusGames(view.getResources().getString(R.string.statusGamesPlay));

            //отключение изменяемости кнопок выбора символа и игроков
            disableChangeSymbol();

            //установка в нажатую кнопку акивного символа
            if (leftPlayer.isActive()) {
                btn.setText(leftPlayer.getSymbol());
            } else if (rightPlayer.isActive()) {
                btn.setText(rightPlayer.getSymbol());
            }

            //увеличить счетчик нажатых кнопок
            model.setClickedButtonsTotal(model.getClickedButtonsTotal() + 1);


            // плюсовать в БД игру для первого нажатия кнопки
            if (model.getClickedButtonsTotal() == 1) {
                addGameToDb();
            }
            // проверка на выигрыш
            checkWin();

            //при нажатии всех кнопок в игровом поле ставим статус финиш и рстанавливаем анимацию
            if (model.getClickedButtonsTotal() == NUM_OF_ALL_BUTTONS) {
                model.setStatusGames(view.getResources().getString(R.string.statusGamesFinish));
                stopOfAnimation();

            }

            //передача ход 2му игроку если андроид, то ход по алгоритму, иначе обрабатывать нажатие
            if ((model.getSpinnerRightValue().equals(view.getResources().getString(R.string.droids_name))) & (leftPlayer.isActive())
                    & (model.getStatusGames().equals(view.getResources().getString(R.string.statusGamesPlay)))) {
                invertPlayersActivity();

                clickPlayFieldBtn(view.arrayOfButtons[logic.droidsStep(view.arrayOfButtons, leftPlayer, rightPlayer, model)]);
            } else if (model.getStatusGames().equals(view.getResources().getString(R.string.statusGamesPlay))) {
                invertPlayersActivity();
            }

        }
        //в статусе Финиш нажатие перезапускает игру
        else if (model.getStatusGames().equals(view.getResources().getString(R.string.statusGamesFinish))) {
            restartGame();
        }
    }


    // запоминаем в настройках спинеры
    private void saveSpinnersPreferences() {

        SharedPreferences.Editor ed = view.prefs.edit();

        ed.putString(LASTLEFTSPINN, view.spinnerLeft.getSelectedItem().toString());
        ed.putString(LASTRIGHTSPINN, view.spinnerRight.getSelectedItem().toString());

        if (view.spinnerRight.getSelectedItem().toString().equals(view.getResources().getString(R.string.droids_name))) {

            ed.putString(LASTLEVELSPINN, view.spinnerLevel.getSelectedItem().toString());

        }
        //ed.commit();
        ed.apply();

    }

    //добавление запущенной игры в базу
    private void addGameToDb() {

        db = new Db(view);
        db.addGame(leftPlayer.getName(), rightPlayer.getName());
        db.close();
    }

    //отключение возможности менять игровые символы
    private void disableChangeSymbol() {
        view.symbolOfBtnLeftPlayer.setEnabled(false);
        view.symbolOfBtnRightPlayer.setEnabled(false);
        view.spinnerLeft.setEnabled(false);
        view.spinnerRight.setEnabled(false);
        view.spinnerLevel.setEnabled(false);
        setSettingsEnabled(false);

    }

    //включение возможности менять игровые символы
    private void enableChangeSymbol() {
        view.symbolOfBtnLeftPlayer.setEnabled(true);
        view.symbolOfBtnRightPlayer.setEnabled(true);
        view.spinnerLeft.setEnabled(true);
        view.spinnerRight.setEnabled(true);
        view.spinnerLevel.setEnabled(true);
        setSettingsEnabled(true);
    }

    //включение доступа к Настройкам в Меню
    private void setSettingsEnabled(boolean enabled) {

        view.myMenu.setGroupEnabled(R.id.group1, enabled);

    }

    //проверка на выигрыш
    private void checkWin() {

        String symbolActive;
        if (leftPlayer.isActive()) {
            symbolActive = leftPlayer.getSymbol();
        } else symbolActive = rightPlayer.getSymbol();


        int winLinesNumber = -1;
        int j = 0;
        while ((winLinesNumber < 0) & (j < 8)) {

            winLinesNumber = -1;
            int amountOfSymbolActive = 0;


            for (int i = 0; i < 3; i++) {

                if (view.arrayOfButtons[model.winLines[j][i]].getText().equals(symbolActive)) {
                    amountOfSymbolActive++;
                }
                if (amountOfSymbolActive == 3) {
                    winLinesNumber = j;
                }
            }
            j++;

        }


        if (winLinesNumber >= 0) {
            Log.d(LOG_TAG, "выиграла линия № " + winLinesNumber);

            model.setStatusGames(view.getResources().getString(R.string.statusGamesFinish));

            for (int i = 0; i < 3; i++) {

                view.arrayOfButtons[model.winLines[winLinesNumber][i]].setTextColor(Color.RED);

            }

            saveResult(symbolActive);

        }
    }


    //сохранение победного результата в базе и счетчике побед
    private void saveResult(CharSequence winSymbol) {

        stopOfAnimation();
        db = new Db(view);

        //сравниваем выигравший символ с символом левого игрока, если совпадают,
        // то выиграл левый игрок, добавляем ему выигрыш
        if (view.symbolOfBtnLeftPlayer.getText().equals(winSymbol)) {


            model.setTotalWinLeft(incrementTotalWin(model.getTotalWinLeft()));

            // добавлять в бд выигрыш
            db.addWinToDb(leftPlayer.getName());


            view.winLeft.setText(Integer.toString(model.getTotalWinLeft()));


        }
        //иначе добавляем выигрыш правому
        else if (view.symbolOfBtnRightPlayer.getText().equals(winSymbol)) {

            model.setTotalWinRight(incrementTotalWin(model.getTotalWinRight()));

            //  добавлять в бд выигрыш
            db.addWinToDb(rightPlayer.getName());

            view.winRight.setText(Integer.toString(model.getTotalWinRight()));
        }
        db.close();
    }

    int incrementTotalWin(int totalWin) {

        totalWin = totalWin + 1;
        return totalWin;
    }


    private void stopOfAnimation() {
        view.imageOfLeftPlayer.clearAnimation();
        view.imageOfRightPlayer.clearAnimation();
    }

    //при смене игроков обнулять счет
    private void clearScore() {
        model.setTotalWinLeft(0);
        view.winLeft.setText(Integer.toString(model.getTotalWinLeft()));

        model.setTotalWinRight(0);
        view.winRight.setText(Integer.toString(model.getTotalWinRight()));
    }


    void setSpinnerLevelFromPreferences() {

        String prefLevel = view.prefs.getString(LASTLEVELSPINN, EMPTY_SYMBOL);

        int positionLastSpinLevel = view.adapterLevel.getPosition(prefLevel);
        view.spinnerLevel.setSelection(positionLastSpinLevel);

        setSpinnerLevel(prefLevel);
    }

    //проверка и запрет выбора одного игрока в обоих спинерах при выборе левого
    void checkEqualsSpinnerLeft() {


        if (view.spinnerLeft.getSelectedItem().toString().equals(getSpinnerRight())) {

            int positionCurrentSpinLeft = view.adapterForLeft.getPosition(getSpinnerLeft());

            //если после удаления не найден игрок из геттера
            if (positionCurrentSpinLeft < 0) {

                setDefaultSpinners();

            } else {
                Toast.makeText(view.getBaseContext(), R.string.doNotChooseEqualsPlayer, Toast.LENGTH_SHORT).show();

                equalsPlayersLeft = true;
                view.spinnerLeft.setSelection(positionCurrentSpinLeft);
            }


        } else {
            setSpinnerLeft(view.spinnerLeft.getSelectedItem().toString());

            //если после удаления игрока система пытается поставить одинаковые спинеры
            if (view.spinnerRight.getSelectedItem().toString().equals(view.spinnerLeft.getSelectedItem().toString())) {

                setDefaultSpinners();
            }

            //сброс счетчика счета
            if (!equalsPlayersLeft) {
                clearScore();
            }
            equalsPlayersLeft = false;
        }
    }

    //проверка и запрет выбора одного игрока в обоих спинерах при выборе правого
    void checkEqualsSpinnerRight() {

        if (view.spinnerRight.getSelectedItem().toString().equals(getSpinnerLeft())) {

            int positionCurrentSpinRight = view.adapter.getPosition(getSpinnerRight());

            //-1
            //если было удаление и не найден игрок из геттера, ставим начальных игроков
            if (positionCurrentSpinRight < 0) {

                setDefaultSpinners();

            } else {
                Toast.makeText(view.getBaseContext(), R.string.doNotChooseEqualsPlayer, Toast.LENGTH_SHORT).show();

                equalsPlayersRight = true;
                view.spinnerRight.setSelection(positionCurrentSpinRight);
            }

        } else {
            setSpinnerRight(view.spinnerRight.getSelectedItem().toString());

            //если после удаления игрока система пытается поставить одинаковые спинеры то сброс на начальные
            if (view.spinnerRight.getSelectedItem().toString().equals(view.spinnerLeft.getSelectedItem().toString())) {

                setDefaultSpinners();
            }
            //сброс счетчика счета
            if (!equalsPlayersRight) {
                clearScore();
            }
            equalsPlayersRight = false;

            //проверить видимость спинера уровня для игрока Андроид
            checkVisibilitySpinnerLevel();
        }
    }

    //установка в спинеры игроков по умолчанию
    private void setDefaultSpinners() {

        view.spinnerLeft.setSelection(0);
        setSpinnerLeft(view.spinnerLeft.getSelectedItem().toString());
        view.spinnerRight.setSelection(1);
        setSpinnerRight(view.spinnerRight.getSelectedItem().toString());
        Toast.makeText(view.getBaseContext(), R.string.setDefaultPlayers, Toast.LENGTH_SHORT).show();
    }

}






