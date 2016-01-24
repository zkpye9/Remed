package com.example.zkp.remed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
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

        String AM_PM;
        if(radioValue.equals("AM")) {
            AM_PM = "AM";
        } else {
            AM_PM = "PM";
        }

        RadioGroup rg2 = (RadioGroup)findViewById(R.id.radioFeq);
        String radioValue2 = ((RadioButton)findViewById(rg2.getCheckedRadioButtonId())).getText().toString();

        String Freq;
        if(radioValue2.equals("Daily")) {
            Freq = "Daily";

        } else {
            Freq = "Weekly";
        }

        //ParseUser.getCurrentUser().remove("medName");
        ParseUser.getCurrentUser().put("medName", medNameParse);
        ParseUser.getCurrentUser().saveInBackground();
        //ParseUser.getCurrentUser().remove("hour");
        ParseUser.getCurrentUser().put("hour", hourParse);
        ParseUser.getCurrentUser().saveInBackground();
        //ParseUser.getCurrentUser().remove("min");
        ParseUser.getCurrentUser().put("min", minParse);
        ParseUser.getCurrentUser().saveInBackground();
        //newRecord = new Record(medNameParse, hourParse, minParse);
        /*Record[] tempRecord = ((Record[])ParseUser.getCurrentUser().get("record"));
        if(tempRecord[0]!=null){
            tempRecord[0] = newRecord;
        }*/

        ArrayList<String> result = new ArrayList<String>();
        result.add(medNameParse);
        result.add(Freq);
        result.add(hourParse);
        result.add(minParse);
        result.add(AM_PM);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("resultArray", result);
        setResult(Activity.RESULT_OK, returnIntent);


        finish();
    }

}
