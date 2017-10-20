package com.example.smagionas.redbustickets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.smagionas.redbustickets.R.string.title_activity_routes_return;
import static com.example.smagionas.redbustickets.R.string.title_activity_routes;


public class RoutesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//////////////////// START OF GLOBAL VARIABLES/////////////////////////
    public GridView GridViewItems;
    RelativeLayout layout1;
    private Menu menu;
    MenuItem Direction;
    ActionBar ab;

    boolean bus_direction_forth;
//////////////////// END OF GLOBAL VARIABLES///////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        Bundle extras = getIntent().getExtras();
        bus_direction_forth = extras.getBoolean("Direction Forth");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ab = getSupportActionBar();

        final FloatingActionButton teams = findViewById(R.id.teams);
        teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout_routes);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        layout1 = findViewById(R.id.Routes_top_layout);
        layout1.removeView(GridViewItems);

        if(bus_direction_forth){
            layout1.setBackgroundResource(R.color.colorPrimary);                                    // On normal Route display normal title and paint background blue.
            ab.setTitle(title_activity_routes);
        }else{
            layout1.setBackgroundResource(R.color.colorSecondary);                                  // On return Route display return title and paint background orange.
            ab.setTitle(title_activity_routes_return);
        }


        int number_of_routes=17;                                                                    // number of routes to be displayed


        ObjectLog[] ObjectLogDataFinal = new ObjectLog[number_of_routes];                           // Hardcoded data for testing.
        ObjectLogDataFinal[0]= new ObjectLog(1,"Σέρρες - Θεσσαλονίκη ");
        ObjectLogDataFinal[1]= new ObjectLog(1,"Σέρρες - Κατερίνη - Λάρισα - Τρίκαλα - Καρδίτσα - Βόλος - Λαμία - Αθήνα - Άργος - Πάτρα");
        ObjectLogDataFinal[2]= new ObjectLog(1,"Σέρρες - Κιλκίς ");
        ObjectLogDataFinal[3]= new ObjectLog(1,"Σέρρες - Βέροια ");
        ObjectLogDataFinal[4]= new ObjectLog(1,"Σέρρες - Νάουσα ");
        ObjectLogDataFinal[5]= new ObjectLog(1,"Σέρρες - Αιγίνιο ");
        ObjectLogDataFinal[6]= new ObjectLog(1,"Σέρρες - Μεθώνη ");
        ObjectLogDataFinal[7]= new ObjectLog(1,"Σέρρες - Κοζάνη ");
        ObjectLogDataFinal[8]= new ObjectLog(1,"Σέρρες - Γιαννιτσά ");
        ObjectLogDataFinal[9]= new ObjectLog(1,"Σέρρες - Ιωάννινα ");
        ObjectLogDataFinal[10]= new ObjectLog(1,"Σέρρες - Καστοριά ");
        ObjectLogDataFinal[11]= new ObjectLog(1,"Σέρρες - Σέρβια ");
        ObjectLogDataFinal[12]= new ObjectLog(1,"Σέρρες - Ξάνθη ");
        ObjectLogDataFinal[13]= new ObjectLog(1,"Σέρρες - Κομοτηνή ");
        ObjectLogDataFinal[14]= new ObjectLog(1,"Σέρρες - Καβάλα ");
        ObjectLogDataFinal[15]= new ObjectLog(1,"Σέρρες - Ορεστιάδα ");
        ObjectLogDataFinal[16]= new ObjectLog(1,"Σέρρες - Αθήνα ");

        View view = findViewById(android.R.id.content);
        ArrayAdapterRoutes adapter = new ArrayAdapterRoutes(view.getContext(), R.layout.list_view_items, ObjectLogDataFinal);


        GridViewItems = new GridView(view.getContext());
        GridViewItems.setNumColumns(1);
        GridViewItems.setAdapter(adapter);
        GridViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Context context = view.getContext();

                TextView textViewRouteName = view.findViewById(R.id.textViewRoute);
                String listRouteName = textViewRouteName.getText().toString();

                Intent new_intent = new Intent(context, TicketIssuanceActivity.class);              // Go to "TicketIssuanceActivity" passing the route name and whether
                Bundle extras = new Bundle();                                                       // the route is normal route or return route
                extras.putString("Route name", listRouteName);
                extras.putBoolean("Direction Forth",bus_direction_forth );
                new_intent.putExtras(extras);
                context.startActivity(new_intent);
            }
        });
        layout1.addView(GridViewItems);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.direction:                                                                    // When user presses this menu item go from normal route to return route
                                                                                                    // and vice-versa.
                if(bus_direction_forth){
                    layout1.setBackgroundResource(R.color.colorSecondary);
                    ab.setTitle(title_activity_routes_return);
                    item.setIcon(R.mipmap.ic_rewind);
                    bus_direction_forth = false;
                }else{
                    layout1.setBackgroundResource(R.color.colorPrimary);
                    ab.setTitle(title_activity_routes);
                    item.setIcon(R.mipmap.ic_fast_forward);
                    bus_direction_forth = true;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer_routes = findViewById(R.id.drawer_layout_routes);
        if (drawer_routes.isDrawerOpen(GravityCompat.START)) {
            drawer_routes.closeDrawer(GravityCompat.START);
        } else {
            Intent new_intent = new Intent(this, SignInActivity.class);                             // When user presses the device back button, always return to
            this.startActivity(new_intent);                                                         // the "SignInActivity"
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        Direction = menu.findItem(R.id.direction);                                                  // Display the correct menu item depending on whether
                                                                                                    // the route is normal or return route.
        if(bus_direction_forth){
            Direction.setIcon(R.mipmap.ic_fast_forward);
        }else{
            Direction.setIcon(R.mipmap.ic_rewind);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.my_menu_routes, menu);
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

        DrawerLayout drawer = findViewById(R.id.drawer_layout_routes);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}