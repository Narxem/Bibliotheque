package com.sarah_wissocq_adrien_agez.bibliotheque.book;

import android.graphics.drawable.Drawable;

/**
 * Created by wissocq on 24/09/15.
 */
public class Book {

    protected String author;
    protected String title;
    protected String isbn;
    protected String img;
    protected Drawable image;

    public Book() {
        super();
    }

    public Book(String author, String title, String isbn) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
    }

    /**
     * Create a book
     * @param author author of the book
     * @param title title of the book
     * @param isbn isbn of the book
     * @param img image of the book
     */
    public Book(String author, String title, String isbn, Drawable img) {
        this(author, title, isbn);
        this.image = img;
    }

    @Deprecated
    public Book(String author, String title, String isbn, String img) { // TODO Vérifier création de l'image
        this(author, title, isbn);
        Drawable d = Drawable.createFromPath(img);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
