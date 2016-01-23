package com.example.zkp.remed;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {



    private EditText firstName;
    private EditText secondName;
    private EditText email;
    private EditText phoneNumber;
    private EditText pw;
    private EditText cPW;
    private Button signUpButton;
    private String pwParse;
    private String emailParse;
    private String firstParse;
    private String lastParse;
    private String phoneParse;
    private String cPWParse;

    private boolean fullFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Parse.initialize(this, "4ZnAIecM4dhXJ1iqM4KtdoZHPkyOG415vACmyMgb", "0hCpbdIilElJcZGj8NUBln85ekiFO1xI2LEpqVEi");
    }

    public void signUpConfirmation(View view) {
        fullFields = true;

        firstName = (EditText)findViewById(R.id.FirstName);
        firstParse = firstName.getText().toString();

        if(TextUtils.isEmpty(firstParse)) {
            firstName.setError(getString(R.string.missing_firstName));
            fullFields = false;
        }

        secondName = (EditText)findViewById(R.id.LastName);
        lastParse = secondName.getText().toString();

        if(TextUtils.isEmpty(lastParse)) {
            secondName.setError(getString(R.string.missing_lastName));
            fullFields = false;
        }

        email = (EditText) findViewById(R.id.LoginEmail);
        emailParse = email.getText().toString();

        if(TextUtils.isEmpty(emailParse)) {
            email.setError(getString(R.string.missing_email));
            fullFields = false;
        }

        phoneNumber = (EditText) findViewById(R.id.PhoneNumber);
        phoneParse = phoneNumber.getText().toString();

        if(TextUtils.isEmpty(phoneParse)) {
            phoneNumber.setError(getString(R.string.missing_phoneNumber));
            fullFields = false;
        }

        pw = (EditText) findViewById(R.id.LoginPassword);
        pwParse = pw.getText().toString();

        if(TextUtils.isEmpty(pwParse)) {
            pw.setError(getString(R.string.missing_LoginPassword));
            fullFields = false;
        }

        cPW = (EditText) findViewById(R.id.ConfirmPassword);
        cPWParse = cPW.getText().toString();

        if(TextUtils.isEmpty(cPWParse)) {
            cPW.setError(getString(R.string.missing_ConfirmPassword));
            fullFields = false;
        }

        if(fullFields == false) {
            ParseUser.getCurrentUser().logOut();
            return;
        }

        if (pwParse.compareTo(cPWParse) == 0) {
            //System.out.println("Work2\n\n");
            ParseUser user = new ParseUser();
            user.setPassword(pwParse);
            user.setUsername(emailParse);

            // other fields can be set just like with ParseObject
            user.put("phone", phoneParse);
            user.put("firstName", firstParse);
            user.put("lastName", lastParse);

            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        // Hooray! Let them use the app now.
                    } else {
                        if (e.getCode() == ParseException.USERNAME_TAKEN)
                            email.setError(getString(R.string.email_taken));

                        ParseUser.getCurrentUser().logOut();
                        return;
                        // Sign up didn't succeed. Look at the ParseException
                        // to figure out what went wrong
                    }
                }
            });
        } else {
            cPW.setError(getString(R.string.matching_pass_error));
            ParseUser.getCurrentUser().logOut();
            return;
        }
    }

}

