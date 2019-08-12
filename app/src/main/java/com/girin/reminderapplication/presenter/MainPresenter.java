package com.girin.reminderapplication.presenter;

import com.girin.reminderapplication.model.MainModel;
import com.girin.reminderapplication.model.Reminder;
import com.girin.reminderapplication.view.IMainView;

import java.util.List;

public class MainPresenter {
    private IMainView iMainView;
    private MainModel mainModel;

    public MainPresenter(IMainView iMainView) {
        this.iMainView = iMainView;
        mainModel = new MainModel(iMainView);
    }

    public void getReminderFromModel() {
        List<Reminder> reminders = mainModel.getReminderFromDB();
        iMainView.setReminder(reminders);
    }

    public int deleteReminder(int _id) {
        return mainModel.deleteReminder(_id);
    }

    public void updateAlarmCheck(int _id, int check) {
        int result = mainModel.updateAlarmCheck(_id, check);
        iMainView.updateAlarmCheck(result);
    }
}
