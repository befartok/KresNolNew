package com.example.admin.kresnol;

import android.util.Log;

public class Player {

    public String name;
    public String symbol;
    private boolean active;

    final String LOG_TAG = "myLogs";


    public Player(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isActive(){
            return active;

    }

    public void setActive(final boolean active){
        this.active = active;

    }

    public void setPassive(){
        active = false;
    }

    /*public void droidsStep(){
       if (name.equals("Android")){


       } else {
           Log.d(LOG_TAG, "ошибка, игрок не Андроид");

       }
    }*/
}
