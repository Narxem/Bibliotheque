package com.sarah_wissocq_adrien_agez.bibliotheque.book.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Adrien Agez
 * @author Sarah Wissocq
 */
public class BookDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Library";


    // ############### TABLE BOOK ###############
    public static final String TABLE_BOOKS = "Books";

    public static final String BOOK_ID = "id";
    public static final int COL_BOOK_ID = 0;
    public static final String BOOK_TITLE = "title";
    public static final int COL_BOOK_TITLE = 1;
    public static final String BOOK_SERIES = "series";
    public static final int COL_BOOK_SERIES = 2;
    public static final String BOOK_NUMSERIES = "numSeries";
    public static final int COL_BOOK_NUMSERIES = 3;
    public static final String BOOK_EDITOR = "editor";
    public static final int COL_BOOK_EDITOR = 4;
    public static final String BOOK_YEAR = "year";
    public static final int COL_BOOK_YEAR = 5;
    public static final String BOOK_ISBN = "isbn";
    public static final int COL_BOOK_ISBN = 6;
    public static final String BOOK_COVER = "cover";
    public static final int COL_BOOK_COVER = 7;
    public static final String BOOK_SUMMARY = "summary";
    public static final int COL_BOOK_SUMMARY = 8;

    private static final String CREATE_BOOK_TABLE =
            "CREATE TABLE " + TABLE_BOOKS + " (" +
                    BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BOOK_TITLE + " TEXT NOT NULL, " +
                    BOOK_SERIES + " TEXT, " +
                    BOOK_NUMSERIES + " INTEGER, " +
                    BOOK_EDITOR + " TEXT, " +
                    BOOK_YEAR + " INTEGER, " +
                    BOOK_ISBN + " TEXT, " +
                    BOOK_COVER + " TEXT, " +
                    BOOK_SUMMARY + " TEXT);";


    // ############### TABLE AUTHOR ###############
    public static final String TABLE_AUTHORS = "Authors";

    public static final String AUTHOR_ID = "id";
    public static final int COL_AUTHOR_ID = 0;
    public static final String AUTHOR_NAME = "name";
    public static final int COL_AUTHOR_NAME = 1;

    private static final String CREATE_AUTHOR_TABLE =
            "CREATE TABLE " + TABLE_AUTHORS + " (" +
                    AUTHOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    AUTHOR_NAME + " TEXT NOT NULL);";



    // ############### TABLE TAGS ###############
    public static final String TABLE_TAGS = "Tags";

    public static final String TAG_ID = "id";
    public static final int COL_TAG_ID = 0;
    public static final String TAG_NAME = "tagName";
    public static final int COL_TAG_NAME = 1;

    private static final String CREATE_TAG_TABLE =
            "CREATE TABLE " + TABLE_TAGS + " (" +
                    TAG_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TAG_NAME + " TEXT NOT NULL);";

    // ############### TABLES INTERMEDIAIRRES ###############
    private static final String CREATE_BOOK_AUTHOR_TABLE = "CREATE TABLE BookAuthor (bookID INTEGER NOT NULL, authorID INTEGER NOT NULL);";
    private static final String CREATE_BOOK_TAG_TABLE = "CREATE TABLE BookTag (bookID INTEGER NOT NULL, tagID INTEGER NOT NULL);";



    // ############### METHODS ###############
    public BookDatabaseHelper(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK_TABLE);
        db.execSQL(CREATE_AUTHOR_TABLE);
        db.execSQL(CREATE_TAG_TABLE);
        db.execSQL(CREATE_BOOK_AUTHOR_TABLE);
        db.execSQL(CREATE_BOOK_TAG_TABLE);
        ContentValues values = new ContentValues();

        // TODO remove  test values
        values.put(BOOK_TITLE, "Hunger Games");
        values.put(BOOK_ISBN, "24");
        db.insert(TABLE_BOOKS, null, values);

        values.put(BOOK_TITLE, "Harry Potter");
        values.put(BOOK_ISBN, "7");
        db.insert(TABLE_BOOKS,null, values);

        values.put(BOOK_TITLE, "Le Seigneur des anneaux");
        values.put(BOOK_ISBN, "666");
        db.insert(TABLE_BOOKS,null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE *");
        this.onCreate(db);
    }
}
