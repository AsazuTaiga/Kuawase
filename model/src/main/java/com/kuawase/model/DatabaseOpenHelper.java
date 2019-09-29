package com.kuawase.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "kuawase.db";
    private static final int DB_VERSION = 1;

    DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createKukaiInfoTable = "CREATE TABLE kukai_info_table(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "uuid TEXT, " +
                "name TEXT, " +
                "start_date TEXT, " +
                "end_date TEXT);";

        String createHaikuTable = "CREATE TABLE haiku_table(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "kukai_id BLOB, " +
                "haiku TEXT, " +
                "author TEXT, " +
                "point INTEGER);";

        sqLiteDatabase.execSQL(createKukaiInfoTable);
        sqLiteDatabase.execSQL(createHaikuTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
