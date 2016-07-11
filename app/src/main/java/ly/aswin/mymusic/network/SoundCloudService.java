package ly.aswin.mymusic.network;

import java.util.List;

import ly.aswin.mymusic.data.models.Track;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Aswin on 10-7-2016
 */
public interface SoundCloudService {
    @GET(WSConstants.USERS_ENDPOINT + "{userId}/" + WSConstants.TRACKS_ENDPOINT)
    Observable<List<Track>> getTracks(@Path("userId") String userId);
}
