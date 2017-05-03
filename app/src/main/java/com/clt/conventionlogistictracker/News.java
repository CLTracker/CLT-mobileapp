package com.clt.conventionlogistictracker;

/**
 * Created by DemiHe on 4/4/17.
 */

public class News {
    public String title;
    public String text;
    public String author;
    public String logo_url;

    public News(String title, String text, String author, String logo_url) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.logo_url = logo_url;
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
    public String getLogoUrl() {
        return this.logo_url;
    }

}
