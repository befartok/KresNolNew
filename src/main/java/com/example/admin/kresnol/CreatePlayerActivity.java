package com.example.admin.kresnol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_player);

        initCreatePlayerActivity();
    }




    private  void initCreatePlayerActivity() {

        etNameOfPlayer= (EditText) findViewById(R.id.editTextName);
        rbForLeft= (RadioButton) findViewById(R.id.radioButtonLeft);
        rbForRight= (RadioButton) findViewById(R.id.radioButtonRight);
        btnCreate= (Button) findViewById(R.id.buttonCreate);
        btnCancel= (Button) findViewById(R.id.buttonCancel);


    }

    // TODO: 08.11.18 проверить на уникальность имен при создании нового
    // TODO: 08.11.18 выходить на первый экран после создания и обновлять спинер
    // TODO: 16.11.18 кнопку отмена настроить

    public void onclick(View v) {

        //String nameOfPlayer;

        switch (v.getId()) {
            case R.id.buttonCreate:

                //nameOfPlayer = etNameOfPlayer.getText().toString();


                db = new Db(this);

                db.addPlayer(etNameOfPlayer.getText().toString());

                db.close();
                break;


        }

    }


    }
