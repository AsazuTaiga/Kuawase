package com.kuawase.kuawase;

import android.app.Application;

import com.kuawase.model.SoundPlayer;

public class KuawaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SoundPlayer.getInstance(getApplicationContext());
    }
}
