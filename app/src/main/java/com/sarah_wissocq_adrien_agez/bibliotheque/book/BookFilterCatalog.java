package com.sarah_wissocq_adrien_agez.bibliotheque.book;

import java.util.ArrayList;

/**
 * Created by wissocq on 07/10/15.
 */
public class BookFilterCatalog {

    protected ArrayList<BookFilter> bookFilters = new ArrayList<BookFilter>();

    public BookFilterCatalog(){
        super();
    }

    public boolean addFilter(BookFilter bf) {
        return bookFilters.add(bf);
    }

    public boolean removeFilter(BookFilter bf) {
        return bookFilters.remove(bf);
    }

    public BookFilter createFilter() {
        return new BookFilter();
    }

    public ArrayList<BookFilter> getBookFilters() {
        return bookFilters;
    }
}
