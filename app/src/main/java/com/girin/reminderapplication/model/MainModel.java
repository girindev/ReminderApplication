package com.girin.reminderapplication.model;

import android.content.Context;
import android.database.Cursor;

import com.girin.reminderapplication.db.DataBases;
import com.girin.reminderapplication.db.DbOpenHelper;
import com.girin.reminderapplication.view.IMainView;

import java.util.ArrayList;
import java.util.List;

public class MainModel {
    private DbOpenHelper database;

    public MainModel(IMainView iMainView) {
        database = new DbOpenHelper((Context) iMainView).open();
    }

    public List<Reminder> getReminderFromDB() {
        database.open();
        List<Reminder> reminders = new ArrayList<>();
        Cursor cursor = this.database.selectColumns();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Reminder reminder = new Reminder();
            reminder.setTitle(cursor.getString(cursor.getColumnIndex(DataBases.CreateDB.TITLE)));
            reminder.setContent(cursor.getString(cursor.getColumnIndex(DataBases.CreateDB.CONTENT)));
            reminder.setTime(cursor.getString(cursor.getColumnIndex(DataBases.CreateDB.DATE)));
            reminder.setAlertCheck(cursor.getInt(cursor.getColumnIndex(DataBases.CreateDB.ALRAM_CHECK)));
            reminder.set_id(cursor.getInt(cursor.getColumnIndex(DataBases.CreateDB._ID)));
            reminders.add(reminder);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return reminders;
    }

    public int deleteReminder(int _id) {
        database.open();
        int result = database.deleteReminder(_id);
        database.close();
        return result;
    }

    public int updateAlarmCheck(int _id, int check) {
        database.open();
        int result = database.updateAlarmCheck(_id, check);
        database.close();
        return result;
    }
}
