package com.kuawase.kuawase.screen.result;

import androidx.annotation.NonNull;

import com.kuawase.model.HaikuInfo;

import java.util.Comparator;

class PointComparator implements Comparator<HaikuInfo> {
    @Override
    public int compare(@NonNull HaikuInfo arg1, @NonNull HaikuInfo arg2) {
        if (arg1.getPoint() == arg2.getPoint()) {
            return 0;
        }
        return arg1.getPoint() > arg2.getPoint() ? -1 : 1;
    }
}
