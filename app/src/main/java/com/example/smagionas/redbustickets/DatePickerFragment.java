package com.example.smagionas.redbustickets;

/**
 * Created by s.magionas on 19/10/2017.
 */

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.widget.TextView;
import android.widget.DatePicker;
import android.app.Dialog;
import java.util.Calendar;
import java.util.Date;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    TextView DateDisplayed;
    DatePicker datepicker;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current date as the default date in the date picker

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(getActivity(),this, year, month, day);
        dialog.getDatePicker().setMaxDate(new Date().getTime());
        return dialog;

        //Create a new DatePickerDialog instance and return it
        /*
            DatePickerDialog Public Constructors - Here we uses first one
            public DatePickerDialog (Context context, DatePickerDialog.OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth)
            public DatePickerDialog (Context context, int theme, DatePickerDialog.OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth)
         */
        //return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        //Do something with the date chosen by the user
        //TextView tv = (TextView) getActivity().findViewById(R.id.tv);

        datepicker = (DatePicker) getActivity().findViewById(R.id.datePicker);
        datepicker.updateDate(year, monthOfYear + 1, dayOfMonth);

        DateDisplayed = (TextView) getActivity().findViewById(R.id.DateDisplayed);
        DateDisplayed.setText(dayOfMonth + " / " + (monthOfYear+1) + " / " + year);
        //tv.setText("Date changed...");
        //tv.setText(tv.getText() + "\nYear: " + year);
        //tv.setText(tv.getText() + "\nMonth: " + month);
        //tv.setText(tv.getText() + "\nDay of Month: " + day);

        //String stringOfDate = day + "/" + month + "/" + year;
        //tv.setText(tv.getText() + "\n\nFormatted date: " + stringOfDate);
    }
}