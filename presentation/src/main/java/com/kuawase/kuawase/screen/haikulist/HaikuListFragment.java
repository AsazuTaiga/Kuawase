package com.kuawase.kuawase.screen.haikulist;

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

public class HaikuListFragment extends Fragment {
    @NonNull
    private static final String KEY = "kukaiId";

    @Nullable
    private HaikuListViewModel viewModel;

    @Nullable
    private ListView haikuList;

    @Nullable
    private Button finishVoteButton;

    private HaikuListFragment() {
    }

    public static HaikuListFragment newInstance(@Nullable Integer kukaiId) {
        Objects.requireNonNull(kukaiId);
        HaikuListFragment fragment = new HaikuListFragment();
        Bundle args = new Bundle();
        args.putInt(KEY, kukaiId);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.haiku_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        haikuList = view.findViewById(R.id.haiku_list);
        finishVoteButton = view.findViewById(R.id.finish_vote_button);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity parentActivity = Objects.requireNonNull(getActivity());
        viewModel = ViewModelUtils.provideViewModel(parentActivity, HaikuListViewModel.class);

        Bundle args = new Bundle();
        viewModel.setKukaiId(args.getInt(KEY));

        Objects.requireNonNull(haikuList);
        haikuList.setAdapter(new HaikuListAdapter(parentActivity, viewModel.getKukaiInfo().getHaikuInfos()));

        Objects.requireNonNull(finishVoteButton);
        finishVoteButton.setOnClickListener(l -> viewModel.onFinishVoteButtonClick());
    }
}
