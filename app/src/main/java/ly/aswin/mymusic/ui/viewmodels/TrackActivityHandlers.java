package ly.aswin.mymusic.ui.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;

import ly.aswin.mymusic.BR;
import ly.aswin.mymusic.R;
import ly.aswin.mymusic.utils.MusicPlayer;

/**
 * Created by Aswin on 12-7-2016
 */
public class TrackActivityHandlers extends BaseObservable {

    private boolean isPlaying;
    private MusicPlayer musicPlayer;

    public TrackActivityHandlers(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    @Bindable
    public boolean getIsPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        if (musicPlayer.isPrepared()) {
            isPlaying = playing;
            if (isPlaying) {
                musicPlayer.play();
            } else {
                musicPlayer.pause();
            }
            notifyPropertyChanged(BR.isPlaying);
        }
    }

    public void onClickPlayPause(View view) {
        if (musicPlayer.isPrepared()) {
            setPlaying(!isPlaying);
        } else {
            Toast.makeText(view.getContext(), R.string.loading, Toast.LENGTH_SHORT).show();
        }
    }
}
