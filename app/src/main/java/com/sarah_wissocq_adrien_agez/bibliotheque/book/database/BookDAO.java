package com.sarah_wissocq_adrien_agez.bibliotheque.book.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;

import java.sql.SQLException;

/**
 * @author Adrien Agez
 * @author Sarah Wissocq
 */
public class BookDAO {
    private static final int DATABASE_VERSION = 1;

    private BookDatabaseHelper databaseHelper;
    private SQLiteDatabase database;


    public BookDAO(Context context) {
        databaseHelper = new BookDatabaseHelper(context, DATABASE_VERSION);
    }

    public void openWritable() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void openReadable() throws SQLException {
        database = databaseHelper.getReadableDatabase();
    }

    public void close() {
        database.close();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public long insert(Book book) {
        ContentValues value = new ContentValues();

    }

}
