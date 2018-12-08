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
    RadioButton rbForLeft;
    RadioButton rbForRight;
    Button btnCreate;
    Button btnCancel;

    Db db;
    static boolean  updateSpinner=false;

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_player);

        initCreatePlayerActivity();
    }


    private void initCreatePlayerActivity() {

        etNameOfPlayer = (EditText) findViewById(R.id.editTextName);
        rbForLeft = (RadioButton) findViewById(R.id.radioButtonLeft);
        rbForRight = (RadioButton) findViewById(R.id.radioButtonRight);
        btnCreate = (Button) findViewById(R.id.buttonCreate);
        btnCancel = (Button) findViewById(R.id.btnCancel);


    }

    public static boolean isUpdateSpinner() {
        return updateSpinner;
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

                    db.addPlayer(etNameOfPlayer.getText().toString());

                    updateSpinner = true;
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
