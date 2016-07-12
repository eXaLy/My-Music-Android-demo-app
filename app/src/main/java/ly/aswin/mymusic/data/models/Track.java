package ly.aswin.mymusic.data.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import ly.aswin.mymusic.BR;

/**
 * Created by Aswin on 10-7-2016
 */
public class Track extends BaseObservable implements Parcelable {

    @SerializedName("id")
    @Bindable
    private String id;

    @Bindable
    private String owner;

    @SerializedName("title")
    @Bindable
    private String title;

    @SerializedName("description")
    @Bindable
    private String description;

    @SerializedName("created_at")
    @Bindable
    private String date;

    @SerializedName("duration")
    @Bindable
    private long durationInMilli;

    @SerializedName("playback_count")
    @Bindable
    private int playCount;

    @SerializedName("download_count")
    @Bindable
    private int downloadCount;

    @Bindable
    private String imageUrl;

    @SerializedName("waveform_url")
    @Bindable
    private String waveformUrl;

    @Bindable
    private String streamUrl;

    @Bindable
    private String downloadUrl;

    public Track() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
        notifyPropertyChanged(BR.owner);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    public long getDurationInMilli() {
        return durationInMilli;
    }

    public void setDurationInMilli(long durationInMilli) {
        this.durationInMilli = durationInMilli;
        notifyPropertyChanged(BR.durationInMilli);
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
        notifyPropertyChanged(BR.playCount);
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
        notifyPropertyChanged(BR.downloadCount);
    }

    public String getWaveformUrl() {
        return waveformUrl;
    }

    public void setWaveformUrl(String waveformUrl) {
        this.waveformUrl = waveformUrl;
        notifyPropertyChanged(BR.waveformUrl);
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
        notifyPropertyChanged(BR.streamUrl);
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
        notifyPropertyChanged(BR.downloadUrl);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.owner);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.date);
        dest.writeLong(this.durationInMilli);
        dest.writeInt(this.playCount);
        dest.writeInt(this.downloadCount);
        dest.writeString(this.imageUrl);
        dest.writeString(this.waveformUrl);
        dest.writeString(this.streamUrl);
        dest.writeString(this.downloadUrl);
    }

    protected Track(Parcel in) {
        this.id = in.readString();
        this.owner = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.date = in.readString();
        this.durationInMilli = in.readLong();
        this.playCount = in.readInt();
        this.downloadCount = in.readInt();
        this.imageUrl = in.readString();
        this.waveformUrl = in.readString();
        this.streamUrl = in.readString();
        this.downloadUrl = in.readString();
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel source) {
            return new Track(source);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };
}
