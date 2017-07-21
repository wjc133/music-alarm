package cn.ellacat.tools.alarm.netease;

import cn.ellacat.tools.alarm.common.encrypt.EncryptGenertor;
import cn.ellacat.tools.alarm.common.encrypt.EncryptResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wjc133
 */
public class NeteaseClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(NeteaseClient.class);
    private SongApi songApi;

    private static final String CSRF_TOKEN = "6817f1ae5c9664c9076e301c537afc29";

    public NeteaseClient(String baseUrl) {
        final Logger logger = LoggerFactory.getLogger(SongApi.class.getPackage().getName());
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> {
            if (logger.isDebugEnabled()) {
                logger.debug(message);
            }
        });
        if (logger.isDebugEnabled() || logger.isTraceEnabled()) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder().addInterceptor(interceptor).build())
                .addConverterFactory(GsonConverterFactory.create(gson))
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
            Map<String, String> headers = getHeaders();
            EncryptResult encryptResult = EncryptGenertor.getResult(id);

            String params = encryptResult.getEncText();
            String encSecKey = encryptResult.getEncSecKey();
            LOGGER.info("getMusic invoked.. params={}, encSecKey={}", params, encSecKey);
            Call<MusicResponse> call = songApi.getMusic(CSRF_TOKEN, headers, params, encSecKey);
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
                    List<Music> data = body.getData();
                    if (data == null) {
                        callback.onFailed();
                        return;
                    }
                    callback.onSuccess(data.get(0));
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

    public MusicResponse getMusic(long id) {
        try {
            Map<String, String> headers = getHeaders();
            EncryptResult encryptResult = EncryptGenertor.getResult(id);

            String params = encryptResult.getEncText();
            String encSecKey = encryptResult.getEncSecKey();
            LOGGER.info("getMusic invoked.. params={}, encSecKey={}", params, encSecKey);
            Call<MusicResponse> call = songApi.getMusic(CSRF_TOKEN, headers, params, encSecKey);
            Response<MusicResponse> response = call.execute();
            if (response == null) {
                return null;
            }
            return response.body();
        } catch (Exception e) {
            LOGGER.warn("getMusic failed...", e);
        }
        return null;
    }

    public void searchMusic(String keyword, SearchType type, Callback<SearchResult> callback) {
        LOGGER.info("searchMusic invoked... keyword={}, searchType={}", keyword, type.getCode());
        Call<SearchResponse> call = songApi.searchMusic(keyword, type.getCode(), 20, 0);
        call.enqueue(new retrofit2.Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (!response.isSuccessful()) {
                    LOGGER.warn("getPlaylist failed. keyword={}, searchType={}, status code={}", keyword, type.getCode(), response.code());
                    return;
                }
                SearchResponse body = response.body();
                if (body == null) {
                    LOGGER.warn("getPlaylist failed. body is null. keyword={}, searchType={}", keyword, type.getCode());
                    return;
                }
                if (!body.isSuccess()) {
                    LOGGER.warn("getPlaylist failed. code = {}, keyword={}, searchType={}", body.getCode(), keyword, type.getCode());
                    return;
                }
                callback.onSuccess(body.getResult());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                LOGGER.warn("getPlaylist failed. keyword={}, searchType={}", keyword, type.getCode(), t);
            }
        });
    }

    public SearchResult searchMusic(String keyword, SearchType type) {
        LOGGER.info("searchMusic invoked... keyword={}, searchType={}", keyword, type.getCode());
        Call<SearchResponse> call = songApi.searchMusic(keyword, type.getCode(), 20, 0);
        try {
            Response<SearchResponse> response = call.execute();
            if (!response.isSuccessful()) {
                LOGGER.warn("getPlaylist failed. keyword={}, searchType={}, status code={}", keyword, type.getCode(), response.code());
                return null;
            }
            SearchResponse body = response.body();
            if (body == null) {
                LOGGER.warn("getPlaylist failed. body is null. keyword={}, searchType={}", keyword, type.getCode());
                return null;
            }
            if (!body.isSuccess()) {
                LOGGER.warn("getPlaylist failed. code = {}, keyword={}, searchType={}", body.getCode(), keyword, type.getCode());
                return null;
            }
            return body.getResult();
        } catch (IOException e) {
            LOGGER.warn("getPlaylist failed. keyword={}, searchType={}", keyword, type.getCode(), e);
        }
        return null;
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36");
        headers.put("Referer", "http://music.163.com/");
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        return headers;
    }

    public interface Callback<T> {
        void onSuccess(T data);

        void onFailed();
    }
}
