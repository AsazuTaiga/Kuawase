package com.kuawase.kuawase.screen.haikulist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuawase.kuawase.utility.Event;
import com.kuawase.model.KukaiInfo;
import com.kuawase.model.KukaiInfoDataSource;
import com.kuawase.model.KukaiInfoRepository;

import java.util.Objects;

public class HaikuListViewModel extends ViewModel {
    @NonNull
    private KukaiInfoDataSource dataSource = KukaiInfoRepository.getInstance();

    private int kukaiId;

    @Nullable
    private KukaiInfo kukaiInfo;

    @NonNull
    private MutableLiveData<Event<Integer>> onFinishVoteButtonClick = new MutableLiveData<>();

    @NonNull
    public LiveData<Event<Integer>> getOnFinishInputButtonClick() {
        return onFinishVoteButtonClick;
    }

    void onFinishVoteButtonClick() {
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
}
