package cn.ellacat.tools.alarm.persist;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 歌曲元数据，用于持久化
 *
 * @author wjc133
 */
@DatabaseTable(tableName = "music")
public class SongMeta {
    @DatabaseField(id = true, generatedId = true)
    private long id;
    @DatabaseField(dataType = DataType.STRING, width = 48, canBeNull = false)
    private String name;
    private long duration;
    @DatabaseField(dataType = DataType.STRING, width = 128)
    private String artists;
    @DatabaseField(dataType = DataType.STRING, width = 128)
    private String album;
    @DatabaseField(dataType = DataType.STRING, width = 256, canBeNull = false)
    private String url;
    private long size;
    @DatabaseField(columnName = "oriId")
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
