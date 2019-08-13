package com.girin.reminderapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Reminder implements Parcelable {
    private String title;
    private String content;
    private String time;
    private int alertCheck;
    private int _id;
    private long milli_second;

    public Reminder() {
    }

    protected Reminder(Parcel in) {
        title = in.readString();
        content = in.readString();
        time = in.readString();
        alertCheck = in.readInt();
        _id = in.readInt();
        milli_second = in.readInt();
    }

    public static final Creator<Reminder> CREATOR = new Creator<Reminder>() {
        @Override
        public Reminder createFromParcel(Parcel in) {
            return new Reminder(in);
        }

        @Override
        public Reminder[] newArray(int size) {
            return new Reminder[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(time);
        parcel.writeInt(alertCheck);
        parcel.writeInt(_id);
        parcel.writeLong(milli_second);
    }

    public long getMilli_second() {
        return milli_second;
    }

    public void setMilli_second(long milli_second) {
        this.milli_second = milli_second;
    }
}
