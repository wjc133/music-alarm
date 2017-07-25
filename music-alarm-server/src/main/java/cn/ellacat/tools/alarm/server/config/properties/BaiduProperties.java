package cn.ellacat.tools.alarm.server.config.properties;

/**
 * @author wjc133
 */
public class BaiduProperties {
    private String tokenurl;
    private String voiceurl;

    public String getTokenurl() {
        return tokenurl;
    }

    public void setTokenurl(String tokenurl) {
        this.tokenurl = tokenurl;
    }

    public String getVoiceurl() {
        return voiceurl;
    }

    public void setVoiceurl(String voiceurl) {
        this.voiceurl = voiceurl;
    }

    @Override
    public String toString() {
        return "BaiduProperties{" +
                "tokenurl='" + tokenurl + '\'' +
                ", voiceurl='" + voiceurl + '\'' +
                '}';
    }
}
