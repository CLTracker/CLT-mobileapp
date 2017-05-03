package com.clt.conventionlogistictracker;

import android.util.Log;

/**
 * Created by dongw on 4/4/17.
 */

public class Exhibitor {
    public String company_name;
    public String logo_url;
    public String conference;
    public String bio;
//
//
    public Exhibitor(String company_name, String logo_url, String bio) {
        this.company_name = company_name;
        this.logo_url = logo_url;
        this.bio = bio;
        //this.conference = 1;
    }

    public void setCompanyName(String company_name) {
        this.company_name = company_name;
    }
    public String getCompanyName() {
        return this.company_name;
    }

    public void setLogoUrl(String logo_url) {
        this.logo_url = logo_url;
    }
    public String getLogoUrl() {
        return this.logo_url;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }
    public String getConference() {
        return this.conference;
    }

    public void printOut(){
        Log.d(company_name, conference);
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    public String getBio() {
        return this.bio;
    }


}
