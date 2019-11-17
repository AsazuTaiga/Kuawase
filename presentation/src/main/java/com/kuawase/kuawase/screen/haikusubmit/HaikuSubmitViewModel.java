package com.kuawase.kuawase.screen.haikusubmit;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuawase.kuawase.utility.Event;

public class HaikuSubmitViewModel extends ViewModel {
    @NonNull
    private final MutableLiveData<String> haiku = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<String> author = new MutableLiveData<>();

    @NonNull
    private MutableLiveData<Event<String>> onSubmitButtonClick = new MutableLiveData<>();

    @NonNull
    public LiveData<Event<String>> getOnSubmitButtonClick() {
        return onSubmitButtonClick;
    }

    void onSubmitButtonClick(@NonNull String haiku, @NonNull String author) {
        final String content = haiku + ";" + author;
        onSubmitButtonClick.setValue(new Event<>(content));
    }
}
