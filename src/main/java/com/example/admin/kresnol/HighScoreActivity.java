package com.example.admin.kresnol;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

//класс активити "Счет"
public class HighScoreActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        Db db = new Db(this);

        Cursor mCursor = db.getAllItems();

        String[] from = new String[]{DbHelper.KEY_NAME, DbHelper.KEY_TOTAL_PLAY,
                DbHelper.KEY_TOTAL_WIN};

        //массив из полей шаблона item_high_score
        int[] to = new int[]{R.id.high_score_name, R.id.high_score_totalPlay,
                R.id.high_score_totalWin};

        SimpleCursorAdapter mCursorAd = new SimpleCursorAdapter(this, R.layout.item_high_score, mCursor, from, to, 0);

        ListView mLv = (ListView) findViewById(R.id.lv);

        mLv.setAdapter(mCursorAd);


        db.close();


    }

}
