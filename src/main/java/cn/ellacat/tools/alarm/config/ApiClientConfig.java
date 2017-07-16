package cn.ellacat.tools.alarm.config;

import cn.ellacat.tools.alarm.netease.NeteaseClient;
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
}
