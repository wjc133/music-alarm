package cn.ellacat.tools.alarm.netease;

/**
 * @author wjc133
 */
public class Artist {
    private String name;
    private long id;
    private String picUrl;

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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}
