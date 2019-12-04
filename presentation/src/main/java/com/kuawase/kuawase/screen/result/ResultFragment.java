package com.kuawase.kuawase.screen.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kuawase.kuawase.R;
import com.kuawase.kuawase.utility.ViewModelUtils;

import java.util.Objects;

public class ResultFragment extends Fragment {
    @Nullable
    private ResultViewModel mViewModel;

    private ResultFragment() {
    }

    @NonNull
    public static ResultFragment newInstance(@Nullable Integer kukaiId) {
        Objects.requireNonNull(kukaiId);
        return new ResultFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.result_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelUtils.provideViewModel(Objects.requireNonNull(getActivity()), ResultViewModel.class);
        // TODO: Use the ViewModel
    }
}
