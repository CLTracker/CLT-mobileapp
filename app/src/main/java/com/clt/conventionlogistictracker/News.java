package com.clt.conventionlogistictracker;

/**
 * Created by DemiHe on 4/4/17.
 */

public class News {
    public String title;
    public String text;
    public String author;

    public News(String title, String text, String author) {
        this.title = title;
        this.text = text;
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }
    public String getText() {
        return this.text;
    }
    public String getAuthor() {
        return this.author;
    }

}
