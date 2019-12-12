package com.kuawase.kuawase.screen.haikusubmit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.kuawase.kuawase.R;
import com.kuawase.kuawase.utility.SoundPlayer;
import com.kuawase.kuawase.utility.ViewModelUtils;

import java.util.Objects;

public class HaikuSubmitFragment extends Fragment {
    @Nullable
    private HaikuSubmitViewModel viewModel;

    @Nullable
    private EditText haikuEdit;

    @Nullable
    private EditText authorEdit;

    @Nullable
    private Button submitButton;

    @Nullable
    private SoundPlayer soundPlayer;

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
        return inflater.inflate(R.layout.haiku_submit_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        haikuEdit = view.findViewById(R.id.haiku_edit);
        authorEdit = view.findViewById(R.id.author_edit);
        submitButton = view.findViewById(R.id.submit_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity parentActivity = Objects.requireNonNull(getActivity());
        viewModel = ViewModelUtils.provideViewModel(parentActivity, HaikuSubmitViewModel.class);
        soundPlayer = new SoundPlayer(parentActivity);

        Objects.requireNonNull(haikuEdit);
        Objects.requireNonNull(authorEdit);
        Objects.requireNonNull(submitButton);
        submitButton.setOnClickListener(l -> {
            soundPlayer.playTapSound();
            viewModel.onSubmitButtonClick(haikuEdit.getText().toString(), authorEdit.getText().toString());
        });
    }
}
