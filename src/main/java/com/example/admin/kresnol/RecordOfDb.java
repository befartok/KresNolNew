package com.example.admin.kresnol;

/**
 * Created by admin on 08.10.18.
 */

//модель базы данных

class RecordOfDb {

    private Integer id;
    private String name;
    private Integer totalPlay;
    private Integer totalWin;


    RecordOfDb(Integer id, String name, Integer totalPlay, Integer totalWin
    ) {
        this.id = id;
        this.name = name;
        this.totalPlay = totalPlay;
        this.totalWin = totalWin;
    }

    RecordOfDb(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalPlay() {
        return totalPlay;
    }

    public void setTotalPlay(Integer totalPlay) {
        this.totalPlay = totalPlay;
    }

    public Integer getTotalWin() {
        return totalWin;
    }

    public void setTotalWin(Integer totalWin) {
        this.totalWin = totalWin;
    }


}
