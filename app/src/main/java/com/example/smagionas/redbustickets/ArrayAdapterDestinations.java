package com.example.smagionas.redbustickets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;




class ArrayAdapterDestinations extends ArrayAdapter<ObjectLog2> {

//////////////////// START OF GLOBAL VARIABLES/////////////////////////

    private Context mContext;
    private int layoutResourceId;
    ObjectLog2 data[] = null;
//////////////////// END OF GLOBAL VARIABLES///////////////////////////

    ArrayAdapterDestinations(Context mContext, int layoutResourceId, ObjectLog2[] data) {

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


        ObjectLog2 objectLog = data[position];

        TextView buttonDestination = convertView.findViewById(R.id.buttonDestination);
        //buttonDestination.setId(objectLog.itemId);
        buttonDestination.setText(objectLog.Route);

//        TextView DestinationValue = convertView.findViewById(R.id.TextViewDestinationValue);
//        Double Value = objectLog.value;
//        String ValueString = (String)valueOf(Value);
//        DestinationValue.setText(ValueString);






        return convertView;

    }



}
