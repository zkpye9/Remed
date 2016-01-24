package com.example.zkp.remed.model;

import java.util.Calendar;

/**
 * Created by zkp on 1/23/16.
 */
public class Record {
    private String medName;
    private String hour;
    private String min;

    public Record(String medName, String hour, String min) {
        this.medName = medName;
        this.hour = hour;
        this.min = min;
    }

    public String getMedName () {
        return medName;
    }

    public String getHour() { return hour;}

    public String getMin() {return min;}
}
