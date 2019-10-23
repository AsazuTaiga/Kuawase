package com.kuawase.kuawase.screen.qrshow;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QRShowViewModel extends ViewModel {
    @NonNull
    MutableLiveData<String> haiku = new MutableLiveData<>();

    @NonNull
    MutableLiveData<String> author = new MutableLiveData<>();

    @NonNull
    MutableLiveData<Drawable> qrCodeDrawable = new MutableLiveData<>();

    // TODO:Generate QR code by ZXing.
}
