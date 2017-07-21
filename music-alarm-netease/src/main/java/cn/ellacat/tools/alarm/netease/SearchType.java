package cn.ellacat.tools.alarm.netease;

/**
 * @author wjc133
 */
public enum SearchType {
    TRACK(1),   //单曲
    ALBUM(10),  //专辑
    ARTIST(100),   //艺术家
    PLAYLIST(1000),  //歌单
    USER(1002);    //用户

    private short code;

    SearchType(Integer code) {
        this.code = code.shortValue();
    }

    public short getCode() {
        return code;
    }

    public boolean isMe(short code) {
        return this.code == code;
    }

    @Override
    public String toString() {
        return "SearchType{" +
                "code=" + code +
                '}';
    }
}
