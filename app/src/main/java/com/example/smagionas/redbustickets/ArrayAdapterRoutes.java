package com.example.smagionas.redbustickets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


class ArrayAdapterRoutes extends ArrayAdapter<ObjectLog> {

//////////////////// START OF GLOBAL VARIABLES/////////////////////////

    private Context mContext;
    private int layoutResourceId;
    ObjectLog data[] = null;
//////////////////// END OF GLOBAL VARIABLES///////////////////////////
    ArrayAdapterRoutes(Context mContext, int layoutResourceId, ObjectLog[] data) {

        super(mContext, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {


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
