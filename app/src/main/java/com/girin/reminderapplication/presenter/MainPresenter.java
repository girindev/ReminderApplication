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

}