package com.girin.reminderapplication.model;

import java.util.Date;

public class Reminder {
    private String title;
    private String content;
    private Date time;
    private boolean alertCheck;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean isAlertCheck() {
        return alertCheck;
    }

    public void setAlertCheck(boolean alertCheck) {
        this.alertCheck = alertCheck;
    }
}
