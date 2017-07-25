package cn.ellacat.tools.alarm.server.service;

import cn.ellacat.tools.alarm.speaker.AudioCard;
import cn.ellacat.tools.alarm.speaker.baidu.AccessTokenClient;
import cn.ellacat.tools.alarm.speaker.baidu.AccessTokenResponse;
import cn.ellacat.tools.alarm.speaker.baidu.VoiceClient;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author wjc133
 */
@Service
public class VoiceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoiceService.class);
    @Autowired
    private VoiceClient voiceClient;
    @Autowired
    private AccessTokenClient tokenClient;

//    OutputStream outputStream = new ByteArrayOutputStream(2048);

    File file = new File("./exchange.wav");

    Cache<String, String> tokenCache = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.DAYS).build();

    public String getAccessToken() {
        try {
            return tokenCache.get("token", () -> {
                AccessTokenResponse token = tokenClient.getAccessToken();
                if (token == null) {
                    return "";
                }
                return token.getAccessToken();
            });
        } catch (ExecutionException e) {
            LOGGER.warn("execute getAccessToken failed.", e);
        }
        return "";
    }

    public void start() {
        AudioCard.getInstance().startCapture(file);
    }

    public void stopAndDistinguish() {
        AudioCard.getInstance().stopCapture();
        voiceClient.distinguishVoice(getAccessToken(), file);
    }
}
