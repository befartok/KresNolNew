package com.example.admin.kresnol;

import android.util.Log;

public class Player {

    public CharSequence name;
    public String symbol;
    private boolean activ;

    final String LOG_TAG = "myLogs";


    public Player(CharSequence name) {
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public CharSequence getName() {
        return name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isActiv(){
            return activ;

    }

    public void setActiv(final boolean activ){
        this.activ = activ;

    }

    public void setPassiv(){
        activ = false;
    }

    /*public void droidsStep(){
       if (name.equals("Android")){


       } else {
           Log.d(LOG_TAG, "ошибка, игрок не Андроид");

       }
    }*/
}
