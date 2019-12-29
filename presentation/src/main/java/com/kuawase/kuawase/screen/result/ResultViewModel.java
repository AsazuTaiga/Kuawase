package com.kuawase.kuawase.screen.result;

import android.content.Context;

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
import com.kuawase.model.SoundPlayer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ResultViewModel extends ViewModel {
    @NonNull
    private KukaiInfoDataSource dataSource = KukaiInfoRepository.getInstance();

    private int kukaiId;

    @Nullable
    private KukaiInfo kukaiInfo;

    @Nullable
    private SoundPlayer soundPlayer;

    @NonNull
    private MutableLiveData<Event<Object>> onExitButtonClick = new MutableLiveData<>();

    @NonNull
    public LiveData<Event<Object>> getOnExitButtonClick() {
        return onExitButtonClick;
    }

    void prepareSoundPlayer(@NonNull Context context) {
        soundPlayer = SoundPlayer.getInstance(context);
    }

    void onExitButtonClick() {
        Objects.requireNonNull(soundPlayer);
        soundPlayer.playTapSound();
        dataSource.deleteKukaiInfo();
        onExitButtonClick.setValue(new Event<>(new Object()));
    }

    void setKukaiId(int kukaiId) {
        this.kukaiId = kukaiId;
        if (null == kukaiInfo) {
            kukaiInfo = Objects.requireNonNull(dataSource.getKukaiInfo());
        }
    }

    @NonNull
    List<HaikuInfo> getSortedHaikuInfos() {
        Objects.requireNonNull(kukaiInfo);
        Comparator<HaikuInfo> pointComparator = new PointComparator();
        List<HaikuInfo> haikuInfos = kukaiInfo.getHaikuInfos();
        Collections.sort(haikuInfos, pointComparator);
        return haikuInfos;
    }
}
