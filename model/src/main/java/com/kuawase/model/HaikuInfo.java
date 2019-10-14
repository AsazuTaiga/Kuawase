package com.kuawase.model;

import androidx.annotation.NonNull;

public class HaikuInfo {
    @NonNull
    private final String haiku;

    @NonNull
    private final String author;

    private int point = 0;

    public HaikuInfo(@NonNull String haiku, @NonNull String author) {
        this.haiku = haiku;
        this.author = author;
    }

    @NonNull
    public String getHaiku() {
        return haiku;
    }

    @NonNull
    public String getAuthor() {
        return author;
    }

    public int getPoint() {
        return point;
    }

    void setPoint(int point) {
        this.point = point;
    }
}
