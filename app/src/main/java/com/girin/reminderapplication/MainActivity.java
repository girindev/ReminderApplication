package com.girin.reminderapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.girin.reminderapplication.adapter.ReminderAdapter;
import com.girin.reminderapplication.model.Reminder;
import com.girin.reminderapplication.presenter.MainPresenter;
import com.girin.reminderapplication.view.IMainView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

    private TextView mainContent;
    private RecyclerView reminderList;
    private ReminderAdapter reminderAdapter;
    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton reminderAdd;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mainPresenter.getReminderFromModel();

        reminderAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reminder reminder = new Reminder();
                reminder.setTitle("테스트입니다.");
                reminder.setContent("처음으로 작성된 내용입니다.");
                reminder.setTime("2019.09.20 14:00");
                reminder.setAlertCheck(0);
                mainPresenter.insertIntoDB(reminder);
            }
        });

        reminderAdapter.setSwipeChangeListener(new ReminderAdapter.OnSwipeChangeListener() {
            @Override
            public void onSwipeListener(Reminder reminder, int position) {
                int result = mainPresenter.deleteReminder(reminder.get_id());
                Toast.makeText(MainActivity.this, "delete reminder result : " + result, Toast.LENGTH_SHORT).show();
                if (result > 0) {
                    mainPresenter.getReminderFromModel();
                }
            }
        });
    }

    private void initView() {
        mainContent = findViewById(R.id.main_content);
        reminderList = findViewById(R.id.reminder_list);
        reminderAdapter = new ReminderAdapter();
        linearLayoutManager = new LinearLayoutManager(this);
        reminderAdd = findViewById(R.id.reminder_add);
        mainPresenter = new MainPresenter(MainActivity.this);
        reminderList.setAdapter(reminderAdapter);
        reminderList.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void setReminder(List<Reminder> reminder) {
        reminderAdapter.clear();
        reminderAdapter.addAll(reminder);
        if (reminderAdapter.getItemCount() > 0) {
            mainContent.setVisibility(View.GONE);
        } else
            mainContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void reminderInsertIntoDB(long result) {
        Toast.makeText(this, "" + result, Toast.LENGTH_SHORT).show();
        if (result > 0) {
            mainPresenter.getReminderFromModel();
        }
    }
}
