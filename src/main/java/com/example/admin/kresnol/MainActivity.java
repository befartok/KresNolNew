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

        Log.d(LOG_TAG, " onCreateEnd" );

    }

    protected void onResume() {

        Log.d(LOG_TAG, " onResume " );
        presenter.setSpinnerLevelFromPreferences();

        presenter.checkChangePlayer();

        presenter.checkSpinnerToNewPlayer();

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

        //определение кнопок игрового поля
        arrayOfButtons[0] = (SquareButton) findViewById(R.id.button0);
        arrayOfButtons[1] = (SquareButton) findViewById(R.id.button1);
        arrayOfButtons[2] = (SquareButton) findViewById(R.id.button2);
        arrayOfButtons[3] = (SquareButton) findViewById(R.id.button3);
        arrayOfButtons[4] = (SquareButton) findViewById(R.id.button4);
        arrayOfButtons[5] = (SquareButton) findViewById(R.id.button5);
        arrayOfButtons[6] = (SquareButton) findViewById(R.id.button6);
        arrayOfButtons[7] = (SquareButton) findViewById(R.id.button7);
        arrayOfButtons[8] = (SquareButton) findViewById(R.id.button8);

        //определение текст вью для счета
        winLeft = (TextView) findViewById(R.id.totalWinLeftPlayer);
        winRight = (TextView) findViewById(R.id.totalWinRightPlayer);

        // заполнение массива игроков из БД для использования в спинерах
        presenter.getArrayOfPlayer();
        presenter.getArrayOfPlayerForLeft();

        // адаптер для спинеров
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presenter.arrayOfPlayer);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterForLeft = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presenter.arrayOfPlayerForLeft);
        adapterForLeft.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        imageOfLeftPlayer = (ImageView) findViewById(R.id.imageView);
        imageOfRightPlayer = (ImageView) findViewById(R.id.imageView2);


        spinnerLeft = (Spinner) findViewById(R.id.spinnerLeft);
        spinnerLeft.setAdapter(adapterForLeft);

        spinnerRight = (Spinner) findViewById(R.id.spinnerRight);
        spinnerRight.setAdapter(adapter);

        // устанавливаем спинер
        spinnerLeft.setSelection(0);
        // запоминать спинер
        presenter.setSpinnerLeft(spinnerLeft.getSelectedItem().toString());

        spinnerRight.setSelection(1);
        presenter.setSpinnerRight(spinnerRight.getSelectedItem().toString());

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tv_animation);

        // установка левого игрока активным в первый раз
        presenter.makeIconActive(LEFT_NAME);

        // устанавливаем обработчик нажатия
        spinnerLeft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                //проверка равенства выбранных спинеров
                presenter.checkEqualsSpinnerLeft();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        spinnerRight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                //проверка равенства выбранных спинеров
                presenter.checkEqualsSpinnerRight();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        adapterLevel = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presenter.getArrayOfLevel());
        adapterLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerLevel = (Spinner) findViewById(R.id.spinnerLevel);
        spinnerLevel.setAdapter(adapterLevel);

        spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                presenter.setSpinnerLevel(spinnerLevel.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        //проверка видимости спинера уровня
        presenter.checkVisibilitySpinnerLevel();


        //определение кнопки выбора символов
        symbolOfBtnLeftPlayer = (Button) findViewById(R.id.buttonSymbolLeftPlayer);
        symbolOfBtnRightPlayer = (Button) findViewById(R.id.buttonSymbolRightPlayer);

    }

    //обновление адаптеров спинеров после создания новых игроков
    public void updateAdapters() {
        adapter.notifyDataSetChanged();
        adapterForLeft.notifyDataSetChanged();

    }

    // обработка нажатий
    public void onclick(View v) {

        presenter.click(v.getId());

    }

    public void menuSettings() {
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
