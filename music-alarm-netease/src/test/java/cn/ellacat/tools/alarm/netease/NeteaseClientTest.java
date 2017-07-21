package cn.ellacat.tools.alarm.netease;

import org.junit.Test;

import java.util.List;

/**
 * @author wjc133
 */
public class NeteaseClientTest {
    @Test
    public void testSearchMusic() {
        String baseUrl = "https://music.163.com";
        NeteaseClient client = new NeteaseClient(baseUrl);
        SearchResult result = client.searchMusic("心愿便利贴", SearchType.TRACK);
        List<Track> songs = result.getSongs();
        System.out.println(songs);
    }
}
