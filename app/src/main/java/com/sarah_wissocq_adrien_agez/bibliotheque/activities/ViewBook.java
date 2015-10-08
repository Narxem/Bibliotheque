package com.sarah_wissocq_adrien_agez.bibliotheque.activities;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sarah_wissocq_adrien_agez.bibliotheque.R;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.BookAdapter;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.BookLibrary;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import static com.sarah_wissocq_adrien_agez.bibliotheque.activities.Main.LIBRARY;


public class ViewBook extends ActionBarActivity {

    public static final   ArrayList<Book> books = new ArrayList<Book>();

    static {

        String uri ="@drawable/lille1";
        books.add(new Book("Tolkien", "Le seigneur des anneaux", "667",uri));
        books.add(new Book("Cacahuète", "Cookie", "665",uri));
        books.add(new Book("Jean Robert","42","666",uri));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        BookAdapter adapter = new BookAdapter(this, LIBRARY.getBookList());
        ListView listView = (ListView) findViewById(R.id.lvBook);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_book, menu);
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
}
