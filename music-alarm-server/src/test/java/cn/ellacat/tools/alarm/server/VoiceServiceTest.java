package cn.ellacat.tools.alarm.server;

import cn.ellacat.tools.alarm.server.service.VoiceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wjc133
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VoiceServiceTest {
    public static final Logger LOGGER = LoggerFactory.getLogger(VoiceServiceTest.class);

    @Autowired
    private VoiceService voiceService;

    @Test
    public void testGetToken() {
        String accessToken = voiceService.getAccessToken();
        LOGGER.info("AccessToken >>> {}", accessToken);
    }
}
