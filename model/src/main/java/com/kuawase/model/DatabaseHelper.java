package com.kuawase.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.NonNull;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class DatabaseHelper {
    private static final String SELECT_ALL_KUKAI_INFO = "SELECT * FROM kukai_info_table";
    private static final String SELECT_KUKAI_INFO_PRIMARY_KEY = "SELECT _id FROM kukai_info_table WHERE uuid = ?";
    private static final String INSERT_KUKAI_INFO = "INSERT INTO kukai_info (uuid, name, start_date, end_date) VALUES (?, ?, ?, ?)";

    private DatabaseHelper() {}

    @NonNull
    static List<KukaiInfo> getAllKukaiInfo(@NonNull SQLiteOpenHelper helper) {
        List<KukaiInfo> kukaiInfos = new ArrayList<>();
        try (SQLiteDatabase db = helper.getWritableDatabase();
             Cursor cursor = db.rawQuery(SELECT_ALL_KUKAI_INFO, null)) {
            // cursorの内容をもとに、kukaiInfoを順に作成し、kukaiInfosに詰める
            return kukaiInfos;
        }
    }

    @NonNull
    static List<Haiku> getHaikuList(@NonNull SQLiteOpenHelper helper, @NonNull KukaiInfo kukaiInfo){
        List<Haiku> haikus = new ArrayList<>();
        String[] strings = {kukaiInfo.getUuid().toString()};
        try (SQLiteDatabase db = helper.getWritableDatabase();
             Cursor cursor = db.rawQuery(SELECT_KUKAI_INFO_PRIMARY_KEY, strings)) {
            // cursorの内容をもとに、haikuを順に作成し、haikusに詰める
            return haikus;
        }
    }

    static void saveKukaiInfo(@NonNull SQLiteOpenHelper helper, @NonNull KukaiInfo kukaiInfo) {
        String[] strings = {kukaiInfo.getUuid().toString(), kukaiInfo.getName(), kukaiInfo.getStartDate().toString(), kukaiInfo.getEndDate().toString()};
        try (SQLiteDatabase db = helper.getWritableDatabase()) {
            SQLiteStatement statement = db.compileStatement(INSERT_KUKAI_INFO);
            statement.bindAllArgsAsStrings(strings);
            statement.executeInsert();
        }
    }
}
