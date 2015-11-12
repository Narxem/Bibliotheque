package com.sarah_wissocq_adrien_agez.bibliotheque.book;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by wissocq on 24/09/15.
 */
public class Book implements Serializable {


    private String title;
    private String author;
    private String isbn;
    private String series;
    private int volume;
    private String editor;
    private int editionYear;
    private int pages;
    private String details;
    private Drawable image;



    public Book() {
        super();
    }


    public Book(String title, String author, String isbn, Drawable image, String details){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.image = image;
        this.details = details;
    }

    public Book(String title, String author, String isbn){
        this.title=title;
        this.author=author;
        this.isbn=isbn;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public void setDetails(String details) {
        this.details = details;
    }


}