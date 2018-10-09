package com.example.admin.kresnol;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;



public class HighScoreActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    private Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        db = new Db(this);


        //SQLiteOpenHelper openHelper = new DataBaseOpenHelper(this);



        // получаем количество записей в базе перед изменениями
        int mCount = db.getItemCount();
        Log.d(LOG_TAG, "Количество записей в базе:" + mCount);

        // обновляем запись с id = 1 (меняем email)
        //db.updateEmail("Igor", "newemail@newemail.com");

        // удаляем запись с id = 3
        //db.deleteItem(3);

        // выводим все имеющиеся записи в лог
        List<RecordOfDb> records = db.getRecordOfDb();
        for (RecordOfDb record : records) {
            Log.d(LOG_TAG, "Имя: " + record.getName() + " сыграно: " + record.getTotalPlay());
        }
        db.close();


    }

}
