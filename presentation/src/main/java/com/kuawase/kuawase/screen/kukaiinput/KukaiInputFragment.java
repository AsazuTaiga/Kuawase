package com.kuawase.kuawase.screen.kukaiinput;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.kuawase.kuawase.R;
import com.kuawase.kuawase.utility.ViewModelUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class KukaiInputFragment extends Fragment {
    @Nullable
    private KukaiInputViewModel viewModel;

    @Nullable
    private EditText kukaiNameEdit;

    @Nullable
    private Button startDateButton;

    @Nullable
    private TextView startDateText;

    @Nullable
    private Button endDateButton;

    @Nullable
    private TextView endDateText;

    @Nullable
    private Button finishInputButton;

    private KukaiInputFragment() {
    }

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
        startDateText = view.findViewById(R.id.start_date_text);
        endDateButton = view.findViewById(R.id.end_date_button);
        endDateText = view.findViewById(R.id.end_date_text);
        finishInputButton = view.findViewById(R.id.finish_input_button);
    }

    @NonNull
    private Date parse(@NonNull String string) {
        String pattern = "yyyy年MM月dd日";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.JAPAN);
        Date date = new Date();
        try {
            date = sdf.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(date);
        return date;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelUtils.provideViewModel(Objects.requireNonNull(getActivity()), KukaiInputViewModel.class);
        FragmentManager fragmentManager = Objects.requireNonNull(getFragmentManager());
        SelectDateFragment startDateFragment = getSelectDateFragment(Objects.requireNonNull(startDateText));
        SelectDateFragment endDateFragment = getSelectDateFragment(Objects.requireNonNull(endDateText));

        Objects.requireNonNull(startDateButton);
        Objects.requireNonNull(startDateText);
        startDateButton.setOnClickListener(l -> startDateFragment.show(fragmentManager, null));

        Objects.requireNonNull(endDateButton);
        Objects.requireNonNull(endDateText);
        endDateButton.setOnClickListener(l -> endDateFragment.show(fragmentManager, null));

        Objects.requireNonNull(kukaiNameEdit);
        Objects.requireNonNull(finishInputButton);
        finishInputButton.setOnClickListener(l -> viewModel.onFinishInputButtonClick(kukaiNameEdit.getText().toString(),
                parse(startDateText.getText().toString()), parse(endDateText.getText().toString())));
    }

    @NonNull
    private SelectDateFragment getSelectDateFragment(@NonNull TextView dateText) {
        SelectDateFragment fragment = new SelectDateFragment();
        fragment.setTextView(dateText);
        return fragment;
    }
}
