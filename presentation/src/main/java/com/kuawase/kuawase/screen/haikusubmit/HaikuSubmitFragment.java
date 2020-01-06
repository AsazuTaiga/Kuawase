package com.kuawase.kuawase.screen.haikusubmit;

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
import com.kuawase.kuawase.databinding.HaikuSubmitFragmentBinding;
import com.kuawase.kuawase.utility.ViewModelUtils;

import java.util.Objects;

public class HaikuSubmitFragment extends Fragment {
    @Nullable
    private HaikuSubmitFragmentBinding binding;

    private HaikuSubmitFragment() {
    }

    @NonNull
    public static HaikuSubmitFragment newInstance() {
        return new HaikuSubmitFragment();
    }

    @Override
    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.haiku_submit_fragment,
                container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity parentActivity = Objects.requireNonNull(getActivity());
        HaikuSubmitViewModel viewModel = ViewModelUtils.provideViewModel(parentActivity, HaikuSubmitViewModel.class);
        Objects.requireNonNull(binding);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(parentActivity);

        viewModel.prepareSoundPlayer(parentActivity);
    }
}
