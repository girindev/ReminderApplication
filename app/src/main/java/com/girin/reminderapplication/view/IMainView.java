package com.girin.reminderapplication.view;

import com.girin.reminderapplication.model.Reminder;

import java.util.List;

public interface IMainView {
    void setReminder(List<Reminder> reminder);
    void updateAlarmCheck(int result);
}
