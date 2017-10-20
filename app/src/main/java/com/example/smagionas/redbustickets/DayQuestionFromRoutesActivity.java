package com.example.smagionas.redbustickets;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class DayQuestionFromRoutesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Calendar cal;
    java.util.Date date;
    DatePicker datepicker;
    int day;
    int month;
    int year;
    int year_picked ;
    int month_picked ;
    int day_picked ;
    boolean bus_direction_forth;
    TextView DateDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_question_from_routes);

        Bundle extras = getIntent().getExtras();
        bus_direction_forth = extras.getBoolean("Direction Forth");

        DateDisplayed = findViewById(R.id.DateDisplayed);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_routes);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        date = new Date();                                                            // Get today's date
        cal = Calendar.getInstance();
        cal.setTime(date);
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);


        year_picked = year;
        month_picked = month + 1;
        day_picked = day;

        DateDisplayed.setText(day_picked + " / " + month_picked + " / " + year_picked);

        View view = findViewById(android.R.id.content);
        datepicker = (DatePicker) findViewById(R.id.datePicker);

        datepicker.setMaxDate((new Date().getTime()));
        datepicker.init(year_picked, month_picked, day_picked, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {  // Notify every time user picks a new date

                date= new Date();                                                               // Get today's date
                cal = Calendar.getInstance();
                cal.setTime(date);
                int Today_day = cal.get(Calendar.DAY_OF_MONTH);
                int Today_month = cal.get(Calendar.MONTH) + 1;
                int Today_year = cal.get(Calendar.YEAR);                                            // Todo don't allow future dates

                year_picked = year;
                month_picked = monthOfYear;
                day_picked = dayOfMonth;


                if(  (year_picked<= Today_year)  && (month_picked<= Today_month)  &&  (day_picked<= Today_day) ){

                    DateDisplayed.setText(day_picked + " / " + month_picked + " / " + year_picked);


                }





            }
        });

    }


    public void onPrevDatePressed(View view){


        datepicker.updateDate(year_picked,month_picked,day_picked -1);


    }

    public void onNextDatePressed(View view){


        date= new Date();                                                               // Get today's date
        cal = Calendar.getInstance();
        cal.setTime(date);
        int Today_day = cal.get(Calendar.DAY_OF_MONTH);
        int Today_month = cal.get(Calendar.MONTH) + 1;
        int Today_year = cal.get(Calendar.YEAR);

        if(  (year_picked<= Today_year)  && (month_picked<= Today_month)  &&  (day_picked < Today_day) ){

            datepicker.updateDate(year_picked, month_picked, day_picked + 1);

        }else{

            datepicker.updateDate(Today_year, Today_month, Today_day);
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer_routes = (DrawerLayout) findViewById(R.id.drawer_layout_routes);
        if (drawer_routes.isDrawerOpen(GravityCompat.START)) {
            drawer_routes.closeDrawer(GravityCompat.START);
        } else {

            Intent new_intent = new Intent(this, RoutesActivity.class);
            new_intent.putExtra("Direction Forth",bus_direction_forth);
            this.startActivity(new_intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_first_item_routes) {
            Intent intent = new Intent(this,RoutesActivity.class);
            intent.putExtra("Direction Forth",bus_direction_forth);
            startActivity(intent);
        }else if (id == R.id.nav_second_item_routes) {
            Intent intent = new Intent(this,DefinedTicketsFromRoutesActivity.class);
            intent.putExtra("Direction Forth",bus_direction_forth);
            startActivity(intent);
        } else if (id == R.id.nav_third_item_routes) {
            Intent intent = new Intent(this,NonUpdatedTicketsFromRoutesActivity.class);
            intent.putExtra("Direction Forth",bus_direction_forth);
            startActivity(intent);
        } else if (id == R.id.nav_fourth_item_routes) {
            Intent intent = new Intent(this,BalanceQuestionFromRoutesActivity.class);
            intent.putExtra("Direction Forth",bus_direction_forth);
            startActivity(intent);
        } else if (id == R.id.nav_fifth_item_routes) {
            Intent intent = new Intent(this,DayQuestionFromRoutesActivity.class);
            intent.putExtra("Direction Forth",bus_direction_forth);
            startActivity(intent);
        } else if (id == R.id.nav_sixth_item_routes) {
            Intent intent = new Intent(this,DetailListFromRoutesActivity.class);
            intent.putExtra("Direction Forth",bus_direction_forth);
            startActivity(intent);
        }else if (id == R.id.nav_seventh_item_routes) {
            Intent intent = new Intent(this,InformationFromRoutesActivity.class);
            intent.putExtra("Direction Forth",bus_direction_forth);
            startActivity(intent);
        }else if (id == R.id.nav_eighth_item_routes) {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_routes);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void OnDateDisplayedPressed(View view){

        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"Date Picker");

    }

}
