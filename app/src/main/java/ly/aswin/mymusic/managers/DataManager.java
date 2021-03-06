package ly.aswin.mymusic.managers;

import java.util.List;

import ly.aswin.mymusic.data.models.Track;
import ly.aswin.mymusic.network.NetworkHandler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Aswin on 10-7-2016
 *
 * The DataManager is used to get the needed data
 */
public class DataManager {

    public static final String KIPZES_USER_ID = "25613013";
    public static final String SPECDRUMS_USER_ID = "51536542";

    public DataManager() {
    }

    /**
     * Get music from SoundCloud from a user
     *
     * @param userId SoundCloud userId
     * @param subscriber subscriber to receive tracks
     * @return Subscription
     */
    public Subscription getMusic(String userId, Subscriber<List<Track>> subscriber) {
        return NetworkHandler.getInstance().getSoundCloudService().getTracks(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }
}
