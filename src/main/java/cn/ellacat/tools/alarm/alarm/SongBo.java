package cn.ellacat.tools.alarm.alarm;

/**
 * @author wjc133
 */
public class SongBo {
    private String name;
    private String artists;
    private String album;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "SongBo{" +
                "name='" + name + '\'' +
                ", artists='" + artists + '\'' +
                ", album='" + album + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
