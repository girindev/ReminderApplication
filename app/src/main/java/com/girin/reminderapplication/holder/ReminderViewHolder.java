package com.girin.reminderapplication.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.girin.reminderapplication.R;
import com.girin.reminderapplication.listener.OnAlarmChangeListener;
import com.girin.reminderapplication.listener.OnMoveToLeftSwipeListener;
import com.girin.reminderapplication.listener.OnMoveToRightSwipeListener;
import com.girin.reminderapplication.model.Reminder;
import com.girin.reminderapplication.util.Util;

import java.util.Calendar;
import java.util.Date;

import ru.rambler.libs.swipe_layout.SwipeLayout;

import static com.girin.reminderapplication.util.Util.currentTimeDifference;

public class ReminderViewHolder extends RecyclerView.ViewHolder {
    private TextView reminderTitle;
    private TextView reminderContent;
    private ImageButton alarmButton;
    private TextView reminderTime;
    private SwipeLayout swipeLayout;
    private OnAlarmChangeListener onAlarmChangeListener;
    private LinearLayout reminderLayout;
    private Reminder mReminder;
    private OnMoveToRightSwipeListener onMoveToRightSwipeListener;
    private OnMoveToLeftSwipeListener onMoveToLeftSwipeListener;
    private Context context;

    public ReminderViewHolder(@NonNull final View itemView) {
        super(itemView);
        context = itemView.getContext();
        reminderTitle = itemView.findViewById(R.id.reminder_title);
        reminderContent = itemView.findViewById(R.id.reminder_content);
        reminderTime = itemView.findViewById(R.id.reminder_time);
        alarmButton = itemView.findViewById(R.id.alram_ibutton);
        swipeLayout = itemView.findViewById(R.id.swipe_layout);
        reminderLayout = itemView.findViewById(R.id.reminder_layout);

        swipeLayout.setOnSwipeListener(new SwipeLayout.OnSwipeListener() {
            @Override
            public void onBeginSwipe(SwipeLayout swipeLayout, boolean moveToRight) {

            }

            @Override
            public void onSwipeClampReached(SwipeLayout swipeLayout, boolean moveToRight) {
                if (!moveToRight && onMoveToLeftSwipeListener != null) {
                    onMoveToLeftSwipeListener.onSwipeListener(mReminder, getAdapterPosition());
                } else if (moveToRight && onMoveToRightSwipeListener != null) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        onMoveToRightSwipeListener.OnItemClick(pos);
                    }
                }
                swipeLayout.reset();
            }

            @Override
            public void onLeftStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {

            }

            @Override
            public void onRightStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {

            }
        });


        alarmButton.setOnClickListener((View view) -> {
            onAlarmChangeListener.onAlarmCheckClickListener(alarmButton.isSelected(), getAdapterPosition());
        });
    }

    public void setReminder(Reminder mReminder) {
        if (mReminder != null) {
            this.mReminder = mReminder;
            if (mReminder.getTitle() != null) {
                reminderTitle.setText(mReminder.getTitle());
            }
            if (mReminder.getContent() != null) {
                reminderContent.setText(mReminder.getContent());
            }
            if (mReminder.getTitle() != null) {
                reminderTime.setText(mReminder.getTime());
            }
            alarmButton.setSelected(true);
            if (mReminder.getAlertCheck() == 0)
                alarmButton.setSelected(false);

            if (currentTimeDifference(Util.stringToDate(mReminder.getTime()))) {
                reminderLayout.getBackground().setAlpha(120);
            } else {
                reminderLayout.getBackground().setAlpha(0);
            }

        }
    }

    public void setAlarmChangeListsner(OnAlarmChangeListener onAlarmChangeListener) {
        this.onAlarmChangeListener = onAlarmChangeListener;
    }

    public void setOnMoveToRightSwipeListener(OnMoveToRightSwipeListener onMoveToRightSwipeListener) {
        this.onMoveToRightSwipeListener = onMoveToRightSwipeListener;
    }

    public void setOnMoveToLeftSwipeListener(OnMoveToLeftSwipeListener onMoveToLeftSwipeListener) {
        this.onMoveToLeftSwipeListener = onMoveToLeftSwipeListener;
    }

}
