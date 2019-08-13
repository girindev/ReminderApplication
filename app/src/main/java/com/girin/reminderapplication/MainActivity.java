package com.girin.reminderapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.girin.reminderapplication.adapter.ReminderAdapter;
import com.girin.reminderapplication.model.Reminder;
import com.girin.reminderapplication.presenter.MainPresenter;
import com.girin.reminderapplication.view.IMainView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static com.girin.reminderapplication.util.Util.addAlarm;

public class MainActivity extends AppCompatActivity implements IMainView {

    private TextView mainContent;
    private RecyclerView reminderList;
    private ReminderAdapter reminderAdapter;
    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton reminderAdd;
    private MainPresenter mainPresenter;
    public static final int ADD_REMINDER_REQUEST_CODE = 3001;
    public static final int UPDATE_REMINDER_REQUEST_CODE = 3002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mainPresenter.getReminderFromModel();

        /**Call to Add reminder Activity*/
        reminderAdd.setOnClickListener((View view) -> {
            Intent intent = new Intent(MainActivity.this, AddReminderActivity.class);
            startActivityForResult(intent, ADD_REMINDER_REQUEST_CODE);
        });
        reminderAdapter.setSwipeChangeListener((Reminder reminder, int position) -> {
            int result = mainPresenter.deleteReminder(reminder.get_id());
            if (result > 0) {
                mainPresenter.getReminderFromModel();
            }
        });

        reminderAdapter.setOnMoveToRightSwipeListener((int position) -> {
            Reminder reminder = reminderAdapter.getItem(position);
            Intent intent = new Intent(MainActivity.this, UpdateReminderActivity.class);
            intent.putExtra("Reminder", reminder);
            startActivityForResult(intent, UPDATE_REMINDER_REQUEST_CODE);
        });

        reminderAdapter.setOnAlarmChangeListener((boolean isState, int position) -> {
            int isCheck = 0;
            Reminder reminder = reminderAdapter.getItem(position);
            if (isState == false) {
                addAlarm(reminder.get_id(), reminder.getTime(), MainActivity.this);
                isCheck = 1;
            }
            //progress 보여주고
            mainPresenter.updateAlarmCheck(reminder.get_id(), isCheck);
            //progress 닫고
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
    public void updateAlarmCheck(int result) {
        if (result > 0)
            mainPresenter.getReminderFromModel();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_REMINDER_REQUEST_CODE) {
                long result = data.getLongExtra("result", -2);
                if (result > 0) {
                    mainPresenter.getReminderFromModel();
                } else if (result == -1) {
                    Snackbar.make(findViewById(R.id.main_layout), "등록에 실패했습니다.", 3000).show();
                } else {
                    Log.d("MAIN ACTIVITY", "Fail to load intent data");
                }
            } else if (requestCode == UPDATE_REMINDER_REQUEST_CODE) {
                long result = data.getLongExtra("result", -2);
                if (result > 0) {
                    mainPresenter.getReminderFromModel();
                } else if (result == -1) {
                    Snackbar.make(findViewById(R.id.main_layout), "데이터 갱신에 실패했습니다.", 3000).show();
                } else {
                    Log.d("MAIN ACTIVITY", "Fail to load intent data");
                }
            }
        }
    }
}
