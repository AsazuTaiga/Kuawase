package com.kuawase.kuawase.screen.modeChoice;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ModeChoiceViewModel extends ViewModel {
    @NonNull
    private final MutableLiveData<Object> onParentButtonClick = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<Object> onChildButotnClick = new MutableLiveData<>();

    @NonNull
    public LiveData<Object> getOnParentButtonClick() {
        return onParentButtonClick;
    }

    @NonNull
    public LiveData<Object> getOnChildButtonClick() {
        return onChildButotnClick;
    }

    void onParentButtonClick() {
        onParentButtonClick.setValue(new Object());
    }

    void onChildButtonClick() {
        onChildButotnClick.setValue(new Object());
    }
}
