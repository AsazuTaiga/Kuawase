package com.kuawase.model;

import androidx.annotation.NonNull;

import java.util.Date;

public class Model implements ModelInterface {
    private static Model singletonInstance;
    private KukaiInfo kukaiInfo;

    @NonNull
    public static ModelInterface getInstance() {
        if (null == singletonInstance) {
            singletonInstance = new Model();
        }
        return singletonInstance;
    }

    @NonNull
    @Override
    public KukaiInfo createKukaiInfo(@NonNull String name, @NonNull Date startDate, @NonNull Date endDate) {
        kukaiInfo = new KukaiInfo(name, startDate, endDate);
        return kukaiInfo;
    }

    @NonNull
    @Override
    public KukaiInfo getKukaiInfo(int id) {
        return kukaiInfo;
    }
}
