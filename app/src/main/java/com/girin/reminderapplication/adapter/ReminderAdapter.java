package com.girin.reminderapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.girin.reminderapplication.R;
import com.girin.reminderapplication.holder.ReminderViewholder;
import com.girin.reminderapplication.model.Reminder;

import java.util.ArrayList;
import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderViewholder> {
    List<Reminder> reminderList = new ArrayList<>();

    public void addAll(List<Reminder> reminders) {
        reminderList.addAll(reminders);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReminderViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_list_view, parent, false);
        return new ReminderViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewholder holder, int position) {
        holder.setReminder(reminderList.get(position));
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
