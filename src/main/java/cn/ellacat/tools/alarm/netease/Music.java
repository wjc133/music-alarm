package cn.ellacat.tools.alarm.netease;

/**
 * @author wjc133
 */
public class Music {
    private String url;
    private long id;
    private String type;
    private long size;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Music{" +
                "url='" + url + '\'' +
                ", id=" + id +
                ", type='" + type + '\'' +
                ", size=" + size +
                '}';
    }
}
