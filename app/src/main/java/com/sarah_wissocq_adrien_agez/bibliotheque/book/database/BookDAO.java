package com.sarah_wissocq_adrien_agez.bibliotheque.book.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_AUTHOR;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_ID;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_ISBN;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_TABLE;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_TITLE;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.NUM_BOOK_AUTHOR;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.NUM_BOOK_ISBN;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.NUM_BOOK_TITLE;

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
        value.put(BOOK_TITLE, book.getTitle());
        value.put(BOOK_AUTHOR, book.getAuthor());
        value.put(BOOK_ISBN, book.getIsbn());
        return database.insert(BOOK_TABLE, null, value);
    }

    public int updateById(int id, Book book) {
        ContentValues value = new ContentValues();
        value.put(BOOK_TITLE, book.getTitle());
        value.put(BOOK_AUTHOR, book.getAuthor());
        value.put(BOOK_ISBN, book.getIsbn());
        return database.update(BOOK_TABLE, value, BOOK_ID + " = " + id, null);
    }

    public int removeById(int id) {
        return database.delete(BOOK_TABLE, BOOK_ID + " = " + id, null);
    }

    public int removeBook(Book book) {
        return database.delete(BOOK_TABLE,
                BOOK_TITLE + " = '" + book.getTitle() + "' AND " +
                        BOOK_AUTHOR + " = '" + book.getAuthor() + "' AND " +
                        BOOK_ISBN + " = '" + book.getIsbn() + "';"
                , null);
    }

    /**
     * @param sortedBy must be BOOK_TITLE, BOOK_AUTHOR, BOOK_ISBN or null. Sort all the book by the given parameter
     */
    public Cursor getAllBook(@Nullable String sortedBy) {
        return database.query(BOOK_TABLE, null, null, null, null, null, sortedBy);
    }

    /**
     * @param sortedBy must be BOOK_TITLE, BOOK_AUTHOR, BOOK_ISBN or null. Sort all the book by the given parameter
     */
    public List<Book> getAllBookList(@Nullable String sortedBy) {
        Cursor cursor = database.query(BOOK_TABLE, null, null, null, null, null, sortedBy);
        List<Book> list = new ArrayList<Book>(cursor.getCount());
        while (cursor.moveToNext()) {
            list.add(new Book(cursor.getString(NUM_BOOK_TITLE), cursor.getString(NUM_BOOK_AUTHOR), cursor.getString(NUM_BOOK_ISBN)));
        }
        return list;
    }

}
