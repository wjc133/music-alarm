package cn.ellacat.tools.alarm.netease;

import java.util.List;

/**
 * @author wjc133
 */
public class Playlist {
    private List<Track> tracks;
    private String name;
    private long id;

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "tracks=" + tracks +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
