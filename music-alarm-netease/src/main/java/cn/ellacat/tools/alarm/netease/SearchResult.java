package cn.ellacat.tools.alarm.netease;

import java.util.List;

/**
 * @author wjc133
 */
public class SearchResult {
    private List<Track> songs;
    private int songCount;

    public List<Track> getSongs() {
        return songs;
    }

    public void setSongs(List<Track> songs) {
        this.songs = songs;
    }

    public int getSongCount() {
        return songCount;
    }

    public void setSongCount(int songCount) {
        this.songCount = songCount;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "songs=" + songs +
                ", songCount=" + songCount +
                '}';
    }
}
