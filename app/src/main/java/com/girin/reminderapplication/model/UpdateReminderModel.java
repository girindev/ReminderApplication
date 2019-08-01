package com.girin.reminderapplication.model;

import android.content.Context;

import com.girin.reminderapplication.db.DbOpenHelper;
import com.girin.reminderapplication.view.IUpdateReminderView;

public class UpdateReminderModel {

    private DbOpenHelper database;

    public UpdateReminderModel(IUpdateReminderView iUpdateReminderView) {
        database = new DbOpenHelper((Context) iUpdateReminderView).open();
    }

    public long reminderUpdate(Reminder reminder) {
        database.open();
        if (reminder != null) {
            long result = database.updateReminder(reminder);
            database.close();
            return result;
        }
        database.close();
        return -1;
    }
}
