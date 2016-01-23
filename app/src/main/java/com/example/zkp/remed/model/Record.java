package com.example.zkp.remed.model;

/**
 * Created by zkp on 1/23/16.
 */
public class Record {
    private String medName;
    private int time;

    public Record(String medName, int time) {
        this.medName = medName;
        this.time = time;
    }

    public String getMedName () {
        return medName;
    }

    public int getTime() {
        return time;
    }
}
