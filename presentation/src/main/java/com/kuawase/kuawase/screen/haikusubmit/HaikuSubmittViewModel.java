package com.kuawase.kuawase.screen.haikusubmit;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuawase.kuawase.utility.Event;
import com.kuawase.model.HaikuInfo;

public class HaikuSubmittViewModel extends ViewModel {
    @NonNull
    private MutableLiveData<Event<HaikuInfo>> submitHaiku = new MutableLiveData<>();

    @NonNull
    public LiveData<Event<HaikuInfo>> getOnSubmitButtonClick() {
        return submitHaiku;
    }

    void onSubmitButtonClick(String haiku, String author) {
        submitHaiku.setValue(new Event<>(new HaikuInfo(haiku, author)));
    }
}
