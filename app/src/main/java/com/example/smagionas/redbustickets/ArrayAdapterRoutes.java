package com.example.smagionas.redbustickets;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by s.magionas on 9/10/2017.
 */

public class ArrayAdapterRoutes extends ArrayAdapter<ObjectLog> {

    Context mContext;
    int layoutResourceId;
    ObjectLog data[] = null;

    public ArrayAdapterRoutes(Context mContext, int layoutResourceId, ObjectLog[] data) {

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


        ObjectLog objectLog = data[position];

        TextView textViewRoute = convertView.findViewById(R.id.textViewRoute);
        textViewRoute.setText(objectLog.Route);





        return convertView;

    }



}
