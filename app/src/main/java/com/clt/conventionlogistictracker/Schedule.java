package com.clt.conventionlogistictracker;

import java.util.Date;

/**
 * Created by DemiHe on 4/25/17.
 */

public class Schedule {
    public String start_time;
    public String end_time;
    public String event_name;

    public Schedule(String start_time, String event_name, String end_time) {
        this.start_time = start_time;
        this.event_name = event_name;
        this.end_time = end_time;
    }

    public String getStartTime() {
        return start_time;
    }
    public void setStartTime(String start_time) {
        this.start_time = start_time;
    }

    public String getEndTime() {
        return end_time;
    }
    public void setEndTime(String end_time) {
        this.end_time = end_time;
    }

    public String getEventName() {
        return event_name;
    }
    public void setEventName(String event_name) {
        this.event_name = event_name;
    }
}
