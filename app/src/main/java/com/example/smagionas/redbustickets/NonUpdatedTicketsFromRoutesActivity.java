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

public class NonUpdatedTicketsFromRoutesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_updated_tickets_from_routes);


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
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_routes);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_first_item_routes) {
            Intent intent = new Intent(this,RoutesActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_second_item_routes) {
            Intent intent = new Intent(this,DefinedTicketsFromRoutesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_third_item_routes) {
            Intent intent = new Intent(this,NonUpdatedTicketsFromRoutesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_fourth_item_routes) {
            Intent intent = new Intent(this,BalanceQuestionFromRoutesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_fifth_item_routes) {
            Intent intent = new Intent(this,DayQuestionFromRoutesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_sixth_item_routes) {
            Intent intent = new Intent(this,DetailListFromRoutesActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_seventh_item_routes) {
            Intent intent = new Intent(this,InformationFromRoutesActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_eighth_item_routes) {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_routes);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
