package com.example.admin.kresnol;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by admin on 12.09.18.
 */

//класс экрана настроек, вызывает франмент с настройками
public class SettingsActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getFragmentManager().beginTransaction().add(R.id.prefs_content, new SettingsFragment()).commit();

    }


}



