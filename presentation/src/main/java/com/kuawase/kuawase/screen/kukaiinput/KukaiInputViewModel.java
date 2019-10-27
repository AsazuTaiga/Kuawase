package com.kuawase.kuawase.screen.kukaiinput;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuawase.kuawase.utility.Event;
import com.kuawase.kuawase.utility.SelectDateFragment;
import com.kuawase.model.KukaiInfo;
import com.kuawase.model.KukaiInfoDataSource;

import java.util.Date;

public class KukaiInputViewModel extends ViewModel {
    @NonNull
    private final MutableLiveData<String> kukaiName = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<Date> startDate = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<Date> endDate = new MutableLiveData<>();

    @Nullable
    private final MutableLiveData<Event<KukaiInfo>> kukaiInfo = null;

    @Nullable
    private final KukaiInfoDataSource kukaiInfoDataSource = null;
}
