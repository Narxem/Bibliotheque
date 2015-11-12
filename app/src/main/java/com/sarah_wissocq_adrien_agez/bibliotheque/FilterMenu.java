package com.sarah_wissocq_adrien_agez.bibliotheque;

import android.app.Activity;
import android.os.Bundle;



public class FilterMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_menu);
    }
/*
    public void add(){
        EditText filtreAut = (EditText) findViewById(R.id.filtreauteur);
        String filtreauteur = filtreAut.getText().toString();

        Intent intent = new Intent(this, ViewBookList.class);
        intent.putExtra("AUTHOR_FILTER", filtreauteur);
        startActivity(intent);
    } */

}
