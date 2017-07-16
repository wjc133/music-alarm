package cn.ellacat.tools.alarm.netease;

/**
 * @author wjc133
 */
public class MusicResponse {
    private int code;
    private Music data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Music getData() {
        return data;
    }

    public void setData(Music data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return this.code == 200;
    }

    @Override
    public String toString() {
        return "MusicResponse{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
