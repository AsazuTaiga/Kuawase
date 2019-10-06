package com.kuawase.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

class DatabaseHelper {
    private static final String SELECT_ALL_KUKAI_INFO = "SELECT * FROM kukai_info_table";
    private static final String SELECT_KUKAI_INFO_PRIMARY_KEY = "SELECT _id FROM kukai_info_table WHERE uuid = ?";
    private static final String INSERT_KUKAI_INFO = "INSERT INTO kukai_info_table (uuid, name, start_date, end_date) VALUES (?, ?, ?, ?)";

    private DatabaseHelper() {}

    @NonNull
    static List<KukaiInfo> getAllKukaiInfo(@NonNull SQLiteOpenHelper helper) {
        List<KukaiInfo> kukaiInfos = new ArrayList<>();
        try (SQLiteDatabase db = helper.getWritableDatabase();
             Cursor cursor = db.rawQuery(SELECT_ALL_KUKAI_INFO, null)) {
            int[] indexes = {
                    cursor.getColumnIndex("_id"),
                    cursor.getColumnIndex("uuid"),
                    cursor.getColumnIndex("name"),
                    cursor.getColumnIndex("start_date"),
                    cursor.getColumnIndex("end_date")
            };
            // cursorの内容をもとに、kukaiInfoを順に作成し、kukaiInfosに詰める
            while (cursor.moveToNext()) {
                UUID uuid = UUID.fromString(cursor.getString(indexes[1]));
                String name = cursor.getString(indexes[2]);
                Date startDate = parse(cursor.getString(indexes[3]));
                Date endDate = parse(cursor.getString(indexes[4]));
                kukaiInfos.add(new KukaiInfo(uuid, name, startDate, endDate));
            }
            return kukaiInfos;
        }
    }

    @NonNull
    static List<Haiku> getHaikuList(@NonNull SQLiteOpenHelper helper, @NonNull KukaiInfo kukaiInfo){
        List<Haiku> haikus = new ArrayList<>();
        String[] strings = {kukaiInfo.getUuid().toString()};
        try (SQLiteDatabase db = helper.getWritableDatabase();
             Cursor cursor = db.rawQuery(SELECT_KUKAI_INFO_PRIMARY_KEY, strings)) {
            int[] indexes = {
                    cursor.getColumnIndex("_id"),
                    cursor.getColumnIndex("haiku"),
                    cursor.getColumnIndex("author"),
                    cursor.getColumnIndex("point")
            };
            while (cursor.moveToNext()) {
                String haiku = cursor.getString(indexes[1]);
                String author = cursor.getString(indexes[2]);
                int point = cursor.getInt(indexes[3]);
                haikus.add(new Haiku(haiku, author, point, kukaiInfo));
            }
            return haikus;
        }
    }

    static void saveKukaiInfo(@NonNull SQLiteOpenHelper helper, @NonNull KukaiInfo kukaiInfo) {
        try (SQLiteDatabase db = helper.getWritableDatabase()) {
            SQLiteStatement statement = db.compileStatement(INSERT_KUKAI_INFO);
            statement.bindString(1, kukaiInfo.getUuid().toString());
            statement.bindString(2, kukaiInfo.getName());
            statement.bindString(3, kukaiInfo.getStartDate().toString());
            statement.bindString(4, kukaiInfo.getEndDate().toString());
            statement.executeInsert();
        }
    }

    @NonNull
    private static String format(@NonNull Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPAN);
        return sdf.format(date);
    }

    @NonNull
    private static Date parse(@NonNull String text) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPAN);
        Date date = null;
        try {
            date = sdf.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (null == date) {
            throw new IllegalArgumentException();
        }
        return date;
    }
}
