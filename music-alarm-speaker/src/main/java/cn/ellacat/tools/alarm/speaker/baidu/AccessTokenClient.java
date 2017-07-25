package cn.ellacat.tools.alarm.speaker.baidu;

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

/**
 * @author wjc133
 */
public class AccessTokenClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenClient.class);
    private AccessTokenApi accessTokenApi;

    public AccessTokenClient(String baseUrl) {
        final Logger logger = LoggerFactory.getLogger(AccessTokenApi.class.getPackage().getName());
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

        accessTokenApi = retrofit.create(AccessTokenApi.class);
    }

    public AccessTokenResponse getAccessToken() {
        try {
            Call<AccessTokenResponse> call = accessTokenApi.getAccessToken("client_credentials", "hBk41V9Mlb8zC588gUDSsxuD", "42a9e649ef0bad7b2e62c6bbb53ad54d");
            Response<AccessTokenResponse> execute = call.execute();
            return execute.body();
        } catch (IOException e) {
            LOGGER.warn("getAccessToken failed.", e);
        }
        return null;
    }
}
