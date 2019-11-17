package com.kuawase.kuawase.screen.qrshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.kuawase.kuawase.R;

import java.util.Objects;

public class QRShowFragment extends Fragment {
    @Nullable
    private QRShowViewModel viewModel;

    @Nullable
    private ImageView qrCodeImage;

    private int qrCodeImageWidth;

    private int qrCodeImageHeight;

    public static QRShowFragment newInstance(@Nullable String content) {
        Objects.requireNonNull(content);
        QRShowFragment fragment = new QRShowFragment();
        Bundle args = new Bundle();
        args.putString("content", content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.qr_show_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        qrCodeImage = view.findViewById(R.id.qr_code_image);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity parentActivity = getActivity();
        Objects.requireNonNull(parentActivity);
        viewModel = ViewModelProviders.of(parentActivity).get(QRShowViewModel.class);

        Bundle args = Objects.requireNonNull(getArguments());
        String content = args.getString("content");
        Objects.requireNonNull(viewModel);
        Objects.requireNonNull(qrCodeImage);
        Objects.requireNonNull(content);
        viewModel.showQrCodeDrawable(qrCodeImage, content);
    }
}
