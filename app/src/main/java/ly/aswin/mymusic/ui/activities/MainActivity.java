package ly.aswin.mymusic.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ly.aswin.mymusic.R;
import ly.aswin.mymusic.data.models.Track;
import ly.aswin.mymusic.managers.DataManager;
import ly.aswin.mymusic.ui.adapters.TracksRecyclerViewAdapter;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String KEY_SHARED_IMAGE = "trackImage";

    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DataManager dataManager;
    private TracksRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
    }

    private void initViews() {
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        this.adapter = new TracksRecyclerViewAdapter();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadTracks();
            }
        });
        adapter.setOnTrackClickListener(new TracksRecyclerViewAdapter.OnTrackClickListener() {
            @Override
            public void onTrackClick(Track track, View sharedImage) {
                openTrack(track, sharedImage);
            }
        });
    }

    private void initData() {
        this.dataManager = new DataManager();
        progressBar.setVisibility(View.VISIBLE);
        loadTracks();
    }

    private void loadTracks() {
        dataManager.getMyMusic(new Subscriber<List<Track>>() {
            @Override
            public void onCompleted() {
                hideLoadingIndicators();
            }

            @Override
            public void onError(Throwable e) {
                hideLoadingIndicators();
                String errorMsg = "Loading tracks went wrong";
                Log.e(TAG, errorMsg, e);
                Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(List<Track> tracks) {
                adapter.clearTracks();
                adapter.addTracks(tracks);
            }
        });
    }

    private void openTrack(Track track, View sharedImage) {
        Intent intent = new Intent(this, TrackActivity.class);
        intent.putExtra(TrackActivity.KEY_TRACK, track);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    sharedImage, KEY_SHARED_IMAGE);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    private void hideLoadingIndicators() {
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }
}
