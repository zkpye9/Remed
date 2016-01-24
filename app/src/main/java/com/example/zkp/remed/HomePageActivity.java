package com.example.zkp.remed;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.PushService;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home_page);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBar actionBar = getSupportActionBar();

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.setDisplayShowTitleEnabled(true);

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.flContent, new YouLayoutFragment());
        ft.commit();
        setTitle(ParseUser.getCurrentUser().get("firstName").toString());
        nvDrawer.getMenu().findItem(R.id.nav_first_fragment).setTitle(ParseUser.getCurrentUser().get("firstName").toString());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, NewMedActivity.class);
                startActivity(intent);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();


                //Test. will be deleted.
                //PushNotification(ParseUser.getCurrentUser());
            }
        });

        ParsePush.subscribeInBackground(ParseUser.getCurrentUser().getObjectId());

        if (ParseUser.getCurrentUser().get("medName") == null) {

        } else {
            PushNotification(ParseUser.getCurrentUser());
            GregorianCalendar calendarNow = new GregorianCalendar();
            //int year = calendarNow.getGreatestMinimum(Calendar.YEAR);
            //int month = calendarNow.getGreatestMinimum(Calendar.MONTH);
            //int day = calendarNow.getGreatestMinimum(Calendar.DAY_OF_MONTH);
            int hour = calendarNow.getGreatestMinimum(Calendar.HOUR);
            int hourNeed = Integer.parseInt((String) ParseUser.getCurrentUser().get("hour"));
            int minNeed = Integer.parseInt((String) ParseUser.getCurrentUser().get("min"));
            int min = calendarNow.getGreatestMinimum((Calendar.MINUTE));

            int delayH;
            int delayM;
            if (hourNeed - hour >= 0) {
                if (minNeed - min >=0) {
                    delayH = hourNeed - hour;
                    delayM = minNeed - min;
                }
                else {
                    if (hourNeed-hour == 0) {
                        delayH = 23;
                        delayM = 60 - (min-minNeed);
                    } else {
                        delayH = hourNeed - hour - 1;
                        delayM = 60 - (min-minNeed);
                    }
                }
            } else {
                delayH = 0;
                delayM = 0;
                //TODO: calculate the time;
            }

            long delay = (delayH*60*60+delayM*60) * 1000;
            //GregorianCalendar calendarNeed = new GregorianCalendar(year, month, day, hour, min);
            long period = 86400000;
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    PushNotification(ParseUser.getCurrentUser());
                    System.out.println("456");
                }
            };
            Timer timer = new Timer("news", true);
            timer.scheduleAtFixedRate(task, 10000, period);
            //timer.scheduleAtFixedRate(task, delay, period);

        }
        /*GregorianCalendar calendarNow = new GregorianCalendar();
        //int year = calendarNow.getGreatestMinimum(Calendar.YEAR);
        //int month = calendarNow.getGreatestMinimum(Calendar.MONTH);
        //int day = calendarNow.getGreatestMinimum(Calendar.DAY_OF_MONTH);
        int hour = calendarNow.getGreatestMinimum(Calendar.HOUR);
        int hourNeed = Integer.parseInt((String) ParseUser.getCurrentUser().get("hour"));
        int minNeed = Integer.parseInt((String) ParseUser.getCurrentUser().get("min"));
        int min = calendarNow.getGreatestMinimum((Calendar.MINUTE));

        int delayH;
        int delayM;
        if (hourNeed - hour >= 0) {
            if (minNeed - min >=0) {
                delayH = hourNeed - hour;
                delayM = minNeed - min;
            }
            else {
                if (hourNeed-hour == 0) {
                    delayH = 23;
                    delayM = 60 - (min-minNeed);
                } else {
                    delayH = hourNeed - hour - 1;
                    delayM = 60 - (min-minNeed);
                }
            }
        } else {
            delayH = 0;
            delayM = 0;
            //TODO: calculate the time;
        }

        long delay = (delayH*60*60+delayM*60) * 1000;
        //GregorianCalendar calendarNeed = new GregorianCalendar(year, month, day, hour, min);
        long period = 86400000;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                PushNotification(ParseUser.getCurrentUser());
            }
        };
        Timer timer = new Timer("news", true);
        timer.scheduleAtFixedRate(task, delay, period);*/

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
        Fragment fragment = null;

        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = YouLayoutFragment.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = DoctorsLayoutFragment.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = FamilyLayoutFragment.class;
                break;
            case R.id.nav_fourth_fragment:
                Toast.makeText(getApplicationContext(), "Not yet Implemented", Toast.LENGTH_SHORT).show();
                return;
            case R.id.nav_fifth_fragment:
                ParseUser.logOut();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return;
            default:
                fragmentClass = YouLayoutFragment.class;
        }

        try {

            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item, update the title, and close the drawer
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    public void PushNotification(ParseUser pu) {
        ParsePush.subscribeInBackground(ParseUser.getCurrentUser().getObjectId());
        try{
            pu.fetchIfNeeded();
            ParsePush push = new ParsePush();
            push.setChannel(pu.getObjectId());
            push.setMessage("Notification of HomePageActivity");
            push.sendInBackground();

            System.out.println("123");

        }catch(Exception e){

        }
    }
}
