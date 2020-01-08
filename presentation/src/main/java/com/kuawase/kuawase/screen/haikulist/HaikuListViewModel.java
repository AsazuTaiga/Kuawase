package com.kuawase.kuawase.screen.haikulist;

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
import java.util.List;
import java.util.Objects;

public class HaikuListViewModel extends ViewModel {
    @NonNull
    private KukaiInfoDataSource dataSource = KukaiInfoRepository.getInstance();

    private int kukaiId;

    @Nullable
    private KukaiInfo kukaiInfo;

    @Nullable
    private SoundPlayer soundPlayer;

    @NonNull
    private MutableLiveData<Event<Integer>> onFinishVoteButtonClick = new MutableLiveData<>();

    @NonNull
    public LiveData<Event<Integer>> getOnFinishInputButtonClick() {
        return onFinishVoteButtonClick;
    }

    void prepareSoundPlayer(@NonNull Context context) {
        soundPlayer = SoundPlayer.getInstance(context);
    }

    void onFinishVoteButtonClick() {
        Objects.requireNonNull(soundPlayer);
        soundPlayer.playResultSound();
        onFinishVoteButtonClick.setValue(new Event<>(kukaiId));
    }

    void setKukaiId(int kukaiId) {
        this.kukaiId = kukaiId;
    }

    @NonNull
    KukaiInfo getKukaiInfo() {
        if (null == kukaiInfo) {
            kukaiInfo = Objects.requireNonNull(dataSource.getKukaiInfo());
        }
        return kukaiInfo;
    }

    @NonNull
    List<HaikuInfo> getRondomHaikuInfos() {
        List<HaikuInfo> haikuInfos = getKukaiInfo().getHaikuInfos();
        Collections.shuffle(haikuInfos);
        return haikuInfos;
    }
}
