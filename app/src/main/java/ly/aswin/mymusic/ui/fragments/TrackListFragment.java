package ly.aswin.mymusic.ui.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ly.aswin.mymusic.R;
import ly.aswin.mymusic.data.models.Track;
import ly.aswin.mymusic.managers.DataManager;
import ly.aswin.mymusic.ui.activities.TrackActivity;
import ly.aswin.mymusic.ui.adapters.TracksRecyclerViewAdapter;
import rx.Subscriber;

/**
 * Created by Aswin on 13-7-2016
 */
public class TrackListFragment extends Fragment {

    private static final String TAG = TrackListFragment.class.getSimpleName();
    private static final String KEY_SHARED_IMAGE = "trackImage";
    private static final String SOUNDCLOUD_USER_ID = "soundcloud_user_id";

    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DataManager dataManager;
    private TracksRecyclerViewAdapter adapter;
    private String userId;

    public TrackListFragment() {
    }

    public static TrackListFragment newInstance(String userId) {
        TrackListFragment fragment = new TrackListFragment();
        Bundle args = new Bundle();
        args.putString(SOUNDCLOUD_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_track_list, container, false);
        this.userId = getArguments().getString(SOUNDCLOUD_USER_ID);
        initViews(rootView);
        initData();
        return rootView;
    }

    private void initViews(View rootView) {
        this.progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        this.swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        this.adapter = new TracksRecyclerViewAdapter();

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
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
        dataManager.getMusic(userId, new Subscriber<List<Track>>() {
            @Override
            public void onCompleted() {
                hideLoadingIndicators();
            }

            @Override
            public void onError(Throwable e) {
                hideLoadingIndicators();
                String errorMsg = "Loading tracks went wrong";
                Log.e(TAG, errorMsg, e);
                Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(List<Track> tracks) {
                adapter.clearTracks();
                adapter.addTracks(tracks);
            }
        });
    }

    private void openTrack(Track track, View sharedImage) {
        Intent intent = new Intent(getContext(), TrackActivity.class);
        intent.putExtra(TrackActivity.KEY_TRACK, track);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
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
