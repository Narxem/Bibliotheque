package com.sarah_wissocq_adrien_agez.bibliotheque.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sarah_wissocq_adrien_agez.bibliotheque.R;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.BookLibrary;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;


public class Main extends ActionBarActivity {

    static {
        BookLibrary.LIBRARY.addBook(new Book("Jean Robert", "42", "666"));
        BookLibrary.LIBRARY.addBook(new Book("Tolkien", "Le seigneur des anneaux", "667"));
        BookLibrary.LIBRARY.addBook(new Book("Cacahuète", "Cookie", "665"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void create(View view){
        Intent intent = new Intent(this, CreateBook.class);
        startActivity(intent);
    }

    public void view(View view){
        Intent intent = new Intent(this, ViewBook.class);
        startActivity(intent);
    }
}
