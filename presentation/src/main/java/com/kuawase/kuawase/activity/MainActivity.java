package com.kuawase.kuawase.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

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
import com.kuawase.kuawase.utility.ViewModelUtils;
import com.kuawase.model.SoundPlayer;

public class MainActivity extends AppCompatActivity {
    private SoundPlayer soundPlayer;
    private NavController navController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soundPlayer = SoundPlayer.getInstance(this);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.setGraph(R.navigation.nav_graph);
        commitFragment(NavHostFragment.create(R.navigation.nav_graph));
    }

    @Override
    protected void onStart() {
        super.onStart();

        ModeChoiceViewModel modeChoiceViewModel = getViewModel(ModeChoiceViewModel.class);
        modeChoiceViewModel.getOnParentButtonClick().observe(this, event -> {
            navController.navigate(R.id.action_modeChoiceFragment_to_kukaiInputFragment);
            soundPlayer.playBgm1();
        });
        modeChoiceViewModel.getOnChildButtonClick().observe(this,
                event -> navController.navigate(R.id.action_modeChoiceFragment_to_haikuSubmitFragment));

        KukaiInputViewModel kukaiInputViewModel = getViewModel(KukaiInputViewModel.class);
        kukaiInputViewModel.getOnFinishInputButtonClick().observe(this, event -> {
            Integer kukaiId = event.getContentIfNotHandled();
            if (null != kukaiId) {
                navController.navigate(R.id.action_kukaiInputFragment_to_QRReadFragment);
            }
        });

        QRReadViewModel qrReadViewModel = getViewModel(QRReadViewModel.class);
        qrReadViewModel.getOnFinishReadButtonClick().observe(this, event -> {
            Integer kukaiId = event.getContentIfNotHandled();
            if (null != kukaiId) {
                navController.navigate(R.id.action_QRReadFragment_to_haikuListFragment);
            }
        });

        HaikuListViewModel haikuListViewModel = getViewModel(HaikuListViewModel.class);
        haikuListViewModel.getOnFinishInputButtonClick().observe(this, event -> {
            Integer kukaiId = event.getContentIfNotHandled();
            if (null != kukaiId) {
                soundPlayer.stopBgm();
                navController.navigate(R.id.action_haikuListFragment_to_resultFragment);
            }
        });

        ResultViewModel resultViewModel = getViewModel(ResultViewModel.class);
        resultViewModel.getOnExitButtonClick().observe(this,
                event -> navController.navigate(R.id.action_resultFragment_to_modeChoiceFragment));

        HaikuSubmitViewModel haikuSubmitViewModel = getViewModel(HaikuSubmitViewModel.class);
        haikuSubmitViewModel.getOnSubmitButtonClick().observe(this, event -> {
            String content = event.getContentIfNotHandled();
            if (null != content) {
                navController.navigate(R.id.action_haikuSubmitFragment_to_QRShowFragment);
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
