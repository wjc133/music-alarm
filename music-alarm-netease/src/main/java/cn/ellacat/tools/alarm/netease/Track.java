package cn.ellacat.tools.alarm.netease;

import java.util.List;

/**
 * @author wjc133
 */
public class Track {
    private String name;
    private long id;
    private long duration;
    private List<Artist> artists;
    private Album album;

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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", duration=" + duration +
                ", artists=" + artists +
                ", album=" + album +
                '}';
    }
}
