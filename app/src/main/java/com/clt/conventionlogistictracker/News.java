package com.clt.conventionlogistictracker;

/**
 * Created by DemiHe on 4/4/17.
 */

public class News {
    public final String title;
    public final String announcement;

    public News(String title, String announcement) {
        this.title = title;
        this.announcement = announcement;
    }

    public String getTitle() {
        return this.title;
    }
    public String getAnnouncement() {
        return this.announcement;
    }

}
