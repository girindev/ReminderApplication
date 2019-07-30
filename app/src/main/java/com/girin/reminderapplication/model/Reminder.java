package com.girin.reminderapplication.model;

public class Reminder {
    private String title;
    private String content;
    private String time;
    private int alertCheck;
    private int _id;

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

    public void setAlertCheck(int alertCheck) {
        this.alertCheck = alertCheck;
    }

    public int getAlertCheck() {
        return alertCheck;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
