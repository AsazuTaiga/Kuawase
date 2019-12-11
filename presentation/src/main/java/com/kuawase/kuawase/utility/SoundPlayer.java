package com.kuawase.kuawase.utility;

import android.content.Context;
import android.media.SoundPool;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kuawase.kuawase.R;

public class SoundPlayer {
    // TODO: "com.kuawase.kuawase W/SoundPool:   sample XX not READY"により鳴らないケースの回避

    @Nullable
    private static SoundPlayer singletonInstance;
    @NonNull
    private static final SoundPool soundPool = new SoundPool.Builder().build();
    private static int tapSoundId;
    private static int finishReadSoundId;
    private static int resultSoundId;

    private SoundPlayer() {
    }

    @NonNull
    public static SoundPlayer newInstance(@NonNull Context context) {
        if (null == singletonInstance) {
            singletonInstance = new SoundPlayer();
        }
        tapSoundId = soundPool.load(context, R.raw.tap, 1);
        finishReadSoundId = soundPool.load(context, R.raw.read_finish, 1);
        resultSoundId = soundPool.load(context, R.raw.result, 1);
        return singletonInstance;
    }

    public void playTapSound() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        playSound(tapSoundId);
    }

    public void playFinishReadSound() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        playSound(finishReadSoundId);
    }

    public void playResultSound() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        playSound(resultSoundId);
    }

    private void playSound(int soundId) {
        soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
    }
}
