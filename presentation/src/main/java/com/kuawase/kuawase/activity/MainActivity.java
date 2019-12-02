package com.kuawase.kuawase.activity;

import android.os.Bundle;

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
import com.kuawase.kuawase.screen.result.ResultViewModel;
import com.kuawase.kuawase.utility.ViewModelUtils;

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

        ResultViewModel resultViewModel = getViewModel(ResultViewModel.class);

        HaikuSubmitViewModel haikuSubmitViewModel = getViewModel(HaikuSubmitViewModel.class);
        haikuSubmitViewModel.getOnSubmitButtonClick().observe
                (this, event -> launchFragment(QRShowFragment.newInstance(event.getContentIfNotHandled())));
    }

    private void launchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment).commit();
    }

    private <T extends ViewModel> T getViewModel(Class<T> viewModelClass) {
        return ViewModelUtils.provideViewModel(this, viewModelClass);
    }
}
