package com.kuawase.kuawase.screen.modeChoice;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kuawase.kuawase.R;

public class ModeChoiceFragment extends Fragment {

    @Nullable
    private ModeChoiceViewModel mViewModel;

    @Nullable
    private Button parentButton;

    @Nullable
    private Button childButton;

    @NonNull
    public static ModeChoiceFragment newInstance() {
        return new ModeChoiceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mode_choice_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parentButton = view.findViewById(R.id.parent_button);
        childButton = view.findViewById(R.id.child_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ModeChoiceViewModel.class);
        if (null != parentButton) {
            parentButton.setOnClickListener(view -> mViewModel.transitToParentMode());
        }
        if (null != childButton) {
            childButton.setOnClickListener(view -> mViewModel.transitToChildMode());
        }
    }
}
