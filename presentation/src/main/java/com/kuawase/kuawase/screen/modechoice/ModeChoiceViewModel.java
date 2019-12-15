package com.kuawase.kuawase.screen.modechoice;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuawase.kuawase.utility.Event;
import com.kuawase.model.SoundPlayer;

import java.util.Objects;

public class ModeChoiceViewModel extends ViewModel {
    @NonNull
    private final MutableLiveData<Event<Object>> onParentButtonClick = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<Event<Object>> onChildButotnClick = new MutableLiveData<>();

    @Nullable
    private com.kuawase.model.SoundPlayer soundPlayer;

    @NonNull
    public LiveData<Event<Object>> getOnParentButtonClick() {
        return onParentButtonClick;
    }

    @NonNull
    public LiveData<Event<Object>> getOnChildButtonClick() {
        return onChildButotnClick;
    }

    void prepareSoundPlayer(@NonNull Context context) {
        soundPlayer = SoundPlayer.newInstance(context);
    }

    public void onParentButtonClick() {
        Objects.requireNonNull(soundPlayer);
        soundPlayer.playTapSound();
        onParentButtonClick.setValue(new Event<>(new Object()));
    }

    public void onChildButtonClick() {
        Objects.requireNonNull(soundPlayer);
        soundPlayer.playTapSound();
        onChildButotnClick.setValue(new Event<>(new Object()));
    }
}
