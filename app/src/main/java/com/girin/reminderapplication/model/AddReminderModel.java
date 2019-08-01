package com.girin.reminderapplication.model;

import android.content.Context;

import com.girin.reminderapplication.db.DbOpenHelper;
import com.girin.reminderapplication.view.IAddReminderView;

public class AddReminderModel {

    private DbOpenHelper database;

    public AddReminderModel(IAddReminderView iAddReminderView) {
        database = new DbOpenHelper((Context) iAddReminderView).open();

    }

    public long insertIntoReminder(Reminder reminder) {
        database.open();
        long result = database.insertReminder(reminder.getTitle()
                , reminder.getContent()
                , reminder.getTime()
                , reminder.getAlertCheck());
        database.close();
        return result;
    }
}
