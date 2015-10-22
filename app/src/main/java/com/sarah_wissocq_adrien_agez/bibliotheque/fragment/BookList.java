package com.sarah_wissocq_adrien_agez.bibliotheque.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sarah_wissocq_adrien_agez.bibliotheque.R;

/**
 * Created by agez on 22/10/15.
 */
public class BookList extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_list, container);
    }
}
