package cn.ellacat.tools.alarm.netease;

import cn.ellacat.tools.alarm.util.AES;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wjc133
 */
public class NeteaseClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(NeteaseClient.class);
    private SongApi songApi;

    private static final String CSRF_TOKEN = "6817f1ae5c9664c9076e301c537afc29";

    public NeteaseClient(String baseUrl) {
        final Logger logger = LoggerFactory.getLogger(SongApi.class.getPackage().getName());
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (logger.isDebugEnabled()) {
                    logger.debug(message);
                }
            }
        });
        if (logger.isDebugEnabled() || logger.isTraceEnabled()) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(interceptor).build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();

        songApi = retrofit.create(SongApi.class);
    }

    public void getPlaylist(long id, Callback<Playlist> callback) {
        LOGGER.info("getPlaylist invoked... id={}", id);
        Call<PlaylistResponse> call = songApi.getPlayList(id);
        call.enqueue(new retrofit2.Callback<PlaylistResponse>() {
            @Override
            public void onResponse(Call<PlaylistResponse> call, Response<PlaylistResponse> response) {
                if (!response.isSuccessful()) {
                    LOGGER.warn("getPlaylist failed. id={}, status code={}", id, response.code());
                    return;
                }
                PlaylistResponse body = response.body();
                if (body == null) {
                    LOGGER.warn("getPlaylist failed. body is null. id={}", id);
                    return;
                }
                if (!body.isSuccess()) {
                    LOGGER.warn("getPlaylist failed. code = {}, id={}", body.getCode(), id);
                    return;
                }
                callback.onSuccess(body.getResult());
            }

            @Override
            public void onFailure(Call<PlaylistResponse> call, Throwable t) {
                LOGGER.warn("getPlaylist failed. id={}", id, t);
            }
        });
    }

    public void getMusic(long id, Callback<Music> callback) {
        try {
            String str = "{\"ids\":\"[" + id + "]\",\"br\":320000,\"csrf_token\":\"\"}";
            String params = AES.getParams(str);
            String encSecKey = AES.getEncSecKey();
            LOGGER.info("getMusic invoked.. params={}, encSecKey={}", params, encSecKey);
            Call<MusicResponse> call = songApi.getMusic(params, encSecKey);
            call.enqueue(new retrofit2.Callback<MusicResponse>() {
                @Override
                public void onResponse(Call<MusicResponse> call, Response<MusicResponse> response) {
                    if (!response.isSuccessful()) {
                        LOGGER.warn("getMusic failed. id={}, status code={}", id, response.code());
                        return;
                    }
                    MusicResponse body = response.body();
                    if (body == null) {
                        LOGGER.warn("getMusic failed. body is null, id={}", id);
                        return;
                    }
                    if (!body.isSuccess()) {
                        LOGGER.warn("getMusic failed. code = {}, id={}", body.getCode(), id);
                        return;
                    }
                    callback.onSuccess(body.getData());
                }

                @Override
                public void onFailure(Call<MusicResponse> call, Throwable t) {
                    LOGGER.warn("getMusic failed. id={}", id, t);
                }
            });
        } catch (Exception e) {
            LOGGER.error("getMusic failed. id={}", id, e);
        }
    }

    public interface Callback<T> {
        void onSuccess(T data);
    }
}
