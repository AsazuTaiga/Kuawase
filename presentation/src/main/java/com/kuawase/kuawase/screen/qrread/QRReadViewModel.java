package com.kuawase.kuawase.screen.qrread;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuawase.kuawase.utility.Event;
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
    private KukaiInfo kukaiInfo;

    private int kukaiId;

    @Nullable
    private List<HaikuInfo> haikuInfos;

    @NonNull
    private MutableLiveData<Event<Integer>> finishReadButtonClick = new MutableLiveData<>();

    @NonNull
    public LiveData<Event<Integer>> getOnFinishReadButtonClick() {
        return finishReadButtonClick;
    }

    void onFinishInputButtonClick() {
        finishReadButtonClick.setValue(new Event<>(kukaiId));
    }

    void onReadQRCode(@NonNull String haikuInfoString) {
        if (null == kukaiInfo) {
            kukaiInfo = dataSource.getKukaiInfo();
        }
        if (null == haikuInfos) {
            Objects.requireNonNull(kukaiInfo);
            haikuInfos = kukaiInfo.getHaikuInfos();
        }
        Objects.requireNonNull(haikuInfos);
        String[] haikuInfoStringArray = haikuInfoString.split(";");
        haikuInfos.add(new HaikuInfo(haikuInfoStringArray[0], haikuInfoStringArray[1]));
    }

    void setKukaiId(int kukaiId) {
        this.kukaiId = kukaiId;
    }
}
