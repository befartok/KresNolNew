package com.example.admin.kresnol;


class Player {

    public String name;

    //символ, которым ходит игрок
    private String symbol;

    //ходит ли игрок
    private boolean active;

    final String LOG_TAG = "myLogs";


    Player(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    String getSymbol() {
        return symbol;
    }

    boolean isActive() {
        return active;

    }

    void setActive(final boolean active) {
        this.active = active;

    }


}
