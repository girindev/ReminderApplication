package com.girin.reminderapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.girin.reminderapplication.model.Reminder;
import com.girin.reminderapplication.presenter.UpdateReminderPresenter;
import com.girin.reminderapplication.view.IUpdateReminderView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;

public class UpdateReminderActivity extends AppCompatActivity implements IUpdateReminderView {

    private EditText reminderTitle, reminderContent;
    private TextView reminderDate;
    private ImageButton reminderCheck;
    private Button btnUpdate;
    private DatePickerDialog datePickerDialog;
    private int year, month, date, hour, minute;
    private TimePickerDialog timePickerDialog;
    private UpdateReminderPresenter updateReminderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_reminder);
        initView();
        Reminder reminder = getIntent().getParcelableExtra("Reminder");
        reminderTitle.setText(reminder.getTitle());
        reminderContent.setText(reminder.getContent());
        reminderDate.setText(reminder.getTime());
        if (reminder.getAlertCheck() == 1)
            reminderCheck.setSelected(true);


        /**Date Click*/
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        date = calendar.get(Calendar.DATE);

        timePickerDialog = new TimePickerDialog(UpdateReminderActivity.this, R.style.AddReminderDatePicker,
                (TimePicker timePicker, int i, int i1) -> {
                    hour = i;
                    minute = i1;
                    StringBuilder stringBuilder = new StringBuilder(year + ".");

                    if (month < 10)
                        stringBuilder.append("0");
                    stringBuilder.append(month + ".");
                    if (date < 10)
                        stringBuilder.append("0");
                    stringBuilder.append(date + " ");
                    if (hour < 10)
                        stringBuilder.append("0");
                    stringBuilder.append(hour + ":");
                    if (minute < 10)
                        stringBuilder.append("0");
                    stringBuilder.append(minute);
                    reminderDate.setText(stringBuilder.toString());

                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

        datePickerDialog = new DatePickerDialog(UpdateReminderActivity.this, R.style.AddReminderDatePicker,
                (DatePicker datePicker, int y, int m, int d) -> {
                    year = y;
                    month = m + 1;
                    date = d;
                    timePickerDialog.show();
                }, year, month, date);
        datePickerDialog.getDatePicker().setMinDate(new Date().getTime());

        reminderDate.setOnClickListener((View view) -> datePickerDialog.show());

        reminderCheck.setOnClickListener((View view) -> switchAlarmCheck());


        btnUpdate.setOnClickListener((View view) -> {
            if (reminderTitle != null && reminderContent != null && reminderCheck != null && reminderDate != null) {
                if (!reminderTitle.getText().toString().isEmpty() && !reminderDate.getText().toString().equals("날짜 선택")) {
                    reminder.setTitle(reminderTitle.getText().toString());
                    reminder.setContent("");
                    if (!reminderContent.getText().toString().equals(getResources().getString(R.string.add_reminder_content)))
                        reminder.setContent(reminderContent.getText().toString());
                    reminder.setTime(reminderDate.getText().toString());
                    reminder.setAlertCheck(0);
                    if (reminderCheck.isSelected())
                        reminder.setAlertCheck(1);
                    updateReminderPresenter.reminderUpdate(reminder);
                } else
                    Snackbar.make(findViewById(R.id.update_reminder_layout), getResources().getString(R.string.add_reminder_error), 2000).show();
            }
        });
    }

    private void initView() {
        reminderTitle = findViewById(R.id.et_reminder_title);
        reminderContent = findViewById(R.id.et_reminder_content);
        reminderDate = findViewById(R.id.tv_pick_date);
        reminderCheck = findViewById(R.id.alarm_ibutton);
        btnUpdate = findViewById(R.id.btn_update_reminder);
        updateReminderPresenter = new UpdateReminderPresenter(UpdateReminderActivity.this);
    }

    private void switchAlarmCheck() {
        reminderCheck.setSelected(!reminderCheck.isSelected());
    }

    @Override
    public void reminderUpdate(long result) {
        if (result > 0) {
            Intent intent = new Intent();
            intent.putExtra("result", result);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
