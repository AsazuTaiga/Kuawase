package com.kuawase.kuawase.screen.haikusubmit;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuawase.kuawase.utility.Event;
import com.kuawase.model.SoundPlayer;

import java.util.Objects;

public class HaikuSubmitViewModel extends ViewModel {
    private static final String FORMAT = "%s;%s";
    @NonNull
    private final MutableLiveData<String> haiku = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> author = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Event<String>> onSubmitButtonClick = new MutableLiveData<>();
    @Nullable
    private SoundPlayer soundPlayer;

    @NonNull
    public MutableLiveData<String> getHaiku() {
        return haiku;
    }

    @NonNull
    public MutableLiveData<String> getAuthor() {
        return author;
    }

    @NonNull
    public LiveData<Event<String>> getOnSubmitButtonClick() {
        return onSubmitButtonClick;
    }

    void prepareSoundPlayer(@NonNull Context context) {
        soundPlayer = SoundPlayer.getInstance(context);
    }

    public void onSubmitButtonClick() {
        Objects.requireNonNull(soundPlayer);
        soundPlayer.playTapSound();
        final String content = String.format(FORMAT, getHaiku().getValue(), getAuthor().getValue());
        onSubmitButtonClick.setValue(new Event<>(content));
    }
}
