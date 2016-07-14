package ly.aswin.mymusic.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import ly.aswin.mymusic.BuildConfig;
import ly.aswin.mymusic.data.deserializers.TrackDeserializer;
import ly.aswin.mymusic.data.models.Track;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aswin on 10-7-2016
 *
 * The NetworkHandler is a singleton and will setup the needed stuff to make network requests.
 * Settings as logging, interceptors
 */
public class NetworkHandler {
    /*************************************
     * VARIABLES
     *************************************/

    private static NetworkHandler networkHandler;

    private SoundCloudService soundCloudService;

    /*************************************
     * SINGLETON
     *************************************/

    public static NetworkHandler getInstance() {
        if (networkHandler == null) {
            networkHandler = new NetworkHandler();
        }
        return networkHandler;
    }

    public NetworkHandler() {

    }

    /*************************************
     * PUBLIC METHODS
     *************************************/

    public SoundCloudService getSoundCloudService() {
        if (soundCloudService == null) {
            initService();
        }
        return soundCloudService;
    }

    /*************************************
     * PRIVATE METHODS
     *************************************/

    private void initService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(createApiKeyQueryInterceptor())
                .addInterceptor(createLoggingInterceptor())
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .build();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Track.class, new TrackDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WSConstants.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        this.soundCloudService = retrofit.create(SoundCloudService.class);
    }

    private Interceptor createApiKeyQueryInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();
                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter(WSConstants.CLIENT_ID_QUERY,
                                BuildConfig.SOUNDCLOUD_API_KEY)
                        .build();
                return chain.proceed(original.newBuilder().url(url).build());
            }
        };
    }

    private HttpLoggingInterceptor createLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return interceptor;
    }
}
