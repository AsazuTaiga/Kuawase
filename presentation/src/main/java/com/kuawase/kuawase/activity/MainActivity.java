package com.kuawase.kuawase.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.kuawase.kuawase.R;
import com.kuawase.kuawase.screen.haikulist.HaikuListViewModel;
import com.kuawase.kuawase.screen.haikusubmit.HaikuSubmitFragment;
import com.kuawase.kuawase.screen.haikusubmit.HaikuSubmittViewModel;
import com.kuawase.kuawase.screen.kukaiinput.KukaiInputFragment;
import com.kuawase.kuawase.screen.kukaiinput.KukaiInputViewModel;
import com.kuawase.kuawase.screen.modechoice.ModeChoiceFragment;
import com.kuawase.kuawase.screen.modechoice.ModeChoiceViewModel;
import com.kuawase.kuawase.screen.qeshow.QRShowFragment;
import com.kuawase.kuawase.screen.qeshow.QRShowViewModel;
import com.kuawase.kuawase.screen.qrread.QRReadViewModel;
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

        // モード選択画面
        ModeChoiceViewModel modeChoiceViewModel = ViewModelProviders.of(this).get(ModeChoiceViewModel.class);
        modeChoiceViewModel.getOnParentButtonClick().observe
                (this, event -> launchFragment(KukaiInputFragment.newInstance()));
        modeChoiceViewModel.getOnChildButtonClick().observe
                (this, event -> launchFragment(HaikuSubmitFragment.newInstance()));

        // 親モード
        // 句会情報入力画面
        KukaiInputViewModel kukaiInputViewModel = ViewModelProviders.of(this).get(KukaiInputViewModel.class);

        // QRコード読み取り画面
        QRReadViewModel qrReadViewModel = ViewModelProviders.of(this).get(QRReadViewModel.class);

        // 投句一覧(選句)画面
        HaikuListViewModel haikuListViewModel = ViewModelProviders.of(this).get(HaikuListViewModel.class);

        // 結果表示画面
        ResultViewModel resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

        // 子モード
        // 俳句投稿画面
        HaikuSubmittViewModel haikuSubmittViewModel = ViewModelProviders.of(this).get(HaikuSubmittViewModel.class);
        haikuSubmittViewModel.getOnSubmitButtonClick().observe
                (this, event -> launchFragment(QRShowFragment.newInstance()));

        // QRコード表示画面
        QRShowViewModel qrShowViewModel = ViewModelProviders.of(this).get(QRShowViewModel.class);
    }

    private void launchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment).commit();
    }
}
