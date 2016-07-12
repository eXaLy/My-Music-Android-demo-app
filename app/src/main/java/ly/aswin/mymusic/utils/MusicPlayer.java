package ly.aswin.mymusic.utils;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Aswin on 12-7-2016
 */
public class MusicPlayer {

    private MediaPlayer mediaPlayer;
    private boolean isPrepared;

    public MusicPlayer(String url) {
        this.mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    isPrepared = true;
                }
            });
        } catch (IOException e) {
            Log.e("MEDIA PLAYER", "URL not valid");
        }
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public void play() {
        if (isPrepared && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void pause() {
        if (isPrepared && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }
}
