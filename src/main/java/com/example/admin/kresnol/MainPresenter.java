package com.example.admin.kresnol;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

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

    boolean settingsGroupVisible = false;


    final String LOG_TAG = "myLogs";


    public MainPresenter(MainActivity mainActivity) {
        view = mainActivity;
        model = new MainModel();
// TODO: 30.08.18 вынести в модель
//        setMenuEnabled();

        logic = new LogicOfDroid();

        //view.menu.setGroupVisible(group1, false);

        //создание левого и правого игрока
        leftPlayer = new Player(model.getSpinnerLeftValue());
        rightPlayer = new Player(model.getSpinnerRightValue());

        leftPlayer.setSymbol("x");
        leftPlayer.setActive(true);
        rightPlayer.setSymbol("o");
        rightPlayer.setActive(false);

    }

    public String[] getArrayOfPlayer() {
        return model.arrayOfPlayers;
    }

    public String[] getArrayOfLevel() {
        return model.arrayOfLevel;
    }

    public void getSpinnerLeft() {
        model.getSpinnerLeftValue();
    }

    public void setSpinnerLeft(String spinLeft) {

        model.setSpinnerLeftValue(spinLeft);
    }

    /*public void getSpinnerRight() {
        model.getSpinnerRightValue();
    }*/

    public void setSpinnerRight(String spinRight) {
        model.setSpinnerRightValue(spinRight);
        setImageRight();
    }

    public void setImageRight() {
        if (model.getSpinnerRightValue().equals("Android")) {
            view.imageOfRightPlayer.setImageResource(R.drawable.ic_android_black_24dp);
        } else view.imageOfRightPlayer.setImageResource(R.drawable.ic_accessibility_black_24dp);
    }

    public void setSpinnerLevel(String spinLevel) {

        model.setSpinnerLevelValue(spinLevel);
    }

    public void checkVisibilitySpinnerLevel() {
        if (model.getSpinnerRightValue().equals("Android")) {


            view.spinnerLevel.setVisibility(View.VISIBLE);
        } else view.spinnerLevel.setVisibility(View.INVISIBLE);
    }

    public void click(Integer id) {
        switch (id) {
            case R.id.buttonSymbolRightPlayer:
            case R.id.buttonSymbolLeftPlayer:
                Log.d(LOG_TAG, "92 click test");


                // TODO: 28.07.18 вынести в модель?
                if (model.statusGames == "ready") {

                    // меняет символ
                    invertVariables();

                    //первый выбор символа, установка активности
                    // TODO: 28.07.18 вынести в модель?
                    if ((model.clickedButtonsTotal == 0) & (model.numOfRestart == 0)) {
                        //Log.d(LOG_TAG, "128 clickedButtonsTotal=" + clickedButtonsTotal);
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

            //перезапуск
            case R.id.layout0:
                if (model.statusGames == "finish") {
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
            /*case R.id.menu_setting_player:
                //Log.d(LOG_TAG, "menu_setting test ");

                menuSettingPlayer();
                return true;
*/
            case R.id.menu_settings:
                view.setPrefs();

                return true;

            case R.id.menu_records:
                view.menuRecords();
                return true;
            case R.id.menu_about:
                view.menuAbout();
                return true;
          /*  case R.id.menu_create_player:
                menuCreatePlayer(myMenu);
                return true;
            case R.id.menu_edit_player:
                menuEditPlayer(myMenu);
                return true;
            case R.id.menu_delete_player:
                menuDeletePlayer(myMenu);
                return true;
            case R.id.menu_level:
                menuLevel(myMenu);
                return true;
            case R.id.menu_records:
                menuRecords(myMenu);
                return true;
            case R.id.menu_about:
                menuAbout(myMenu);
                return true;*/
           /* case R.id.create_player:


                showHelp();
                return true;*/
            default:
                return false;
        }

    }




    public void menuSettingPlayer() {

        Log.d(LOG_TAG, "menuSetting ");
        Log.d(LOG_TAG, "settingsGroupVisible = " + settingsGroupVisible);


        /*if (settingsGroupVisible == false) {
            Log.d(LOG_TAG, "settingsGroupVisible1 = " + settingsGroupVisible);
            view.myMenu.setGroupVisible(group1, true);
            settingsGroupVisible = true;

            //view.myMenu.onMenuPressed();
            //invalidateOptionsMenu();

            //view.onPrepareOptionsMenu(view.myMenu);

            view.myMenu.close();
            view.openOptionsMenu();

            Log.d(LOG_TAG, "settingsGroupVisible2 = " + settingsGroupVisible);
        } else if (settingsGroupVisible = true) {
            view.myMenu.setGroupVisible(group1, false);
            settingsGroupVisible = false;

            view.onPrepareOptionsMenu(view.myMenu);

            view.myMenu.close();
            view.openOptionsMenu();

            Log.d(LOG_TAG, "settingsGroupVisible3 = " + settingsGroupVisible);
        }
    */
    }


// TODO: 02.09.18 посмотреть про настройки у климова, использовать?

    public void menuCreatePlayer(Menu myMenu){

    }
     public void menuEditPlayer(Menu myMenu){

    }
     public void menuDeletePlayer(Menu myMenu){

    }
     public void menuLevel(Menu myMenu){

    }
     public void menuRecords(Menu myMenu){

    }
     public void menuAbout(Menu myMenu){

    }





    //меняем символ у игроков
    public void invertVariables() {
        String buferVariable;

        buferVariable = leftPlayer.getSymbol();
        leftPlayer.setSymbol(rightPlayer.getSymbol());
        rightPlayer.setSymbol(buferVariable);

        view.symbolOfBtnLeftPlayer.setText(leftPlayer.getSymbol());
        view.symbolOfBtnRightPlayer.setText(rightPlayer.getSymbol());

    }

    // TODO: 28.07.18 перенести в модель
    public void makeNameActive(String selectedSymbolsButton) {

        switch (selectedSymbolsButton) {
            case "leftButton":

                ((TextView) view.spinnerLeft.getSelectedView()).setTextColor(view.getResources().getColor(R.color.buttonsTextActive));
                ((TextView) view.spinnerLeft.getSelectedView()).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);

                ((TextView) view.spinnerRight.getSelectedView()).setTextColor(view.getResources().getColor(R.color.buttonsText));
                ((TextView) view.spinnerRight.getSelectedView()).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
                break;
            case "rightButton":
                ((TextView) view.spinnerLeft.getSelectedView()).setTextColor(view.getResources().getColor(R.color.buttonsText));
                ((TextView) view.spinnerLeft.getSelectedView()).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);

                ((TextView) view.spinnerRight.getSelectedView()).setTextColor(view.getResources().getColor(R.color.buttonsTextActive));
                ((TextView) view.spinnerRight.getSelectedView()).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28);

                break;
        }
    }

    private void restartGame() {

        Log.d(view.LOG_TAG, "258 restart");
        model.numOfRestart++;

        model.clickedButtonsTotal = 0;

        for (int i = 0; i < 9; i++) {
            view.arrayOfButtons[i].setTextColor(Color.BLACK);
            view.arrayOfButtons[i].setText("");
        }

        model.statusGames = "ready";
// TODO: 30.08.18 вынести в модель

        enableChangeSymbol();
        invertPlayersActivity();

        if ((view.spinnerRight.getSelectedItem().toString().equals("Android")) & rightPlayer.isActive()) {

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
            makeNameActive("leftButton");
        } else if (rightPlayer.isActive()) {
            makeNameActive("rightButton");
        }
    }

    public void clickPlayFieldBtn(SquareButton btn) {
        // public void clickPlayFieldBtn(Button btn) {

        // проверка нажатости кнопки и закончившесяй игры
        if ((model.statusGames != "finish") & (btn.getText().equals(""))) {
            model.statusGames = "inplay";
            //отключение изменяемости кнопок выбора символа и игроков
            disableChangeSymbol();

            if (leftPlayer.isActive()) {
                btn.setText(leftPlayer.getSymbol());
            } else if (rightPlayer.isActive()) {
                btn.setText(rightPlayer.getSymbol());
            }

            //увеличить счетчик нажатых кнопок
            model.clickedButtonsTotal++;
            //Log.d(LOG_TAG, "223 clickedButtonsTotal " + clickedButtonsTotal);

            // проверка на выигрыш
            checkWin();

            //проверка числа нажатых кнопок
            if (model.clickedButtonsTotal == 9) {
                model.statusGames = "finish";
            }

            //передача ход 2му игроку если андроид, то ход по алгоритму, иначе обрабатывать нажатие
            // TODO: 13.04.18 заменить на имя из ресурсов
            if ((model.getSpinnerRightValue().equals("Android")) & (leftPlayer.isActive())
                    & (model.statusGames.equals("inplay"))) {
                invertPlayersActivity();

                clickPlayFieldBtn(view.arrayOfButtons[logic.droidsStep(view.arrayOfButtons, leftPlayer, rightPlayer, model)]);
            } else if (model.statusGames == "inplay") {
                invertPlayersActivity();
            }

        } else if (model.statusGames == "finish") {
            restartGame();
        }
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

        // TODO: 10.03.18 присваивать символ актив перед иф

        String symbolActive;
        if (leftPlayer.isActive()) {
            symbolActive = leftPlayer.getSymbol();
        } else symbolActive = rightPlayer.getSymbol();

        if ((view.arrayOfButtons[0].getText().equals(symbolActive))
                & (view.arrayOfButtons[1].getText().equals(symbolActive))
                & (view.arrayOfButtons[2].getText().equals(symbolActive))) {

            model.statusGames = "finish";
            view.arrayOfButtons[0].setTextColor(Color.RED);
            view.arrayOfButtons[1].setTextColor(Color.RED);
            view.arrayOfButtons[2].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[0].getText());
        }

        if ((view.arrayOfButtons[3].getText().equals(symbolActive))
                & (view.arrayOfButtons[4].getText().equals(symbolActive))
                & (view.arrayOfButtons[5].getText().equals(symbolActive))) {

            model.statusGames = "finish";

            view.arrayOfButtons[3].setTextColor(Color.RED);
            view.arrayOfButtons[4].setTextColor(Color.RED);
            view.arrayOfButtons[5].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[3].getText());
        }

        if ((view.arrayOfButtons[6].getText().equals(symbolActive))
                & (view.arrayOfButtons[7].getText().equals(symbolActive))
                & (view.arrayOfButtons[8].getText().equals(symbolActive))) {

            model.statusGames = "finish";

            view.arrayOfButtons[6].setTextColor(Color.RED);
            view.arrayOfButtons[7].setTextColor(Color.RED);
            view.arrayOfButtons[8].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[6].getText());

        }
        if ((view.arrayOfButtons[0].getText().equals(symbolActive))
                & (view.arrayOfButtons[3].getText().equals(symbolActive))
                & (view.arrayOfButtons[6].getText().equals(symbolActive))) {
            model.statusGames = "finish";

            view.arrayOfButtons[0].setTextColor(Color.RED);
            view.arrayOfButtons[3].setTextColor(Color.RED);
            view.arrayOfButtons[6].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[0].getText());
        }

        if ((view.arrayOfButtons[1].getText().equals(symbolActive))
                & (view.arrayOfButtons[4].getText().equals(symbolActive))
                & (view.arrayOfButtons[7].getText().equals(symbolActive))) {
            model.statusGames = "finish";

            view.arrayOfButtons[1].setTextColor(Color.RED);
            view.arrayOfButtons[4].setTextColor(Color.RED);
            view.arrayOfButtons[7].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[1].getText());
        }
        if ((view.arrayOfButtons[2].getText().equals(symbolActive))
                & (view.arrayOfButtons[5].getText().equals(symbolActive))
                & (view.arrayOfButtons[8].getText().equals(symbolActive))) {
            model.statusGames = "finish";

            view.arrayOfButtons[2].setTextColor(Color.RED);
            view.arrayOfButtons[5].setTextColor(Color.RED);
            view.arrayOfButtons[8].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[2].getText());
        }
        if ((view.arrayOfButtons[0].getText().equals(symbolActive))
                & (view.arrayOfButtons[4].getText().equals(symbolActive))
                & (view.arrayOfButtons[8].getText().equals(symbolActive))) {
            model.statusGames = "finish";

            view.arrayOfButtons[0].setTextColor(Color.RED);
            view.arrayOfButtons[4].setTextColor(Color.RED);
            view.arrayOfButtons[8].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[0].getText());
        }
        if ((view.arrayOfButtons[2].getText().equals(symbolActive))
                & (view.arrayOfButtons[4].getText().equals(symbolActive))
                & (view.arrayOfButtons[6].getText().equals(symbolActive))) {

            model.statusGames = "finish";
            view.arrayOfButtons[2].setTextColor(Color.RED);
            view.arrayOfButtons[4].setTextColor(Color.RED);
            view.arrayOfButtons[6].setTextColor(Color.RED);

            saveResult(view.arrayOfButtons[2].getText());
        }
    }

    public void saveResult(CharSequence winSimbol) {

        TextView winLeft = (TextView) view.findViewById(R.id.totalWinLeftPlayer);
        TextView winRight = (TextView) view.findViewById(R.id.totalWinRightPlayer);

        // TODO: 01.08.18 вынести винсимвол в модель?
        if (view.symbolOfBtnLeftPlayer.getText().equals(winSimbol)) {

            model.totalWinLeft++;
            winLeft.setText(Integer.toString(model.totalWinLeft));
        } else if (view.symbolOfBtnRightPlayer.getText().equals(winSimbol)) {

            model.totalWinRight++;
            winRight.setText(Integer.toString(model.totalWinRight));
        }
    }
}






