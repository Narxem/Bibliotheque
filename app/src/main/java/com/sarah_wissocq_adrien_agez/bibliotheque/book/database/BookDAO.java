package com.sarah_wissocq_adrien_agez.bibliotheque.book.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.*;

/**
 * @author Adrien Agez
 * @author Sarah Wissocq
 */
public class BookDAO {
    private static final int DATABASE_VERSION = 12;

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
            authorValues.clear();
            Cursor cursor = database.query(TABLE_AUTHORS, null, AUTHOR_NAME + " = '" + author.replaceAll("'", "''") + "'", null, null, null, null, "1");
            if (cursor.getCount() == 0) {  // Auteur pas encore présent
                authorValues.put(AUTHOR_NAME, author);
                long authorId = database.insert(TABLE_AUTHORS, null, authorValues);
                authorValues.clear();
                authorValues.put(BOOK_AUTHOR_BOOKID, bookId);
                authorValues.put(BOOK_AUTHOR_AUTHORID, authorId);
                database.insert(TABLE_BOOK_AUTHOR, null, authorValues);
            } else {    // Auteur déjà présent
                cursor.moveToNext();
                authorValues.put(BOOK_AUTHOR_BOOKID, bookId);
                authorValues.put(BOOK_AUTHOR_AUTHORID, cursor.getLong(COL_AUTHOR_ID));
                database.insert(TABLE_BOOK_AUTHOR, null, authorValues);
            }
        }
    }


    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<>();
        Cursor cursor = database.query(TABLE_BOOKS, null, null, null, null, null, null, null);
        while (!cursor.isLast()) {
            cursor.moveToNext();
            long bookId = cursor.getLong(COL_BOOK_ID);
            Cursor authorCursor = database.query(TABLE_BOOK_AUTHOR + " JOIN " + TABLE_AUTHORS + " ON " + BOOK_AUTHOR_AUTHORID + " = " + AUTHOR_ID, null,
                    BOOK_AUTHOR_BOOKID + " = '" + bookId + "'", null, null, null, null, null);
            List<String> authors = new LinkedList<String>();
            while (!authorCursor.isLast()) {
                authorCursor.moveToNext();
                authors.add(authorCursor.getString(3));
            }
            books.add(new Book(cursor.getString(COL_BOOK_TITLE), cursor.getString(COL_BOOK_SERIES), cursor.getInt(COL_BOOK_NUMSERIES), cursor.getString(COL_BOOK_EDITOR),
                    cursor.getInt(COL_BOOK_YEAR), cursor.getString(COL_BOOK_ISBN), cursor.getString(COL_BOOK_COVER), cursor.getString(COL_BOOK_SUMMARY), authors));
        }
        return  books;
    }

    public void deleteBook(Book book) {
        String title = book.getTitle().replaceAll("'", "''");
        List<String> author = book.getAuthors();
        database.delete(TABLE_BOOKS
                , BOOK_TITLE +"='"+ title+"'"
                , null);
    }
}
