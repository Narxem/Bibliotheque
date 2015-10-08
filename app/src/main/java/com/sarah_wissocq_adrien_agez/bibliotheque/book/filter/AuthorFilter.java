package com.sarah_wissocq_adrien_agez.bibliotheque.book.filter;

import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;

/**
 * Created by agez on 08/10/15.
 */
public class AuthorFilter implements BookFilter {

    private String author = "";

    public AuthorFilter(String author) {
        this.author = author;
    }

    @Override
    public boolean isSelected(Book book) {
        return book.getAuthor().contains(this.author);
    }
}
