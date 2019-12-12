package com.kuawase.kuawase.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.kuawase.kuawase.R;
import com.kuawase.kuawase.screen.haikulist.HaikuListFragment;
import com.kuawase.kuawase.screen.haikulist.HaikuListViewModel;
import com.kuawase.kuawase.screen.haikusubmit.HaikuSubmitFragment;
import com.kuawase.kuawase.screen.haikusubmit.HaikuSubmitViewModel;
import com.kuawase.kuawase.screen.kukaiinput.KukaiInputFragment;
import com.kuawase.kuawase.screen.kukaiinput.KukaiInputViewModel;
import com.kuawase.kuawase.screen.modechoice.ModeChoiceFragment;
import com.kuawase.kuawase.screen.modechoice.ModeChoiceViewModel;
import com.kuawase.kuawase.screen.qrread.QRReadFragment;
import com.kuawase.kuawase.screen.qrread.QRReadViewModel;
import com.kuawase.kuawase.screen.qrshow.QRShowFragment;
import com.kuawase.kuawase.screen.result.ResultFragment;
import com.kuawase.kuawase.screen.result.ResultViewModel;
import com.kuawase.kuawase.utility.SoundPlayer;
import com.kuawase.kuawase.utility.ViewModelUtils;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ModeChoiceFragment modeChoiceFragment = ModeChoiceFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.container, modeChoiceFragment).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // BGM再生
        SoundPlayer player = new SoundPlayer(this);
        player.playBgm1();

        ModeChoiceViewModel modeChoiceViewModel = getViewModel(ModeChoiceViewModel.class);
        modeChoiceViewModel.getOnParentButtonClick().observe(this,
                event -> commitFragment(KukaiInputFragment.newInstance()));
        modeChoiceViewModel.getOnChildButtonClick().observe(this,
                event -> commitFragment(HaikuSubmitFragment.newInstance()));

        KukaiInputViewModel kukaiInputViewModel = getViewModel(KukaiInputViewModel.class);
        kukaiInputViewModel.getOnFinishInputButtonClick().observe(this, event -> {
            Integer kukaiId = event.getContentIfNotHandled();
            if (null != kukaiId) {
                commitFragment(QRReadFragment.newInstance(kukaiId));
            }
        });

        QRReadViewModel qrReadViewModel = getViewModel(QRReadViewModel.class);
        qrReadViewModel.getOnFinishReadButtonClick().observe(this, event -> {
            Integer kukaiId = event.getContentIfNotHandled();
            if (null != kukaiId) {
                commitFragment(HaikuListFragment.newInstance(kukaiId));
            }
        });

        HaikuListViewModel haikuListViewModel = getViewModel(HaikuListViewModel.class);
        haikuListViewModel.getOnFinishInputButtonClick().observe(this, event -> {
            Integer kukaiId = event.getContentIfNotHandled();
            if (null != kukaiId) {
                player.stopBgm();
                commitFragment(ResultFragment.newInstance(kukaiId));
            }
        });

        ResultViewModel resultViewModel = getViewModel(ResultViewModel.class);
        resultViewModel.getOnExitButtonClick().observe(this, event -> {
            player.playBgm1();
            commitFragment(ModeChoiceFragment.newInstance());
        });

        HaikuSubmitViewModel haikuSubmitViewModel = getViewModel(HaikuSubmitViewModel.class);
        haikuSubmitViewModel.getOnSubmitButtonClick().observe(this, event -> {
            String content = event.getContentIfNotHandled();
            if (null != content) {
                commitFragment(QRShowFragment.newInstance(content));
            }
        });
    }

    @NonNull
    private <T extends ViewModel> T getViewModel(@NonNull Class<T> viewModelClass) {
        return ViewModelUtils.provideViewModel(this, viewModelClass);
    }

    private void commitFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment).commit();
    }
}
