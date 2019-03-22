package com.example.admin.kresnol;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


//это вью паттерна мвп
public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    final String LEFT_NAME = "leftName";

    SquareButton arrayOfButtons[] = new SquareButton[9];

    Button symbolOfBtnLeftPlayer;
    Button symbolOfBtnRightPlayer;

    Spinner spinnerLeft;
    Spinner spinnerRight;
    Spinner spinnerLevel;

    TextView winLeft;
    TextView winRight;

    ImageView imageOfRightPlayer;
    ImageView imageOfLeftPlayer;

    Animation animation;

    MainPresenter presenter;

    Menu myMenu;

    SharedPreferences prefs;


    Db db;

    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterForLeft;
    ArrayAdapter<String> adapterLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "onCreate");

        init();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        presenter.setSpinnersFromPreferences();


        /*db = new Db(this);
        db.upgradeBase();
        *//*db.deleteItem(1);
        db.deleteItem(2);*//*

        //db.addPlayer("Player 1");


        db.close();*/


        //db = new Db(this);

        //db.addGame("Player1", "Player2");


        // получаем количество записей в базе перед изменениями
        /*int mCount = db.getItemCount();
        Log.d(LOG_TAG, "Количество записей в базе:" + mCount);
*/
        // обновляем запись с id = 1 (меняем email)
        //db.updateEmail("Igor", "newemail@newemail.com");

        // удаляем запись с id = 3
        //db.deleteItem(3);


        // выводим все имеющиеся записи в лог
        //        List<RecordOfDb> records = db.getNamesFromDb();
        //Log.d(LOG_TAG, "Имя: " + record.getName());


        /*db = new Db(this);
        List<RecordOfDb> records = db.getRecordOfDb();
        for (RecordOfDb record : records) {
            Log.d(LOG_TAG, "Имя: " + record.getName() + " сыграно: " + record.getTotalPlay());
        }

        db.close();*/

        // presenter.getArrayOfPlayer();
    }

    protected void onResume() {

        presenter.setSpinnerLevelFromPreferences();

        presenter.updateSpinner();


        Log.d(LOG_TAG, " onResume " );
       // presenter.setSpinnerColor();

        presenter.setSpinnerToNewPlayer();

        Log.d(LOG_TAG, " onResume end" );

        super.onResume();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        myMenu = menu;
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // обновление меню
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    // обработка нажатий меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (presenter.clickMenu(item.getItemId()) == true) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    private void init() {

         Log.d(LOG_TAG, "init");

        presenter = new MainPresenter(this);

        //определение кнопки игрового поля
        arrayOfButtons[0] = (SquareButton) findViewById(R.id.button0);
        arrayOfButtons[1] = (SquareButton) findViewById(R.id.button1);
        arrayOfButtons[2] = (SquareButton) findViewById(R.id.button2);
        arrayOfButtons[3] = (SquareButton) findViewById(R.id.button3);
        arrayOfButtons[4] = (SquareButton) findViewById(R.id.button4);
        arrayOfButtons[5] = (SquareButton) findViewById(R.id.button5);
        arrayOfButtons[6] = (SquareButton) findViewById(R.id.button6);
        arrayOfButtons[7] = (SquareButton) findViewById(R.id.button7);
        arrayOfButtons[8] = (SquareButton) findViewById(R.id.button8);

        //определени текст вью для счета
        winLeft = (TextView) findViewById(R.id.totalWinLeftPlayer);
        winRight = (TextView) findViewById(R.id.totalWinRightPlayer);


        // адаптер
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presenter.getArrayOfPlayer());
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presenter.arrayOfPlayer);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presenter.arrayOfPlayer);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterForLeft = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presenter.arrayOfPlayerForLeft);
        adapterForLeft.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        imageOfLeftPlayer = (ImageView) findViewById(R.id.imageView);
        imageOfRightPlayer = (ImageView) findViewById(R.id.imageView2);


        spinnerLeft = (Spinner) findViewById(R.id.spinnerLeft);
        spinnerLeft.setAdapter(adapterForLeft);

        spinnerRight = (Spinner) findViewById(R.id.spinnerRight);
        spinnerRight.setAdapter(adapter);

        // устанавливаем элемент
        //spinnerLeft.setSelection(0, true);
        spinnerLeft.setSelection(0);
        // запоминать спинер
        presenter.setSpinnerLeft(spinnerLeft.getSelectedItem().toString());

        //spinnerRight.setSelection(1,true);
        spinnerRight.setSelection(1);
        presenter.setSpinnerRight(spinnerRight.getSelectedItem().toString());


        //устанавливаем цвет спинера
       /* ((TextView) spinnerLeft.getSelectedView()).setTextColor(getResources().getColor(R.color.buttonsTextActive));
        ((TextView) spinnerRight.getSelectedView()).setTextColor(getResources().getColor(R.color.colorAccent));
*/
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tv_animation);

        // установка левого активным в первый раз
        presenter.makeNameActive(LEFT_NAME);
        // Log.d(LOG_TAG, "тест цвета 2", ((TextView) spinnerLeft.getSelectedView()).getTextColors());

        // устанавливаем обработчик нажатия
        spinnerLeft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           // boolean equalsPlayersLeft = false;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.d(LOG_TAG, "218 ma presenter.checkEqualsSpinnerLeft();" );


                presenter.checkEqualsSpinnerLeft();

               // presenter.setSpinnerColor(presenter.leftPlayer,parent);


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        spinnerRight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //boolean equalsPlayers = false;


            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.d(LOG_TAG, "240 ma presenter.checkEqualsSpinnerRight();" );
                //((TextView) spinnerRight.getSelectedView()).setTextColor(getResources().getColor(R.color.colorAccent));


                presenter.checkEqualsSpinnerRight();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        //ArrayAdapter<String> adapterLevel = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayOfLevel);
        adapterLevel = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presenter.getArrayOfLevel());
        adapterLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerLevel = (Spinner) findViewById(R.id.spinnerLevel);
        spinnerLevel.setAdapter(adapterLevel);

        spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
                presenter.setSpinnerLevel(spinnerLevel.getSelectedItem().toString());

                //установка  уровня  в преференс из спинера
                // TODO: 13.12.18 проверить не работает?
               // prefs.edit().putString("pref_level", spinnerLevel.getSelectedItem().toString()).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        presenter.checkVisibilitySpinnerLevel();


        //определение кнопки выбора символов
        symbolOfBtnLeftPlayer = (Button) findViewById(R.id.buttonSymbolLeftPlayer);
        symbolOfBtnRightPlayer = (Button) findViewById(R.id.buttonSymbolRightPlayer);





    }

    //обновление адаптеров спинеров после создания новых игроков
    public void updateAdapters() {
        adapter.notifyDataSetChanged();
        adapterForLeft.notifyDataSetChanged();

        Log.d(LOG_TAG, "updateAdapters main");


    }


    public void onclick(View v) {

        Log.d(LOG_TAG, "150 onclick test");


        // TODO: 20.02.19 зашел-вышел в меню, а спинеры сбросились на начальные

        // TODO: 20.04.18 сделать анимацию зачеркивания выигрышных кнопок

        //// TODO: 22.09.18 добавить тесты

        // TODO: 15.10.18 вынести БД в другой поток

        // TODO: 22.12.18 перенести методы из вью в презентер

        //TODO тексты вынести в константы или ресурсы

// TODO: 21.03.19 не запоминает уровень в спинере после выхода

        // обработку нажатий
        presenter.click(v.getId());

    }

    public void setPrefs() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void menuHighScore() {
        Intent intent = new Intent(MainActivity.this, HighScoreActivity.class);

        startActivity(intent);

    }

    public void menuAbout() {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }


}
