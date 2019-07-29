package com.girin.reminderapplication.model;

public class Reminder {
    private String title;
    private String content;
    private String time;
    private int alertCheck;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int isAlertCheck() {
        return alertCheck;
    }

    public void setAlertCheck(int alertCheck) {
        this.alertCheck = alertCheck;
    }
}
