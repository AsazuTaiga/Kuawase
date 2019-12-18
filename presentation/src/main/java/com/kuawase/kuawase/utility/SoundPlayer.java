package com.kuawase.kuawase.utility;

import android.content.Context;
import android.media.SoundPool;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.kuawase.kuawase.R;

public class SoundPlayer {
    private static final String BGM_1_URI = "file:///android_asset/bgm1.mp3";

    @NonNull
    private final SimpleExoPlayer exoPlayer;
    @NonNull
    private final DataSource.Factory dataSourceFactory;

    @NonNull
    private final SoundPool soundPool = new SoundPool.Builder().build();
    private static int tapSoundId;
    private static int finishReadSoundId;
    private static int resultSoundId;
    private static int failedSoundId;

    public SoundPlayer(@NonNull Context context) {
        exoPlayer = new SimpleExoPlayer.Builder(context).build();
        dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "Kuawase"));
        tapSoundId = soundPool.load(context, R.raw.tap, 1);
        finishReadSoundId = soundPool.load(context, R.raw.read_finish, 1);
        resultSoundId = soundPool.load(context, R.raw.result, 1);
        failedSoundId = soundPool.load(context, R.raw.failed, 1);
    }

    public void playBgm1() {
        MediaSource bgm1 = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(BGM_1_URI));
        LoopingMediaSource loopBgm1 = new LoopingMediaSource(bgm1);
        exoPlayer.prepare(loopBgm1);
        exoPlayer.setPlayWhenReady(true);
    }

    public void stopBgm() {
        if (exoPlayer.isPlaying()) {
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

    public void playFailedSound() {
        playShortSound(failedSoundId);
    }

    private void playShortSound(int soundId) {
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
            }
        });
    }
}
