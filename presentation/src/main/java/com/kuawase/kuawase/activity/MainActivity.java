package com.kuawase.kuawase.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.kuawase.kuawase.R;
import com.kuawase.kuawase.screen.kukaiInput.KukaiInputFragment;
import com.kuawase.kuawase.screen.modeChoice.ModeChoiceFragment;
import com.kuawase.kuawase.screen.modeChoice.ModeChoiceViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ModeChoiceFragment modeChoiceFragment = ModeChoiceFragment.newInstance();
        getSupportFragmentManager().beginTransaction().
                add(R.id.container_view, modeChoiceFragment).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        ModeChoiceViewModel modeChoiceViewModel = ViewModelProviders
                .of(this)
                .get(ModeChoiceViewModel.class);
        modeChoiceViewModel.getOnParentButtonClick().observe(this, event ->
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container_view, KukaiInputFragment.newInstance())
                        .commit());
    }
}
