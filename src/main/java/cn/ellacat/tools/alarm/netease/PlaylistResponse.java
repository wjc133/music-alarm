package cn.ellacat.tools.alarm.netease;

/**
 * @author wjc133
 */
public class PlaylistResponse {
    private int code;
    private Playlist result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Playlist getResult() {
        return result;
    }

    public void setResult(Playlist result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return code == 200;
    }

    @Override
    public String toString() {
        return "PlaylistResponse{" +
                "code=" + code +
                ", result=" + result +
                '}';
    }
}
