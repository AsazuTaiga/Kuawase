package com.kuawase.kuawase.screen.qrread;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import com.kuawase.kuawase.R;

import java.util.List;
import java.util.Objects;

public class QRReadFragment extends Fragment {

    @Nullable
    private QRReadViewModel viewModel;

    private int kukaiId;

    @Nullable
    private CompoundBarcodeView barcodeScanner;

    @Nullable
    private TextView resultText;

    @NonNull
    public static QRReadFragment newInstance(int kukaiId) {
        Bundle args = new Bundle();
        args.putInt("kukaiId", kukaiId);
        QRReadFragment fragment = new QRReadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        Objects.requireNonNull(args);
        this.kukaiId = args.getInt("kukaiId");
        return inflater.inflate(R.layout.qr_read_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        barcodeScanner = view.findViewById(R.id.zxing_barcode_scanner);
        resultText = view.findViewById(R.id.result_text);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity parentActivity = getActivity();
        Objects.requireNonNull(parentActivity);
        viewModel = ViewModelProviders.of(parentActivity).get(QRReadViewModel.class);

        Objects.requireNonNull(barcodeScanner);
        Objects.requireNonNull(resultText);
        barcodeScanner.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                viewModel.onReadQRCode(result.getText());
                resultText.setText(result.getText().split(";")[0]);
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {
            }
        });
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
}
