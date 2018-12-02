package com.example.admin.kresnol;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.List;


//это вью паттерна мвп
public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "41 test");




        init();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // TODO: 24.09.18 тест, потом удалить
        String prefTest = prefs.getString("pref_level", "");
        //winLeft.setText(prefTest);
        Log.d(LOG_TAG, "Уровень сложности сохраненный в настройках" + prefTest);

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

        // TODO: 30.09.18 вынести в метод? или в модель?
        String prefTest = prefs.getString("pref_level", "");

        //winLeft.setText(prefTest);
        Log.d(LOG_TAG, "Уровень сложности установленный в настройках" + prefTest);

        presenter.setSpinnerLevel(prefTest);


        switch (prefTest) {
            case "Easy":
                // TODO: 28.07.18 вынести в метод?
                spinnerLevel.setSelection(0);
                break;

            case "Normal":
                spinnerLevel.setSelection(1);

                break;
            case "Hard":
                spinnerLevel.setSelection(2);
                break;
        }
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

    // обработка нажатий
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (presenter.clickMenu(item.getItemId()) == true) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    private void init() {

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
        //winLeft.setText(SettingsActivity.SettingsFragment.settingLevel.getEntry());

/*        List<String> catNames = new ArrayList<String>();
        catNames.add("Барсик");
        catNames.add("Мурзик");
        catNames.add("Рыжик");*/


        //presenter.getArrayOfPlayer();

        // адаптер
       //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presenter.getArrayOfPlayer());
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presenter.arrayOfPlayer);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        imageOfLeftPlayer = (ImageView) findViewById(R.id.imageView);
        imageOfRightPlayer = (ImageView) findViewById(R.id.imageView2);


        spinnerLeft = (Spinner) findViewById(R.id.spinnerLeft);
        spinnerLeft.setAdapter(adapter);

        spinnerRight = (Spinner) findViewById(R.id.spinnerRight);
        spinnerRight.setAdapter(adapter);

        // устанавливаем элемент
        spinnerLeft.setSelection(0, true);
        //spinnerRight.setSelection(1,true);

        //spinnerLeft.setSelection(0);
        spinnerRight.setSelection(1);

        //устанавливаем цвет спинера
        ((TextView) spinnerLeft.getSelectedView()).setTextColor(getResources().getColor(R.color.buttonsTextActive));

        // устанавливаем обработчик нажатия
        spinnerLeft.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                //Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
                presenter.setSpinnerLeft(spinnerLeft.getSelectedItem().toString());

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
                //Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();

                //установить сеттер для модели транзитом через презентер
                presenter.setSpinnerRight(spinnerRight.getSelectedItem().toString());
                //видимость спинера уровня для игрока Андроид
                presenter.checkVisibilitySpinnerLevel();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }


        })


        ;

        //ArrayAdapter<String> adapterLevel = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayOfLevel);
        ArrayAdapter<String> adapterLevel = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presenter.getArrayOfLevel());
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
                prefs.edit().putString("pref_level", spinnerLevel.getSelectedItem().toString()).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        //определение кнопки выбора символов
        symbolOfBtnLeftPlayer = (Button) findViewById(R.id.buttonSymbolLeftPlayer);
        symbolOfBtnRightPlayer = (Button) findViewById(R.id.buttonSymbolRightPlayer);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tv_animation);

        // анимацию запуск для левого в первый раз
        imageOfLeftPlayer.startAnimation(animation);

        //Log.d(LOG_TAG, "148 init test");

    }

    public void onclick(View v) {

        Log.d(LOG_TAG, "150 onclick test");


        // TODO: 15.08.18 подсвечивать активного игрока при смене на андроид

        // TODO: 20.04.18 после тура p-p, на дроид изи первый ход в центр не делает человек, если в предыдущем туре ход перешол к правому

        // TODO: 20.04.18 сделать анимацию зачеркивания выигрышных кнопок

        // TODO: 13.05.18 добавить создание нового игрока

        // TODO: 13.05.18 добавить бд с игроками

        // TODO: 13.05.18 добавить в бд результаты

        // TODO: 15.04.18 расставить паузы

        // TODO: 09.07.18 при смене игроков менять счет

        // TODO: 08.08.18 запретить выбирать в спинере выбранного с другой стороны игрока

        //// TODO: 22.09.18 добавить в настройки выбор игроков

        //// TODO: 22.09.18 добавить тесты

        // TODO: 24.09.18 сделать иконки неактивного серыми

        // TODO: 15.10.18 вынести БД в другой поток

        // TODO: 02.12.18 обновлять спинеры после изменения и создания игроков


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
