package com.example.admin.kresnol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

//import static com.example.admin.kresnol.R.id.group1;

//это вью паттерна мвп
public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    SquareButton arrayOfButtons[] = new SquareButton[9];

    Button symbolOfBtnLeftPlayer;
    Button symbolOfBtnRightPlayer;

    Spinner spinnerLeft;
    Spinner spinnerRight;
    Spinner spinnerLevel;

    ImageView imageOfRightPlayer;

    MainPresenter presenter;

    Menu myMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "41 test");
        init();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        myMenu = menu;
        getMenuInflater().inflate(R.menu.mymenu, menu);

        //getFragmentManager().beginTransaction().replace(android.R.id.content, new Fragment()).commit();
        //getFragmentManager().beginTransaction().replace(R.id.prefs_content, new Fragment()).commit();

        return super.onCreateOptionsMenu(menu);
    }

    // обновление меню
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // пункты меню с ID группы = 1 видны, если в CheckBox стоит галка
        //menu.setGroupVisible(1, chb.isChecked());
        return super.onPrepareOptionsMenu(menu);
        //onMenuOpened();


    }


    // TODO: 28.08.18 блокировать пункты меню в зависимости от статуса игры
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

        // адаптер
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayOfPlayers);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presenter.getArrayOfPlayer());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        imageOfRightPlayer = (ImageView) findViewById(R.id.imageView2);

        spinnerLeft = (Spinner) findViewById(R.id.spinnerLeft);
        spinnerLeft.setAdapter(adapter);

        spinnerRight = (Spinner) findViewById(R.id.spinnerRight);
        spinnerRight.setAdapter(adapter);

        // выделяем элемент
        spinnerLeft.setSelection(0);
        spinnerRight.setSelection(1);

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
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        //определение кнопки выбора символов
        symbolOfBtnLeftPlayer = (Button) findViewById(R.id.buttonSymbolLeftPlayer);
        symbolOfBtnRightPlayer = (Button) findViewById(R.id.buttonSymbolRightPlayer);

        Log.d(LOG_TAG, "148 init test");


    }

    public void onclick(View v) {

        Log.d(LOG_TAG, "150 onclick test");


        // TODO: 15.08.18 подсвечивать активного игрока при смене на андроид

        // TODO: 20.04.18 после тура p-p, на дроид изи первый ход в центр не делает человек, если в предыдущем туре ход перешол к правому

        // TODO: 20.04.18 сделать анимацию зачеркивания выигрышных кнопок

        // TODO: 12.03.18 заменить подсветку ходящего на анимацию

        // TODO: 13.05.18 добавить меню с настройками

        // TODO: 13.05.18 добавить создание нового игрока

        // TODO: 13.05.18 добавить бд с игроками

        // TODO: 13.05.18 добавить в бд результаты

        // TODO: 15.04.18 расставить паузы

        // TODO: 09.07.18 при смене игроков менять счет

        // TODO: 08.08.18 запретить выбирать в спинере выбранного с другой стороны игрока

        // TODO: 12.08.18 вынести имя Андроид в ресурсы


        // обработку нажатий
        presenter.click(v.getId());

    }

    public void setPrefs() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void menuRecords() {
        Intent intent = new Intent(MainActivity.this, HighScoreActivity.class);
        startActivity(intent);

    }

    public void menuAbout() {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }


}
