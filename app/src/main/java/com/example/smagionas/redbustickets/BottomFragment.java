package com.example.smagionas.redbustickets;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smagionas.redbustickets.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomFragment extends Fragment
{


    public BottomFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_fragment, container, false);
    }

}
