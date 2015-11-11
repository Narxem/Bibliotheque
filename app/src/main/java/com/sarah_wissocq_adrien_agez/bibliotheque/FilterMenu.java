package com.sarah_wissocq_adrien_agez.bibliotheque;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.sarah_wissocq_adrien_agez.bibliotheque.activities.ViewBookList;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.filter.AuthorFilter;


public class FilterMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_menu);
    }

    public void add(){
        EditText filtreAut = (EditText) findViewById(R.id.filtreauteur);
        String filtreauteur = filtreAut.getText().toString();
        /** Retourner à la liste des livres avec le filtre afin de filtrer.*/
        Intent intent = new Intent(this, ViewBookList.class);
        intent.putExtra("AUTHOR_FILTER", filtreauteur);
        startActivity(intent);
    }

}
