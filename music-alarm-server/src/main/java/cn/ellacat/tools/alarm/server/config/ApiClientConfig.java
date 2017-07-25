package cn.ellacat.tools.alarm.server.config;

import cn.ellacat.tools.alarm.server.config.properties.BaiduProperties;
import cn.ellacat.tools.alarm.server.config.properties.NeteaseProperties;
import cn.ellacat.tools.alarm.netease.NeteaseClient;
import cn.ellacat.tools.alarm.speaker.baidu.AccessTokenClient;
import cn.ellacat.tools.alarm.speaker.baidu.VoiceClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wjc133
 */
@Configuration
public class ApiClientConfig {

    @Bean("clientProperties")
    @ConfigurationProperties(prefix = "netease")
    public NeteaseProperties getProperties() {
        return new NeteaseProperties();
    }

    @Bean
    public NeteaseClient getNeteaseClient(NeteaseProperties properties) {
        return new NeteaseClient(properties.getBaseurl());
    }

    @Bean("baiduProperties")
    @ConfigurationProperties(prefix = "baidu")
    public BaiduProperties getBaiduProperties() {
        return new BaiduProperties();
    }

    @Bean
    public AccessTokenClient getAccessTokenClient(BaiduProperties properties) {
        return new AccessTokenClient(properties.getTokenurl());
    }

    @Bean
    public VoiceClient getVoiceClient(BaiduProperties properties) {
        return new VoiceClient(properties.getVoiceurl());
    }
}
