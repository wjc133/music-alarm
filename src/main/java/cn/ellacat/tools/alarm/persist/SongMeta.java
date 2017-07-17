package cn.ellacat.tools.alarm.persist;

/**
 * 歌曲元数据，用于持久化
 *
 * @author wjc133
 */
public class SongMeta {
    private long id;
    private String name;
    private long duration;
    private String artists;
    private String album;
    private String url;
    private long size;
    private String oriId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getOriId() {
        return oriId;
    }

    public void setOriId(String oriId) {
        this.oriId = oriId;
    }

    @Override
    public String toString() {
        return "SongMeta{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", artists='" + artists + '\'' +
                ", album='" + album + '\'' +
                ", url='" + url + '\'' +
                ", size=" + size +
                ", oriId='" + oriId + '\'' +
                '}';
    }
}
