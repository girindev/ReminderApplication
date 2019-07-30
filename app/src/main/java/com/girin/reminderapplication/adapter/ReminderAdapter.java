package com.girin.reminderapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.girin.reminderapplication.R;
import com.girin.reminderapplication.holder.ReminderViewHolder;
import com.girin.reminderapplication.model.Reminder;

import java.util.ArrayList;
import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderViewHolder> {
    List<Reminder> reminderList = new ArrayList<>();

    public void addAll(List<Reminder> reminders) {
        reminderList.addAll(reminders);
        notifyDataSetChanged();
    }

    public interface OnSwipeChangeListener {
        void onSwipeListener(Reminder reminder, int position);
    }

    public OnSwipeChangeListener onSwipeChangeListener = null;

    public void setSwipeChangeListener(OnSwipeChangeListener onSwipeChangeListener) {
        this.onSwipeChangeListener = onSwipeChangeListener;
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_list_view, parent, false);
        return new ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        holder.setmReminder(reminderList.get(position));
        holder.setOnSwipeChangeListsner(onSwipeChangeListener);
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    public void clear() {
        reminderList.clear();
        notifyDataSetChanged();
    }
}
