package com.kuawase.kuawase.screen.qrread;

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

public class QRReadFragment extends Fragment {

    @Nullable
    private QRReadViewModel viewModel;

    private int kukaiId;

    @Nullable
    private ImageView QRCodeView;

    public static QRReadFragment newInstance(int kukaiId) {
        QRReadFragment fragment = new QRReadFragment();
        Bundle args = fragment.getArguments();
        Objects.requireNonNull(args);
        args.putInt("kukaiId", kukaiId);
        return new QRReadFragment();
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
        QRCodeView = view.findViewById(R.id.qr_code_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity parentActivity = getActivity();
        Objects.requireNonNull(parentActivity);
        viewModel = ViewModelProviders.of(parentActivity).get(QRReadViewModel.class);

        Objects.requireNonNull(QRCodeView);
    }
}
