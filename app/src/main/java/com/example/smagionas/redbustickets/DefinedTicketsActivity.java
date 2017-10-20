package com.example.smagionas.redbustickets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
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

public class DefinedTicketsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String value1;
    public TextView displayed_value;
    public String value_to_display="";
    public Double value= 0.0;

    boolean type_selected = false;
    boolean comma_used = false;
    boolean has_inputed_value = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defined_tickets);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        displayed_value = findViewById(R.id.defined_tickets_numpad_screen);
        displayed_value.setTextSize(TypedValue.COMPLEX_UNIT_PX,45);



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle extras = getIntent().getExtras();
        value1 = extras.getString("Route name");
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



    public void OnButtonPressed_0(View view)
    {

        if(type_selected == false){
            value_to_display = value_to_display+"0";

            displayed_value.setText(value_to_display);
            has_inputed_value = true;
        }


    }

    public void OnButtonPressed_1(View view)
    {
        if(type_selected == false){
            value_to_display = value_to_display+"1";

            displayed_value.setText(value_to_display);
            has_inputed_value = true;
        }



    }

    public void OnButtonPressed_2(View view)
    {

        if(type_selected == false){
            value_to_display = value_to_display+"2";

            displayed_value.setText(value_to_display);
            has_inputed_value = true;
        }

    }

    public void OnButtonPressed_3(View view)
    {

        if(type_selected == false){
            value_to_display = value_to_display+"3";

            displayed_value.setText(value_to_display);
            has_inputed_value = true;
        }

    }

    public void OnButtonPressed_4(View view)
    {

        if(type_selected == false){
            value_to_display = value_to_display+"4";

            displayed_value.setText(value_to_display);
            has_inputed_value = true;
        }

    }

    public void OnButtonPressed_5(View view)
    {

        if(type_selected == false){
            value_to_display = value_to_display+"5";

            displayed_value.setText(value_to_display);
            has_inputed_value = true;
        }

    }

    public void OnButtonPressed_6(View view)
    {

        if(type_selected == false){
            value_to_display = value_to_display+"6";

            displayed_value.setText(value_to_display);
            has_inputed_value = true;
        }

    }

    public void OnButtonPressed_7(View view)
    {

        if(type_selected == false){
            value_to_display = value_to_display+"7";

            displayed_value.setText(value_to_display);
            has_inputed_value = true;
        }

    }

    public void OnButtonPressed_8(View view)
    {

        if(type_selected == false){
            value_to_display = value_to_display+"8";

            displayed_value.setText(value_to_display);
            has_inputed_value = true;
        }

    }

    public void OnButtonPressed_9(View view)
    {

        if(type_selected == false){
            value_to_display = value_to_display+"9";

            displayed_value.setText(value_to_display);
            has_inputed_value = true;
        }

    }

    public void OnButtonPressed_comma(View view)
    {

        if(  (type_selected == false) && (comma_used == false)  ){
            value_to_display = value_to_display+".";

            displayed_value.setText(value_to_display);
            comma_used = true;
        }

    }

    public void OnButtonPressed_clear(View view)
    {
        value_to_display = "";

        displayed_value.setText(value_to_display);
        type_selected = false;
        comma_used = false;
        has_inputed_value = false;
    }





    public void OnNormalTicketButtonPressed(View view)
    {

        if(  (type_selected == false) && (has_inputed_value==true)  ){

            value = Double.parseDouble(value_to_display);

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }


    public void OnHalfTicketButtonPressed(View view)
    {

        if(  (type_selected == false) && (has_inputed_value==true)  )  {

            value = Double.parseDouble(value_to_display);

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }


    public void OnStudent50percentTicketButtonPressed(View view)
    {

        if(  (type_selected == false) && (has_inputed_value==true)  ){

            value = Double.parseDouble(value_to_display);

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }


    public void OnStudent25percentTicketButtonPressed(View view)
    {

        if(  (type_selected == false) && (has_inputed_value==true)  ){

            value = Double.parseDouble(value_to_display);

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }


    public void OnMilitary15percentTicketButtonPressed(View view)
    {

        if(  (type_selected == false) && (has_inputed_value==true)  ){

            value = Double.parseDouble(value_to_display);

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }


    public void OnPackageTicketButtonPressed(View view)
    {

        if(  (type_selected == false) && (has_inputed_value==true)  ){

            value = Double.parseDouble(value_to_display);

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }



    public void OnWithReturnTicketButtonPressed(View view)
    {

        if(  (type_selected == false) && (has_inputed_value==true)  ){

            value = Double.parseDouble(value_to_display);

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }


    public void OnWithReturn25percentTicketButtonPressed(View view)
    {

        if(  (type_selected == false) && (has_inputed_value==true)  ){

            value = Double.parseDouble(value_to_display);

            value_to_display = String.valueOf(value);

            displayed_value.setText(value_to_display + " €");

            type_selected = true;

        }


    }









    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_first_item) {
            Intent intent = new Intent(this,TicketIssuanceActivity.class);
            intent.putExtra("Route name", value1);
            startActivity(intent);
        } else if (id == R.id.nav_second_item) {
            Intent intent = new Intent(this,DefinedTicketsActivity.class);
            intent.putExtra("Route name", value1);
            startActivity(intent);
        } else if (id == R.id.nav_third_item) {
            Intent intent = new Intent(this,NonUpdatedTicketsActivity.class);
            intent.putExtra("Route name", value1);
            startActivity(intent);
        } else if (id == R.id.nav_fourth_item) {
            Intent intent = new Intent(this,BalanceQuestionActivity.class);
            intent.putExtra("Route name", value1);
            startActivity(intent);
        } else if (id == R.id.nav_fifth_item) {
            Intent intent = new Intent(this,DayQuestionActivity.class);
            intent.putExtra("Route name", value1);
            startActivity(intent);
        } else if (id == R.id.nav_sixth_item) {
            Intent intent = new Intent(this,DetailListActivity.class);
            intent.putExtra("Route name", value1);
            startActivity(intent);
        }else if (id == R.id.nav_seventh_item) {
            Intent intent = new Intent(this,InformationActivity.class);
            intent.putExtra("Route name", value1);
            startActivity(intent);
        }else if (id == R.id.nav_eighth_item) {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
