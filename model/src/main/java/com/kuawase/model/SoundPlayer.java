package com.kuawase.model;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.SoundPool;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.exoplayer2.ExoPlayer;
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

    @NonNull
    private static final SoundPool soundPool = new SoundPool.Builder().build();

    @Nullable
    private static LoopingMediaSource bgm1;
    @Nullable
    private static ExoPlayer exoPlayer;
    private static int tapSoundId;
    private static int finishReadSoundId;
    private static int resultSoundId;
    private static int failedSoundId;

    private SoundPlayer() {
    }

    @NonNull
    public static SoundPlayer getInstance(@NonNull Context context) {
        if (null == singletonInstance) {
            singletonInstance = new SoundPlayer();
            exoPlayer = new SimpleExoPlayer.Builder(context).build();
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                    Util.getUserAgent(context, "Kuawase"));
            MediaSource temp = new ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse("file:///android_asset/bgm1.mp3"));
            bgm1 = new LoopingMediaSource(temp);

            AssetManager am = context.getAssets();
            try {
                AssetFileDescriptor afd = am.openFd("tap.mp3");
                tapSoundId = soundPool.load(afd, 1);
                afd = am.openFd("read_finish.mp3");
                finishReadSoundId = soundPool.load(afd, 1);
                afd = am.openFd("result.mp3");
                resultSoundId = soundPool.load(afd, 1);
                afd = am.openFd("failed.mp3");
                failedSoundId = soundPool.load(afd, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return singletonInstance;
    }

    public void playBgm1() {
        Objects.requireNonNull(exoPlayer);
        Objects.requireNonNull(bgm1);
        exoPlayer.prepare(bgm1);
        exoPlayer.setPlayWhenReady(true);
    }

    public void stopBgm() {
        Objects.requireNonNull(exoPlayer);
        exoPlayer.stop();
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

    public void playFailedSound() {
        playShortSound(failedSoundId);
    }

    private void playShortSound(int soundId) {
        soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
    }
}
