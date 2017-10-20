package com.example.smagionas.redbustickets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.MotionEvent;
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
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.Math.abs;
import static java.lang.String.valueOf;


public class TicketIssuanceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String value1;
    TextView Passenger_Destination;
    TextView Destination_Value;
    public ExpandableHeightGridView GridViewItems;
    RelativeLayout layout1;
    ScrollView scrollview;
    Button name_button;
    Toolbar toolbar;
    ImageButton printButton;

    Button normalButton;
    Button halfButton;
    Button student50percentButton;
    Button student25percentButton;
    Button military15percentButton;
    Button packageButton;
    Button withReturnButton;
    Button withReturn25percentButton;

    TextView currentLocation;
    ImageButton prevLocationButton;
    ImageButton nextLocationButton;

    boolean bus_direction_forth ;

    ObjectLog2[] ObjectLogDataFinalForth;
    ObjectLog2[] ObjectLogDataFinalBackward;
    ObjectLog2[] ObjectLogDataFinalTemp;
    ObjectLog2[] ObjectLogDataFinal;
    ArrayAdapterDestinations adapter;
    RelativeLayout.LayoutParams p;

    private Menu menu;

    MenuItem Route_Direction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_issuance);



        layout1 = findViewById(R.id.ticket_issuance_grid_items);
        layout1.removeView(GridViewItems);

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



        Bundle extras = getIntent().getExtras();
        value1 = extras.getString("Route name");
        bus_direction_forth = extras.getBoolean("Direction Forth");
        ab.setTitle(value1);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();

                Intent intent = new Intent(context,RoutesActivity.class);
                intent.putExtra("Direction Forth",bus_direction_forth);
                startActivity(intent);



            }
        });


        name_button = findViewById(R.id.Route_full_name_button);
        name_button.setText(value1);
        Passenger_Destination = findViewById(R.id.Passenger_Destination);
        Destination_Value = findViewById(R.id.TextViewDestinationValue);
        printButton = findViewById(R.id.Print_button);
        printButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

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
        Route_Direction = findViewById(R.id.Route_Direction);

        if(bus_direction_forth){
            currentLocation.setText("Σέρραι (from antenna)");


        }else{
            currentLocation.setText("Αθήνα (from antenna)");

        }



    }


    @Override
    public void onResume() {
        super.onResume();


        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();


                    Intent intent = new Intent(context,RoutesActivity.class);
                    intent.putExtra("Direction Forth",bus_direction_forth);
                    startActivity(intent);


            }
        });


        int number_of_destinations=20;                                                                     // number of destinations to be displayed


        layout1.removeView(GridViewItems);



        ObjectLogDataFinal = new ObjectLog2[number_of_destinations];
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


        //Collections.reverse(Arrays.asList(ObjectLogDataFinal));                                                               // Gia na pairno to ObjectLogDataFinal anastrofa


        //ObjectLog2[] ObjectLogDataFinalFinal;
        //ObjectLogDataFinalFinal = Arrays.copyOfRange(ObjectLogDataFinal,1,ObjectLogDataFinal.length);                         // Gia na pairno kommatia tou ObjectLogDataFinal


        View view = findViewById(android.R.id.content);

        //ObjectLog2[] ObjectLogDataFinalForth;
         //ObjectLog2[] ObjectLogDataFinalBackward;
         //ObjectLog2[] ObjectLogDataFinalTemp;

        if(bus_direction_forth){


            ObjectLogDataFinalForth = Arrays.copyOfRange(ObjectLogDataFinal,1,ObjectLogDataFinal.length);

            ObjectLogDataFinalTemp = Arrays.copyOfRange(ObjectLogDataFinal,0,ObjectLogDataFinal.length - 1);
            Collections.reverse(Arrays.asList(ObjectLogDataFinalTemp));
            ObjectLogDataFinalBackward = Arrays.copyOfRange(ObjectLogDataFinalTemp,0,ObjectLogDataFinalTemp.length);


            adapter = new ArrayAdapterDestinations(view.getContext(), R.layout.grid_view_items, ObjectLogDataFinalForth);


        }else{

            ObjectLogDataFinalForth = Arrays.copyOfRange(ObjectLogDataFinal,1,ObjectLogDataFinal.length);

            ObjectLogDataFinalTemp = Arrays.copyOfRange(ObjectLogDataFinal,0,ObjectLogDataFinal.length - 1);
            Collections.reverse(Arrays.asList(ObjectLogDataFinalTemp));
            ObjectLogDataFinalBackward = Arrays.copyOfRange(ObjectLogDataFinalTemp,0,ObjectLogDataFinalTemp.length);

            adapter = new ArrayAdapterDestinations(view.getContext(), R.layout.grid_view_items, ObjectLogDataFinalBackward);

        }


        //adapter = new ArrayAdapterDestinations(view.getContext(), R.layout.grid_view_items, ObjectLogDataFinal);


        p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(15,10,25,10);
        GridViewItems = new ExpandableHeightGridView(view.getContext());
        GridViewItems.setLayoutParams(p);
        GridViewItems.setNumColumns(1);
        GridViewItems.setAdapter(adapter);
        GridViewItems.setExpanded(true);
        GridViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {                // TODO H 8a krathso auton ton listener h se ksexoristo arxeio. Na epilekso!
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                TextView buttonDestinationName = view.findViewById(R.id.buttonDestination);
                String listDestinationName = buttonDestinationName.getText().toString();

                long unique_id = id;
                String unique_id_string = (String)valueOf(unique_id);
                String value_string;

                double normalValue;
                double halfValue;
                double student50Value ;
                double student25Value ;
                double military15Value ;
                double packageValue;
                double withreturnValue;
                double withreturn25Value;


                normalButton.setText("Κανονικο");
                halfButton.setText("Μισο" );
                student50percentButton.setText("Φοιτητικο  50%" );
                student25percentButton.setText("Φοιτητικο  25%" );
                military15percentButton.setText("Στρατιωτικο  15%");
                packageButton.setText("Δεμα");
                withReturnButton.setText("Με επιστροφη");
                withReturn25percentButton.setText("Με επιστροφη  25%" );


                String normal_value_string ;
                String half_value_string ;
                String student_50_value_string ;
                String student_25_value_string ;
                String military_15_value_string ;
                String package_value_string ;
                String with_return_value_string ;
                String with_return_25_value_string ;


                NumberFormat formatter = new DecimalFormat("#0.0");

                //Toast.makeText(view.getContext(), unique_id_string, Toast.LENGTH_LONG).show();


                if(bus_direction_forth){

                    //Toast.makeText(view.getContext(), unique_id_string, Toast.LENGTH_LONG).show();
                    //Toast.makeText(view.getContext(), position + " !!!", Toast.LENGTH_LONG).show();

                    double value = ObjectLogDataFinalForth[(int) unique_id].value;
                    value_string = (String)valueOf(value) + " €";
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


                    normal_value_string = (String)valueOf(formatter.format(normalValue)) + " €";
                    half_value_string = (String)valueOf(formatter.format(halfValue)) + " €";
                    student_50_value_string = (String)valueOf(formatter.format(student50Value)) + " €";
                    student_25_value_string = (String)valueOf(formatter.format(student25Value)) + " €";
                    military_15_value_string = (String)valueOf(formatter.format(military15Value)) + " €";
                    package_value_string = (String)valueOf(formatter.format(packageValue)) + " €";
                    with_return_value_string = (String)valueOf(formatter.format(withreturnValue)) + " €";
                    with_return_25_value_string = (String)valueOf(formatter.format(withreturn25Value)) + " €";

                    normalButton.setText(normalButton.getText() + "\n"  +  normal_value_string );
                    halfButton.setText(halfButton.getText() + "\n"  +  half_value_string );
                    student50percentButton.setText(student50percentButton.getText() + "\n"  +  student_50_value_string );
                    student25percentButton.setText(student25percentButton.getText() + "\n"  +  student_25_value_string );
                    military15percentButton.setText(military15percentButton.getText() + "\n"  +  military_15_value_string );
                    packageButton.setText(packageButton.getText() + "\n"  +  package_value_string );
                    withReturnButton.setText(withReturnButton.getText() + "\n"  +  with_return_value_string );
                    withReturn25percentButton.setText(withReturn25percentButton.getText() + "\n"  +  with_return_25_value_string );



                }else{

                    //Toast.makeText(view.getContext(), unique_id_string, Toast.LENGTH_LONG).show();
                    //Toast.makeText(view.getContext(), position + " !!!", Toast.LENGTH_LONG).show();
                    //Toast.makeText(view.getContext(), "ObjectLogDataFinal  " + ObjectLogDataFinal.length + " !!!", Toast.LENGTH_LONG).show();
                    //Toast.makeText(view.getContext(), "ObjectLogDataFinalBackward  " + ObjectLogDataFinalBackward.length + " !!!", Toast.LENGTH_LONG).show();



                    //double value = ObjectLogDataFinal[abs(((int) unique_id) - (ObjectLogDataFinalBackward.length - 1))].value;
                    double value = ObjectLogDataFinalForth[(int) unique_id].value;
                    value_string = (String)valueOf(value) + " €";
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


                    normal_value_string = (String)valueOf(formatter.format(normalValue)) + " €";
                    half_value_string = (String)valueOf(formatter.format(halfValue)) + " €";
                    student_50_value_string = (String)valueOf(formatter.format(student50Value)) + " €";
                    student_25_value_string = (String)valueOf(formatter.format(student25Value)) + " €";
                    military_15_value_string = (String)valueOf(formatter.format(military15Value)) + " €";
                    package_value_string = (String)valueOf(formatter.format(packageValue)) + " €";
                    with_return_value_string = (String)valueOf(formatter.format(withreturnValue)) + " €";
                    with_return_25_value_string = (String)valueOf(formatter.format(withreturn25Value)) + " €";

                    normalButton.setText(normalButton.getText() + "\n"  +  normal_value_string );
                    halfButton.setText(halfButton.getText() + "\n"  +  half_value_string );
                    student50percentButton.setText(student50percentButton.getText() + "\n"  +  student_50_value_string );
                    student25percentButton.setText(student25percentButton.getText() + "\n"  +  student_25_value_string );
                    military15percentButton.setText(military15percentButton.getText() + "\n"  +  military_15_value_string );
                    packageButton.setText(packageButton.getText() + "\n"  +  package_value_string );
                    withReturnButton.setText(withReturnButton.getText() + "\n"  +  with_return_value_string );
                    withReturn25percentButton.setText(withReturn25percentButton.getText() + "\n"  +  with_return_25_value_string );


                }
                //double value = ObjectLogDataFinal[(int) unique_id].value;
                //String value_string = (String)valueOf(value) + " €";



                //Passenger_Destination.setText("Σέρραι - " + listDestinationName);
               // Destination_Value.setText(value_string);

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Route_full_name:

                if(name_button.getVisibility() == View.GONE){
                    name_button.setVisibility(View.VISIBLE);
                }else if(name_button.getVisibility() == View.VISIBLE){
                    name_button.setVisibility(View.GONE);
            }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

//        Route_Direction = findViewById(R.id.Route_Direction);
//
//        if(!bus_direction_forth){
//
//            Route_Direction.setIcon(R.mipmap.ic_rewind);
//
//        }
        this.menu = menu;


        getMenuInflater().inflate(R.menu.my_menu_ticket_issuance, menu);


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



    public void OnPrint_button_pressed(View view){

        Toast.makeText(view.getContext(), "Μονή εκτύπωση", Toast.LENGTH_LONG).show();

    }


    public void OnPrevLocationButtonPressed(View view){

        //Toast.makeText(view.getContext(), (String)valueOf(ObjectLogDataFinalForth.length), Toast.LENGTH_LONG).show();

        if(  (ObjectLogDataFinalForth.length < (ObjectLogDataFinal.length - 1)) ||  (ObjectLogDataFinalBackward.length < (ObjectLogDataFinalTemp.length ))   ){

            //Toast.makeText(view.getContext(), "I am alive", Toast.LENGTH_LONG).show();

            nextLocationButton.setImageResource(R.mipmap.ic_arrow_right_black);

            if(bus_direction_forth){


                ObjectLogDataFinalForth = Arrays.copyOfRange(ObjectLogDataFinal,((ObjectLogDataFinal.length - ObjectLogDataFinalForth.length - 1)),ObjectLogDataFinal.length);
                if(ObjectLogDataFinalForth.length == (ObjectLogDataFinal.length - 1)) prevLocationButton.setImageResource(R.mipmap.ic_arrow_left_gray);

                adapter = new ArrayAdapterDestinations(view.getContext(), R.layout.grid_view_items, ObjectLogDataFinalForth);


            }else{

                ObjectLogDataFinalBackward = Arrays.copyOfRange(ObjectLogDataFinalTemp,((ObjectLogDataFinalTemp.length - ObjectLogDataFinalBackward.length - 1)),ObjectLogDataFinalTemp.length);
                if(ObjectLogDataFinalBackward.length == (ObjectLogDataFinalTemp.length - 1)) prevLocationButton.setImageResource(R.mipmap.ic_arrow_left_gray);
                //            ObjectLogDataFinalForth = Arrays.copyOfRange(ObjectLogDataFinal,1,ObjectLogDataFinal.length);

                //            ObjectLogDataFinalTemp = Arrays.copyOfRange(ObjectLogDataFinal,0,ObjectLogDataFinal.length - 1);
                //            Collections.reverse(Arrays.asList(ObjectLogDataFinalTemp));
                //            ObjectLogDataFinalBackward = Arrays.copyOfRange(ObjectLogDataFinalTemp,0,ObjectLogDataFinalTemp.length);

                adapter = new ArrayAdapterDestinations(view.getContext(), R.layout.grid_view_items, ObjectLogDataFinalBackward);

            }



            layout1.removeView(GridViewItems);

            p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            p.setMargins(15,10,25,10);
            GridViewItems = new ExpandableHeightGridView(view.getContext());
            GridViewItems.setLayoutParams(p);
            GridViewItems.setNumColumns(1);
            GridViewItems.setAdapter(adapter);
            GridViewItems.setExpanded(true);
            GridViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {                // TODO H 8a krathso auton ton listener h se ksexoristo arxeio. Na epilekso!
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    TextView buttonDestinationName = view.findViewById(R.id.buttonDestination);
                    String listDestinationName = buttonDestinationName.getText().toString();

                    long unique_id = id;
                    String unique_id_string = (String)valueOf(unique_id);
                    String value_string;

                    double normalValue;
                    double halfValue;
                    double student50Value ;
                    double student25Value ;
                    double military15Value ;
                    double packageValue;
                    double withreturnValue;
                    double withreturn25Value;


                    normalButton.setText("Κανονικο");
                    halfButton.setText("Μισο" );
                    student50percentButton.setText("Φοιτητικο  50%" );
                    student25percentButton.setText("Φοιτητικο  25%" );
                    military15percentButton.setText("Στρατιωτικο  15%");
                    packageButton.setText("Δεμα");
                    withReturnButton.setText("Με επιστροφη");
                    withReturn25percentButton.setText("Με επιστροφη  25%" );


                    String normal_value_string ;
                    String half_value_string ;
                    String student_50_value_string ;
                    String student_25_value_string ;
                    String military_15_value_string ;
                    String package_value_string ;
                    String with_return_value_string ;
                    String with_return_25_value_string ;


                    NumberFormat formatter = new DecimalFormat("#0.0");

                    //Toast.makeText(view.getContext(), unique_id_string, Toast.LENGTH_LONG).show();


                    if(bus_direction_forth){

                        //Toast.makeText(view.getContext(), unique_id_string, Toast.LENGTH_LONG).show();
                        //Toast.makeText(view.getContext(), position + " !!!", Toast.LENGTH_LONG).show();

                        double value = ObjectLogDataFinalForth[(int) unique_id].value;
                        value_string = (String)valueOf(value) + " €";
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


                        normal_value_string = (String)valueOf(formatter.format(normalValue)) + " €";
                        half_value_string = (String)valueOf(formatter.format(halfValue)) + " €";
                        student_50_value_string = (String)valueOf(formatter.format(student50Value)) + " €";
                        student_25_value_string = (String)valueOf(formatter.format(student25Value)) + " €";
                        military_15_value_string = (String)valueOf(formatter.format(military15Value)) + " €";
                        package_value_string = (String)valueOf(formatter.format(packageValue)) + " €";
                        with_return_value_string = (String)valueOf(formatter.format(withreturnValue)) + " €";
                        with_return_25_value_string = (String)valueOf(formatter.format(withreturn25Value)) + " €";

                        normalButton.setText(normalButton.getText() + "\n"  +  normal_value_string );
                        halfButton.setText(halfButton.getText() + "\n"  +  half_value_string );
                        student50percentButton.setText(student50percentButton.getText() + "\n"  +  student_50_value_string );
                        student25percentButton.setText(student25percentButton.getText() + "\n"  +  student_25_value_string );
                        military15percentButton.setText(military15percentButton.getText() + "\n"  +  military_15_value_string );
                        packageButton.setText(packageButton.getText() + "\n"  +  package_value_string );
                        withReturnButton.setText(withReturnButton.getText() + "\n"  +  with_return_value_string );
                        withReturn25percentButton.setText(withReturn25percentButton.getText() + "\n"  +  with_return_25_value_string );



                    }else{

                        //Toast.makeText(view.getContext(), unique_id_string, Toast.LENGTH_LONG).show();
                        //Toast.makeText(view.getContext(), position + " !!!", Toast.LENGTH_LONG).show();
                        //Toast.makeText(view.getContext(), "ObjectLogDataFinal  " + ObjectLogDataFinal.length + " !!!", Toast.LENGTH_LONG).show();
                        //Toast.makeText(view.getContext(), "ObjectLogDataFinalBackward  " + ObjectLogDataFinalBackward.length + " !!!", Toast.LENGTH_LONG).show();



                        //double value = ObjectLogDataFinal[abs(((int) unique_id) - (ObjectLogDataFinalBackward.length - 1))].value;
                        double value = ObjectLogDataFinalForth[(int) unique_id].value;
                        value_string = (String)valueOf(value) + " €";
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


                        normal_value_string = (String)valueOf(formatter.format(normalValue)) + " €";
                        half_value_string = (String)valueOf(formatter.format(halfValue)) + " €";
                        student_50_value_string = (String)valueOf(formatter.format(student50Value)) + " €";
                        student_25_value_string = (String)valueOf(formatter.format(student25Value)) + " €";
                        military_15_value_string = (String)valueOf(formatter.format(military15Value)) + " €";
                        package_value_string = (String)valueOf(formatter.format(packageValue)) + " €";
                        with_return_value_string = (String)valueOf(formatter.format(withreturnValue)) + " €";
                        with_return_25_value_string = (String)valueOf(formatter.format(withreturn25Value)) + " €";

                        normalButton.setText(normalButton.getText() + "\n"  +  normal_value_string );
                        halfButton.setText(halfButton.getText() + "\n"  +  half_value_string );
                        student50percentButton.setText(student50percentButton.getText() + "\n"  +  student_50_value_string );
                        student25percentButton.setText(student25percentButton.getText() + "\n"  +  student_25_value_string );
                        military15percentButton.setText(military15percentButton.getText() + "\n"  +  military_15_value_string );
                        packageButton.setText(packageButton.getText() + "\n"  +  package_value_string );
                        withReturnButton.setText(withReturnButton.getText() + "\n"  +  with_return_value_string );
                        withReturn25percentButton.setText(withReturn25percentButton.getText() + "\n"  +  with_return_25_value_string );


                    }
                    //double value = ObjectLogDataFinal[(int) unique_id].value;
                    //String value_string = (String)valueOf(value) + " €";



                    //Passenger_Destination.setText("Σέρραι - " + listDestinationName);
                    // Destination_Value.setText(value_string);

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




    }




    public void OnNextLocationButtonPressed(View view){

        //ObjectLogDataFinalForth = Arrays.copyOfRange(ObjectLogDataFinal,1,ObjectLogDataFinal.length);
        //Toast.makeText(view.getContext(), (String)valueOf(ObjectLogDataFinalForth.length), Toast.LENGTH_LONG).show();

        if(   (ObjectLogDataFinalForth.length> 0)  &&  (ObjectLogDataFinalBackward.length> 0)   ){

            prevLocationButton.setImageResource(R.mipmap.ic_arrow_left_black);

            if(bus_direction_forth){

                ObjectLogDataFinalForth = Arrays.copyOfRange(ObjectLogDataFinalForth,1,ObjectLogDataFinalForth.length);
                if(ObjectLogDataFinalForth.length == 0) nextLocationButton.setImageResource(R.mipmap.ic_arrow_right_gray);

                adapter = new ArrayAdapterDestinations(view.getContext(), R.layout.grid_view_items, ObjectLogDataFinalForth);


            }else{

                ObjectLogDataFinalBackward = Arrays.copyOfRange(ObjectLogDataFinalBackward,1,ObjectLogDataFinalBackward.length);
                if(ObjectLogDataFinalBackward.length == 0) nextLocationButton.setImageResource(R.mipmap.ic_arrow_right_gray);

                adapter = new ArrayAdapterDestinations(view.getContext(), R.layout.grid_view_items, ObjectLogDataFinalBackward);


            }

            layout1.removeView(GridViewItems);

            p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            p.setMargins(15,10,25,10);
            GridViewItems = new ExpandableHeightGridView(view.getContext());
            GridViewItems.setLayoutParams(p);
            GridViewItems.setNumColumns(1);
            GridViewItems.setAdapter(adapter);
            GridViewItems.setExpanded(true);
            GridViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {                // TODO H 8a krathso auton ton listener h se ksexoristo arxeio. Na epilekso!
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    TextView buttonDestinationName = view.findViewById(R.id.buttonDestination);
                    String listDestinationName = buttonDestinationName.getText().toString();

                    long unique_id = id;
                    String unique_id_string = (String)valueOf(unique_id);
                    String value_string;

                    double normalValue;
                    double halfValue;
                    double student50Value ;
                    double student25Value ;
                    double military15Value ;
                    double packageValue;
                    double withreturnValue;
                    double withreturn25Value;


                    normalButton.setText("Κανονικο");
                    halfButton.setText("Μισο" );
                    student50percentButton.setText("Φοιτητικο  50%" );
                    student25percentButton.setText("Φοιτητικο  25%" );
                    military15percentButton.setText("Στρατιωτικο  15%");
                    packageButton.setText("Δεμα");
                    withReturnButton.setText("Με επιστροφη");
                    withReturn25percentButton.setText("Με επιστροφη  25%" );


                    String normal_value_string ;
                    String half_value_string ;
                    String student_50_value_string ;
                    String student_25_value_string ;
                    String military_15_value_string ;
                    String package_value_string ;
                    String with_return_value_string ;
                    String with_return_25_value_string ;


                    NumberFormat formatter = new DecimalFormat("#0.0");

                    //Toast.makeText(view.getContext(), unique_id_string, Toast.LENGTH_LONG).show();


                    if(bus_direction_forth){

                        //Toast.makeText(view.getContext(), unique_id_string, Toast.LENGTH_LONG).show();
                        //Toast.makeText(view.getContext(), position + " !!!", Toast.LENGTH_LONG).show();

                        double value = ObjectLogDataFinalForth[(int) unique_id].value;
                        value_string = (String)valueOf(value) + " €";
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


                        normal_value_string = (String)valueOf(formatter.format(normalValue)) + " €";
                        half_value_string = (String)valueOf(formatter.format(halfValue)) + " €";
                        student_50_value_string = (String)valueOf(formatter.format(student50Value)) + " €";
                        student_25_value_string = (String)valueOf(formatter.format(student25Value)) + " €";
                        military_15_value_string = (String)valueOf(formatter.format(military15Value)) + " €";
                        package_value_string = (String)valueOf(formatter.format(packageValue)) + " €";
                        with_return_value_string = (String)valueOf(formatter.format(withreturnValue)) + " €";
                        with_return_25_value_string = (String)valueOf(formatter.format(withreturn25Value)) + " €";

                        normalButton.setText(normalButton.getText() + "\n"  +  normal_value_string );
                        halfButton.setText(halfButton.getText() + "\n"  +  half_value_string );
                        student50percentButton.setText(student50percentButton.getText() + "\n"  +  student_50_value_string );
                        student25percentButton.setText(student25percentButton.getText() + "\n"  +  student_25_value_string );
                        military15percentButton.setText(military15percentButton.getText() + "\n"  +  military_15_value_string );
                        packageButton.setText(packageButton.getText() + "\n"  +  package_value_string );
                        withReturnButton.setText(withReturnButton.getText() + "\n"  +  with_return_value_string );
                        withReturn25percentButton.setText(withReturn25percentButton.getText() + "\n"  +  with_return_25_value_string );



                    }else{

                        //Toast.makeText(view.getContext(), unique_id_string, Toast.LENGTH_LONG).show();
                        //Toast.makeText(view.getContext(), position + " !!!", Toast.LENGTH_LONG).show();
                        //Toast.makeText(view.getContext(), "ObjectLogDataFinal  " + ObjectLogDataFinal.length + " !!!", Toast.LENGTH_LONG).show();
                        //Toast.makeText(view.getContext(), "ObjectLogDataFinalBackward  " + ObjectLogDataFinalBackward.length + " !!!", Toast.LENGTH_LONG).show();



                        //double value = ObjectLogDataFinal[abs(((int) unique_id) - (ObjectLogDataFinalBackward.length - 1))].value;
                        double value = ObjectLogDataFinalForth[(int) unique_id].value;
                        value_string = (String)valueOf(value) + " €";
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


                        normal_value_string = (String)valueOf(formatter.format(normalValue)) + " €";
                        half_value_string = (String)valueOf(formatter.format(halfValue)) + " €";
                        student_50_value_string = (String)valueOf(formatter.format(student50Value)) + " €";
                        student_25_value_string = (String)valueOf(formatter.format(student25Value)) + " €";
                        military_15_value_string = (String)valueOf(formatter.format(military15Value)) + " €";
                        package_value_string = (String)valueOf(formatter.format(packageValue)) + " €";
                        with_return_value_string = (String)valueOf(formatter.format(withreturnValue)) + " €";
                        with_return_25_value_string = (String)valueOf(formatter.format(withreturn25Value)) + " €";

                        normalButton.setText(normalButton.getText() + "\n"  +  normal_value_string );
                        halfButton.setText(halfButton.getText() + "\n"  +  half_value_string );
                        student50percentButton.setText(student50percentButton.getText() + "\n"  +  student_50_value_string );
                        student25percentButton.setText(student25percentButton.getText() + "\n"  +  student_25_value_string );
                        military15percentButton.setText(military15percentButton.getText() + "\n"  +  military_15_value_string );
                        packageButton.setText(packageButton.getText() + "\n"  +  package_value_string );
                        withReturnButton.setText(withReturnButton.getText() + "\n"  +  with_return_value_string );
                        withReturn25percentButton.setText(withReturn25percentButton.getText() + "\n"  +  with_return_25_value_string );


                    }
                    //double value = ObjectLogDataFinal[(int) unique_id].value;
                    //String value_string = (String)valueOf(value) + " €";



                    //Passenger_Destination.setText("Σέρραι - " + listDestinationName);
                    // Destination_Value.setText(value_string);

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

    }

}