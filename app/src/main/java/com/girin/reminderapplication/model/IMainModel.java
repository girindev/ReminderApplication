package com.girin.reminderapplication.model;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.girin.reminderapplication.db.DataBases;
import com.girin.reminderapplication.db.DbOpenHelper;
import com.girin.reminderapplication.view.IMainView;

import java.util.ArrayList;
import java.util.List;

public class IMainModel {
    private DbOpenHelper database;


    public IMainModel(IMainView iMainView) {
        database = new DbOpenHelper((Context) iMainView).open();
    }

    public long insertReminderDataToDB(Reminder reminder) {
        database.open();
        if (reminder != null) {
            String title = reminder.getTitle();
            String content = reminder.getContent();
            String date = reminder.getTime();
            int reminderCheck = reminder.getAlertCheck();
            return this.database.insertColumn(title, content, date, reminderCheck);
        }
        database.close();
        return -1;
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
        if (_id > 0) {
            return this.database.deleteReminder(_id);
        }
        return -1;
    }
}
