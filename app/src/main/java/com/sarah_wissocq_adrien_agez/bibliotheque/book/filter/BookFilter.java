package com.sarah_wissocq_adrien_agez.bibliotheque.book.filter;

import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wissocq on 07/10/15.
 */
public abstract class BookFilter {

    public abstract boolean isSelected(Book book);

    public List<Book> filter(List<Book> list) {
        List<Book> filteredList = new LinkedList<Book>();
        for (Book book : list) {
            if (isSelected(book))
                filteredList.add(book);
        }
        return filteredList;
    }
}
