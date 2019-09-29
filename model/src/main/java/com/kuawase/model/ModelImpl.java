package com.kuawase.model;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;
import java.util.List;

public class ModelImpl implements ModelInterface {
    @Nullable
    private static ModelImpl MODEL_IMPL = null;

    private ModelImpl() {
    }

    @NonNull
    public static ModelImpl getInstance() {
        if (MODEL_IMPL == null) {
            MODEL_IMPL = new ModelImpl();
        }
        return MODEL_IMPL;
    }

    @NonNull
    @Override
    public List<KukaiInfo> getAllKukaiInfo(@NonNull Context context) {
        try (SQLiteOpenHelper helper = new DatabaseOpenHelper(context)) {
            return DatabaseHelper.getAllKukaiInfo(helper);
        }
    }

    @NonNull
    @Override
    public List<Haiku> getHaikuList(@NonNull Context context, @NonNull KukaiInfo kukaiInfo) {
        try (SQLiteOpenHelper helper = new DatabaseOpenHelper(context)) {
            return DatabaseHelper.getHaikuList(helper, kukaiInfo);
        }
    }

    @NonNull
    @Override
    public KukaiInfo createKukaiInfo(@NonNull String kukaiName, @NonNull Date startDate, @NonNull Date endDate) {
        return new KukaiInfo(kukaiName, startDate, endDate);
    }

    @NonNull
    @Override
    public Haiku createHaiku(@NonNull String haiku, @NonNull String author) {
        return new Haiku(haiku, author);
    }

    @Override
    public void bindHaikuToKukai(@NonNull Haiku haiku, @NonNull KukaiInfo kukaiInfo) {
        haiku.setKukaiInfo(kukaiInfo);
    }

    @Override
    public void setHaikuPoint(@NonNull Haiku haiku, int point) {
        haiku.setPoint(point);
    }

    @Override
    public void saveKukaiInfo(@NonNull Context context, @NonNull KukaiInfo kukaiInfo) {
        try (SQLiteOpenHelper helper = new DatabaseOpenHelper(context)) {
            DatabaseHelper.saveKukaiInfo(helper, kukaiInfo);
        }
    }

//    @Nullable
//    @Override
//    public KukaiInfo getKukaiInfo(int id) {
//        String sqlGetKukaiInfo = "SELECT * FROM kukai_info_table WHERE _id = " + id + ";";
//        String sqlGetHaiku = "SELECT * FROM haiku_table WHERE kukai_id = " + id + ";";
//
//        try (SQLiteDatabase database = HELPER.getWritableDatabase();
//             Cursor kukaiInfoCursor = database.rawQuery(sqlGetKukaiInfo, null);
//             Cursor haikuCursor = database.rawQuery(sqlGetHaiku, null)) {
//
//            KukaiInfo info = new KukaiInfo();
//            List<Haiku> haikuList = new ArrayList<>();
//            Haiku tempHaiku = new Haiku();
//            int index;
//
//            info.setId(id);
//            index = kukaiInfoCursor.getColumnIndex("name");
//            info.setName(kukaiInfoCursor.getString(index));
//            index = kukaiInfoCursor.getColumnIndex("start_date");
//            info.setStartDate(stringToDate(kukaiInfoCursor.getString(index)));
//            index = kukaiInfoCursor.getColumnIndex("end_date");
//            info.setEndDate(stringToDate(kukaiInfoCursor.getString(index)));
//
//            while (haikuCursor.moveToNext()) {
//                index = haikuCursor.getColumnIndex("_id");
//                tempHaiku.setId(haikuCursor.getInt(index));
//                tempHaiku.setKukaiId(id);
//                index = haikuCursor.getColumnIndex("haiku");
//                tempHaiku.setHaiku(haikuCursor.getString(index));
//                index = haikuCursor.getColumnIndex("author");
//                tempHaiku.setAuthor(haikuCursor.getString(index));
//                index = haikuCursor.getColumnIndex("point");
//                tempHaiku.setPoint(haikuCursor.getInt(index));
//
//                haikuList.add(tempHaiku);
//            }
//
//            info.setHaikuList(haikuList);
//            return info;
//        }
//    }
//
//    @Nullable
//    @Override
//    public List<KukaiInfo> getAllKukaiInfo() {
//        Cursor kukaiInfoCursor = null;
//        Cursor haikuCursor = null;
//        try (SQLiteDatabase database = HELPER.getWritableDatabase()) {
//
//            List<KukaiInfo> allKukaiInfo = new ArrayList<KukaiInfo>();
//            KukaiInfo tempInfo = new KukaiInfo();
//            List<Haiku> haikuList = new ArrayList<>();
//            Haiku tempHaiku = new Haiku();
//            int index;
//
//            String sqlGetKukaiInfo = "SELECT * FROM kukai_info_table;";
//            kukaiInfoCursor = database.rawQuery(sqlGetKukaiInfo, null);
//
//            while (kukaiInfoCursor.moveToNext()) {
//                index = kukaiInfoCursor.getColumnIndex("_id");
//                tempInfo.setId(kukaiInfoCursor.getInt(index));
//                index = kukaiInfoCursor.getColumnIndex("name");
//                tempInfo.setName(kukaiInfoCursor.getString(index));
//                index = kukaiInfoCursor.getColumnIndex("start_date");
//                tempInfo.setStartDate(stringToDate(kukaiInfoCursor.getString(index)));
//                index = kukaiInfoCursor.getColumnIndex("end_date");
//                tempInfo.setEndDate(stringToDate(kukaiInfoCursor.getString(index)));
//                index = kukaiInfoCursor.getColumnIndex("_id");
//                String sqlGetHaiku = "SELECT * FROM haiku_table WHERE haiku_id = " + kukaiInfoCursor.getInt(index) + ";";
//                haikuCursor = database.rawQuery(sqlGetHaiku, null);
//
//                while (haikuCursor.moveToNext()) {
//                    index = haikuCursor.getColumnIndex("_id");
//                    tempHaiku.setId(haikuCursor.getInt(index));
//                    index = haikuCursor.getColumnIndex("kukai_id");
//                    tempHaiku.setKukaiId(haikuCursor.getInt(index));
//                    index = haikuCursor.getColumnIndex("haiku");
//                    tempHaiku.setHaiku(haikuCursor.getString(index));
//                    index = haikuCursor.getColumnIndex("author");
//                    tempHaiku.setAuthor(haikuCursor.getString(index));
//                    index = haikuCursor.getColumnIndex("point");
//                    tempHaiku.setPoint(haikuCursor.getInt(index));
//
//                    haikuList.add(tempHaiku);
//                }
//                allKukaiInfo.add(tempInfo);
//            }
//
//            tempInfo.setHaikuList(haikuList);
//            return allKukaiInfo;
//        } finally {
//            if (kukaiInfoCursor != null) {
//                kukaiInfoCursor.close();
//            }
//            if (haikuCursor != null) {
//                haikuCursor.close();
//            }
//        }
//    }
//
//    @Override
//    public void addKukaiInfo(@NonNull String name, @NonNull Date startDate, @NonNull Date endDate, @NonNull List<Haiku> haikuList) {
//        try (SQLiteDatabase database = HELPER.getWritableDatabase()) {
//            String sqlInsertKukaiInfo = "INSERT INTO kukai_info_table(name, start_date, end_date, haiku_list) VALUES (?, ?, ?, ?)";
//            String sqlInsertHaikuInfo = "INSERT INTO haiku_table(kukai_id, haiku, author, point) VALUES (?, ?, ?, ?)";
//            long kukaiId;
//
//            SQLiteStatement kukaiInfoStatement = database.compileStatement(sqlInsertKukaiInfo);
//            kukaiInfoStatement.bindString(1, name);
//            kukaiInfoStatement.bindString(2, dateToString(startDate));
//            kukaiInfoStatement.bindString(3, dateToString(endDate));
//            kukaiId = kukaiInfoStatement.executeInsert();
//
//            for (Haiku haiku : haikuList) {
//                SQLiteStatement haikuStatement = database.compileStatement(sqlInsertHaikuInfo);
//                haikuStatement.bindLong(1, kukaiId);
//                haikuStatement.bindString(2, haiku.getHaiku());
//                haikuStatement.bindString(3, haiku.getAuthor());
//                haikuStatement.bindLong(4, haiku.getPoint());
//                haikuStatement.executeInsert();
//            }
//        }
//    }
//
//    @Override
//    public void deleteKukaiInfo(int id) {
//        //TODO: implement method
//    }
//
//    @NonNull
//    private String dateToString(@NonNull Date date) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDHHmm", Locale.JAPAN);
//        return sdf.format(date);
//    }
//
//    @Nullable
//    private Date stringToDate(@NonNull String string) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDDHHmm", Locale.JAPAN);
//        Date date = null;
//        try {
//            date = sdf.parse(string);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }
}
