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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ModeChoiceViewModel extends ViewModel {
    @NonNull
    private final MutableLiveData<Event<Object>> onParentButtonClick = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<Event<Object>> onChildButotnClick = new MutableLiveData<>();

    @Nullable
    private SoundPlayer soundPlayer;

    @NonNull
    public LiveData<Event<Object>> getOnParentButtonClick() {
        return onParentButtonClick;
    }

    @NonNull
    public LiveData<Event<Object>> getOnChildButtonClick() {
        return onChildButotnClick;
    }

    @NonNull
    private Executor executor = Executors.newSingleThreadExecutor();

    void prepareSoundPlayer(@NonNull Context context) {
        executor.execute(() -> soundPlayer = SoundPlayer.getInstance(context));
    }

    public void onParentButtonClick() {
        Objects.requireNonNull(soundPlayer);
        executor.execute(() -> soundPlayer.playTapSound());
        onParentButtonClick.setValue(new Event<>(new Object()));
    }

    public void onChildButtonClick() {
        Objects.requireNonNull(soundPlayer);
        executor.execute(() -> soundPlayer.playTapSound());
        onChildButotnClick.setValue(new Event<>(new Object()));
    }
}
