package com.kuawase.kuawase.screen.qrread;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuawase.kuawase.utility.Event;
import com.kuawase.model.HaikuInfo;
import com.kuawase.model.KukaiInfo;
import com.kuawase.model.KukaiInfoDataSource;
import com.kuawase.model.KukaiInfoRepository;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class QRReadViewModel extends ViewModel {
    private static final String PATTERN = "^.*;.*$";
    private static final String SPLITTER = ";";
    private static final String ERROR_MESSAGE = "不正なパターンです";

    @NonNull
    private KukaiInfoDataSource dataSource = KukaiInfoRepository.getInstance();

    @Nullable
    private KukaiInfo kukaiInfo;

    private int kukaiId;

    @Nullable
    private List<HaikuInfo> haikuInfos;

    @NonNull
    private MutableLiveData<Event<Integer>> onFinishReadButtonClick = new MutableLiveData<>();

    @NonNull
    public LiveData<Event<Integer>> getOnFinishReadButtonClick() {
        return onFinishReadButtonClick;
    }

    void onFinishReadButtonClick() {
        onFinishReadButtonClick.setValue(new Event<>(kukaiId));
    }

    void onReadQRCode(@NonNull String haikuInfoString) throws RuntimeException {
        if (null == kukaiInfo) {
            kukaiInfo = dataSource.getKukaiInfo();
        }
        if (null == haikuInfos) {
            Objects.requireNonNull(kukaiInfo);
            haikuInfos = kukaiInfo.getHaikuInfos();
        }
        Objects.requireNonNull(haikuInfos);

        if (!Pattern.matches(PATTERN, haikuInfoString)) {
            throw new RuntimeException(ERROR_MESSAGE);
        }
        String[] haikuInfoStringArray = TextUtils.split(haikuInfoString, SPLITTER);
        haikuInfos.add(new HaikuInfo(haikuInfoStringArray[0], haikuInfoStringArray[1]));
    }

    void setKukaiId(int kukaiId) {
        this.kukaiId = kukaiId;
    }
}
