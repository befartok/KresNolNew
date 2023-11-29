package com.example.admin.kresnol;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by admin on 03.11.18.
 */

//класс активити создания нового игрока
public class CreatePlayerActivity extends AppCompatActivity {

    private EditText etNameOfPlayer;
    private RadioButton rbSetLeft;
    private RadioButton rbSetRight;
    private Button btnCreate;
    private Button btnCancel;

    private SharedPreferences prefs;


    private Db db;

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_player);

        initCreatePlayerActivity();
    }


    private void initCreatePlayerActivity() {

        etNameOfPlayer = (EditText) findViewById(R.id.editTextName);
        rbSetLeft = (RadioButton) findViewById(R.id.radioButtonLeft);
        rbSetRight = (RadioButton) findViewById(R.id.radioButtonRight);
        btnCreate = (Button) findViewById(R.id.buttonCreate);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

    }


    public void onclick(View v) {


        switch (v.getId()) {

                //нажатие кнопки ок
                 case R.id.buttonCreate:

                db = new Db(this);

                //проверяем игрока на существование в бд
                if (db.checkName(etNameOfPlayer.getText().toString())) {

                    Toast toast = Toast.makeText(getBaseContext(), R.string.playerIsAlreadyExist, Toast.LENGTH_LONG);
                    //Выставляем положение сообщения вверху экрана:
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();

                } else {

                    //добавляем игрока в бд
                    db.addPlayer(etNameOfPlayer.getText().toString());

                    SharedPreferences.Editor ed = prefs.edit();

                    //добавляем в преференсес флаг о создании нового игрока
                    ed.putBoolean("prefNewPlayer", true);
                    ////добавляем в преференсес имя нового игрока
                    ed.putString("prefNameNewPlayer", etNameOfPlayer.getText().toString());

                    //проверяем чекбокс установить игрока слева
                    if (rbSetLeft.isChecked()) {

                        //добавляем в преференсес флаг, что игрок выбран для установки левым игроком
                        ed.putBoolean("leftPositionToSet", true);

                        //добавляем в преференсес имя нового игрока выбранного для установки левым игроком
                        ed.putString("nameLeftPlayerToSet", etNameOfPlayer.getText().toString());

                        //добавляем в преференсес флаг, установить игрока для игры
                        ed.putBoolean("setPlayerToGame", true);

                    }

                    //проверяем чекбокс установить игрока справа
                    if (rbSetRight.isChecked()) {

                        //добавляем в преференсес флаг, что игрок выбран для установки правым игроком
                        ed.putBoolean("rightPositionToSet", true);

                        //добавляем в преференсес имя нового игрока выбранного для установки правым игроком
                        ed.putString("nameRightPlayerToSet", etNameOfPlayer.getText().toString());

                        //добавляем в преференсес флаг, установить игрока для игры
                        ed.putBoolean("setPlayerToGame", true);

                    }

                    ed.apply();

                    // возврат на предыдущий activity
                    onBackPressed();
                }

                db.close();
                break;


            //нажатие кнопки отмена
            case R.id.btnCancel:

            // возврат на предыдущий activity
            onBackPressed();

                break;
        }

    }

}
