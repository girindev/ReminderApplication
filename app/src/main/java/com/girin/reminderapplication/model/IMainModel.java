package com.girin.reminderapplication.model;

import android.content.Context;
import android.database.Cursor;

import com.girin.reminderapplication.db.DataBases;
import com.girin.reminderapplication.db.DbOpenHelper;
import com.girin.reminderapplication.view.IMainView;

import java.util.ArrayList;
import java.util.List;

public class IMainModel {
    private DbOpenHelper database;


    public IMainModel(IMainView iMainView) {
        database = new DbOpenHelper((Context)iMainView).open();
    }

    public List<Reminder> getReminderFromDB() {
        List<Reminder> reminders = new ArrayList<>();
        Cursor cursor = this.database.selectColumns();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Reminder reminder = new Reminder();
            reminder.setTitle(cursor.getString(cursor.getColumnIndex(DataBases.CreateDB.TITLE)));
            reminder.setContent(cursor.getString(cursor.getColumnIndex(DataBases.CreateDB.CONTENT)));
            reminder.setTime(cursor.getString(cursor.getColumnIndex(DataBases.CreateDB.DATE)));
            reminder.setAlertCheck(cursor.getInt(cursor.getColumnIndex(DataBases.CreateDB.ALRAM_CHECK)));
            reminders.add(reminder);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return reminders;
    }
}
