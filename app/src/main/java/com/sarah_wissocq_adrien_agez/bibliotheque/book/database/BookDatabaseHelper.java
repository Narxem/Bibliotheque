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
    public static final String TABLE_BOOK_AUTHOR = "bookAuthor";
    public static final String BOOK_AUTHOR_BOOKID = "bookID";
    public static final int COL_BOOK_AUTHOR_BOOKID = 0;
    public static final String BOOK_AUTHOR_AUTHORID = "authorID";
    public static final int COL_BOOK_AUTHOR_AUTHORID = 1;
    private static final String CREATE_BOOK_AUTHOR_TABLE = "CREATE TABLE BookAuthor (bookID INTEGER NOT NULL, authorID INTEGER NOT NULL);";


    public static final String TABLE_BOOK_TAG = "bookTag";
    public static final String BOOK_TAG_BOOKID = "bookID";
    public static final int COL_BOOK_TAG_BOOKID = 0;
    public static final String BOOK_TAG_TAGID = "tagID";
    public static final int COL_BOOK_TAG_TAGID = 0;
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
        long bookID = db.insert(TABLE_BOOKS, null, values);
        values.clear();
        values.put(AUTHOR_NAME, "Suzanne Collins");
        long authorID = db.insert(TABLE_AUTHORS, null, values);
        values.clear();
        values.put(BOOK_AUTHOR_BOOKID, bookID);
        values.put(BOOK_AUTHOR_AUTHORID, authorID);
        db.insert(TABLE_BOOK_AUTHOR, null, values);
        db.insert(TABLE_BOOK_AUTHOR, null, values);

        values.clear();
        values.put(BOOK_TITLE, "Harry Potter");
        values.put(BOOK_ISBN, "7");
        bookID = db.insert(TABLE_BOOKS,null, values);
        values.clear();
        values.put(AUTHOR_NAME, "J.K.Rowling");
        authorID = db.insert(TABLE_AUTHORS, null, values);
        values.clear();
        values.put(BOOK_AUTHOR_BOOKID, bookID);
        values.put(BOOK_AUTHOR_AUTHORID, authorID);
        db.insert(TABLE_BOOK_AUTHOR, null, values);

        values.clear();
        values.put(BOOK_TITLE, "Le Seigneur des anneaux");
        values.put(BOOK_ISBN, "666");
        bookID = db.insert(TABLE_BOOKS,null, values);
        values.clear();
        values.put(AUTHOR_NAME, "Tolkien");
        authorID = db.insert(TABLE_AUTHORS, null, values);
        values.clear();
        values.put(BOOK_AUTHOR_BOOKID, bookID);
        values.put(BOOK_AUTHOR_AUTHORID, authorID);
        db.insert(TABLE_BOOK_AUTHOR, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_BOOK_AUTHOR);
        db.execSQL("DROP TABLE " + TABLE_BOOKS);
        db.execSQL("DROP TABLE " + TABLE_AUTHORS);
        db.execSQL("DROP TABLE " + TABLE_BOOK_TAG);
        db.execSQL("DROP TABLE " + TABLE_TAGS);
        this.onCreate(db);
    }
}
