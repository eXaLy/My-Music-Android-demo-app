package ly.aswin.mymusic.ui.customviews;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;

import ly.aswin.mymusic.data.models.Track;
import ly.aswin.mymusic.databinding.ViewTrackCardBinding;

/**
 * Created by Aswin on 10-7-2016
 */
public class TrackCardView extends CardView {

    private ViewTrackCardBinding binding;

    public TrackCardView(Context context) {
        super(context);
        init();
    }

    public TrackCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TrackCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        binding.trackCardThumbnail.setOnClickListener(l);
        super.setOnClickListener(l);
    }

    public void setTrack(Track track) {
        binding.setTrack(track);
    }

    public ImageView getThumbnail() {
        return binding.trackCardThumbnail;
    }

    private void init() {
        this.binding = ViewTrackCardBinding.inflate(LayoutInflater.from(getContext()), this, true);
    }
}
