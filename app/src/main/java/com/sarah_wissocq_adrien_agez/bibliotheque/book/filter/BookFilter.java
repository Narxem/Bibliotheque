package com.sarah_wissocq_adrien_agez.bibliotheque.book.filter;

import com.sarah_wissocq_adrien_agez.bibliotheque.book.Book;

import java.util.ArrayList;

/**
 * Created by wissocq on 07/10/15.
 */
public interface BookFilter {

    public boolean isSelected(Book book);
}
