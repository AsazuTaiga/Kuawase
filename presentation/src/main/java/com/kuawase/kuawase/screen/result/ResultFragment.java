package com.kuawase.kuawase.screen.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.kuawase.kuawase.R;
import com.kuawase.kuawase.utility.ViewModelUtils;

import java.util.Objects;

public class ResultFragment extends Fragment {
    private static final String KEY = "kukaiId";

    @Nullable
    private ListView resultList;

    @Nullable
    private Button exitButton;

    @Nullable
    private ResultViewModel viewModel;

    private ResultFragment() {
    }

    @NonNull
    public static ResultFragment newInstance(@Nullable Integer kukaiId) {
        Objects.requireNonNull(kukaiId);
        Bundle args = new Bundle();
        args.putInt(KEY, kukaiId);
        ResultFragment fragment = new ResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    @NonNull
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.result_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        resultList = view.findViewById(R.id.result_list);
        exitButton = view.findViewById(R.id.exit_button);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity parentActivity = Objects.requireNonNull(getActivity());
        viewModel = ViewModelUtils.provideViewModel(parentActivity, ResultViewModel.class);

        Bundle args = Objects.requireNonNull(getArguments());
        viewModel.setKukaiId(args.getInt(KEY));

        Objects.requireNonNull(resultList);
        resultList.setAdapter(new ResultAdapter(parentActivity, viewModel.getSortedHaikuInfos()));

        Objects.requireNonNull(exitButton);
        exitButton.setOnClickListener(l -> viewModel.onExitButtonClick());
    }
}
