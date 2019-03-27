package com.example.admin.kresnol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by admin on 03.11.18.
 */

public class CreatePlayerActivity extends AppCompatActivity {

    EditText etNameOfPlayer;
    RadioButton rbSetLeft;
    RadioButton rbSetRight;
    Button btnCreate;
    Button btnCancel;

    Db db;

    public static boolean isUpdateSpinner() {
        return updateSpinner;
    }

    static boolean  updateSpinner=false;

    public static boolean isSetPlayerToGame() {
        return setPlayerToGame;
    }

    static boolean  setPlayerToGame=false;

    static public String getNameLeftPlayerToSet() {
        return nameLeftPlayerToSet;
    }
    static public String getNameRightPlayerToSet() {
        return nameRightPlayerToSet;
    }


    public static boolean isLeftPositionToSet() {
        return leftPositionToSet;
    }

    static boolean leftPositionToSet=false;

    public static boolean isRightPositionToSet() {
        return rightPositionToSet;
    }

    static boolean rightPositionToSet = false;
    static String nameLeftPlayerToSet;
    static String nameRightPlayerToSet;


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

                    updateSpinner = true;

                    //проверяем чекбокс установить игрока слева
                    if (rbSetLeft.isChecked()) {
                        leftPositionToSet=true;
                        nameLeftPlayerToSet=etNameOfPlayer.getText().toString();

                        setPlayerToGame = true;

                    };

                    //проверяем чекбокс установить игрока справа
                    if (rbSetRight.isChecked()) {

                        rightPositionToSet=true;
                        nameRightPlayerToSet=etNameOfPlayer.getText().toString();

                        setPlayerToGame = true;

                    }

                    onBackPressed();// возврат на предыдущий activity
                }

                db.close();
                break;

            //нажатие кнопки отмена
            case R.id.btnCancel:
                Log.i(LOG_TAG, "btnCancel");

                onBackPressed();// возврат на предыдущий activity

                break;
        }

    }




}
