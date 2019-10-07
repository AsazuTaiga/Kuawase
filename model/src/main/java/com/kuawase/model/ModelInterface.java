package com.kuawase.model;

import androidx.annotation.NonNull;

import java.util.Date;

public interface ModelInterface {
    @NonNull
    KukaiInfo createKukaiInfo(@NonNull String name, @NonNull Date startDate, @NonNull Date endDate);

    @NonNull
    KukaiInfo getKukaiInfo(int id);
}
