package com.kuawase.kuawase.screen.modechoice;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuawase.kuawase.utility.Event;

public class ModeChoiceViewModel extends ViewModel {
    @NonNull
    private final MutableLiveData<Event<Object>> onParentButtonClick = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<Event<Object>> onChildButotnClick = new MutableLiveData<>();

    @NonNull
    public LiveData<Event<Object>> getOnParentButtonClick() {
        return onParentButtonClick;
    }

    @NonNull
    public LiveData<Event<Object>> getOnChildButtonClick() {
        return onChildButotnClick;
    }

    void onParentButtonClick() {
        onParentButtonClick.setValue(new Event<>(new Object()));
    }

    void onChildButtonClick() {
        onChildButotnClick.setValue(new Event<>(new Object()));
    }
}
