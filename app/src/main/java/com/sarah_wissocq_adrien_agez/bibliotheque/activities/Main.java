package com.sarah_wissocq_adrien_agez.bibliotheque.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sarah_wissocq_adrien_agez.bibliotheque.R;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;
import com.sarah_wissocq_adrien_agez.bibliotheque.book.BookLibrary;


public class Main extends Activity {

    static {
        BookLibrary.LIBRARY.addBook(new Book("Harry Potter", "Rowling", "7"));
        BookLibrary.LIBRARY.addBook(new Book("Le seigneur des anneaux", "Tolkien", "666"));
        BookLibrary.LIBRARY.addBook(new Book("Hunger Games", "Collins", "24"));
        BookLibrary.LIBRARY.addBook(new Book("Cacahuète", "Cookie", "665"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void create(View view){
        Intent intent = new Intent(this, CreateBook.class);
        startActivity(intent);
    }

    public void view(View view){
        Intent intent = new Intent(this, ViewBookList.class);
        startActivity(intent);
    }
}
