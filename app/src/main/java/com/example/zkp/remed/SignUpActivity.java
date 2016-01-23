package com.example.zkp.remed;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void signUp(View view) {
        EditText firstName = (EditText)findViewById(R.id.FirstName);
        EditText secondName = (EditText)findViewById(R.id.LastName);
        EditText email = (EditText) findViewById(R.id.LoginEmail);
        EditText phoneNumber = (EditText) findViewById(R.id.PhoneNumber);
        EditText pw = (EditText) findViewById(R.id.LoginPassword);
        EditText cPW = (EditText) findViewById(R.id.ConfirmPassword);

        
    }

}
