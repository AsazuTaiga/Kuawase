package com.kuawase.kuawase.screen.modechoice;

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
import com.kuawase.kuawase.databinding.ModeChoiceFragmentBinding;
import com.kuawase.kuawase.utility.ViewModelUtils;

import java.util.Objects;

public class ModeChoiceFragment extends Fragment {
    @Nullable
    private ModeChoiceFragmentBinding binding;

    public ModeChoiceFragment() {
    }

    @NonNull
    public static ModeChoiceFragment newInstance() {
        return new ModeChoiceFragment();
    }

    @Override
    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.mode_choice_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity parentActivity = Objects.requireNonNull(getActivity());
        ModeChoiceViewModel viewModel = ViewModelUtils.provideViewModel
                (parentActivity, ModeChoiceViewModel.class);
        viewModel.prepareSoundPlayer(parentActivity);

        Objects.requireNonNull(binding);
        binding.setLifecycleOwner(parentActivity);
        binding.setViewModel(viewModel);
    }
}