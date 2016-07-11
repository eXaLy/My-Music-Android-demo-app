package ly.aswin.mymusic.data.deserializers;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import ly.aswin.mymusic.data.models.Track;

/**
 * Created by Aswin on 10-7-2016
 */
public class TrackDeserializer implements JsonDeserializer<Track> {

    private static final String KEY_USER = "user";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ARTWORK_URL = "artwork_url";

    @Override
    public Track deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject data = json.getAsJsonObject();
        if (data != null && !data.isJsonNull()) {
            Track track = new Gson().fromJson(data, Track.class);
            track.setOwner(findOwner(data));
            track.setImageUrl(getBigArtwork(data));
            return track;
        }
        return null;
    }

    private String getBigArtwork(JsonObject data) {
        JsonElement obj = data.get(KEY_ARTWORK_URL);
        if (obj != null && !obj.isJsonNull()) {
            return obj.getAsString().replace("large", "t500x500");
        }
        return null;
    }

    private String findOwner(JsonObject data) {
        JsonObject obj = data.get(KEY_USER).getAsJsonObject();
        if (obj != null && !obj.isJsonNull()) {
            return obj.get(KEY_USERNAME).getAsString();
        }
        return null;
    }
}
