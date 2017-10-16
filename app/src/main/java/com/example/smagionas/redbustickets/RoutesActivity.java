package com.example.smagionas.redbustickets;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.util.TypedValue;
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
import android.widget.TextView;

import static com.example.smagionas.redbustickets.R.string.title_activity_routes;
import static com.example.smagionas.redbustickets.R.string.title_activity_routes_return;

public class RoutesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public GridView GridViewItems;
    RelativeLayout layout1;
    boolean bus_direction_forth = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);


        layout1 = findViewById(R.id.Routes_top_layout);


        layout1.removeView(GridViewItems);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();



        final FloatingActionButton teams = (FloatingActionButton) findViewById(R.id.teams);
        teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        final FloatingActionButton direction = (FloatingActionButton) findViewById(R.id.direction);
        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(bus_direction_forth == true){

                    layout1.setBackgroundResource(R.color.colorSecondary);

                    //String title_to_be = "";
                    //title_to_be.equals(title_activity_routes_return);

                    //TextView otinanai;
                    //otinanai.setText(title_activity_routes_return);


                    ab.setTitle(title_activity_routes_return);
                    direction.setImageResource(R.mipmap.ic_arrow_left_thick);
                    bus_direction_forth = false;
                }else{

                    layout1.setBackgroundResource(R.color.colorPrimary);
                    ab.setTitle(title_activity_routes);
                    direction.setImageResource(R.mipmap.ic_arrow_right_thick);
                    bus_direction_forth = true;

                }


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_routes);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }



    @Override
    public void onResume()
    {
        super.onResume();



        int number_of_routes=17;                                                                     // number of routes to be displayed


        layout1.removeView(GridViewItems);
        //LinearLayout   layout2 = (LinearLayout)   findViewById(R.id.Routes_bottom_layout);


        ObjectLog[] ObjectLogDataFinal = new ObjectLog[number_of_routes];
        ObjectLogDataFinal[0]= new ObjectLog(1,"Σέρρες - Θεσσαλονίκη");
        ObjectLogDataFinal[1]= new ObjectLog(1,"Σέρρες - Κατερίνη - Λάρισα - Τρίκαλα - Καρδίτσα - Βόλος - Λαμία - Αθήνα - Άργος - Πάτρα");
        ObjectLogDataFinal[2]= new ObjectLog(1,"Σέρρες - Κιλκίς");
        ObjectLogDataFinal[3]= new ObjectLog(1,"Σέρρες - Βέροια");
        ObjectLogDataFinal[4]= new ObjectLog(1,"Σέρρες - Νάουσα");
        ObjectLogDataFinal[5]= new ObjectLog(1,"Σέρρες - Αιγίνιο");
        ObjectLogDataFinal[6]= new ObjectLog(1,"Σέρρες - Μεθώνη");
        ObjectLogDataFinal[7]= new ObjectLog(1,"Σέρρες - Κοζάνη");
        ObjectLogDataFinal[8]= new ObjectLog(1,"Σέρρες - Γιαννιτσά");
        ObjectLogDataFinal[9]= new ObjectLog(1,"Σέρρες - Ιωάννινα");
        ObjectLogDataFinal[10]= new ObjectLog(1,"Σέρρες - Καστοριά");
        ObjectLogDataFinal[11]= new ObjectLog(1,"Σέρρες - Σέρβια");
        ObjectLogDataFinal[12]= new ObjectLog(1,"Σέρρες - Ξάνθη");
        ObjectLogDataFinal[13]= new ObjectLog(1,"Σέρρες - Κομοτηνή");
        ObjectLogDataFinal[14]= new ObjectLog(1,"Σέρρες - Καβάλα");
        ObjectLogDataFinal[15]= new ObjectLog(1,"Σέρρες - Ορεστιάδα");
        ObjectLogDataFinal[16]= new ObjectLog(1,"Σέρρες - Δομοκός");

        View view = findViewById(android.R.id.content);
        ArrayAdapterRoutes adapter = new ArrayAdapterRoutes(view.getContext(), R.layout.list_view_row, ObjectLogDataFinal);


        GridViewItems = new GridView(view.getContext());
        GridViewItems.setNumColumns(1);
        GridViewItems.setAdapter(adapter);
        GridViewItems.setOnItemClickListener(new OnRouteClickListener());
        layout1.addView(GridViewItems);




    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer_routes = (DrawerLayout) findViewById(R.id.drawer_layout_routes);
        if (drawer_routes.isDrawerOpen(GravityCompat.START)) {
            drawer_routes.closeDrawer(GravityCompat.START);
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
