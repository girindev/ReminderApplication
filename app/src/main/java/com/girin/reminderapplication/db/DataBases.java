package com.girin.reminderapplication.db;

import android.provider.BaseColumns;

public final class DataBases {

    public static final class
    CreateDB implements BaseColumns {
        public static final String TITLE = "title";
        public static final String CONTENT = "content";
        public static final String DATE = "date";
        public static final String ALRAM_CHECK = "alram_check";
        public static final String MIllI_SECOND = "milli_second";
        public static final String _TABLENAME0 = "reminder";
        public static final String _CREATE0 = "create table if not exists " + _TABLENAME0
                +"("
                +_ID+" integer primary key autoincrement, "
                +TITLE+" text not null , "
                +CONTENT+" text not null , "
                +DATE+" text not null , "
                +ALRAM_CHECK+" integer not null, "
                +MIllI_SECOND+" integer not null);";
    }
}
