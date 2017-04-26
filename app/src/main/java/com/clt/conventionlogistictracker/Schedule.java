package com.clt.conventionlogistictracker;

import java.util.Date;

/**
 * Created by DemiHe on 4/25/17.
 */

public class Schedule {
    public Date startTime;
    public Date endTime;
    public String eventName;

    public Schedule(Date startTime, String eventName, Date endTime) {
        this.startTime = startTime;
        this.eventName = eventName;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
