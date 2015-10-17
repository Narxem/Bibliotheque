package com.sarah_wissocq_adrien_agez.bibliotheque.book;

import android.graphics.drawable.Drawable;

/**
 * Created by wissocq on 24/09/15.
 */
public class Book {


    private String title;
    private String author;
    private String ISBN;
    private Drawable image;
    private String details;

    public Book() {
        super();
    }

    public Book(String title, String author, String ISBN, Drawable image, String details){
        this.title=title;
        this.author=author;
        this.ISBN=ISBN;
        this.image=image;
        this.details=details;
    }

    public Book(String title, String author, String ISBN){
        this.title=title;
        this.author=author;
        this.ISBN=ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


}