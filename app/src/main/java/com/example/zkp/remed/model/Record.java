package com.example.zkp.remed.model;

import java.util.Calendar;

/**
 * Created by zkp on 1/23/16.
 */
public class Record {
    private String medName;
    private Calendar time;

    public Record(String medName, Calendar time) {
        this.medName = medName;
        this.time = time;
    }

    public String getMedName () {
        return medName;
    }

    public Calendar getTime() {
        return time;
    }
}
