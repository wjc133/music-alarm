package cn.ellacat.tools.alarm.persist;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * 歌曲元数据，用于持久化
 *
 * @author wjc133
 */
@DatabaseTable(tableName = "song")
public class SongMeta {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(dataType = DataType.STRING, width = 128, canBeNull = false)
    private String name;
    @DatabaseField
    private long duration;
    @DatabaseField(dataType = DataType.STRING, width = 128)
    private String artists;
    @DatabaseField(dataType = DataType.STRING, width = 128)
    private String album;
    @DatabaseField(columnName = "ori_id", canBeNull = false)
    private long oriId;
    @DatabaseField(columnName = "update_time", dataType = DataType.DATE_LONG)
    private Date updateTime;
    @DatabaseField(columnName = "playlist_id")
    private int playlistId;

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

    public long getOriId() {
        return oriId;
    }

    public void setOriId(long oriId) {
        this.oriId = oriId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    @Override
    public String toString() {
        return "SongMeta{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", artists='" + artists + '\'' +
                ", album='" + album + '\'' +
                ", oriId=" + oriId +
                ", updateTime=" + updateTime +
                ", playlistId=" + playlistId +
                '}';
    }
}
