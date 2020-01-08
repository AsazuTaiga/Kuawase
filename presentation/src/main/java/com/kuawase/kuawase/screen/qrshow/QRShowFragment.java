package com.kuawase.kuawase.screen.qrshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.kuawase.kuawase.R;
import com.kuawase.kuawase.databinding.QrShowFragmentBinding;
import com.kuawase.kuawase.utility.ViewModelUtils;

import java.util.Objects;

public class QRShowFragment extends Fragment {
    private static final String KEY = "content";
    @Nullable
    private QrShowFragmentBinding binding;

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
        binding = DataBindingUtil.inflate(inflater,
                R.layout.qr_show_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = Objects.requireNonNull(getArguments());
        String content = Objects.requireNonNull(args.getString(KEY));
        FragmentActivity parentActivity = Objects.requireNonNull(getActivity());
        QRShowViewModel viewModel = ViewModelUtils.provideViewModel(parentActivity, QRShowViewModel.class);

        Objects.requireNonNull(binding);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(parentActivity);
        viewModel.setQrImageLiveDataFromString(content);
    }
}
