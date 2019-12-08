package com.kuawase.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;

public interface KukaiInfoDataSource {
    @NonNull
    KukaiInfo createKukaiInfo(@NonNull String name, @NonNull Date startDate, @NonNull Date endDate);

    @Nullable
    KukaiInfo getKukaiInfo();

    void deleteKukaiInfo();
}
