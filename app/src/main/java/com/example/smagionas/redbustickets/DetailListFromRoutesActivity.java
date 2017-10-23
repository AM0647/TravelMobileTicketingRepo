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
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import static java.lang.String.valueOf;

public class DetailListFromRoutesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public GridView GridViewItems;
    RelativeLayout layout1;

    boolean bus_direction_forth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list_from_routes);

        Bundle extras = getIntent().getExtras();
        bus_direction_forth = extras.getBoolean("Direction Forth");

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
    public void onResume() {
        super.onResume();

        layout1 = findViewById(R.id.logs_layout);
        //layout1.removeView(GridViewItems);





        //java.util.Date date= new Date();                                                            // Get today's date
        //Calendar cal = Calendar.getInstance();
        //cal.setTime(date);
        //int day = cal.get(Calendar.DAY_OF_MONTH);
        //int month = cal.get(Calendar.MONTH);
        //int year = cal.get(Calendar.YEAR);

        Long current_time = System.currentTimeMillis();
        //current_time = current_time - 300000000;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(current_time);
        int day1 = cal.get(Calendar.DAY_OF_MONTH);
        int month1 = cal.get(Calendar.MONTH) + 1;
        int year1 = cal.get(Calendar.YEAR);
        int hour1 = cal.get(Calendar.HOUR_OF_DAY);
        int minute1 = cal.get(Calendar.MINUTE);
        int second1 = cal.get(Calendar.SECOND);

        String day1_string = valueOf(day1);
        String month1_string = valueOf(month1);
        String year1_string = valueOf(year1);
        String hour1_string = valueOf(hour1);
        String minute1_string = valueOf(minute1);
        String second1_string = valueOf(second1);



        String current_time_string = day1_string + " / " + month1_string + " / " + year1_string + "  ||  "  +  hour1_string + ":" + minute1_string + ":" + second1_string + " .";

        Toast.makeText(getApplicationContext(),current_time_string , Toast.LENGTH_LONG).show();

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
}
