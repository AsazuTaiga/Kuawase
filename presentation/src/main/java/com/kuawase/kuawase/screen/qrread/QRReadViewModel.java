package com.kuawase.kuawase.screen.qrread;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.kuawase.model.HaikuInfo;
import com.kuawase.model.KukaiInfo;
import com.kuawase.model.KukaiInfoDataSource;
import com.kuawase.model.KukaiInfoRepository;

import java.util.List;
import java.util.Objects;

public class QRReadViewModel extends ViewModel {
    @NonNull
    private KukaiInfoDataSource dataSource = KukaiInfoRepository.getInstance();

    @Nullable
    private KukaiInfo kukaiInfo = dataSource.getKukaiInfo();

    @Nullable
    private List<HaikuInfo> haikuInfos;

    void onReadQRCode(String haikuInfoString) {
        if (null == haikuInfos) {
            Objects.requireNonNull(kukaiInfo);
            haikuInfos = kukaiInfo.getHaikuInfos();
        }
        Objects.requireNonNull(haikuInfos);
        String[] haikuInfoStringArray = haikuInfoString.split(";");
        haikuInfos.add(new HaikuInfo(haikuInfoStringArray[1], haikuInfoStringArray[2]));
    }
}
