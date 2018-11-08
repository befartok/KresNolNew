package com.example.admin.kresnol;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.List;



public class HighScoreActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    private Db db;
    private Cursor mCursor;
    private SimpleCursorAdapter mCursorAd;
    private ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

      /*  TextView high_score_name= (TextView) findViewById(R.id.high_score_name);
        TextView high_score_totalPlay= (TextView) findViewById(R.id.high_score_totalPlay);
        TextView high_score_totalWin= (TextView) findViewById(R.id.high_score_totalWin);
        TextView high_score_totalLose= (TextView) findViewById(R.id.high_score_totalLose);
*/
        db = new Db(this);


        mCursor = db.getAllItems();

        String[] from = new String[] { DbHelper.KEY_NAME, DbHelper.KEY_TOTAL_PLAY,
                DbHelper.KEY_TOTAL_WIN};
        int[] to = new int[] { R.id.high_score_name, R.id.high_score_totalPlay,
                R.id.high_score_totalWin};

        mCursorAd = new SimpleCursorAdapter(this, R.layout.item_high_score, mCursor, from, to, 0);
        mLv = (ListView) findViewById(R.id.lv);
        mLv.setAdapter(mCursorAd);



        // получаем количество записей в базе перед изменениями
        //int mCount = db.getItemCount();
        //Log.d(LOG_TAG, "Количество записей в базе:" + mCount);

        // обновляем запись с id = 1 (меняем email)
        //db.updateEmail("Igor", "newemail@newemail.com");

        // удаляем запись с id = 3
        //db.deleteItem(3);

        // выводим все имеющиеся записи в лог

        //List<RecordOfDb> records = db.getRecordOfDb();

        //high_score_name.setText(records.get(1).getName());
        //
/*        high_score_name.setText("Игрок"+"\n\n\n" );
        high_score_totalPlay.setText("Всего сыграно"+"\n\n" );
        high_score_totalWin.setText("Всего выиграно"+"\n\n" );
        high_score_totalLose.setText("Всего проиграно"+"\n\n" );

        for (RecordOfDb record : records) {
            Log.d(LOG_TAG, "Имя: " + record.getName() + " сыграно: " + record.getTotalPlay());

            high_score_name.append(record.getName()+ "\n");
            high_score_totalPlay.append(record.getTotalPlay().toString()+ "\n");
            high_score_totalWin.append(record.getTotalWin().toString()+ "\n");
            high_score_totalLose.append(record.getTotalLose().toString() + "\n");
        }*/



        db.close();


    }

}
