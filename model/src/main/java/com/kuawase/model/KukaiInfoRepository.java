package com.kuawase.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;

public class KukaiInfoRepository implements KukaiInfoDataSource {
    @Nullable
    private static KukaiInfoRepository singletonInstance;

    @Nullable
    private KukaiInfo kukaiInfo;

    @NonNull
    public static KukaiInfoDataSource getInstance() {
        if (null == singletonInstance) {
            singletonInstance = new KukaiInfoRepository();
        }
        return singletonInstance;
    }

    @NonNull
    @Override
    public KukaiInfo createKukaiInfo(@NonNull String name, @NonNull Date startDate, @NonNull Date endDate) {
        kukaiInfo = new KukaiInfo(name, startDate, endDate);
        return kukaiInfo;
    }

    @Nullable
    @Override
    public KukaiInfo getKukaiInfo() {
        return kukaiInfo;
    }

    @Override
    public void deleteKukaiInfo() {
        kukaiInfo = null;
    }
}