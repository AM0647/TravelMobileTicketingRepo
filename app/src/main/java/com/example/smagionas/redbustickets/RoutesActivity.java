package com.example.smagionas.redbustickets;

import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class RoutesActivity extends AppCompatActivity {

    public GridView GridViewItems;
    Button normalRouteButton;
    Button returnRouteButton;
    Button teamButton;
    RelativeLayout layout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();

        layout1 = findViewById(R.id.Routes_top_layout);


        layout1.removeView(GridViewItems);


        RoutesBottomFragment bottomRoutesFragment = new RoutesBottomFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.Routes_bottom_layout, bottomRoutesFragment)
                .commit();


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








        normalRouteButton = findViewById(R.id.NormalRouteButton);
        returnRouteButton = findViewById(R.id.ReturnRouteButton);
        teamButton = findViewById(R.id.TeamsButton);






    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_bar_items, menu);
        return true;
    }

    public void onNormalRouteButtonPressed(View view){

        normalRouteButton.setBackgroundResource(R.color.colorAlmostPrimarySelected);
        teamButton.setBackgroundResource(R.color.colorAlmostPrimary);
        returnRouteButton.setBackgroundResource(R.color.colorAlmostPrimary);
        layout1.setBackgroundResource(R.color.colorPrimary);


    }

    public void onTeamsButtonPressed(View view){

        normalRouteButton.setBackgroundResource(R.color.colorAlmostPrimary);
        teamButton.setBackgroundResource(R.color.colorAlmostPrimarySelected);
        returnRouteButton.setBackgroundResource(R.color.colorAlmostPrimary);
        layout1.setBackgroundResource(R.color.colorPrimary);


    }

    public void onReturnRouteButtonPressed(View view){

        normalRouteButton.setBackgroundResource(R.color.colorAlmostSecondary);
        teamButton.setBackgroundResource(R.color.colorAlmostSecondary);
        returnRouteButton.setBackgroundResource(R.color.colorAlmostSecondarySelected);
        layout1.setBackgroundResource(R.color.colorSecondary);


        //teamButton.setBackground(ResourcesCompat.getColor(getResources(), R.color.colorAlmostSecondary, null);
        //returnRouteButton.setBackground(ResourcesCompat.getColor(getResources(), R.color.colorAlmostSecondary, null);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
