package com.example.zkp.remed;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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

        RadioGroup rg = (RadioGroup)findViewById(R.id.AM_PM);
        String radioValue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();

        if(radioValue.equals("AM")) {
            //DO AM STUFF WITH PARSE
        } else {
            //DO FM STUFF WITH PARSE
        }

        RadioGroup rg2 = (RadioGroup)findViewById(R.id.radioFeq);
        String radioValue2 = ((RadioButton)findViewById(rg2.getCheckedRadioButtonId())).getText().toString();

        if(radioValue2.equals(R.string.daily)) {
            //DO Daily STUFF WITH PARSE

        } else {
            //DO weekly STUFF WITH PARSE
        }

        newRecord = new Record(medNameParse, hourParse, minParse);


        //System.out.println(((ArrayList<Record>) ParseUser.getCurrentUser().get("record")).get(0).getMedName().toString());
        ((ArrayList<Record>)ParseUser.getCurrentUser().get("record")).add(newRecord);
    }

}
