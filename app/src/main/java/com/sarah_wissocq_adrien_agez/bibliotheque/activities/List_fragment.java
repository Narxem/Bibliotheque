package com.sarah_wissocq_adrien_agez.bibliotheque.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sarah_wissocq_adrien_agez.bibliotheque.R;

/**
 * Created by haelia on 17/10/15.
 */
public class List_fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.list_fragment_layout, container, false);
    }


}
