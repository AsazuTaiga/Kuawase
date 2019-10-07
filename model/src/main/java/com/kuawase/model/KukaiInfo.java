package com.kuawase.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class KukaiInfo {
    private final int id;

    @NonNull
    private final String name;

    @NonNull
    private final Date startDate;

    @NonNull
    private final Date endDate;

    @NonNull
    private final List<HaikuInfo> haikuInfos;

    KukaiInfo(@NonNull String name, @NonNull Date startDate, @NonNull Date endDate) {
        id = 0;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        haikuInfos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public Date getStartDate() {
        return startDate;
    }

    @NonNull
    public Date getEndDate() {
        return endDate;
    }

    @NonNull
    public List<HaikuInfo> getHaikuInfos() {
        return this.haikuInfos;
    }
}
