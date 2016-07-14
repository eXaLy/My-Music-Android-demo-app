package ly.aswin.mymusic.data.deserializers;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import ly.aswin.mymusic.BuildConfig;
import ly.aswin.mymusic.data.models.Track;
import ly.aswin.mymusic.network.WSConstants;

/**
 * Created by Aswin on 10-7-2016
 *
 * TrackDeserializer will be used to map the JSON to the defined Track model
 */
public class TrackDeserializer implements JsonDeserializer<Track> {

    private static final String KEY_USER = "user";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ARTWORK_URL = "artwork_url";
    private static final String KEY_STREAM_URL = "stream_url";
    private static final String KEY_DOWNLOAD_URL = "download_url";

    private static final String QUERY_API_KEY = "?" + WSConstants.CLIENT_ID_QUERY + "=" + BuildConfig.SOUNDCLOUD_API_KEY;

    @Override
    public Track deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject data = json.getAsJsonObject();
        if (data != null && !data.isJsonNull()) {
            Track track = new Gson().fromJson(data, Track.class);
            track.setOwner(findOwner(data));
            track.setImageUrl(getBigArtwork(data));
            track.setDownloadUrl(updateAuthUrl(data, KEY_DOWNLOAD_URL));
            track.setStreamUrl(updateAuthUrl(data, KEY_STREAM_URL));
            return track;
        }
        return null;
    }

    /**
     * Updates the URL as value with the API KEY as query, so that the URL can be requested
     *
     * @param data json
     * @param key json key
     * @return URL
     */
    private String updateAuthUrl(JsonObject data, String key) {
        JsonElement obj = data.get(key);
        if (obj != null && !obj.isJsonNull()) {
            return obj.getAsString() + QUERY_API_KEY;
        }
        return null;
    }

    /**
     * Updates the image url to the biggest format
     *
     * @param data json
     * @return image url
     */
    private String getBigArtwork(JsonObject data) {
        JsonElement obj = data.get(KEY_ARTWORK_URL);
        if (obj != null && !obj.isJsonNull()) {
            return obj.getAsString().replace("large", "t500x500");
        }
        return null;
    }

    /**
     * Gets the username from the owner object
     *
     * @param data json
     * @return owner username
     */
    private String findOwner(JsonObject data) {
        JsonObject obj = data.get(KEY_USER).getAsJsonObject();
        if (obj != null && !obj.isJsonNull()) {
            return obj.get(KEY_USERNAME).getAsString();
        }
        return null;
    }
}
