package com.example.smagionas.redbustickets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Button;

public class TicketIssuanceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String value1;
    Button routeNameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_issuance);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        //drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        Bundle extras = getIntent().getExtras();
        value1 = extras.getString("Route name");
        routeNameButton = findViewById(R.id.Route_name);
        routeNameButton.setText(value1);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
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
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onRouteNameButtonPressed(View view){

        Context context = view.getContext();

        Intent new_intent = new Intent(context, RoutesActivity.class);

        context.startActivity(new_intent);



    }
}
