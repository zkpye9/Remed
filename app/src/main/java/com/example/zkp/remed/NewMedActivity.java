package com.example.zkp.remed;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.zkp.remed.model.Record;
import com.parse.ParseUser;

import java.util.ArrayList;

public class NewMedActivity extends AppCompatActivity {

    private EditText medName;
    private String medNameParse;
    private EditText hour;
    private String hourParse;
    private EditText min;
    private String minParse;
    private RadioGroup APM;

    private RadioGroup Frequency;

    private Record newRecord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_med);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void create(View view) {
        medName = (EditText)findViewById(R.id.medName);
        medNameParse = medName.getText().toString();

        hour = (EditText)findViewById(R.id.Hour);
        hourParse = hour.getText().toString();

        min = (EditText)findViewById(R.id.Min);
        minParse = min.getText().toString();

        newRecord = new Record(medNameParse, hourParse, minParse);
        ((ArrayList<Record>)ParseUser.getCurrentUser().get("record")).add(newRecord);
    }

}
