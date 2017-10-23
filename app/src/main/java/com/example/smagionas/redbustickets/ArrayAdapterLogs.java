package com.example.smagionas.redbustickets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Calendar;

import static java.lang.String.valueOf;

/**
 * Created by s.magionas on 17/10/2017.
 */

public class ArrayAdapterLogs extends ArrayAdapter<ObjectLog3> {

    Context mContext;
    int layoutResourceId;
    ObjectLog3 data[] = null;

    public ArrayAdapterLogs(Context mContext, int layoutResourceId, ObjectLog3[] data) {

        super(mContext, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView==null){

            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }


        ObjectLog3 objectLog = data[position];

        TextView LogFirstRow = convertView.findViewById(R.id.textViewLogFirstRow);
        TextView LogSecondRow = convertView.findViewById(R.id.textViewLogSecondRow);
        TextView LogThirdRow = convertView.findViewById(R.id.textViewLogThirdRow);




        Long current_time = objectLog.dateAndTime;
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

        String current_time_string = hour1_string + ":" + minute1_string + ":" + second1_string;

        String FirstRowString = current_time_string + "   " + objectLog.route;
        String SecondRowString = objectLog.value +  "€   " + objectLog.from + "  -  " + objectLog.to;
        String ThirdRowString = objectLog.type + "   " +  "Μ/Σ:" + objectLog.tabletCounter + "   Μ/Ο:" + objectLog.driverCounter;

        LogFirstRow.setText(FirstRowString);
        LogSecondRow.setText(SecondRowString);
        LogThirdRow.setText(ThirdRowString);


        return convertView;

    }



}
