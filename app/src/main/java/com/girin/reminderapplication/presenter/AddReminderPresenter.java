package com.girin.reminderapplication.presenter;

import com.girin.reminderapplication.model.AddReminderModel;
import com.girin.reminderapplication.model.Reminder;
import com.girin.reminderapplication.view.IAddReminderView;

public class AddReminderPresenter {

    private AddReminderModel addReminderModel;
    private IAddReminderView iAddReminderView;

    public AddReminderPresenter(IAddReminderView iAddReminderView) {
        addReminderModel = new AddReminderModel(iAddReminderView);
        this.iAddReminderView = iAddReminderView;
    }

    public void addReminderToDb(Reminder reminder) {
        if (reminder != null)
            iAddReminderView.addreminderToDb(addReminderModel.insertIntoReminder(reminder));
    }
}
