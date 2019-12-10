package com.kuawase.kuawase.utility;

import android.content.Context;
import android.media.SoundPool;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kuawase.kuawase.R;

public class SoundPlayer {
    // TODO: "com.kuawase.kuawase W/SoundPool:   sample XX not READY"により鳴らないケースの回避

    @NonNull
    private static final SoundPool soundPool = new SoundPool.Builder().build();
    @Nullable
    private static SoundPlayer singletonInstance;
    private static int tapSoundId;
    private static int resultSoundId;

    private SoundPlayer() {
    }

    @NonNull
    public static SoundPlayer newInstance(@NonNull Context context) {
        if (null == singletonInstance) {
            singletonInstance = new SoundPlayer();
        }
        tapSoundId = soundPool.load(context, R.raw.tap, 1);
        resultSoundId = soundPool.load(context, R.raw.result, 1);
        return singletonInstance;
    }

    public void playTapSound() {
        soundPool.play(tapSoundId, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public void playResultSound() {
        soundPool.play(resultSoundId, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public void releasSoundPool() {
        soundPool.release();
    }

}
