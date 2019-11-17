package com.kuawase.kuawase.screen.haikulist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.kuawase.kuawase.R;

import java.util.Objects;

public class HaikuListFragment extends Fragment {

    private HaikuListViewModel viewModel;

    public static HaikuListFragment newInstance(@Nullable Integer kukaiId) {
        Objects.requireNonNull(kukaiId);
        HaikuListFragment fragment = new HaikuListFragment();
        Bundle args = new Bundle();
        args.putInt("kukaiId", kukaiId);
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
        viewModel = ViewModelProviders.of(this).get(HaikuListViewModel.class);
        // TODO: Use the ViewModel
    }

}
