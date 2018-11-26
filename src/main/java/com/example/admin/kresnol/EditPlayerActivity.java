package com.example.admin.kresnol;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;

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

    Dialog dialog;
    int viewClicked;
    String playersName;

    EditText nameToDialog;

    RadioButton rename;
    RadioButton clearStats;
    RadioButton deletePlayer;
    long idOfRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);


        //initView();


        db = new Db(this);


        mCursor = db.getAllItems();

        String[] from = new String[]{DbHelper.KEY_NAME, DbHelper.KEY_TOTAL_PLAY,
                DbHelper.KEY_TOTAL_WIN};

        //массив из полей шаблона item_edit_player
        int[] to = new int[]{R.id.edit_player_item_name, R.id.edit_player_item_totalPlay,
                R.id.edit_player_item_totalWin};


        mLv = (ListView) findViewById(R.id.edit_player_lv);

        Log.d(LOG_TAG, "test ");

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(LOG_TAG, "position = " + position + " && id = " + id);
                Log.i(LOG_TAG, "имя = " + db.getNameFromRecordOfDb(id));
                playersName = db.getNameFromRecordOfDb(id);
                removeDialog(DIALOG);
                showDialog(DIALOG);
                idOfRecord = id;


            }
        });

        mCursorAd = new SimpleCursorAdapter(this, R.layout.item_edit_player, mCursor, from, to, 0);


        mLv.setAdapter(mCursorAd);



    }


    public void onclick(View v) {
        viewClicked = v.getId();

        switch (viewClicked) {

            case R.id.dialog_button_cancel:
                dialog.dismiss();
                break;
            case R.id.dialog_button_ok:

                if (rename.isChecked()){
                    Log.d(LOG_TAG, "rename");

                }
                if (clearStats.isChecked()){
                    Log.d(LOG_TAG, "clearStats");

                }
                if (deletePlayer.isChecked()){
                    Log.d(LOG_TAG, "deletePlayer");

                    db.deleteItem(idOfRecord);
                    mCursor = db.getAllItems();
                    mCursorAd.changeCursor(mCursor); // обновить список
                    removeDialog(DIALOG);

                }



                break;

        }
    }


    @Override
    protected Dialog onCreateDialog(int id) {

        // создаем view из dialog_edit_player.xml
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_edit_player,
                null);


        nameToDialog = (EditText) layout.findViewById(R.id.dialog_edit_player_item_name);
        /*TextView totalPlay = (TextView)layout.findViewById(R.id.dialog_edit_player_item_totalPlay);
        TextView totalWin = (TextView)layout.findViewById(R.id.dialog_edit_player_item_totalWin);

      */


        rename = (RadioButton) layout.findViewById(R.id.dialog_rename);
        clearStats = (RadioButton) layout.findViewById(R.id.dialog_clear_stats);
        deletePlayer = (RadioButton) layout.findViewById(R.id.dialog_delete_player);
        Button buttonOk = (Button) layout.findViewById(R.id.dialog_button_ok);
        Button buttonCancel = (Button) layout.findViewById(R.id.dialog_button_cancel);

        nameToDialog.setText(playersName);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(layout);

        dialog = builder.create();
        return dialog;


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        //userCursor.close();
    }

}
