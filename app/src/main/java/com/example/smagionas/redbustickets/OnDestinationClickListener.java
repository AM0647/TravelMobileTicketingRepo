package com.example.smagionas.redbustickets;

/**
 * Created by s.magionas on 17/10/2017.
 */


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;


public class OnDestinationClickListener extends AppCompatActivity implements OnItemClickListener {




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Context context = view.getContext();

        Button buttonDestinationName = view.findViewById(R.id.buttonDestination);
        String listDestinationName = buttonDestinationName.getText().toString();

        Toast.makeText(context, listDestinationName, Toast.LENGTH_LONG).show();


        //Intent new_intent = new Intent(context, TicketIssuanceActivity.class);
        //new_intent.putExtra("Route name", listRouteName);
        //context.startActivity(new_intent);

    }

}