package com.kuawase.kuawase.screen.modeChoice;

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

    private ModeChoiceViewModel mViewModel;
    private Button parentButton;
    private Button childButton;

    public static ModeChoiceFragment newInstance() {
        return new ModeChoiceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mode_choice_fragment, container, false);
        parentButton = rootView.findViewById(R.id.parent_button);
        childButton = rootView.findViewById(R.id.child_button);
        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ModeChoiceViewModel.class);
        parentButton.setOnClickListener(v -> mViewModel.transitToParentMode());
        childButton.setOnClickListener(v -> mViewModel.transitToChildMode());
    }

}
