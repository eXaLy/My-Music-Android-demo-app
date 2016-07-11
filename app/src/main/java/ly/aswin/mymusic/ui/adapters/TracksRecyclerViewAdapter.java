package ly.aswin.mymusic.ui.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ly.aswin.mymusic.data.models.Track;
import ly.aswin.mymusic.databinding.CardTrackItemBinding;

public class TracksRecyclerViewAdapter extends RecyclerView.Adapter<TracksRecyclerViewAdapter.ViewHolder> {

    private final List<Track> Tracks;
    private OnTrackClickListener onTrackClickListener;

    public TracksRecyclerViewAdapter() {
        this.Tracks = new ArrayList<>();
    }

    public void addTracks(List<Track> Tracks) {
        this.Tracks.addAll(Tracks);
        notifyItemRangeInserted(Tracks.size() - Tracks.size(), Tracks.size());
    }

    public void clearTracks() {
        Tracks.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardTrackItemBinding binding = CardTrackItemBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Track Track = Tracks.get(position);
        holder.binding.setTrack(Track);
    }

    @Override
    public int getItemCount() {
        return Tracks.size();
    }

    public void setOnTrackClickListener(OnTrackClickListener onTrackClickListener) {
        this.onTrackClickListener = onTrackClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardTrackItemBinding binding;

        public ViewHolder(View view) {
            super(view);
            this.binding = DataBindingUtil.bind(view);
            binding.trackCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onTrackClickListener != null) {
                Track Track = Tracks.get(getAdapterPosition());
                onTrackClickListener.onTrackClick(Track, binding.trackCardView.getThumbnail());
            }
        }
    }

    public interface OnTrackClickListener {
        void onTrackClick(Track Track, View sharedImage);
    }
}
