package com.kuawase.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KukaiInfo {
    private final int id = 0;

    @NonNull
    private final String name;

    @NonNull
    private final Date startDate;

    @NonNull
    private final Date endDate;

    @NonNull
    private final List<HaikuInfo> haikuInfos = new ArrayList<>();

    KukaiInfo(@NonNull String name, @NonNull Date startDate, @NonNull Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
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
        return haikuInfos;
    }
}
