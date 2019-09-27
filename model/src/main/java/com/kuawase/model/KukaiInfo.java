package com.kuawase.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

class KukaiInfo {
    @NonNull
    private UUID uuid;

    @NonNull
    private String name;

    @NonNull
    private Date startDate;

    @NonNull
    private Date endDate;

    KukaiInfo(@NonNull String name, @NonNull Date startDate, @NonNull Date endDate) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @NonNull
    UUID getUuid() {
        return uuid;
    }

    @NonNull
    String getName() {
        return name;
    }

    @NonNull
    Date getStartDate() {
        return startDate;
    }

    @NonNull
    Date getEndDate() {
        return endDate;
    }
}
