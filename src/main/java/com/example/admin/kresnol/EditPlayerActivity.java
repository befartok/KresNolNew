package com.example.admin.kresnol;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by admin on 10.11.18.
 */

public class EditPlayerActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    private Db db;
    private Cursor mCursor;
    private SimpleCursorAdapter mCursorAd;
    private ListView mLv;

    final int DIALOG = 1;
    int lvClicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);

        db = new Db(this);


        mCursor = db.getAllItems();

        String[] from = new String[] { DbHelper.KEY_NAME, DbHelper.KEY_TOTAL_PLAY,
                DbHelper.KEY_TOTAL_WIN};

        //массив из полей шаблона item_edit_player
        int[] to = new int[] { R.id.edit_player_item_name, R.id.edit_player_item_totalPlay,
                R.id.edit_player_item_totalWin};

        mCursorAd = new SimpleCursorAdapter(this, R.layout.item_edit_player, mCursor, from, to, 0);
        mLv = (ListView) findViewById(R.id.edit_player_lv);
        mLv.setAdapter(mCursorAd);



        db.close();


    }

    public void onclick(View v) {
        lvClicked = v.getId();
        showDialog(DIALOG);
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        /*AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Custom dialog");*/
        // создаем view из dialog.xml

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_edit_player,
                null);

        TextView nameHeader = (TextView)layout.findViewById(R.id.dialog_edit_player_name_header);
        TextView totalPlayHeader = (TextView)layout.findViewById(R.id.dialog_edit_player_totalPlay_header);
        TextView totalWinHeader = (TextView)layout.findViewById(R.id.dialog_edit_player_totalWin_header);

        EditText  name= (EditText) layout.findViewById(R.id.dialog_edit_player_item_name);
        TextView totalPlay = (TextView)layout.findViewById(R.id.dialog_edit_player_item_totalPlay);
        TextView totalWin = (TextView)layout.findViewById(R.id.dialog_edit_player_item_totalWin);

        RadioButton rename = (RadioButton)layout.findViewById(R.id.dialog_rename);
        RadioButton clearStats = (RadioButton)layout.findViewById(R.id.dialog_clear_stats);
        RadioButton deletePlayer = (RadioButton)layout.findViewById(R.id.dialog_delete_player);
        Button buttonOk = (Button)layout.findViewById(R.id.dialog_button_ok);
        Button buttonCancel = (Button)layout.findViewById(R.id.dialog_button_cancel);
        //text.setText("Закрыть? Вы уверены?");


        //Далее создается объект AlertDialog.Builder и методом setView() для него устанавливается полученная ранее разметка:


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(layout);

        return builder.create();



    }

}
