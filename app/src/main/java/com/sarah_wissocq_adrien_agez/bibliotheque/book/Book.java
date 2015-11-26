package com.sarah_wissocq_adrien_agez.bibliotheque.book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.sarah_wissocq_adrien_agez.bibliotheque.book.database.BookDatabaseHelper.TABLE_BOOKS;

/**
 * Created by wissocq on 24/09/15.
 */
public class Book implements Serializable {

    private String title;
    private List<String> authors;
    private String series;
    private int numSeries;
    private String editor;
    private int year;
    private String isbn;
    private String coverURI;
    private String summary;
    private List<String> tags;


    public Book() {
        super();
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(String title, String series, int numSeries, String editor, int year, String isbn, String coverURI, String summary, List<String> authors){
        this.title=title;
        this.series=series;
        this.numSeries=numSeries;
        this.editor=editor;
        this.year=year;
        this.isbn=isbn;
        this.coverURI=coverURI;
        this.summary=summary;
        this.authors=authors;
        this.tags=new ArrayList<String>();
    }

    public Book(String title, List<String> authors) {
        this(title);
        this.authors = authors;
        this.series="";
        this.numSeries=-1;
        this.editor="";
        this.year=-1;
        this.isbn="";
        this.coverURI="";
        this.summary="";
        this.tags=new ArrayList<String>();
    }

    public Book(String title, List<String> authors, String isbn) {
        this(title, authors);
        this.isbn = isbn;
        this.series = "";
        this.numSeries = -1;
        this.editor = "";
        this.year = -1;
        this.coverURI = "";
        this.summary = "";
        this.tags = new ArrayList<String>();
    }

    // GETTERS AND SETTERS
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setAuthors(String ... authors) {
        this.authors = new ArrayList<String>();
        for (String author : authors) {
            this.authors.add(author);
        }
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getNumSeries() {
        return numSeries;
    }

    public void setNumSeries(int numSeries) {
        this.numSeries = numSeries;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCoverURI() {
        return coverURI;
    }

    public void setCoverURI(String coverURI) {
        this.coverURI = coverURI;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setTags(String ... tags) {
        this.tags = new ArrayList<String>();
        for (String tag : tags) {
            this.tags.add(tag);
        }
    }
}