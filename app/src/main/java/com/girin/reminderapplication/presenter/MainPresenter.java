package com.girin.reminderapplication.presenter;

import com.girin.reminderapplication.model.IMainModel;
import com.girin.reminderapplication.model.Reminder;
import com.girin.reminderapplication.view.IMainView;

import java.util.List;

public class MainPresenter {
    private IMainView iMainView;
    private IMainModel iMainModel;

    public MainPresenter(IMainView iMainView) {
        this.iMainView = iMainView;
        iMainModel = new IMainModel(iMainView);
    }

    public void getReminderFromModel() {
        List<Reminder> reminders = iMainModel.getReminderFromDB();
        iMainView.setReminder(reminders);
    }

    public void insertIntoDB(Reminder reminder) {
        long result = iMainModel.insertReminderDataToDB(reminder);
        iMainView.reminderInsertIntoDB(result);
    }

    public int deleteReminder(int _id) {
        return iMainModel.deleteReminder(_id);
    }
}
