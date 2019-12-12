package com.kuawase.kuawase.screen.qrshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kuawase.kuawase.R;
import com.kuawase.kuawase.utility.ViewModelUtils;

import java.util.Objects;

public class QRShowFragment extends Fragment {
    private static final String KEY = "content";

    @Nullable
    private QRShowViewModel viewModel;

    @Nullable
    private ImageView qrCodeImage;

    private QRShowFragment() {
    }

    @NonNull
    public static QRShowFragment newInstance(@NonNull String content) {
        QRShowFragment fragment = new QRShowFragment();
        Bundle args = new Bundle();
        args.putString(KEY, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    @NonNull
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
        viewModel = ViewModelUtils.provideViewModel(Objects.requireNonNull(getActivity()), QRShowViewModel.class);
        Bundle args = Objects.requireNonNull(getArguments());
        String content = args.getString(KEY);
        Objects.requireNonNull(viewModel);
        Objects.requireNonNull(qrCodeImage);
        Objects.requireNonNull(content);
        viewModel.showQrCodeDrawable(qrCodeImage, content);
    }
}
