package com.girin.reminderapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;

import com.girin.reminderapplication.db.DataBases;
import com.girin.reminderapplication.db.DbOpenHelper;
import com.girin.reminderapplication.model.Reminder;

import static android.graphics.Color.GREEN;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //channel id 생성
        // id 생성 with intent
        // db에서 reminder 가져오기
        // notification 생성
        int id = intent.getIntExtra("id", -1);
        int channelId = id;

        if (id == -1)
            return;
        DbOpenHelper dbOpenHelper = new DbOpenHelper(context);
        dbOpenHelper.open();
        Cursor cursor = dbOpenHelper.selectColumn(id);

        Reminder reminder = new Reminder();

        if (!cursor.moveToFirst()) {
            cursor.close();
            dbOpenHelper.close();
            return;
        }

        reminder.setTitle(cursor.getString(cursor.getColumnIndex(DataBases.CreateDB.TITLE)));
        reminder.setContent(cursor.getString(cursor.getColumnIndex(DataBases.CreateDB.CONTENT)));
        reminder.setAlertCheck(cursor.getInt(cursor.getColumnIndex(DataBases.CreateDB.ALRAM_CHECK)));

        if (reminder.getAlertCheck() == 0) {
            cursor.close();
            dbOpenHelper.close();
            return;
        }

        //notification manager 생성
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //notification builder 생성
        Notification.Builder notificationBuilder = new Notification.Builder(context);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("reminder", "reminderChannel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("reminder channel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            notificationManager.createNotificationChannel(notificationChannel);
            notificationBuilder.setChannelId("reminder");
        }
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher_reminder);
        notificationBuilder.setTicker(reminder.getContent()); //상태바 한줄 메시지
        notificationBuilder.setContentTitle(reminder.getTitle());
        notificationBuilder.setContentText(reminder.getContent());
        notificationBuilder.setAutoCancel(true);

        //오레오 분기
        //noti 채널생성
        //notibuilder 옵션 추가
        notificationManager.notify(channelId, notificationBuilder.build());
        cursor.close();
        dbOpenHelper.close();
    }
}
