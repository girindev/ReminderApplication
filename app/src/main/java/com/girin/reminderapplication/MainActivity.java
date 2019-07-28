package com.girin.reminderapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.girin.reminderapplication.adapter.ReminderAdapter;
import com.girin.reminderapplication.model.Reminder;
import com.girin.reminderapplication.view.IMainView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

    private TextView mainContent;
    private RecyclerView reminderList;
    private ReminderAdapter reminderAdapter;
    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton reminderAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mainContent = findViewById(R.id.main_content);
        reminderList = findViewById(R.id.reminder_list);
        reminderAdapter = new ReminderAdapter();
        linearLayoutManager = new LinearLayoutManager(this);
        reminderAdd = findViewById(R.id.reminder_add);

    }

    @Override
    public void setReminder(List<Reminder> reminder) {

    }
}
