package com.sarah_wissocq_adrien_agez.bibliotheque.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sarah_wissocq_adrien_agez.bibliotheque.R;


public class Main extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void create(View view){
        Intent intent = new Intent(this, CreateBook.class);
        startActivity(intent);
    }

    public void scanner(View view){
        Intent intent = new Intent(this, ScanBook.class);
        startActivity(intent);
    }

    public void view(View view){
        Intent intent = new Intent(this, ViewBookList.class);
        startActivity(intent);
    }
}
