package com.kuawase.kuawase.screen.result;

import com.kuawase.model.HaikuInfo;

import java.util.Comparator;

class PointComparator implements Comparator<HaikuInfo> {
    @Override
    public int compare(HaikuInfo arg1, HaikuInfo arg2) {
        if (arg1.getPoint() == arg2.getPoint()) {
            return 0;
        }
        return arg1.getPoint() > arg2.getPoint() ? -1 : 1;
    }
}
