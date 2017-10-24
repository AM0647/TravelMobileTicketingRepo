package com.example.smagionas.redbustickets;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

public class DayQuestionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

//////////////////// START OF GLOBAL VARIABLES/////////////////////////

    String value1;
    Calendar cal;
    java.util.Date date;
    DatePicker datepicker;
    int day;
    int month;
    int year;
    int year_picked;
    int month_picked;
    int day_picked;
    TextView DateDisplayed;
    ImageButton nextDate;

    boolean bus_direction_forth;
//////////////////// END OF GLOBAL VARIABLES///////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_question);

        DateDisplayed = findViewById(R.id.DateDisplayed);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //ActionBar ab = getSupportActionBar();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();
        value1 = extras.getString("Route name");
        bus_direction_forth = extras.getBoolean("Direction Forth");

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


        datepicker = findViewById(R.id.datePicker);
        nextDate = findViewById(R.id.day_question_NextDate);

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
                    nextDate.setImageResource(R.mipmap.ic_arrow_right_black);

                    if(day_picked   ==  Today_day) nextDate.setImageResource(R.mipmap.ic_arrow_right_gray);

                }





            }
        });

    }

    public void onPrevDatePressed(View view) {

        nextDate.setImageResource(R.mipmap.ic_arrow_right_black);
        datepicker.updateDate(year_picked, month_picked, day_picked - 1);


    }

    public void onNextDatePressed(View view) {


        date= new Date();                                                               // Get today's date
        cal = Calendar.getInstance();
        cal.setTime(date);
        int Today_day = cal.get(Calendar.DAY_OF_MONTH);
        int Today_month = cal.get(Calendar.MONTH) + 1;
        int Today_year = cal.get(Calendar.YEAR);

        if(  (year_picked<= Today_year)  && (month_picked<= Today_month)  &&  (day_picked < Today_day) ){


            datepicker.updateDate(year_picked, month_picked, day_picked + 1);
            if(day_picked   ==  Today_day) nextDate.setImageResource(R.mipmap.ic_arrow_right_gray);

        }else{

            datepicker.updateDate(Today_year, Today_month, Today_day);
        }



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(this,TicketIssuanceActivity.class);
            intent.putExtra("Route name", value1);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_first_item) {
            Intent intent = new Intent(this,TicketIssuanceActivity.class);
            intent.putExtra("Direction Forth",bus_direction_forth);
            intent.putExtra("Route name", value1);
            startActivity(intent);
        } else if (id == R.id.nav_second_item) {
            Intent intent = new Intent(this,DefinedTicketsActivity.class);
            intent.putExtra("Direction Forth",bus_direction_forth);
            intent.putExtra("Route name", value1);
            startActivity(intent);
        } else if (id == R.id.nav_third_item) {
            Intent intent = new Intent(this,NonUpdatedTicketsActivity.class);
            intent.putExtra("Direction Forth",bus_direction_forth);
            intent.putExtra("Route name", value1);
            startActivity(intent);
        } else if (id == R.id.nav_fourth_item) {
            Intent intent = new Intent(this,BalanceQuestionActivity.class);
            intent.putExtra("Direction Forth",bus_direction_forth);
            intent.putExtra("Route name", value1);
            startActivity(intent);
        } else if (id == R.id.nav_fifth_item) {
            Intent intent = new Intent(this,DayQuestionActivity.class);
            intent.putExtra("Direction Forth",bus_direction_forth);
            intent.putExtra("Route name", value1);
            startActivity(intent);
        } else if (id == R.id.nav_sixth_item) {
            Intent intent = new Intent(this,DetailListActivity.class);
            intent.putExtra("Direction Forth",bus_direction_forth);
            intent.putExtra("Route name", value1);
            startActivity(intent);
        }else if (id == R.id.nav_seventh_item) {
            Intent intent = new Intent(this,InformationActivity.class);
            intent.putExtra("Direction Forth",bus_direction_forth);
            intent.putExtra("Route name", value1);
            startActivity(intent);
        }else if (id == R.id.nav_eighth_item) {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void OnDateDisplayedPressed(View view){

        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"Date Picker");


    }


    public void OnDayQuestionButtonPressed(View view){



    }





}

