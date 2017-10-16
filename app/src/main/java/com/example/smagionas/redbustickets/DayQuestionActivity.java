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
import android.view.Menu;
import android.view.MenuItem;

public class DayQuestionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String value1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_question);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();



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
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
