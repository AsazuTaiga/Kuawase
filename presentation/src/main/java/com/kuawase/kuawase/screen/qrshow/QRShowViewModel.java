package com.kuawase.kuawase.screen.qrshow;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

public class QRShowViewModel extends ViewModel {
    private static final int size = 300;

    @Nullable
    private final MutableLiveData<Bitmap> qrImageLiveData = new MutableLiveData<>();

    @BindingAdapter("imageBitmap")
    public static void loadImageBitmap(ImageView iv, Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }

    void setQrImageLiveDataFromString(@NonNull String content) {
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, UTF_8);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Objects.requireNonNull(qrImageLiveData);
        try {
            qrImageLiveData.setValue(barcodeEncoder.encodeBitmap(
                    content, BarcodeFormat.QR_CODE, size, size, hints));
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public MutableLiveData<Bitmap> getQrImageLiveData() {
        return qrImageLiveData;
    }
}
