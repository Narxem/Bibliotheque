package com.sarah_wissocq_adrien_agez.bibliotheque.book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wissocq on 24/09/15.
 */
public class BookLibrary {


    protected ArrayList<Book> bookList = new ArrayList<Book>();


    public BookLibrary() {
        super();
    }


    public boolean addBook(Book book) {
        return bookList.add(book);
    }


    public boolean removeBook(Book book) {
        return bookList.remove(book);
    }

    public Book createBook() {
        return new Book();
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }


}
