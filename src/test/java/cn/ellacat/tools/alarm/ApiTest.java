package cn.ellacat.tools.alarm;

import cn.ellacat.tools.alarm.netease.NeteaseClient;
import cn.ellacat.tools.alarm.netease.Playlist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author wjc133
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApiTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiTest.class);

    @Autowired
    private NeteaseClient client;

    @Test
    public void testGetPlaylist() throws IOException {
        client.getPlaylist(3236554, data -> LOGGER.info("playlist >>> {}", data));
        System.in.read();
    }

    @Test
    public void testGetMusic() throws IOException {
        client.getMusic(20953761, data -> LOGGER.info("music >>> {}", data));
        System.in.read();
    }
}
