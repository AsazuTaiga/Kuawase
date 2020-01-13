package com.kuawase.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

    private KukaiInfoRepository() {
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

    @NonNull
    @Override
    public KukaiInfo createKukaiInfo(@NonNull String name, @NonNull Date startDate, @NonNull Date endDate) {
        return new KukaiInfo(name, startDate, endDate);
    }
}