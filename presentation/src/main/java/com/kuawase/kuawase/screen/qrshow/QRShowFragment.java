package com.kuawase.kuawase.screen.qrshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.kuawase.kuawase.R;

import java.util.Objects;

public class QRShowFragment extends Fragment {
    @Nullable
    private QRShowViewModel viewModel;

    @Nullable
    private SurfaceView qrCodeView;

    public static QRShowFragment newInstance(@NonNull String[] contents) {
        QRShowFragment fragment = new QRShowFragment();
        Bundle args = new Bundle();
        args.putString("haiku", contents[0]);
        args.putString("author", contents[1]);
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
        qrCodeView = view.findViewById(R.id.qr_code_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(QRShowViewModel.class);
        Objects.requireNonNull(viewModel);

        Bundle args = Objects.requireNonNull(getArguments());
        viewModel.haiku.setValue(args.getString("haiku"));
        viewModel.author.setValue(args.getString("author"));

        // TODO:Show QR code.
    }
}
