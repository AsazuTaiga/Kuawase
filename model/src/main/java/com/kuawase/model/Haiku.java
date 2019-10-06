package com.kuawase.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

class Haiku {
    private static int DEFAULT_POINT = 0;

    @NonNull
    private String haiku;

    @NonNull
    private String author;

    private int point;

    @Nullable
    private KukaiInfo kukaiInfo;

    Haiku(@NonNull String haiku, @NonNull String author) {
        this(haiku, author, DEFAULT_POINT);
    }

    Haiku(@NonNull String haiku, @NonNull String author, int point) {
        this(haiku, author, point, null);
    }

    Haiku(@NonNull String haiku, @NonNull String author, int point, @Nullable KukaiInfo kukaiInfo) {
        this.haiku = haiku;
        this.author = author;
        this.point = point;
        this.kukaiInfo = kukaiInfo;
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

    @Nullable
    KukaiInfo getKukaiInfo() {
        return this.kukaiInfo;
    }

    void setKukaiInfo(@NonNull KukaiInfo kukaiInfo) {
        this.kukaiInfo = kukaiInfo;
    }
}
