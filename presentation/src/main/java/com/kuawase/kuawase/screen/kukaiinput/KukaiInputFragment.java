package com.kuawase.kuawase.screen.kukaiinput;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.kuawase.kuawase.R;
import com.kuawase.kuawase.utility.SelectDateFragment;

public class KukaiInputFragment extends Fragment {
    @Nullable
    private KukaiInputViewModel viewModel;

    @Nullable
    private EditText kukaiNameEdit;

    @Nullable
    private Button startDateButton;

    @Nullable
    private Button endDateButton;

    public static KukaiInputFragment newInstance() {
        return new KukaiInputFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.kukai_input_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        kukaiNameEdit = view.findViewById(R.id.kukai_name_edit);
        startDateButton = view.findViewById(R.id.start_date_button);
        endDateButton = view.findViewById(R.id.end_date_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(KukaiInputViewModel.class);

    }

}
