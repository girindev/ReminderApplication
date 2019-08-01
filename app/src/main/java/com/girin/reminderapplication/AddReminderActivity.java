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

import androidx.appcompat.app.AppCompatActivity;

import com.girin.reminderapplication.model.Reminder;
import com.girin.reminderapplication.presenter.AddReminderPresenter;
import com.girin.reminderapplication.view.IAddReminderView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;

public class AddReminderActivity extends AppCompatActivity implements IAddReminderView {
    private EditText reminderTitle, reminderContent;
    private ImageButton alarmCheck;
    private TextView tvPickDate;
    private DatePickerDialog datePickerDialog;
    private Button btnAddReminder;
    private int year, month, date, hour, minute;
    private TimePickerDialog timePickerDialog;
    private AddReminderPresenter addReminderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        initView();

        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        date = calendar.get(Calendar.DATE);

        timePickerDialog = new TimePickerDialog(AddReminderActivity.this, R.style.AddReminderDatePicker, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
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
                tvPickDate.setText(stringBuilder.toString());
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

        datePickerDialog = new DatePickerDialog(AddReminderActivity.this, R.style.AddReminderDatePicker, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                year = y;
                month = m + 1;
                date = d;
                timePickerDialog.show();
            }
        }, year, month, date);
        datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
        tvPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        alarmCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmCheck.setSelected(!alarmCheck.isSelected());
            }
        });

        btnAddReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reminderTitle != null && reminderContent != null && alarmCheck != null && tvPickDate != null) {
                    if (!reminderTitle.getText().toString().isEmpty() && !tvPickDate.getText().toString().equals("날짜 선택")) {
                        Reminder reminder = new Reminder();
                        reminder.setTitle(reminderTitle.getText().toString());
                        reminder.setContent("");
                        if (!reminderContent.getText().toString().equals(getResources().getString(R.string.add_reminder_content)))
                            reminder.setContent(reminderContent.getText().toString());
                        reminder.setTime(tvPickDate.getText().toString());
                        reminder.setAlertCheck(0);
                        if (alarmCheck.isSelected())
                            reminder.setAlertCheck(1);
                        addReminderPresenter.addReminderToDb(reminder);
                    } else
                        Snackbar.make(findViewById(R.id.add_reminder_layout), getResources().getString(R.string.add_reminder_error), 2000).show();
                }
            }
        });
    }

    private void initView() {
        reminderTitle = findViewById(R.id.et_reminder_title);
        reminderContent = findViewById(R.id.et_reminder_content);
        alarmCheck = findViewById(R.id.alarm_ibutton);
        tvPickDate = findViewById(R.id.tv_pick_date);
        btnAddReminder = findViewById(R.id.btn_add_reminder);
        addReminderPresenter = new AddReminderPresenter(this);
    }

    @Override
    public void addreminderToDb(long result) {
        Intent intent = new Intent();
        //Intent return success
        intent.putExtra("result", result);
        setResult(RESULT_OK, intent);
        finish();

    }
}
