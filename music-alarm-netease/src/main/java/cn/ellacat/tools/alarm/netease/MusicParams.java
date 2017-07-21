package cn.ellacat.tools.alarm.netease;

/**
 * @author wjc133
 */
public class MusicParams {
    private String params;
    private String secret;

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "MusicParams{" +
                "params='" + params + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
