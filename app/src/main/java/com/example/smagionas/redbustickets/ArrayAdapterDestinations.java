package com.example.smagionas.redbustickets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import static java.lang.String.valueOf;

/**
 * Created by s.magionas on 17/10/2017.
 */

public class ArrayAdapterDestinations extends ArrayAdapter<ObjectLog2> {

    Context mContext;
    int layoutResourceId;
    ObjectLog2 data[] = null;

    public ArrayAdapterDestinations(Context mContext, int layoutResourceId, ObjectLog2[] data) {

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
