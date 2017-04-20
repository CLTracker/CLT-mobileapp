package com.clt.conventionlogistictracker;

/**
 * Created by DemiHe on 4/4/17.
 */

public class News {
    public String title;
    public String text;

    public News(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return this.title;
    }
    public String getText() {
        return this.text;
    }

}
