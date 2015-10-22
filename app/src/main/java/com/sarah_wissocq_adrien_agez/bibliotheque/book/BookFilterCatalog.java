package com.sarah_wissocq_adrien_agez.bibliotheque.book;


import com.sarah_wissocq_adrien_agez.bibliotheque.book.filter.BookFilter;

import java.util.ArrayList;

/**
 * @author Adrien Agez
 * @author Sarah Wissocq.
 */
public class BookFilterCatalog {

    protected ArrayList<BookFilter> bookFilters = new ArrayList<>();

    public BookFilterCatalog(){
        super();
    }

    public boolean addFilter(BookFilter bf) {
        return bookFilters.add(bf);
    }

    public boolean removeFilter(BookFilter bf) {
        return bookFilters.remove(bf);
    }


    public ArrayList<BookFilter> getBookFilters() {
        return bookFilters;
    }
}
