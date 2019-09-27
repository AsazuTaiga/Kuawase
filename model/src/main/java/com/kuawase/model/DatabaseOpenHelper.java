package com.kuawase.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                "uuid INTEGER, " +
                "name TEXT, " +
                "start_date INTEGER, " +
                "end_date INTEGER);";

        String createHaikuTable = "CREATE TABLE haiku_table(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "kukai_id INTEGER, " +
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
