package com.kuawase.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.SoundPool;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.IOException;
import java.util.Objects;

public class SoundPlayer {
    @Nullable
    private static SoundPlayer singletonInstance;

    @Nullable
    private static SimpleExoPlayer exoPlayer;

    @Nullable
    private static DataSource.Factory dataSourceFactory;

    @Nullable
    private static LoopingMediaSource bgm1;

    @NonNull
    private static SoundPool soundPool = new SoundPool.Builder().build();
    private static int tapSoundId;
    private static int finishReadSoundId;
    private static int resultSoundId;

    private SoundPlayer() {
    }

    public static SoundPlayer newInstance(@NonNull Context context) {
        if (null == singletonInstance) {
            singletonInstance = new SoundPlayer();
        }

        exoPlayer = new SimpleExoPlayer.Builder(context).build();
        dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "Kuawase"));

        try {
            AssetManager am = context.getAssets();
            tapSoundId = soundPool.load(am.openFd("tap.mp3"), 1);
            finishReadSoundId = soundPool.load(am.openFd("finish_read.mp3"), 1);
            resultSoundId = soundPool.load(am.openFd("result.mp3"), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return singletonInstance;
    }

    public void playBgm1() {
        Objects.requireNonNull(exoPlayer);
        if (null == bgm1) {
            MediaSource temp = new ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse("file:///android_asset/bgm1.mp3"));
            bgm1 = new LoopingMediaSource(temp);
        }
        exoPlayer.prepare(bgm1);
        exoPlayer.setPlayWhenReady(true);
    }

    public void stopBgm() {
        if (null != exoPlayer && exoPlayer.isPlaying()) {
            exoPlayer.setPlayWhenReady(false);
        }
    }

    public void playTapSound() {
        playShortSound(tapSoundId);
    }

    public void playFinishReadSound() {
        playShortSound(finishReadSoundId);
    }

    public void playResultSound() {
        playShortSound(resultSoundId);
    }

    private void playShortSound(int soundId) {
        soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
    }
}
