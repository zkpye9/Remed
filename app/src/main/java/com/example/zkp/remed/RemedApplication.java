package com.example.zkp.remed;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by zkp on 1/23/16.
 */
public class RemedApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "4ZnAIecM4dhXJ1iqM4KtdoZHPkyOG415vACmyMgb", "0hCpbdIilElJcZGj8NUBln85ekiFO1xI2LEpqVEi");

        ParseInstallation.getCurrentInstallation().saveInBackground();
        //ParseUser.enableAutomaticUser();
        //ParseACL defaultACL = new ParseACL();
        //ParseACL.setDefaultACL(defaultACL, true);

        try{
            ParseUser.getCurrentUser().fetchIfNeeded();
            List<String> subscribedChannels = ParseInstallation.getCurrentInstallation().getList("channels");
            if(!subscribedChannels.contains(ParseUser.getCurrentUser().getObjectId())){
                ParsePush.subscribeInBackground(ParseUser.getCurrentUser().getObjectId());
            }
        }catch(Exception e){

        }

    }
}
