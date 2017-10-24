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
import android.widget.GridView;
import android.widget.RelativeLayout;

public class NonUpdatedTicketsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//////////////////// START OF GLOBAL VARIABLES/////////////////////////

    public GridView GridViewItems;
    RelativeLayout layout1;

    boolean bus_direction_forth;
    String value1;
//////////////////// END OF GLOBAL VARIABLES///////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_updated_tickets);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




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
    }

    @Override
    public void onResume() {
        super.onResume();

        layout1 = findViewById(R.id.logs_layout_non_updated_tickets);

        Long current_time = System.currentTimeMillis();

        int number_of_logs=10;                                                                    // number of routes to be displayed

        ObjectLog3[] ObjectLogDataFinal = new ObjectLog3[number_of_logs];                                                                                     // Hardcoded data for testing.
        ObjectLogDataFinal[0]= new ObjectLog3(current_time,"Σέρρες - Θεσσαλονίκη","Σέρρες", "Θεσσαλονίκη", "Κανονικό" , 15.5, "MLS iQ9719/10021", 5001, 347);
        ObjectLogDataFinal[1]= new ObjectLog3(current_time - 15000000,"Σέρρες - Αθήνα ","Θεσσαλονίκη", "Κατερίνη", "Κανονικό" , 10, "MLS iQ9719/10021", 5000, 346);
        ObjectLogDataFinal[2]= new ObjectLog3(current_time - 100000000,"Σέρρες - Αθήνα ","Θεσσαλονίκη", "Λάρισα", "Κανονικό" , 17, "MLS iQ9719/10021", 4999, 345);
        ObjectLogDataFinal[3]= new ObjectLog3(current_time - 200000000,"Σέρρες - Αθήνα ","Σέρρες", "Αθήνα", "Κανονικό" , 60, "MLS iQ9719/10021", 4998, 344);
        ObjectLogDataFinal[4]= new ObjectLog3(current_time - 300000000,"Σέρρες - Αθήνα ","Σέρρες", "Αθήνα", "Φοιτητικό 50%" , 30, "MLS iQ9719/10021", 4997, 343);
        ObjectLogDataFinal[5]= new ObjectLog3(current_time - 400000000,"Σέρρες - Αθήνα ","Σέρρες", "Αθήνα", "Φοιτητικό 25%" , 45, "MLS iQ9719/10021", 4996, 342);
        ObjectLogDataFinal[6]= new ObjectLog3(current_time - 500000000,"Σέρρες - Κιλκίς ","Σέρρες", "Κιλκίς", "Κανονικό" , 15, "MLS iQ9719/10021", 4995, 341);
        ObjectLogDataFinal[7]= new ObjectLog3(current_time - 600000000,"Σέρρες - Αθήνα ","Πλαταμώνα", "Δ. Βόλου", "Κανονικό" , 16, "MLS iQ9719/10021", 4994, 340);
        ObjectLogDataFinal[8]= new ObjectLog3(current_time - 700000000,"Σέρρες - Αθήνα ","Λάρισα", "Θήβα", "Φοιτητικό 50%" , 12.5, "MLS iQ9719/10021", 4993, 339);
        ObjectLogDataFinal[9]= new ObjectLog3(current_time - 800000000,"Σέρρες - Αθήνα ","Σέρρες", "Αυλώνα-Σχηματάρι Μ", "Στρατιωτικό 15%" , 46.6, "MLS iQ9719/10021", 4992, 338);


        View view = findViewById(android.R.id.content);
        ArrayAdapterLogs adapter = new ArrayAdapterLogs(view.getContext(), R.layout.list_view_logs, ObjectLogDataFinal);

        GridViewItems = new GridView(view.getContext());
        GridViewItems.setNumColumns(1);
        GridViewItems.setAdapter(adapter);
        layout1.addView(GridViewItems);

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
}
