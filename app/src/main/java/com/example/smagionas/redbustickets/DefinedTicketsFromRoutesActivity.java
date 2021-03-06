package com.example.smagionas.redbustickets;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DefinedTicketsFromRoutesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//////////////////// START OF GLOBAL VARIABLES/////////////////////////

    public TextView displayed_value;
    public String value_to_display="";
    public Double value= 0.0;

    boolean type_selected = false;
    boolean comma_used = false;
    boolean has_inputted_value = false;
    boolean bus_direction_forth;
//////////////////// END OF GLOBAL VARIABLES///////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defined_tickets_from_routes);

        Bundle extras = getIntent().getExtras();
        bus_direction_forth = extras.getBoolean("Direction Forth");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        displayed_value = findViewById(R.id.defined_tickets_from_routes_numpad_screen);
        displayed_value.setTextSize(TypedValue.COMPLEX_UNIT_PX,45);

        DrawerLayout drawer_routes = findViewById(R.id.drawer_layout_routes);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_routes, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_routes.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer_routes = findViewById(R.id.drawer_layout_routes);
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
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }




    public void OnButtonPressed_0(View view)
    {

        if(!type_selected){

        if(has_inputted_value) {


            if (comma_used) {
                value_to_display = value_to_display.substring(0, value_to_display.length() - 3) + "0" + " €";
            } else {
                value_to_display = value_to_display.substring(0, value_to_display.length() - 2) + "0" + " €";
            }



        }else{

            value_to_display = "0" + " €";

        }

        displayed_value.setText(value_to_display);
        has_inputted_value = true;

    }


    }

    public void OnButtonPressed_1(View view)
    {
        if(!type_selected){

            if(has_inputted_value) {


                if (comma_used) {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 3) + "1" + " €";
                } else {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 2) + "1" + " €";
                }



            }else{

                value_to_display = "1" + " €";

            }

            displayed_value.setText(value_to_display);
            has_inputted_value = true;

        }



    }

    public void OnButtonPressed_2(View view)
    {

        if(!type_selected){

            if(has_inputted_value) {


                if (comma_used) {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 3) + "2" + " €";
                } else {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 2) + "2" + " €";
                }



            }else{

                value_to_display = "2" + " €";

            }

            displayed_value.setText(value_to_display);
            has_inputted_value = true;

        }

    }

    public void OnButtonPressed_3(View view)
    {

        if(!type_selected){

            if(has_inputted_value) {


                if (comma_used) {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 3) + "3" + " €";
                } else {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 2) + "3" + " €";
                }



            }else{

                value_to_display = "3" + " €";

            }

            displayed_value.setText(value_to_display);
            has_inputted_value = true;

        }

    }

    public void OnButtonPressed_4(View view)
    {

        if(!type_selected){

            if(has_inputted_value) {


                if (comma_used) {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 3) + "4" + " €";
                } else {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 2) + "4" + " €";
                }



            }else{

                value_to_display = "4" + " €";

            }

            displayed_value.setText(value_to_display);
            has_inputted_value = true;

        }

    }

    public void OnButtonPressed_5(View view)
    {

        if(!type_selected){

            if(has_inputted_value) {


                if (comma_used) {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 3) + "5" + " €";
                } else {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 2) + "5" + " €";
                }



            }else{

                value_to_display = "5" + " €";

            }

            displayed_value.setText(value_to_display);
            has_inputted_value = true;

        }

    }

    public void OnButtonPressed_6(View view)
    {

        if(!type_selected){

            if(has_inputted_value) {


                if (comma_used) {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 3) + "6" + " €";
                } else {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 2) + "6" + " €";
                }



            }else{

                value_to_display = "6" + " €";

            }

            displayed_value.setText(value_to_display);
            has_inputted_value = true;

        }

    }

    public void OnButtonPressed_7(View view)
    {

        if(!type_selected){

            if(has_inputted_value) {


                if (comma_used) {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 3) + "7" + " €";
                } else {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 2) + "7" + " €";
                }



            }else{

                value_to_display = "7" + " €";

            }

            displayed_value.setText(value_to_display);
            has_inputted_value = true;

        }

    }

    public void OnButtonPressed_8(View view)
    {

        if(!type_selected){

            if(has_inputted_value) {


                if (comma_used) {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 3) + "8" + " €";
                } else {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 2) + "8" + " €";
                }



            }else{

                value_to_display = "8" + " €";

            }

            displayed_value.setText(value_to_display);
            has_inputted_value = true;

        }

    }

    public void OnButtonPressed_9(View view)
    {

        if(!type_selected){

            if(has_inputted_value) {


                if (comma_used) {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 3) + "9" + " €";
                } else {
                    value_to_display = value_to_display.substring(0, value_to_display.length() - 2) + "9" + " €";
                }



            }else{

                value_to_display = "9" + " €";

            }

            displayed_value.setText(value_to_display);
            has_inputted_value = true;

        }

    }

    public void OnButtonPressed_comma(View view)
    {

        if(  (!type_selected) && (!comma_used)  ){

            if(has_inputted_value) {


                value_to_display = value_to_display.substring(0, value_to_display.length() - 2) + ".0" + " €";
            }else{

                value_to_display = ".0" + " €";

            }

            displayed_value.setText(value_to_display);
            has_inputted_value = true;
            comma_used = true;
        }

    }

    public void OnButtonPressed_clear(View view)
    {
        value_to_display = "";

        displayed_value.setText(value_to_display);
        type_selected = false;
        comma_used = false;
        has_inputted_value = false;
    }





    @SuppressLint("SetTextI18n")
    public void OnNormalTicketButtonPressed(View view)
    {

        if(  (!type_selected) && (has_inputted_value)  ){

            value = Double.parseDouble(value_to_display.substring(0,value_to_display.length() - 2));

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }


    @SuppressLint("SetTextI18n")
    public void OnHalfTicketButtonPressed(View view)
    {

        if(  (!type_selected) && (has_inputted_value)  )  {

            value = Double.parseDouble(value_to_display.substring(0,value_to_display.length() - 2));

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }


    @SuppressLint("SetTextI18n")
    public void OnStudent50percentTicketButtonPressed(View view)
    {

        if(  (!type_selected) && (has_inputted_value)  ){

            value = Double.parseDouble(value_to_display.substring(0,value_to_display.length() - 2));

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }


    @SuppressLint("SetTextI18n")
    public void OnStudent25percentTicketButtonPressed(View view)
    {

        if(  (!type_selected) && (has_inputted_value)  ){

            value = Double.parseDouble(value_to_display.substring(0,value_to_display.length() - 2));

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }


    @SuppressLint("SetTextI18n")
    public void OnMilitary15percentTicketButtonPressed(View view)
    {

        if(  (!type_selected) && (has_inputted_value)  ){

            value = Double.parseDouble(value_to_display.substring(0,value_to_display.length() - 2));

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }


    @SuppressLint("SetTextI18n")
    public void OnPackageTicketButtonPressed(View view)
    {

        if(  (!type_selected) && (has_inputted_value)  ){

            value = Double.parseDouble(value_to_display.substring(0,value_to_display.length() - 2));

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }



    @SuppressLint("SetTextI18n")
    public void OnWithReturnTicketButtonPressed(View view)
    {

        if(  (!type_selected) && (has_inputted_value)  ){

            value = Double.parseDouble(value_to_display.substring(0,value_to_display.length() - 2));

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }


    @SuppressLint("SetTextI18n")
    public void OnWithReturn25percentTicketButtonPressed(View view)
    {

        if(  (!type_selected) && (has_inputted_value)  ){

            value = Double.parseDouble(value_to_display.substring(0,value_to_display.length() - 2));

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

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

        DrawerLayout drawer = findViewById(R.id.drawer_layout_routes);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

