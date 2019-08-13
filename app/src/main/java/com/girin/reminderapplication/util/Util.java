package com.girin.reminderapplication.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.girin.reminderapplication.AlarmReceiver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;

public class Util {

    public static void addAlarm(int id, String alarmDate, Context context) {
        // 알람매니저 생성
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        // pendingintent에 들어갈 intent 인턴트 생성
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("id", id);
        // String to Calendar
        Date date = stringToDate(alarmDate);
        // Calendar 시간 생성
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // pending intent 생성
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // alarm 매니저 set
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public static Date stringToDate(String value) {
        Date date = null;
        value += ":00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        try {
            date = simpleDateFormat.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return date;
    }

    public static boolean currentTimeDifference(Date time1) {
        long nowTime = System.currentTimeMillis();
        Date nowDate = new Date(nowTime);
        return nowDate.after(time1);
        //true -> 이전
        //false -> 이후
    }

}
