package cn.ellacat.tools.alarm.persist;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * @author wjc133
 */
@DatabaseTable(tableName = "playlist")
public class PlaylistMeta {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(width = 128)
    private String name;
    @DatabaseField(columnName = "update_time", dataType = DataType.DATE_LONG)
    private Date updateTime;
    @DatabaseField(columnName = "ori_id", unique = true)
    private long oriId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public long getOriId() {
        return oriId;
    }

    public void setOriId(long oriId) {
        this.oriId = oriId;
    }

    @Override
    public String toString() {
        return "PlaylistMeta{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", updateTime=" + updateTime +
                ", oriId=" + oriId +
                '}';
    }
}
