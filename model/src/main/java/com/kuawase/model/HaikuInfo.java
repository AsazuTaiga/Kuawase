package com.kuawase.model;

import androidx.annotation.NonNull;

class HaikuInfo {
    private static int DEFAULT_POINT = 0;

    @NonNull
    private final String haiku;

    @NonNull
    private final String author;

    private int point;

    public HaikuInfo(@NonNull String haiku, @NonNull String author) {
        this.haiku = haiku;
        this.author = author;
        this.point = 0;
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
