package com.girin.reminderapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.girin.reminderapplication.R;
import com.girin.reminderapplication.holder.ReminderViewHolder;
import com.girin.reminderapplication.listener.OnAlarmChangeListener;
import com.girin.reminderapplication.listener.OnMoveToLeftSwipeListener;
import com.girin.reminderapplication.listener.OnMoveToRightSwipeListener;
import com.girin.reminderapplication.model.Reminder;

import java.util.ArrayList;
import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderViewHolder> {
    List<Reminder> reminderList = new ArrayList<>();

    public void addAll(List<Reminder> reminders) {
        reminderList.addAll(reminders);
        notifyDataSetChanged();
    }

    private OnAlarmChangeListener onAlarmChangeListener;

    public void setOnAlarmChangeListener(OnAlarmChangeListener onAlarmChangeListener) {
        this.onAlarmChangeListener = onAlarmChangeListener;
    }

    public OnMoveToLeftSwipeListener onMoveToLeftSwipeListener = null;

    public void setSwipeChangeListener(OnMoveToLeftSwipeListener onMoveToLeftSwipeListener) {
        this.onMoveToLeftSwipeListener = onMoveToLeftSwipeListener;
    }


    private OnMoveToRightSwipeListener onMoveToRightSwipeListener;

    public void setOnMoveToRightSwipeListener(OnMoveToRightSwipeListener onMoveToRightSwipeListener) {
        this.onMoveToRightSwipeListener = onMoveToRightSwipeListener;
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_list_view, parent, false);
        return new ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        holder.setReminder(reminderList.get(position));
        holder.setAlarmChangeListsner(onAlarmChangeListener);
        holder.setOnMoveToRightSwipeListener(onMoveToRightSwipeListener);
        holder.setOnMoveToLeftSwipeListener(onMoveToLeftSwipeListener);
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    public Reminder getItem(int position) {
        return reminderList.get(position);
    }

    public void clear() {
        reminderList.clear();
        notifyDataSetChanged();
    }
}
