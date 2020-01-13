package com.kuawase.kuawase.screen.result;

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
import com.kuawase.kuawase.databinding.ResultFragmentBinding;
import com.kuawase.kuawase.utility.ViewModelUtils;

import java.util.Objects;

public class ResultFragment extends Fragment {
    private static final String KEY = "kukaiId";
    @Nullable
    private ResultFragmentBinding binding;

    public ResultFragment() {
    }

    @NonNull
    public static ResultFragment newInstance(@NonNull Integer kukaiId) {
        Bundle args = new Bundle();
        args.putInt(KEY, kukaiId);
        ResultFragment fragment = new ResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.result_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity parentActivity = Objects.requireNonNull(getActivity());
        ResultViewModel viewModel = ViewModelUtils.provideViewModel(parentActivity, ResultViewModel.class);
        Objects.requireNonNull(binding);
        binding.setLifecycleOwner(parentActivity);
        binding.setViewModel(viewModel);

        viewModel.prepareSoundPlayer(parentActivity);

        Bundle args = Objects.requireNonNull(getArguments());
        viewModel.setKukaiId(args.getInt(KEY));

        binding.resultList.setAdapter(new ResultAdapter(parentActivity, viewModel.getSortedHaikuInfos()));
    }
}
