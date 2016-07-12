package ly.aswin.mymusic.ui.activities;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ly.aswin.mymusic.R;
import ly.aswin.mymusic.data.models.Track;
import ly.aswin.mymusic.databinding.ActivityTrackBinding;
import ly.aswin.mymusic.ui.viewmodels.InfoCircleViewModel;
import ly.aswin.mymusic.ui.viewmodels.TrackActivityHandlers;
import ly.aswin.mymusic.utils.MusicPlayer;

public class TrackActivity extends AppCompatActivity {

    public static final String KEY_TRACK = "KEY_TRACK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
        initToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initLayout() {
        ActivityTrackBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_track);
        Track track = getIntent().getParcelableExtra(KEY_TRACK);
        binding.setTrack(track);

        InfoCircleViewModel playsCountViewModel = new InfoCircleViewModel(getString(R.string.plays_count),
                String.valueOf(track.getPlayCount()));
        InfoCircleViewModel downloadsCountViewModel = new InfoCircleViewModel(getString(R.string.downloads_count),
                String.valueOf(track.getDownloadCount()));
        binding.trackCirclePlays.setInfoCircleViewModel(playsCountViewModel);
        binding.trackCircleDownloads.setInfoCircleViewModel(downloadsCountViewModel);

        binding.setHandlers(new TrackActivityHandlers(new MusicPlayer(track.getStreamUrl())));
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
