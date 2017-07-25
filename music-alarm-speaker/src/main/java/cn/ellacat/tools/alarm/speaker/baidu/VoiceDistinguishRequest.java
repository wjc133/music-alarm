package cn.ellacat.tools.alarm.speaker.baidu;

/**
 * @author wjc133
 */
public class VoiceDistinguishRequest {
    private String format;
    private int rate;
    private int channel;
    private String cuid;
    private String token;
    private String lan; //zh--中文,ct--粤语,en--英语
    private String speech;  //语音数据Base64
    private long len;  //语音长度

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public long getLen() {
        return len;
    }

    public void setLen(long len) {
        this.len = len;
    }

    @Override
    public String toString() {
        return "VoiceDistinguishRequest{" +
                "format='" + format + '\'' +
                ", rate=" + rate +
                ", channel=" + channel +
                ", cuid='" + cuid + '\'' +
                ", token='" + token + '\'' +
                ", lan='" + lan + '\'' +
                ", speech='" + speech + '\'' +
                ", len=" + len +
                '}';
    }
}
