package com.girin.reminderapplication.holder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.girin.reminderapplication.R;
import com.girin.reminderapplication.model.Reminder;

public class ReminderViewholder extends RecyclerView.ViewHolder {
    private TextView reminderTitle;
    private TextView reminderContent;
    private ImageButton alramButton;
    private TextView reminderTime;
    private int alertStatus = 0;

    public ReminderViewholder(@NonNull View itemView) {
        super(itemView);
        reminderTitle = itemView.findViewById(R.id.reminder_title);
        reminderContent = itemView.findViewById(R.id.reminder_content);
        reminderTime = itemView.findViewById(R.id.reminder_time);
        alramButton = itemView.findViewById(R.id.alram_ibutton);

        //TODO alamButton onClick 구현
    }

    public void setReminder(Reminder reminder) {
        if (reminder != null) {
            if (reminder.getTitle() != null) {
                reminderTitle.setText(reminder.getTitle());
            }
            if (reminder.getContent() != null) {
                reminderContent.setText(reminder.getContent());
            }
            if (reminder.getTitle() != null) {
                reminderTime.setText(reminder.getTitle());
            }
            alertStatus = reminder.isAlertCheck();
        }
    }

    //버튼을 클릭했을 때 현재 상태에 따라서 이미지 변경
    public void changeImageButton() {
        //TODO: db에 상태 저장하기
        if (alertStatus == 0) {
            alertStatus = 1;
        } else {
            alertStatus = 0;
        }
    }
}
