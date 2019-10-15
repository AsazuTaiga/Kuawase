package com.kuawase.kuawase.screen.modeChoice;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kuawase.kuawase.R;

import java.util.Objects;

public class ModeChoiceFragment extends Fragment {

    @Nullable
    private ModeChoiceViewModel viewModel;

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
        Objects.requireNonNull(getActivity());
        Objects.requireNonNull(parentButton);
        Objects.requireNonNull(childButton);
        viewModel = ViewModelProviders.of(getActivity()).get(ModeChoiceViewModel.class);
        parentButton.setOnClickListener(view -> viewModel.onParentButtonClick());
        childButton.setOnClickListener(view -> viewModel.onChildButtonClick());
    }
}
