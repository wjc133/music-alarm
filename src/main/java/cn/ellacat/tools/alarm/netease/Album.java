package cn.ellacat.tools.alarm.netease;

/**
 * @author wjc133
 */
public class Album {
    private String name;
    private long id;
    private String picUrl;
    private String company;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", picUrl='" + picUrl + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
