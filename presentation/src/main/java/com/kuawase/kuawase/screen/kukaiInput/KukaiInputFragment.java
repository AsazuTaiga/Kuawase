package com.kuawase.kuawase.screen.kukaiInput;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kuawase.kuawase.R;

public class KukaiInputFragment extends Fragment {

    private KukaiInputViewModel mViewModel;

    public static KukaiInputFragment newInstance() {
        return new KukaiInputFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.kukai_input_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(KukaiInputViewModel.class);
        // TODO: Use the ViewModel
    }

}
