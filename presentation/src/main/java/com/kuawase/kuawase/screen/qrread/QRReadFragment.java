package com.kuawase.kuawase.screen.qrread;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import com.kuawase.kuawase.R;
import com.kuawase.kuawase.utility.SoundPlayer;
import com.kuawase.kuawase.utility.ViewModelUtils;

import java.util.List;
import java.util.Objects;

public class QRReadFragment extends Fragment {
    @NonNull
    private static final String KEY = "kukaiId";

    @Nullable
    private QRReadViewModel viewModel;

    @Nullable
    private CompoundBarcodeView barcodeScanner;

    @Nullable
    private TextSwitcher resultText;

    @Nullable
    private Button scanButton;

    @Nullable
    private Button finishReadButton;

    @Nullable
    private SoundPlayer soundPlayer;

    private QRReadFragment() {
    }

    @NonNull
    public static QRReadFragment newInstance(@Nullable Integer kukaiId) {
        Objects.requireNonNull(kukaiId);
        QRReadFragment fragment = new QRReadFragment();
        Bundle args = new Bundle();
        args.putInt(KEY, kukaiId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.qr_read_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        barcodeScanner = view.findViewById(R.id.zxing_barcode_scanner);
        resultText = view.findViewById(R.id.result_text);
        scanButton = view.findViewById(R.id.scan_button);
        finishReadButton = view.findViewById(R.id.finish_read_button);
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(barcodeScanner);
        barcodeScanner.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Objects.requireNonNull(barcodeScanner);
        barcodeScanner.pause();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity parentActivity = Objects.requireNonNull(getActivity());
        viewModel = ViewModelUtils.provideViewModel(parentActivity, QRReadViewModel.class);
        soundPlayer = SoundPlayer.newInstance(parentActivity);

        Bundle args = getArguments();
        Objects.requireNonNull(args);
        Objects.requireNonNull(viewModel);
        viewModel.setKukaiId(args.getInt(KEY));

        Objects.requireNonNull(barcodeScanner);
        Objects.requireNonNull(resultText);
        Objects.requireNonNull(scanButton);
        resultText.setInAnimation(parentActivity, android.R.anim.slide_in_left);
        resultText.setOutAnimation(parentActivity, android.R.anim.slide_out_right);
        scanButton.setOnClickListener(l -> {
            soundPlayer.playTapSound();
            String[] strings = getResources().getStringArray(R.array.barcode_read_info);
            resultText.setText(strings[0]);
            barcodeScanner.decodeSingle(new BarcodeCallback() {
                @Override
                public void barcodeResult(BarcodeResult result) {
                    String resultString = result.getText();
                    try {
                        viewModel.onReadQRCode(resultString);
                        resultText.setText(strings[1]);
                    } catch (RuntimeException e) {
                        resultText.setText(strings[2]);
                    }
                }

                @Override
                public void possibleResultPoints(List<ResultPoint> resultPoints) {
                }
            });
        });

        Objects.requireNonNull(finishReadButton);
        finishReadButton.setOnClickListener(l -> {
            soundPlayer.playTapSound();
            viewModel.onFinishReadButtonClick();
        });
    }
}
