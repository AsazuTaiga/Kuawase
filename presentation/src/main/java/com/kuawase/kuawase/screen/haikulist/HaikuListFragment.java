package com.kuawase.kuawase.screen.haikulist;

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

public class HaikuListFragment extends Fragment {
    @NonNull
    private static final String KEY = "kukaiId";

    @Nullable
    private HaikuListViewModel viewModel;

    private HaikuListFragment() {
    }

    public static HaikuListFragment newInstance(@Nullable Integer kukaiId) {
        Objects.requireNonNull(kukaiId);
        HaikuListFragment fragment = new HaikuListFragment();
        Bundle args = new Bundle();
        args.putInt(KEY, kukaiId);
        return new HaikuListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.haiku_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelUtils.provideViewModel(Objects.requireNonNull(getActivity()), HaikuListViewModel.class);
    }
}
