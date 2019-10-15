package com.kuawase.kuawase.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.kuawase.kuawase.R;
import com.kuawase.kuawase.screen.haikuSubmitting.HaikuSubmittingFragment;
import com.kuawase.kuawase.screen.kukaiInput.KukaiInputFragment;
import com.kuawase.kuawase.screen.modeChoice.ModeChoiceFragment;
import com.kuawase.kuawase.screen.modeChoice.ModeChoiceViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ModeChoiceFragment modeChoiceFragment = ModeChoiceFragment.newInstance();
        launchFragment(modeChoiceFragment);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ModeChoiceViewModel modeChoiceViewModel = ViewModelProviders.of(this).get(ModeChoiceViewModel.class);
        modeChoiceViewModel.getOnParentButtonClick().observe(this, event -> launchFragment(KukaiInputFragment.newInstance()));
        modeChoiceViewModel.getOnChildButtonClick().observe(this, event -> launchFragment(HaikuSubmittingFragment.newInstance()));
    }

    private void launchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container_view, fragment).commit();
    }
}
