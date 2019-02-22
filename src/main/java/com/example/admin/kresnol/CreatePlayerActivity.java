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

    static public String getPositionToSet() {
        return positionToSet;
    }

    static public String getNamePlayerToSet() {
        return namePlayerToSet;
    }

    static String positionToSet;
    static String namePlayerToSet;


    final String LOG_TAG = "myLogs";
    final String LEFT = "left";
    final String RIGHT = "right";

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

        //String nameOfPlayer;

        switch (v.getId()) {
            case R.id.buttonCreate:

                //nameOfPlayer = etNameOfPlayer.getText().toString();

                db = new Db(this);

                if (db.checkName(etNameOfPlayer.getText().toString())) {

                    Toast toast = Toast.makeText(getBaseContext(), "Игрок с таким именем уже существует", Toast.LENGTH_LONG);
                    //Выставляем положение сообщения вверху экрана:
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                    //Toast.makeText(getBaseContext(),"Игрок с таким именем уже существует", Toast.LENGTH_SHORT).show();

                } else {
// TODO: 10.02.19 не учтена возможность одновременной установки и лефт и райт

                    db.addPlayer(etNameOfPlayer.getText().toString());

                    updateSpinner = true;
                    if (rbSetLeft.isChecked()) {
                        positionToSet=LEFT;
                        namePlayerToSet=etNameOfPlayer.getText().toString();

                        setPlayerToGame = true;

                    };
                    if (rbSetRight.isChecked()) {

                        positionToSet=RIGHT;
                        namePlayerToSet=etNameOfPlayer.getText().toString();

                        setPlayerToGame = true;

                    }

                    onBackPressed();// возврат на предыдущий activity


                }
                db.close();
                break;


            case R.id.btnCancel:
                Log.i(LOG_TAG, "btnCancel");

                onBackPressed();// возврат на предыдущий activity

                break;
        }

    }




}
