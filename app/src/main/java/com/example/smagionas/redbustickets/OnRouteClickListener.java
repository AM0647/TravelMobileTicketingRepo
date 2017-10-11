package com.example.smagionas.redbustickets;

/**
 * Created by s.magionas on 10/10/2017.
 */


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;


public class OnRouteClickListener extends AppCompatActivity implements OnItemClickListener {



    public interface NoticeDialogListener {
        void onDeleteEmployee(DialogFragment dialog);
        void onCancel(DialogFragment dialog);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Context context = view.getContext();



        TextView textViewRouteName = view.findViewById(R.id.textViewRoute);
        //TextView textViewItemLastName = ((TextView) view.findViewById(R.id.textViewItemLastName));


        String listRouteName = textViewRouteName.getText().toString();


        //String listItemTextLastName = textViewItemLastName.getText().toString();


        Intent new_intent = new Intent(context, TicketIssuanceActivity.class);
        new_intent.putExtra("Route name", listRouteName);
        //new_intent.putExtra("lastname", listItemTextLastName);
        context.startActivity(new_intent);



    }

}