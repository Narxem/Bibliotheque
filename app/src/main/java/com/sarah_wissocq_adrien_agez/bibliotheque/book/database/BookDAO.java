package com.sarah_wissocq_adrien_agez.bibliotheque.book.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;

import java.sql.SQLException;
import java.util.List;

import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.AUTHOR_NAME;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_AUTHOR_AUTHORID;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_AUTHOR_BOOKID;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_COVER;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_EDITOR;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_ISBN;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_NUMSERIES;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_SERIES;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_SUMMARY;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_TITLE;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.BOOK_YEAR;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.COL_AUTHOR_ID;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.TABLE_AUTHORS;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.TABLE_BOOKS;
import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.TABLE_BOOK_AUTHOR;

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
        value.put(BOOK_SERIES, book.getSeries());
        value.put(BOOK_NUMSERIES, book.getNumSeries());
        value.put(BOOK_EDITOR, book.getEditor());
        value.put(BOOK_YEAR, book.getYear());
        value.put(BOOK_ISBN, book.getIsbn());
        value.put(BOOK_COVER, book.getCoverURI());
        value.put(BOOK_SUMMARY, book.getSummary());
        long bookId = database.insert(TABLE_BOOKS, null, value);
        List<String> authors = book.getAuthors();
        insertAuthors(bookId, authors);
        return bookId;
    }

    protected void insertAuthors(long bookId, List<String> authors) {
        ContentValues authorValues = new ContentValues();
        for (String author : authors) {
            Cursor cursor = database.query(TABLE_AUTHORS, null, AUTHOR_NAME + " = " + author, null, null, null, null, "1");
            if (cursor.getCount() == 0) {
                authorValues.put(AUTHOR_NAME, author);
                long authorId = database.insert(TABLE_AUTHORS, null, authorValues);
                authorValues.clear();
                authorValues.put(BOOK_AUTHOR_BOOKID, bookId);
                authorValues.put(BOOK_AUTHOR_AUTHORID, authorId);
                database.insert(TABLE_BOOK_AUTHOR, null, authorValues);
            } else {
                authorValues.put(BOOK_AUTHOR_BOOKID, bookId);
                authorValues.put(BOOK_AUTHOR_AUTHORID, cursor.getLong(COL_AUTHOR_ID));
                database.insert(TABLE_BOOK_AUTHOR, null, authorValues);
            }
        }
    }

}
