package com.example.smagionas.redbustickets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import static java.lang.String.valueOf;


public class TicketIssuanceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//////////////////// START OF GLOBAL VARIABLES/////////////////////////
    public ExpandableHeightGridView GridViewItems;
    RelativeLayout layout1;
    ScrollView scrollview;
    ArrayAdapterDestinations adapter;
    RelativeLayout.LayoutParams p;
    private Menu menu;
    MenuItem Route_Direction;
    Toolbar toolbar;
    ObjectLog2[] ObjectLogDataFinalForth;
    ObjectLog2[] ObjectLogDataFinalBackward;
    ObjectLog2[] ObjectLogDataFinalTemp;
    ObjectLog2[] ObjectLogDataFinal;

    TextView Passenger_Destination;
    TextView Destination_Value;
    TextView currentLocation;

    Button name_button;
    Button normalButton;
    Button halfButton;
    Button student50percentButton;
    Button student25percentButton;
    Button military15percentButton;
    Button packageButton;
    Button withReturnButton;
    Button withReturn25percentButton;

    ImageButton printButton;
    ImageButton prevLocationButton;
    ImageButton nextLocationButton;

    boolean bus_direction_forth ;
    String value1;
//////////////////// END OF GLOBAL VARIABLES///////////////////////////


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_issuance);

        View view = findViewById(android.R.id.content);

        Bundle extras = getIntent().getExtras();
        value1 = extras.getString("Route name");
        bus_direction_forth = extras.getBoolean("Direction Forth");


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (ab != null) {
            ab.setTitle(value1);                                                                        // Setting Route title
        }
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();

                Intent intent = new Intent(context,RoutesActivity.class);                           // When user presses the title he goes to "RoutesActivity"
                intent.putExtra("Direction Forth",bus_direction_forth);
                startActivity(intent);
            }
        });

        name_button = findViewById(R.id.Route_full_name_button);                                    // Button to display full Route name
        name_button.setText(value1);

        Passenger_Destination = findViewById(R.id.Passenger_Destination);                           // Bottom=left indicator for passenger's destination
        Destination_Value = findViewById(R.id.TextViewDestinationValue);

        printButton = findViewById(R.id.Print_button);
        printButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {                                                 // Print tickets with multiple mode

                Toast.makeText(v.getContext(), "Πολλαπλή εκτύπωση", Toast.LENGTH_LONG).show();

                return true;
            }
        });

        normalButton = findViewById(R.id.normal_ticket_button);
        halfButton = findViewById(R.id.half_ticket_button);
        student50percentButton = findViewById(R.id.student_50percent_ticket_button);
        student25percentButton = findViewById(R.id.student_25percent_ticket_button);
        military15percentButton = findViewById(R.id.military_15percent_ticket_button);
        packageButton = findViewById(R.id.package_ticket_button);
        withReturnButton = findViewById(R.id.with_return_ticket_button);
        withReturn25percentButton = findViewById(R.id.with_return_25percent_ticket_button);

        currentLocation = findViewById(R.id.locationButton);
        prevLocationButton = findViewById(R.id.prevLocationButton);
        nextLocationButton = findViewById(R.id.nextLocationButton);

        Route_Direction = findViewById(R.id.Route_Direction);                                       // When user presses this menu item go from normal route to return route
                                                                                                    // and vice-versa.

        if(bus_direction_forth){
            currentLocation.setText("Σέρραι (from antenna)");

        }else{
            currentLocation.setText("Αθήνα (from antenna)");
        }

        layout1 = findViewById(R.id.ticket_issuance_grid_items);
        layout1.removeView(GridViewItems);


        int number_of_destinations=20;                                                              // number of destinations to be displayed

        ObjectLogDataFinal = new ObjectLog2[number_of_destinations];                                // Hardcoded data for testing.
        ObjectLogDataFinal[0]= new ObjectLog2(0,"Σέρραι ",0);
        ObjectLogDataFinal[1]= new ObjectLog2(1,"Κατερίνη ",10);
        ObjectLogDataFinal[2]= new ObjectLog2(2,"Πλαταμώνα ",14);
        ObjectLogDataFinal[3]= new ObjectLog2(3,"Ραψάνη ",16.5);
        ObjectLogDataFinal[4]= new ObjectLog2(4,"Λάρισα ",20);
        ObjectLogDataFinal[5]= new ObjectLog2(5,"Μέγα Μοναστήρι ",22.5);
        ObjectLogDataFinal[6]= new ObjectLog2(6,"Δ. Βόλου ",28);
        ObjectLogDataFinal[7]= new ObjectLog2(7,"Δ. Αλμυρού ",29.5);
        ObjectLogDataFinal[8]= new ObjectLog2(8,"Πελασγία ",33);
        ObjectLogDataFinal[9]= new ObjectLog2(9,"Λαμία ",35);
        ObjectLogDataFinal[10]= new ObjectLog2(10,"Θερμοπύλες ",40);
        ObjectLogDataFinal[11]= new ObjectLog2(11,"Καμένα Βούρλα ",42.7);
        ObjectLogDataFinal[12]= new ObjectLog2(12,"Αρκίτσα ",45);
        ObjectLogDataFinal[13]= new ObjectLog2(13,"Αταλάντη ",46);
        ObjectLogDataFinal[14]= new ObjectLog2(14,"Τραγάνα ",48.2);
        ObjectLogDataFinal[15]= new ObjectLog2(15,"Μαρτίνον ",49.5);
        ObjectLogDataFinal[16]= new ObjectLog2(16,"Κάστρο ",50);
        ObjectLogDataFinal[17]= new ObjectLog2(17,"Θήβα ",53);
        ObjectLogDataFinal[18]= new ObjectLog2(18,"Αυλώνα-Σχηματάρι Μ ",54.8);
        ObjectLogDataFinal[19]= new ObjectLog2(19,"Αθήνα ",60);

        ObjectLogDataFinalForth = Arrays.copyOfRange(ObjectLogDataFinal,1,ObjectLogDataFinal.length);

        ObjectLogDataFinalTemp = Arrays.copyOfRange(ObjectLogDataFinal,0,ObjectLogDataFinal.length - 1);
        Collections.reverse(Arrays.asList(ObjectLogDataFinalTemp));
        ObjectLogDataFinalBackward = Arrays.copyOfRange(ObjectLogDataFinalTemp,0,ObjectLogDataFinalTemp.length);

        DisplayDestinations(view);                                                          // Display the destinations
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void OnPrint_button_pressed(View view){

        Toast.makeText(view.getContext(), "Μονή εκτύπωση", Toast.LENGTH_LONG).show();

    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.Route_full_name:                                                              // When user presses this menu item, a Button displaying
                                                                                                    // the full Route name appears. Pressing it again dismisses the button
                if(name_button.getVisibility() == View.GONE){
                    name_button.setVisibility(View.VISIBLE);
                }else if(name_button.getVisibility() == View.VISIBLE){
                    name_button.setVisibility(View.GONE);
            }

                return true;
            case R.id.Route_Direction:                                                              // When user presses this menu item, he goes from normal route to return route
                View view = findViewById(android.R.id.content);                                     // and vice-versa.

                int myInt = (bus_direction_forth) ? 1 : 0;

                switch (myInt) {
                case 1:
                    item.setIcon(R.mipmap.ic_rewind);
                    bus_direction_forth = false;
                    currentLocation.setText("Αθήνα (from antenna)");
                    nextLocationButton.setImageResource(R.mipmap.ic_arrow_right_gray);
                    if (ObjectLogDataFinalBackward.length != 0)nextLocationButton.setImageResource(R.mipmap.ic_arrow_right_black);
                    prevLocationButton.setImageResource(R.mipmap.ic_arrow_left_gray);
                    if (ObjectLogDataFinalBackward.length != (ObjectLogDataFinalTemp.length))prevLocationButton.setImageResource(R.mipmap.ic_arrow_left_black);
                    break;

                case 0:
                    item.setIcon(R.mipmap.ic_fast_forward);
                    bus_direction_forth = true;
                    currentLocation.setText("Σέρραι from antenna)");
                    nextLocationButton.setImageResource(R.mipmap.ic_arrow_right_gray);
                    if(ObjectLogDataFinalForth.length != 0) nextLocationButton.setImageResource(R.mipmap.ic_arrow_right_black);
                    prevLocationButton.setImageResource(R.mipmap.ic_arrow_left_gray);
                    if(ObjectLogDataFinalForth.length != (ObjectLogDataFinal.length - 1)) prevLocationButton.setImageResource(R.mipmap.ic_arrow_left_black);
                    break;

                default:
                    break;
                }
                DisplayDestinations(view);                                                          //Display the destinations

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Route_Direction = menu.findItem(R.id.Route_Direction);
        if(bus_direction_forth){
            Route_Direction.setIcon(R.mipmap.ic_fast_forward);
        }else{
            Route_Direction.setIcon(R.mipmap.ic_rewind);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.my_menu_ticket_issuance, menu);
        return true;
    }

    public void OnPrevLocationButtonPressed(View view){                                             // User presses the left arrow to set current location equal to the previous location

        nextLocationButton.setImageResource(R.mipmap.ic_arrow_right_black);
        if(bus_direction_forth){

            if( (ObjectLogDataFinalForth.length < (ObjectLogDataFinal.length - 1))) {

                prevLocationButton.setImageResource(R.mipmap.ic_arrow_left_black);
                ObjectLogDataFinalForth = Arrays.copyOfRange(ObjectLogDataFinal,((ObjectLogDataFinal.length - ObjectLogDataFinalForth.length - 1)),ObjectLogDataFinal.length);
                if(ObjectLogDataFinalForth.length == (ObjectLogDataFinal.length - 1)) prevLocationButton.setImageResource(R.mipmap.ic_arrow_left_gray);
            }
        }else {

            if ((ObjectLogDataFinalBackward.length < (ObjectLogDataFinalTemp.length))) {
                prevLocationButton.setImageResource(R.mipmap.ic_arrow_left_black);
                ObjectLogDataFinalBackward = Arrays.copyOfRange(ObjectLogDataFinalTemp, ((ObjectLogDataFinalTemp.length - ObjectLogDataFinalBackward.length - 1)), ObjectLogDataFinalTemp.length);
                if (ObjectLogDataFinalBackward.length == (ObjectLogDataFinalTemp.length))prevLocationButton.setImageResource(R.mipmap.ic_arrow_left_gray);
            }
        }
        DisplayDestinations(view);                                                                  //Display the destinations
    }

    public void OnNextLocationButtonPressed(View view){                                             // User presses the right arrow to set current location equal to the next location

        if(bus_direction_forth){

            if( ObjectLogDataFinalForth.length> 0){
                prevLocationButton.setImageResource(R.mipmap.ic_arrow_left_black);
                ObjectLogDataFinalForth = Arrays.copyOfRange(ObjectLogDataFinalForth,1,ObjectLogDataFinalForth.length);
                if(ObjectLogDataFinalForth.length == 0) nextLocationButton.setImageResource(R.mipmap.ic_arrow_right_gray);
            }
        }else {

            if (ObjectLogDataFinalBackward.length > 0) {
                prevLocationButton.setImageResource(R.mipmap.ic_arrow_left_black);
                ObjectLogDataFinalBackward = Arrays.copyOfRange(ObjectLogDataFinalBackward, 1, ObjectLogDataFinalBackward.length);
                if (ObjectLogDataFinalBackward.length == 0)nextLocationButton.setImageResource(R.mipmap.ic_arrow_right_gray);
            }
        }
        DisplayDestinations(view);                                                                  //Display the destinations
    }


    public void DisplayDestinations(View view){

        if(bus_direction_forth){
            adapter = new ArrayAdapterDestinations(view.getContext(), R.layout.grid_view_items, ObjectLogDataFinalForth);
        }else{
            adapter = new ArrayAdapterDestinations(view.getContext(), R.layout.grid_view_items, ObjectLogDataFinalBackward);
        }
        layout1.removeView(GridViewItems);
        p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(15,10,25,0);
        GridViewItems = new ExpandableHeightGridView(view.getContext());
        GridViewItems.setLayoutParams(p);
        GridViewItems.setNumColumns(1);
        GridViewItems.setAdapter(adapter);
        GridViewItems.setExpanded(true);
        GridViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView buttonDestinationName = view.findViewById(R.id.buttonDestination);
                String listDestinationName = buttonDestinationName.getText().toString();

                //long unique_id = id;
                //String unique_id_string = valueOf(unique_id);
                String value_string;

                double normalValue;
                double halfValue;
                double student50Value ;
                double student25Value ;
                double military15Value ;
                double packageValue;
                double withreturnValue;
                double withreturn25Value;

                String normal_value_string ;
                String half_value_string ;
                String student_50_value_string ;
                String student_25_value_string ;
                String military_15_value_string ;
                String package_value_string ;
                String with_return_value_string ;
                String with_return_25_value_string ;

                NumberFormat formatter = new DecimalFormat("#0.0");

                normalButton.setText("Κανονικο");
                halfButton.setText("Μισο" );
                student50percentButton.setText("Φοιτητικο \n  50%" );
                student25percentButton.setText("Φοιτητικο \n  25%" );
                military15percentButton.setText("Στρατιωτικο \n  15%");
                packageButton.setText("Δεμα");
                withReturnButton.setText("Με επιστροφη");
                withReturn25percentButton.setText("Με επιστροφη \n  25%" );

                if(bus_direction_forth){                                                            // TODO     Add real logic behind pricing.  Current one is dummy and causes
                                                                                                    // TODO     array out of bounds exception which is to be expected!!!
                    double value = ObjectLogDataFinalForth[(int) id].value;
                    value_string = valueOf(value) + " €";
                    Passenger_Destination.setText(ObjectLogDataFinal[0].Route + " - " + listDestinationName);
                    Destination_Value.setText(value_string);
                    normalValue = value;
                    halfValue = value * .5;
                    student50Value = value * .5;
                    student25Value = value * .75;
                    military15Value = value * .85;
                    packageValue = value;
                    withreturnValue = value;
                    withreturn25Value = value * .75;

                    normal_value_string = valueOf(formatter.format(normalValue)) + " €";
                    half_value_string = valueOf(formatter.format(halfValue)) + " €";
                    student_50_value_string = valueOf(formatter.format(student50Value)) + " €";
                    student_25_value_string = valueOf(formatter.format(student25Value)) + " €";
                    military_15_value_string = valueOf(formatter.format(military15Value)) + " €";
                    package_value_string = valueOf(formatter.format(packageValue)) + " €";
                    with_return_value_string = valueOf(formatter.format(withreturnValue)) + " €";
                    with_return_25_value_string = valueOf(formatter.format(withreturn25Value)) + " €";

                    normalButton.setText(normalButton.getText() + "\n"  +  normal_value_string );
                    halfButton.setText(halfButton.getText() + "\n"  +  half_value_string );
                    student50percentButton.setText(student50percentButton.getText() + "\n"  +  student_50_value_string );
                    student25percentButton.setText(student25percentButton.getText() + "\n"  +  student_25_value_string );
                    military15percentButton.setText(military15percentButton.getText() + "\n"  +  military_15_value_string );
                    packageButton.setText(packageButton.getText() + "\n"  +  package_value_string );
                    withReturnButton.setText(withReturnButton.getText() + "\n"  +  with_return_value_string );
                    withReturn25percentButton.setText(withReturn25percentButton.getText() + "\n"  +  with_return_25_value_string );

                }else{

                    double value = ObjectLogDataFinalForth[(int) id].value;
                    value_string = valueOf(value) + " €";
                    Passenger_Destination.setText(ObjectLogDataFinal[ObjectLogDataFinal.length - 1].Route + " - " + listDestinationName);
                    Destination_Value.setText(value_string);
                    normalValue = value;
                    halfValue = value * .5;
                    student50Value = value * .5;
                    student25Value = value * .75;
                    military15Value = value * .85;
                    packageValue = value;
                    withreturnValue = value;
                    withreturn25Value = value * .75;

                    normal_value_string = valueOf(formatter.format(normalValue)) + " €";
                    half_value_string = valueOf(formatter.format(halfValue)) + " €";
                    student_50_value_string = valueOf(formatter.format(student50Value)) + " €";
                    student_25_value_string = valueOf(formatter.format(student25Value)) + " €";
                    military_15_value_string = valueOf(formatter.format(military15Value)) + " €";
                    package_value_string = valueOf(formatter.format(packageValue)) + " €";
                    with_return_value_string = valueOf(formatter.format(withreturnValue)) + " €";
                    with_return_25_value_string =valueOf(formatter.format(withreturn25Value)) + " €";

                    normalButton.setText(normalButton.getText() + "\n"  +  normal_value_string );
                    halfButton.setText(halfButton.getText() + "\n"  +  half_value_string );
                    student50percentButton.setText(student50percentButton.getText() + "\n"  +  student_50_value_string );
                    student25percentButton.setText(student25percentButton.getText() + "\n"  +  student_25_value_string );
                    military15percentButton.setText(military15percentButton.getText() + "\n"  +  military_15_value_string );
                    packageButton.setText(packageButton.getText() + "\n"  +  package_value_string );
                    withReturnButton.setText(withReturnButton.getText() + "\n"  +  with_return_value_string );
                    withReturn25percentButton.setText(withReturn25percentButton.getText() + "\n"  +  with_return_25_value_string );
                }
            }
        });

        layout1.addView(GridViewItems);

        scrollview = findViewById(R.id.DestinationsScrollView);
        scrollview.post(new Runnable() {
            @Override
            public void run() {
                scrollview.fullScroll(View.FOCUS_DOWN);
            }
        });
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent new_intent = new Intent(this, RoutesActivity.class);
            new_intent.putExtra("Direction Forth",bus_direction_forth);
            this.startActivity(new_intent);
        }
    }

}