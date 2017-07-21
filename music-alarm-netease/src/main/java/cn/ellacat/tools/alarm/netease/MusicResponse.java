package cn.ellacat.tools.alarm.netease;

import java.util.List;

/**
 * @author wjc133
 */
public class MusicResponse {
    private int code;
    private List<Music> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Music> getData() {
        return data;
    }

    public void setData(List<Music> data) {
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
