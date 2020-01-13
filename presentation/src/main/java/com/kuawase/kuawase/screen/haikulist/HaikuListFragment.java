package com.kuawase.kuawase.screen.haikulist;

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
import com.kuawase.kuawase.databinding.HaikuListFragmentBinding;
import com.kuawase.kuawase.utility.ViewModelUtils;

import java.util.Objects;

public class HaikuListFragment extends Fragment {
    private static final String KEY = "kukaiId";

    @Nullable
    private HaikuListFragmentBinding binding;

    public HaikuListFragment() {
    }

    @NonNull
    public static HaikuListFragment newInstance(@NonNull Integer kukaiId) {
        HaikuListFragment fragment = new HaikuListFragment();
        Bundle args = new Bundle();
        args.putInt(KEY, kukaiId);
        return fragment;
    }

    @Override
    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.haiku_list_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity parentActivity = Objects.requireNonNull(getActivity());
        HaikuListViewModel viewModel =
                ViewModelUtils.provideViewModel(parentActivity, HaikuListViewModel.class);
        Objects.requireNonNull(binding);
        binding.setLifecycleOwner(parentActivity);
        binding.setViewModel(viewModel);

        Bundle args = new Bundle();
        viewModel.setKukaiId(args.getInt(KEY));

        viewModel.prepareSoundPlayer(parentActivity);
        binding.haikuList.setAdapter(new HaikuListAdapter(parentActivity, viewModel.getRandomHaikuInfos()));
    }
}
