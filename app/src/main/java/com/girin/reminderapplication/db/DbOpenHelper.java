package com.girin.reminderapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.girin.reminderapplication.model.Reminder;

public class DbOpenHelper {
    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context context;

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DataBases.CreateDB._CREATE0);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DataBases.CreateDB._TABLENAME0);
        }
    }

    public DbOpenHelper(Context context) {
        this.context = context;
    }

    public DbOpenHelper open() {
        mDBHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create() {
        mDBHelper.onCreate(mDB);
    }
    public void deleteTable() {
        mDB.execSQL("DROP TABLE IF EXISTS " + DataBases.CreateDB._TABLENAME0);
    }

    public void close() {
        mDB.close();
    }

    public long insertReminder(Reminder reminder) {
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.TITLE, reminder.getTitle());
        values.put(DataBases.CreateDB.CONTENT, reminder.getContent());
        values.put(DataBases.CreateDB.DATE, reminder.getTime());
        values.put(DataBases.CreateDB.ALRAM_CHECK, reminder.getAlertCheck());
        values.put(DataBases.CreateDB.MIllI_SECOND, reminder.getMilli_second());
        return mDB.insert(DataBases.CreateDB._TABLENAME0, null, values);
    }

    public Cursor selectColumns() {
        return mDB.rawQuery("SELECT * FROM " + DataBases.CreateDB._TABLENAME0 + " ORDER BY milli_second DESC",null);
    }

    public Cursor selectColumn(int id) {
        return mDB.rawQuery("SELECT * FROM " + DataBases.CreateDB._TABLENAME0 + " WHERE _id= " + id + ";", null);
    }
    public Cursor selectLastColumn() {
        return mDB.rawQuery("SELECT * FROM " + DataBases.CreateDB._TABLENAME0 + " ORDER BY _id DESC LIMIT 1;", null);
    }

    public int deleteReminder(int id) {
        String _id = String.valueOf(id);
        return mDB.delete(DataBases.CreateDB._TABLENAME0, "_id=?", new String[]{_id});
    }

    public int updateReminder(Reminder reminder) {
        String _id = String.valueOf(reminder.get_id());
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.TITLE, reminder.getTitle());
        values.put(DataBases.CreateDB.CONTENT, reminder.getContent());
        values.put(DataBases.CreateDB.DATE, reminder.getTime());
        values.put(DataBases.CreateDB.ALRAM_CHECK, reminder.getAlertCheck());
        values.put(DataBases.CreateDB.MIllI_SECOND, reminder.getMilli_second());
        return mDB.update(DataBases.CreateDB._TABLENAME0, values, "_id=?", new String[]{_id});
    }

    public int updateAlarmCheck(int _id, int check) {
        // where절에 들어갈 _id를 String으로 변환
        String id = String.valueOf(_id);
        // value를 만든다
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.ALRAM_CHECK, check);
        // update를 진행한다
        // 결과를 반환한다.
        return mDB.update(DataBases.CreateDB._TABLENAME0, values, "_id=?", new String[]{id});
    }
}
