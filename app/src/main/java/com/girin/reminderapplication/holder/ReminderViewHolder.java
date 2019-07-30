package com.girin.reminderapplication.holder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.girin.reminderapplication.R;
import com.girin.reminderapplication.adapter.ReminderAdapter;
import com.girin.reminderapplication.model.Reminder;

import ru.rambler.libs.swipe_layout.SwipeLayout;

public class ReminderViewHolder extends RecyclerView.ViewHolder {
    private TextView reminderTitle;
    private TextView reminderContent;
    private ImageButton alramButton;
    private TextView reminderTime;
    private SwipeLayout swipeLayout;
    private ReminderAdapter.OnSwipeChangeListener mOnSwipeChangeListsner;
    private Reminder mReminder;

    public ReminderViewHolder(@NonNull final View itemView) {
        super(itemView);
        reminderTitle = itemView.findViewById(R.id.reminder_title);
        reminderContent = itemView.findViewById(R.id.reminder_content);
        reminderTime = itemView.findViewById(R.id.reminder_time);
        alramButton = itemView.findViewById(R.id.alram_ibutton);
        swipeLayout = itemView.findViewById(R.id.swipe_layout);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    Toast.makeText(itemView.getContext(), "ReminderViewHolder item on click : " + mReminder.get_id(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        swipeLayout.setOnSwipeListener(new SwipeLayout.OnSwipeListener() {
            @Override
            public void onBeginSwipe(SwipeLayout swipeLayout, boolean moveToRight) {

            }

            @Override
            public void onSwipeClampReached(SwipeLayout swipeLayout, boolean moveToRight) {
                if (mOnSwipeChangeListsner != null) {
                    mOnSwipeChangeListsner.onSwipeListener(mReminder, getAdapterPosition());
                    swipeLayout.reset();
                }
            }

            @Override
            public void onLeftStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {

            }

            @Override
            public void onRightStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {

            }
        });


        //TODO alamButton onClick 구현
        alramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void setmReminder(Reminder mReminder) {
        if (mReminder != null) {
            this.mReminder = mReminder;
            if (mReminder.getTitle() != null) {
                reminderTitle.setText(mReminder.getTitle());
            }
            if (mReminder.getContent() != null) {
                reminderContent.setText(mReminder.getContent());
            }
            if (mReminder.getTitle() != null) {
                reminderTime.setText(mReminder.getTitle());
            }
        }
    }

    //버튼을 클릭했을 때 현재 상태에 따라서 이미지 변경
    public void changeImageButton() {
        //TODO: db에 상태 저장하기
        if (mReminder.getAlertCheck() == 0) {
            mReminder.setAlertCheck(1);
        } else {
            mReminder.setAlertCheck(0);
        }
    }

    public void setOnSwipeChangeListsner(ReminderAdapter.OnSwipeChangeListener onSwipeChangeListsner) {
        this.mOnSwipeChangeListsner = onSwipeChangeListsner;
    }
}
