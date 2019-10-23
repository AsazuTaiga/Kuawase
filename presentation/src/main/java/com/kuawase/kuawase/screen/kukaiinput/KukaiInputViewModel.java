package com.kuawase.kuawase.screen.kukaiinput;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

public class KukaiInputViewModel extends ViewModel {
    @NonNull
    private final MutableLiveData<String> kukaiName = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<Date> startDate = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<Date> endDate = new MutableLiveData<>();
}
