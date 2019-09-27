package com.kuawase.kuawase.screen.parentFuncChoice;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kuawase.kuawase.R;

public class ParentFuncChoiceFragment extends Fragment {

    private ParentFuncChoiceViewModel mViewModel;

    public static ParentFuncChoiceFragment newInstance() {
        return new ParentFuncChoiceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.parent_func_choice_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ParentFuncChoiceViewModel.class);
        // TODO: Use the ViewModel
    }

}
