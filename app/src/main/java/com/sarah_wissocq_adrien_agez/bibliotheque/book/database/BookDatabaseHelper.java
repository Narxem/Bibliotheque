package com.sarah_wissocq_adrien_agez.bibliotheque.book.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;

/**
 * @author Adrien Agez
 * @author Sarah Wissocq
 */
public class BookDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Library";

    public static final String BOOK_TABLE = "Books";
    public static final String BOOK_ID = "id";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_AUTHOR = "author";
    public static final String BOOK_ISBN = "isbn";

    public static final int NUM_BOOK_ID = 0;
    public static final int NUM_BOOK_TITLE = 1;
    public static final int NUM_BOOK_AUTHOR = 2;
    public static final int NUM_BOOK_ISBN = 3;

    private static final String CREATE_BOOK_TABLE =
            "CREATE TABLE " + BOOK_TABLE + " (" +
                    BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BOOK_TITLE + " TEXT NOT NULL, " +
                    BOOK_AUTHOR + " TEXT NOT NULL, " +
                    BOOK_ISBN + " TEXT NOT NULL);";

    private static final String DROP_BOOK_TABLE =
            "DROP TABLE " + BOOK_TABLE;

    public BookDatabaseHelper(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK_TABLE);
        ContentValues values = new ContentValues();

        // TODO remove  test values
        values.put(BOOK_TITLE, "Hunger Games");
        values.put(BOOK_AUTHOR, "Suzanne Collins");
        values.put(BOOK_ISBN, "24");
        db.insert(BOOK_TABLE, null, values);

        values.put(BOOK_TITLE, "Harry Potter");
        values.put(BOOK_AUTHOR, "J.K Rowling");
        values.put(BOOK_ISBN, "7");
        db.insert(BOOK_TABLE,null, values);

        values.put(BOOK_TITLE, "Le Seigneur des anneaux");
        values.put(BOOK_AUTHOR, "Tolkien");
        values.put(BOOK_ISBN, "666");
        db.insert(BOOK_TABLE,null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_BOOK_TABLE);
        this.onCreate(db);
    }
}
