package com.girin.reminderapplication.presenter;

import com.girin.reminderapplication.model.UpdateReminderModel;
import com.girin.reminderapplication.model.Reminder;
import com.girin.reminderapplication.view.IUpdateReminderView;

public class UpdateReminderPresenter {

    private UpdateReminderModel updateReminderModel;
    private IUpdateReminderView iUpdateReminderView;

    public UpdateReminderPresenter(IUpdateReminderView iUpdateReminderView) {
        this.iUpdateReminderView = iUpdateReminderView;
        updateReminderModel = new UpdateReminderModel(iUpdateReminderView);
    }

    public void reminderUpdate(Reminder reminder) {
        long result = updateReminderModel.reminderUpdate(reminder);
        iUpdateReminderView.reminderUpdate(result);
    }
}
