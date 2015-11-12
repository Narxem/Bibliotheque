package com.sarah_wissocq_adrien_agez.bibliotheque.book.filter;

import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;

/**
 * Created by agez on 08/10/15.
 */
public class TitleFilter extends BookFilter {

    private String title = "";

    public TitleFilter(String title) {
        this.title = title;
    }

    @Override
    public boolean isSelected(Book book) {
        return book.getTitle().contains(this.title);
    }
}
