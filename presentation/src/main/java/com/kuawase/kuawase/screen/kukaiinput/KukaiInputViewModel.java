package com.kuawase.kuawase.screen.kukaiinput;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuawase.kuawase.utility.Event;
import com.kuawase.model.KukaiInfo;
import com.kuawase.model.KukaiInfoDataSource;
import com.kuawase.model.KukaiInfoRepository;

import java.util.Date;

public class KukaiInputViewModel extends ViewModel {
    private boolean isShowingDateFragment = false;

    @NonNull
    private final KukaiInfoDataSource dataSource = KukaiInfoRepository.getInstance();

    @NonNull
    private MutableLiveData<Event<Integer>> onFinishInputButtonClick = new MutableLiveData<>();

    @NonNull
    public LiveData<Event<Integer>> getOnFinishInputButtonClick() {
        return onFinishInputButtonClick;
    }

    void onFinishInputButtonClick(@NonNull String kukaiName, @NonNull Date startDate, @NonNull Date endDate) {
        KukaiInfo kukaiInfo = dataSource.createKukaiInfo(kukaiName, startDate, endDate);
        onFinishInputButtonClick.setValue(new Event<>(kukaiInfo.getId()));
    }

    public boolean isShowingDateFragment() {
        return isShowingDateFragment;
    }

    public void setShowingDateFragment(boolean showingDateFragment) {
        isShowingDateFragment = showingDateFragment;
    }
}
