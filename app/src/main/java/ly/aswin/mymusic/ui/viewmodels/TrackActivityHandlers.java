package ly.aswin.mymusic.ui.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import ly.aswin.mymusic.BR;

/**
 * Created by Aswin on 12-7-2016
 */
public class TrackActivityHandlers extends BaseObservable {

    private boolean isPlaying;

    @Bindable
    public boolean getIsPlaying() {
        return isPlaying;
    }

    public void onClickPlayPause(View view) {
        isPlaying = !isPlaying;
        notifyPropertyChanged(BR.isPlaying);
    }
}
