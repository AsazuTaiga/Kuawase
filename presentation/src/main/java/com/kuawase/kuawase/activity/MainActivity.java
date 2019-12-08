package com.kuawase.kuawase.activity;

import android.media.SoundPool;
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

        ModeChoiceViewModel modeChoiceViewModel = getViewModel(ModeChoiceViewModel.class);
        modeChoiceViewModel.getOnParentButtonClick().observe
                (this, event -> launchFragment(KukaiInputFragment.newInstance()));
        modeChoiceViewModel.getOnChildButtonClick().observe
                (this, event -> launchFragment(HaikuSubmitFragment.newInstance()));

        KukaiInputViewModel kukaiInputViewModel = getViewModel(KukaiInputViewModel.class);
        kukaiInputViewModel.getOnFinishInputButtonClick().observe
                (this, event -> launchFragment(QRReadFragment.newInstance(event.getContentIfNotHandled())));

        QRReadViewModel qrReadViewModel = getViewModel(QRReadViewModel.class);
        qrReadViewModel.getOnFinishReadButtonClick().observe
                (this, event -> launchFragment(HaikuListFragment.newInstance(event.getContentIfNotHandled())));

        HaikuListViewModel haikuListViewModel = getViewModel(HaikuListViewModel.class);
        haikuListViewModel.getOnFinishInputButtonClick().observe
                (this, event -> launchFragment(ResultFragment.newInstance(event.getContentIfNotHandled())));

        HaikuSubmitViewModel haikuSubmitViewModel = getViewModel(HaikuSubmitViewModel.class);
        haikuSubmitViewModel.getOnSubmitButtonClick().observe
                (this, event -> launchFragment(QRShowFragment.newInstance(event.getContentIfNotHandled())));

        ResultViewModel resultViewModel = getViewModel(ResultViewModel.class);
        resultViewModel.getOnExitButtonClick().observe
                (this, event -> launchFragment(ModeChoiceFragment.newInstance()));
    }

    @NonNull
    private <T extends ViewModel> T getViewModel(@NonNull Class<T> viewModelClass) {
        return ViewModelUtils.provideViewModel(this, viewModelClass);
    }

    private void launchFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment).commit();

        if (ResultFragment.class == fragment.getClass()) {
            playResultSound();
        }
    }

    private void playResultSound() {
        SoundPool.Builder builder = new SoundPool.Builder();
        SoundPool soundPool = builder.build();
        int soundId = soundPool.load(getApplicationContext(), R.raw.result_intro, 1);
        soundPool.setOnLoadCompleteListener((soundPool1, sampleId, status) -> {
            if (0 == status) { // ロード成功時
                soundPool1.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
            }
        });
    }
}
