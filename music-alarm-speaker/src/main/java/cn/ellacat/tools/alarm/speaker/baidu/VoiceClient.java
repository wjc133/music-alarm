package cn.ellacat.tools.alarm.speaker.baidu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.*;
import java.util.List;

/**
 * @author wjc133
 */
public class VoiceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoiceClient.class);
    private VoiceApi voiceApi;

    public VoiceClient(String baseUrl) {
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

        voiceApi = retrofit.create(VoiceApi.class);
    }

    public void distinguishVoice(String token, File output) {
        try {
            byte[] speech = getSpeech(output);

            VoiceDistinguishRequest request = new VoiceDistinguishRequest();
            request.setFormat("wav");
            request.setRate(8000);
            request.setChannel(1);
            request.setCuid("15190042");
            request.setLan("zh");
            request.setToken(token);
            request.setSpeech(Base64Utils.encodeToUrlSafeString(speech));
            request.setLen(output.length());
            Call<VoiceDistinguishResponse> call = voiceApi.distinguish(request);
            call.enqueue(new Callback<VoiceDistinguishResponse>() {
                @Override
                public void onResponse(Call<VoiceDistinguishResponse> call, Response<VoiceDistinguishResponse> response) {
                    if (!response.isSuccessful()) {
                        LOGGER.warn("distinguishVoice failed. status code={}", response.code());
                        return;
                    }
                    VoiceDistinguishResponse body = response.body();
                    if (body.getCode() != 0) {
                        LOGGER.warn("distinguishVoice failed. code={}, msg={}", body.getCode(), body.getMsg());
                        return;
                    }
                    List<String> result = body.getResult();
                    LOGGER.info("distinguishVoice result >>> {}", result);
                }

                @Override
                public void onFailure(Call<VoiceDistinguishResponse> call, Throwable t) {
                    LOGGER.warn("distinguishVoice failed.", t);
                }
            });
        } catch (Exception e) {
            LOGGER.warn("distinguishVoice failed.", e);
        }
    }

    private byte[] getSpeech(File output) throws IOException {
        FileInputStream in = new FileInputStream(output);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.copy(in, out);
        return out.toByteArray();
    }
}
