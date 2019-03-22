package com.example.admin.kresnol;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


//import static com.example.admin.kresnol.R.id.group1;


/**
 * Created by admin on 26.07.18.
 */

public class MainPresenter {
    private MainActivity view;
    private final MainModel model;
    private final LogicOfDroid logic;

    Player leftPlayer;
    Player rightPlayer;

    Db db;
    Context context;

    final String LOG_TAG = "myLogs";

    boolean equalsPlayersLeft = false;
    boolean equalsPlayers = false;


    //константы для запоминания в настройках выбора спинеров
    final String LASTLEFTSPINN = "lastLeftSpinn";
    final String LASTRIGHTSPINN = "lastRightSpinn";
    final String LASTLEVELSPINN = "lastLevelSpinn";

    final String X_SYMBOL = "x";
    final String O_SYMBOL = "o";
    final String EMPTY_SYMBOL = "";


    final String LEFT = "left";
    final String RIGHT = "right";
    final String LEFT_NAME = "leftName";
    final String RIGHT_NAME = "rightName";

    public MainPresenter(MainActivity mainActivity) {
        view = mainActivity;

        //в модель передаю уровни игры из ресурсов
        model = new MainModel(view.getResources().getString(R.string.level_easy),
                view.getResources().getString(R.string.level_normal),
                view.getResources().getString(R.string.level_hard));

        logic = new LogicOfDroid(view.getResources());


        // брать имена игроков из базы для создания объектов игрок
        db = new Db(view);

        leftPlayer = new Player(db.getNameForCreateLeftPlayer());
        rightPlayer = new Player(db.getNameForCreateRightPlayer());

        db.close();

        leftPlayer.setSymbol(X_SYMBOL);
        leftPlayer.setActive(true);

        rightPlayer.setSymbol(O_SYMBOL);
        rightPlayer.setActive(false);

        getArrayOfPlayer();
        getArrayOfPlayerForLeft();

    }

    List<String> arrayOfPlayer = new ArrayList<String>();

    //public String[] getArrayOfPlayer() {
    public List<RecordOfDb> getArrayOfPlayer() {
        db = new Db(view);

        List<RecordOfDb> records = db.getNamesFromDb();
        for (RecordOfDb record : records) {
            arrayOfPlayer.add(record.getName());

            //Log.d(LOG_TAG, "Имя: " + record.getName() + " сыграно: " + record.getTotalPlay());
            Log.d(LOG_TAG, "Имя: " + record.getName());
        }
        //return model.arrayOfPlayers;
        db.close();
        return records;
    }

    List<String> arrayOfPlayerForLeft = new ArrayList<String>();

    //создание массива для спинера левого без игрока Андроид
    public List<RecordOfDb> getArrayOfPlayerForLeft() {
        db = new Db(view);

        List<RecordOfDb> records = db.getNamesFromDb();
        for (RecordOfDb record : records) {

            if (!record.getName().equals(view.getResources().getString(R.string.droids_name))) {
                arrayOfPlayerForLeft.add(record.getName());

                //Log.d(LOG_TAG, "Имя: " + record.getName() + " сыграно: " + record.getTotalPlay());
                Log.d(LOG_TAG, "Имя1: " + record.getName());
            }

        }
        //return model.arrayOfPlayers;
        db.close();
        return records;
    }

    //обновление массивов игроков и адаптеров спинеров после создания новых игроков
    public void updateSpinner() {
        //boolean updSpin = false;
        if (CreatePlayerActivity.isUpdateSpinner() | EditPlayerActivity.isUpdSpinner()) {
            arrayOfPlayer.clear();
            arrayOfPlayerForLeft.clear();
            getArrayOfPlayer();
            getArrayOfPlayerForLeft();
            view.updateAdapters();

            //updSpin = true;
        }
        //return updSpin;
    }

    public void setSpinnerToNewPlayer() {
        //boolean setSpinnerToNewPlayer = false;
        if (CreatePlayerActivity.isSetPlayerToGame()) {
            //int position = view.adapter.getPosition(CreatePlayerActivity.getNamePlayerToSet());

            if (CreatePlayerActivity.getPositionToSet().equals(LEFT)) {
                int positionForLeft = view.adapterForLeft.getPosition(CreatePlayerActivity.getNamePlayerToSet());

                // если позиция удалялась и ее нет в адаптере, то ставим спиннеры по умолчанию

                if (positionForLeft < 0) {
                    setDefaultSpinners();

                }
                //иначе ставим созданного игрока
                else {

                    view.spinnerLeft.setSelection(positionForLeft);
                    setSpinnerLeft(view.spinnerLeft.getSelectedItem().toString());
                    //если после установки спинеров они стали равны, то ставим начальные

                }

            }

            if (CreatePlayerActivity.getPositionToSet().equals(RIGHT)) {
                Log.d(LOG_TAG, "CreatePlayerActivity.getNamePlayerToSet() = " + CreatePlayerActivity.getNamePlayerToSet());

                int position = view.adapter.getPosition(CreatePlayerActivity.getNamePlayerToSet());
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


            //setSpinnerToNewPlayer = true;
        }
        Log.d(LOG_TAG, "view.spinnerLeft.getSelectedItem().toString() = "
                + view.spinnerLeft.getSelectedItem().toString());
       Log.d(LOG_TAG, " getSpinnerRight(); = "
                +  getSpinnerRight());

Log.d(LOG_TAG, " getSpinnerLeft(); = "
                +  getSpinnerLeft());
/*ошибка
        if (getSpinnerLeft().equals(getSpinnerRight())){
            setDefaultSpinners();
        }*/

/*        Log.d(LOG_TAG, "view.spinnerRight.getSelectedItem().toString() = "
                + view.spinnerRight.getSelectedItem().toString());

        if (view.spinnerRight.getSelectedItem().toString().equals(view.spinnerLeft.getSelectedItem().toString())) {
            setDefaultSpinners();

        }*/
    }


    public String[] getArrayOfLevel() {
        return model.arrayOfLevel;
    }


    public void setSpinnersFromPreferences() {


        String lastSpinLeft = view.prefs.getString(LASTLEFTSPINN, EMPTY_SYMBOL);
        String lastSpinRight = view.prefs.getString(LASTRIGHTSPINN, EMPTY_SYMBOL);
        String lastSpinLevel = view.prefs.getString(LASTLEVELSPINN, EMPTY_SYMBOL);

        //view.adapterForLeft.getPosition(lastSpinLeft);


        //проверяем, если удалялись игроки из настроек для запуска, то устанавливаем спинеры по умолчанию
        if ((view.adapter.getPosition(lastSpinRight) < 0)
                | (view.adapterForLeft.getPosition(lastSpinLeft) < 0)
                | view.adapterLevel.getPosition(lastSpinLevel) < 0) {

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

            if (view.spinnerRight.getSelectedItem().toString().equals(view.getResources().getString(R.string.droids_name))) {
                int positionLastSpinLevel = view.adapterLevel.getPosition(lastSpinLevel);
                view.spinnerLevel.setSelection(positionLastSpinLevel);
                setSpinnerLevel(view.spinnerLevel.getSelectedItem().toString());

            }
        }

    }


    public void setSpinnerLeft(String spinLeft) {

        model.setSpinnerLeftValue(spinLeft);
        leftPlayer.setName(spinLeft);
        Log.d(LOG_TAG, "левый игрок = " + leftPlayer.getName());
    }

    public void setSpinnerRight(String spinRight) {
        model.setSpinnerRightValue(spinRight);
        rightPlayer.setName(spinRight);

        Log.d(LOG_TAG, "правый игрок = " + rightPlayer.getName());

        setImageRight();
    }

    public String getSpinnerLeft() {
        return model.getSpinnerLeftValue();
    }

    public String getSpinnerRight() {
        return model.getSpinnerRightValue();
    }

    public void setImageRight() {
        if (model.getSpinnerRightValue().equals(view.getResources().getString(R.string.droids_name))) {
            if (rightPlayer.isActive()) {
                view.imageOfRightPlayer.setImageResource(R.drawable.ic_android_black_24dp);

            } else view.imageOfRightPlayer.setImageResource(R.drawable.ic_android_gray_24dp);
        } else if (rightPlayer.isActive()) {
            view.imageOfRightPlayer.setImageResource(R.drawable.ic_accessibility_black_24dp);

        } else view.imageOfRightPlayer.setImageResource(R.drawable.ic_human_gray_24dp);


    }

    public void setSpinnerLevel(String spinLevel) {

        model.setSpinnerLevelValue(spinLevel);
    }

    public void checkVisibilitySpinnerLevel() {

        if (model.getSpinnerRightValue().equals(view.getResources().getString(R.string.droids_name))) {


            view.spinnerLevel.setVisibility(View.VISIBLE);
        } else view.spinnerLevel.setVisibility(View.INVISIBLE);
    }

    public void click(Integer id) {
        switch (id) {
            case R.id.buttonSymbolRightPlayer:
            case R.id.buttonSymbolLeftPlayer:
                Log.d(LOG_TAG, "92 click test");


                //if (model.statusGames == "ready") {
                if (model.getStatusGames().equals(view.getResources().getString(R.string.statusGamesReady))) {

                    // меняет символ
                    invertVariables();

                    //первый выбор символа, установка активности
                    if ((model.clickedButtonsTotal == 0) & (model.numOfRestart == 0)) {
                        //Log.d(LOG_TAG, "128 clickedButtonsTotal=" + clickedButtonsTotal);
                        if (leftPlayer.getSymbol().equals(X_SYMBOL)) {
                            leftPlayer.setActive(true);

                            //makeNameActive("leftButton");
                            makeNameActive(LEFT_NAME);
                            rightPlayer.setActive(false);
                        } else if (leftPlayer.getSymbol().equals(O_SYMBOL)) {
                            leftPlayer.setActive(false);
                            //makeNameActive("rightButton");
                            makeNameActive(RIGHT_NAME);
                            rightPlayer.setActive(true);
                        }
                    }
                }
                break;

            //перезапуск
            case R.id.layout0:

                //if (model.statusGames == "finish") {
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

    public boolean clickMenu(Integer idMenu) {

        Log.d(LOG_TAG, "clickMenu test ");

        switch (idMenu) {

            case R.id.menu_settings:
                // TODO: 21.03.19 переименовать в менюСеттингс
                view.setPrefs();


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

    // TODO: 04.10.18 доделать меню
    public void menuCreatePlayer(Menu myMenu) {

    }

    public void menuEditPlayer(Menu myMenu) {

    }

    public void menuDeletePlayer(Menu myMenu) {

    }

    public void menuLevel(Menu myMenu) {

    }

    public void menuRecords(Menu myMenu) {

    }

    public void menuAbout(Menu myMenu) {

    }

    //меняем символ у игроков
    public void invertVariables() {
        String bufferVariable;

        bufferVariable = leftPlayer.getSymbol();
        leftPlayer.setSymbol(rightPlayer.getSymbol());
        rightPlayer.setSymbol(bufferVariable);

        view.symbolOfBtnLeftPlayer.setText(leftPlayer.getSymbol());
        view.symbolOfBtnRightPlayer.setText(rightPlayer.getSymbol());

    }

    public void makeNameActive(String selectedSymbolsButton) {

        stopOfAnimation();

        switch (selectedSymbolsButton) {

            //case "leftButton":
            case LEFT_NAME:
                //((TextView) view.spinnerLeft.getSelectedView()).setTextColor(view.getResources().getColor(R.color.buttonsTextActive));
                //((TextView) view.spinnerLeft.getSelectedView()).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);
                //((TextView) view.spinnerRight.getSelectedView()).setTextColor(view.getResources().getColor(R.color.buttonsText));
                //((TextView) view.spinnerRight.getSelectedView()).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
                view.imageOfLeftPlayer.startAnimation(view.animation);

                view.imageOfLeftPlayer.setImageResource(R.drawable.ic_accessibility_black_24dp);

                if (model.getSpinnerRightValue().equals(view.getResources().getString(R.string.droids_name))) {

                    view.imageOfRightPlayer.setImageResource(R.drawable.ic_android_gray_24dp);
                } else

                    view.imageOfRightPlayer.setImageResource(R.drawable.ic_human_gray_24dp);

                break;

            //case "rightButton":
            case RIGHT_NAME:
                //view.imageOfLeftPlayer.clearAnimation();
                // ((TextView) view.spinnerLeft.getSelectedView()).setTextColor(view.getResources().getColor(R.color.buttonsText));
                //((TextView) view.spinnerLeft.getSelectedView()).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);

                //((TextView) view.spinnerRight.getSelectedView()).setTextColor(view.getResources().getColor(R.color.buttonsTextActive));
                //((TextView) view.spinnerRight.getSelectedView()).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);
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

    private void restartGame() {

        Log.d(view.LOG_TAG, "258 restart");
        model.numOfRestart++;

        model.clickedButtonsTotal = 0;

        for (int i = 0; i < 9; i++) {
            view.arrayOfButtons[i].setTextColor(Color.BLACK);
            view.arrayOfButtons[i].setText(EMPTY_SYMBOL);
        }

        model.setStatusGames(view.getResources().getString(R.string.statusGamesReady));

        // TODO: 19.10.18 подумать над брать игрока не из спинера а из гетера модели игрока

        enableChangeSymbol();
        invertPlayersActivity();

        if ((view.spinnerRight.getSelectedItem().toString().equals(view.getResources().getString(R.string.droids_name))) & rightPlayer.isActive()) {

            clickPlayFieldBtn(view.arrayOfButtons[logic.droidsStep(view.arrayOfButtons, leftPlayer, rightPlayer, model)]);
        }
    }

    //меняем активность игроков
    public void invertPlayersActivity() {
        Boolean tempActive;

        tempActive = leftPlayer.isActive();
        leftPlayer.setActive(rightPlayer.isActive());
        rightPlayer.setActive(tempActive);

        if (leftPlayer.isActive()) {
            //makeNameActive("leftButton");
            makeNameActive(LEFT_NAME);
        } else if (rightPlayer.isActive()) {
            //makeNameActive("rightButton");
            makeNameActive(RIGHT_NAME);
        }
    }

    public void clickPlayFieldBtn(SquareButton btn) {

        //запоминаем спинеры, когда нажата кнопка в поле
        saveSpinners();

        // проверка нажатости кнопки и закончившесяй игры
        if ((!model.getStatusGames().equals(view.getResources().getString(R.string.statusGamesFinish)))
                & (btn.getText().equals(EMPTY_SYMBOL))) {

            model.setStatusGames(view.getResources().getString(R.string.statusGamesInplay));

            //отключение изменяемости кнопок выбора символа и игроков
            disableChangeSymbol();

            if (leftPlayer.isActive()) {
                btn.setText(leftPlayer.getSymbol());
            } else if (rightPlayer.isActive()) {
                btn.setText(rightPlayer.getSymbol());
            }

            //увеличить счетчик нажатых кнопок
            model.clickedButtonsTotal++;
            // плюсовать в БД игры для первого нажатия кнопки
            if (model.clickedButtonsTotal == 1) {
                addGameToDb();
            }
            // проверка на выигрыш
            checkWin();

            //проверка числа нажатых кнопок
            if (model.clickedButtonsTotal == 9) {
                //model.statusGames = "finish";
                model.setStatusGames(view.getResources().getString(R.string.statusGamesFinish));
                stopOfAnimation();

            }

            //передача ход 2му игроку если андроид, то ход по алгоритму, иначе обрабатывать нажатие

            if ((model.getSpinnerRightValue().equals(view.getResources().getString(R.string.droids_name))) & (leftPlayer.isActive())
                    & (model.getStatusGames().equals(view.getResources().getString(R.string.statusGamesInplay)))) {
                invertPlayersActivity();

                clickPlayFieldBtn(view.arrayOfButtons[logic.droidsStep(view.arrayOfButtons, leftPlayer, rightPlayer, model)]);
            }
            //else if (model.statusGames == "inplay") {
            else if (model.getStatusGames().equals(view.getResources().getString(R.string.statusGamesInplay))) {
                invertPlayersActivity();
            }

        }
        //else if (model.statusGames == "finish") {
        else if (model.getStatusGames().equals(view.getResources().getString(R.string.statusGamesFinish))) {
            restartGame();
        }
    }

    // TODO: 12.01.19 вынести префс из вью в презентер
    // запоминаем в настройках спинеры
    public void saveSpinners() {
        SharedPreferences.Editor ed = view.prefs.edit();
        ed.putString(LASTLEFTSPINN, view.spinnerLeft.getSelectedItem().toString());
        ed.putString(LASTRIGHTSPINN, view.spinnerRight.getSelectedItem().toString());

        if (view.spinnerRight.getSelectedItem().toString().equals(view.getResources().getString(R.string.droids_name))) {
            ed.putString(LASTLEVELSPINN, view.spinnerLevel.getSelectedItem().toString());

        }
        ed.commit();
    }

    //добавление запущенной игры в базу
    private void addGameToDb() {

        db = new Db(view);
        /*leftPlayer.getName();
        rightPlayer.getName();*/

        Log.d(LOG_TAG, "leftPlayer = " + leftPlayer.getName());
        Log.d(LOG_TAG, "rightPlayer = " + rightPlayer.getName());

        db.addGame(leftPlayer.getName(), rightPlayer.getName());
        //db.addGame("Player1", "Player2");

        db.close();
    }

    //отключение возможности менять игровые символы
    public void disableChangeSymbol() {
        view.symbolOfBtnLeftPlayer.setEnabled(false);
        view.symbolOfBtnRightPlayer.setEnabled(false);
        view.spinnerLeft.setEnabled(false);
        view.spinnerRight.setEnabled(false);
        view.spinnerLevel.setEnabled(false);
        setMenuEnabled(false);

    }

    //включение возможности менять игровые символы
    public void enableChangeSymbol() {
        view.symbolOfBtnLeftPlayer.setEnabled(true);
        view.symbolOfBtnRightPlayer.setEnabled(true);
        view.spinnerLeft.setEnabled(true);
        view.spinnerRight.setEnabled(true);
        view.spinnerLevel.setEnabled(true);
        setMenuEnabled(true);
    }

    private void setMenuEnabled(boolean enabled) {

        view.myMenu.setGroupEnabled(R.id.group1, enabled);

    }

    public void checkWin() {

        String symbolActive;
        if (leftPlayer.isActive()) {
            symbolActive = leftPlayer.getSymbol();
        } else symbolActive = rightPlayer.getSymbol();

        if ((view.arrayOfButtons[0].getText().equals(symbolActive))
                & (view.arrayOfButtons[1].getText().equals(symbolActive))
                & (view.arrayOfButtons[2].getText().equals(symbolActive))) {


            //model.statusGames = "finish";
            model.setStatusGames(view.getResources().getString(R.string.statusGamesFinish));
            view.arrayOfButtons[0].setTextColor(Color.RED);
            view.arrayOfButtons[1].setTextColor(Color.RED);
            view.arrayOfButtons[2].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[0].getText());
        }

        if ((view.arrayOfButtons[3].getText().equals(symbolActive))
                & (view.arrayOfButtons[4].getText().equals(symbolActive))
                & (view.arrayOfButtons[5].getText().equals(symbolActive))) {

            //model.statusGames = "finish";
            model.setStatusGames(view.getResources().getString(R.string.statusGamesFinish));

            view.arrayOfButtons[3].setTextColor(Color.RED);
            view.arrayOfButtons[4].setTextColor(Color.RED);
            view.arrayOfButtons[5].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[3].getText());
        }

        if ((view.arrayOfButtons[6].getText().equals(symbolActive))
                & (view.arrayOfButtons[7].getText().equals(symbolActive))
                & (view.arrayOfButtons[8].getText().equals(symbolActive))) {

            //model.statusGames = "finish";
            model.setStatusGames(view.getResources().getString(R.string.statusGamesFinish));

            view.arrayOfButtons[6].setTextColor(Color.RED);
            view.arrayOfButtons[7].setTextColor(Color.RED);
            view.arrayOfButtons[8].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[6].getText());

        }
        if ((view.arrayOfButtons[0].getText().equals(symbolActive))
                & (view.arrayOfButtons[3].getText().equals(symbolActive))
                & (view.arrayOfButtons[6].getText().equals(symbolActive))) {
            model.setStatusGames(view.getResources().getString(R.string.statusGamesFinish));

            view.arrayOfButtons[0].setTextColor(Color.RED);
            view.arrayOfButtons[3].setTextColor(Color.RED);
            view.arrayOfButtons[6].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[0].getText());
        }

        if ((view.arrayOfButtons[1].getText().equals(symbolActive))
                & (view.arrayOfButtons[4].getText().equals(symbolActive))
                & (view.arrayOfButtons[7].getText().equals(symbolActive))) {
            model.setStatusGames(view.getResources().getString(R.string.statusGamesFinish));

            view.arrayOfButtons[1].setTextColor(Color.RED);
            view.arrayOfButtons[4].setTextColor(Color.RED);
            view.arrayOfButtons[7].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[1].getText());
        }
        if ((view.arrayOfButtons[2].getText().equals(symbolActive))
                & (view.arrayOfButtons[5].getText().equals(symbolActive))
                & (view.arrayOfButtons[8].getText().equals(symbolActive))) {
            model.setStatusGames(view.getResources().getString(R.string.statusGamesFinish));

            view.arrayOfButtons[2].setTextColor(Color.RED);
            view.arrayOfButtons[5].setTextColor(Color.RED);
            view.arrayOfButtons[8].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[2].getText());
        }
        if ((view.arrayOfButtons[0].getText().equals(symbolActive))
                & (view.arrayOfButtons[4].getText().equals(symbolActive))
                & (view.arrayOfButtons[8].getText().equals(symbolActive))) {
            model.setStatusGames(view.getResources().getString(R.string.statusGamesFinish));

            view.arrayOfButtons[0].setTextColor(Color.RED);
            view.arrayOfButtons[4].setTextColor(Color.RED);
            view.arrayOfButtons[8].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[0].getText());
        }
        if ((view.arrayOfButtons[2].getText().equals(symbolActive))
                & (view.arrayOfButtons[4].getText().equals(symbolActive))
                & (view.arrayOfButtons[6].getText().equals(symbolActive))) {

            model.setStatusGames(view.getResources().getString(R.string.statusGamesFinish));

            view.arrayOfButtons[2].setTextColor(Color.RED);
            view.arrayOfButtons[4].setTextColor(Color.RED);
            view.arrayOfButtons[6].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[2].getText());
        }
    }


    public void saveResult(CharSequence winSymbol) {

        stopOfAnimation();
        db = new Db(view);

        if (view.symbolOfBtnLeftPlayer.getText().equals(winSymbol)) {

            model.totalWinLeft++;

            db = new Db(view);
            // добавлять в бд выигрыш
            db.addWinToDb(leftPlayer.getName());


            view.winLeft.setText(Integer.toString(model.totalWinLeft));


        } else if (view.symbolOfBtnRightPlayer.getText().equals(winSymbol)) {

            model.totalWinRight++;
            //  добавлять в бд выигрыш
            db.addWinToDb(rightPlayer.getName());

            view.winRight.setText(Integer.toString(model.totalWinRight));
        }
        db.close();
    }

    public void stopOfAnimation() {
        view.imageOfLeftPlayer.clearAnimation();
        view.imageOfRightPlayer.clearAnimation();
    }

    //при смене игроков обнулять счет
    public void clearScore() {
        model.totalWinLeft = 0;
        view.winLeft.setText(Integer.toString(model.totalWinLeft));

        model.totalWinRight = 0;
        view.winRight.setText(Integer.toString(model.totalWinRight));
    }


    public void setSpinnerLevelFromPreferences() {
        String prefLevel = view.prefs.getString("pref_level", EMPTY_SYMBOL);

        //winLeft.setText(prefLevel);
        Log.d(LOG_TAG, "Уровень сложности установленный в настройках" + prefLevel);

        setSpinnerLevel(prefLevel);
// TODO: 22.02.19 хардкод
        switch (prefLevel) {
            case "Easy":
                view.spinnerLevel.setSelection(0);
                break;

            case "Normal":
                view.spinnerLevel.setSelection(1);

                break;
            case "Hard":
                view.spinnerLevel.setSelection(2);
                break;
        }
    }

    public void checkEqualsSpinnerLeft() {




        if (view.spinnerLeft.getSelectedItem().toString().equals(getSpinnerRight())) {

            int positionCurrentSpinLeft = view.adapterForLeft.getPosition(getSpinnerLeft());

            //если после удаления не найден игрок из геттера
            if (positionCurrentSpinLeft < 0) {

                setDefaultSpinners();

            } else {
                Toast.makeText(view.getBaseContext(), "нельзя выбрать одного игрока ", Toast.LENGTH_SHORT).show();
                Log.d(LOG_TAG, "856 test ");

                equalsPlayers = true;
                view.spinnerLeft.setSelection(positionCurrentSpinLeft);
            }


        } else {
            setSpinnerLeft(view.spinnerLeft.getSelectedItem().toString());

            //если после удаления игрока система пытается поставить одинаковые спинеры
            if (view.spinnerRight.getSelectedItem().toString().equals(view.spinnerLeft.getSelectedItem().toString())){
                Log.d(LOG_TAG, "866 test ");

                setDefaultSpinners();
            }

            //сброс счетчика счета
            if (equalsPlayersLeft == false) {
                clearScore();
            }
            equalsPlayersLeft = false;
        }
    }
    //проверка и запрет выбора одного игрока в обоих спинерах привыборе правого
    public void checkEqualsSpinnerRight() {



        if (view.spinnerRight.getSelectedItem().toString().equals(getSpinnerLeft())) {

            int positionCurrentSpinRight = view.adapter.getPosition(getSpinnerRight()); //-1

            //если после удаления не найден игрок из геттера
            if (positionCurrentSpinRight < 0) {

                setDefaultSpinners();

            } else {
                // TODO: 22.02.19 вынести тосты в ресурсы
                Toast.makeText(view.getBaseContext(), "нельзя выбрать одного игрока ", Toast.LENGTH_SHORT).show();
                Log.d(LOG_TAG, "896 test ");

                equalsPlayers = true;

                view.spinnerRight.setSelection(positionCurrentSpinRight);
            }

        } else {

            setSpinnerRight(view.spinnerRight.getSelectedItem().toString());
            Log.d(LOG_TAG, "896");

            //если после удаления игрока система пытается поставить одинаковые спинеры
            if (view.spinnerRight.getSelectedItem().toString().equals(view.spinnerLeft.getSelectedItem().toString())){
                Log.d(LOG_TAG, "903 test ");

                setDefaultSpinners();
            }
// TODO: 13.02.19 проверить клирскоре везде
            //сброс счетчика счета
            if (equalsPlayers == false) {
                clearScore();
            }
            equalsPlayers = false;

            //видимость спинера уровня для игрока Андроид
            checkVisibilitySpinnerLevel();
        }
    }

    private void setDefaultSpinners() {

        view.spinnerLeft.setSelection(0);
        setSpinnerLeft(view.spinnerLeft.getSelectedItem().toString());
        view.spinnerRight.setSelection(1);
        setSpinnerRight(view.spinnerRight.getSelectedItem().toString());
        Toast.makeText(view.getBaseContext(), "установлены начальные игроки", Toast.LENGTH_SHORT).show();
    }

}






