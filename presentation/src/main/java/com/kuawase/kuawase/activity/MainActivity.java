package com.kuawase.kuawase.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

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
import com.kuawase.kuawase.screen.qrshow.QRShowViewModel;
import com.kuawase.kuawase.screen.result.ResultViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ModeChoiceFragment modeChoiceFragment = ModeChoiceFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.container, modeChoiceFragment).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        ModeChoiceViewModel modeChoiceViewModel = ViewModelProviders.of(this).get(ModeChoiceViewModel.class);
        modeChoiceViewModel.getOnParentButtonClick().observe
                (this, event -> launchFragment(KukaiInputFragment.newInstance()));
        modeChoiceViewModel.getOnChildButtonClick().observe
                (this, event -> launchFragment(HaikuSubmitFragment.newInstance()));

        // 親モード
        KukaiInputViewModel kukaiInputViewModel = ViewModelProviders.of(this).get(KukaiInputViewModel.class);
        kukaiInputViewModel.getOnFinishInputButtonClick().observe
                (this, event -> launchFragment(QRReadFragment.newInstance(event.getContentIfNotHandled())));

        QRReadViewModel qrReadViewModel = ViewModelProviders.of(this).get(QRReadViewModel.class);
        qrReadViewModel.getOnFinishReadButtonClick().observe
                (this, event -> launchFragment(HaikuListFragment.newInstance(event.getContentIfNotHandled())));

        HaikuListViewModel haikuListViewModel = ViewModelProviders.of(this).get(HaikuListViewModel.class);

        ResultViewModel resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

        // 子モード
        HaikuSubmitViewModel haikuSubmitViewModel = ViewModelProviders.of(this).get(HaikuSubmitViewModel.class);
        haikuSubmitViewModel.getOnSubmitButtonClick().observe
                (this, event -> launchFragment(QRShowFragment.newInstance(event.getContentIfNotHandled())));

        QRShowViewModel qrShowViewModel = ViewModelProviders.of(this).get(QRShowViewModel.class);
    }

    private void launchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment).commit();
    }
}
