package com.kuawase.kuawase.screen.haikuSubmitting;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kuawase.kuawase.R;

public class HaikuSubmittingFragment extends Fragment {

    private HaikuSubmittingViewModel mViewModel;

    public static HaikuSubmittingFragment newInstance() {
        return new HaikuSubmittingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.haiku_submitting_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HaikuSubmittingViewModel.class);
        // TODO: Use the ViewModel
    }

}
