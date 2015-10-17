package com.sarah_wissocq_adrien_agez.bibliotheque.book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wissocq on 24/09/15.
 */
public class BookLibrary extends ArrayList<Book>{

    public static BookLibrary LIBRARY = new BookLibrary();

    private BookLibrary(){
        super();
    }

    public boolean addBook(Book book) {
        return this.add(book);
    }


    public boolean removeBook(Book book) {
        return this.remove(book);
    }

    public Book createBook() {
        return new Book();
    }
}
