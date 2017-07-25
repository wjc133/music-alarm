package cn.ellacat.tools.alarm.speaker.baidu;

import com.google.gson.annotations.SerializedName;

/**
 * @author wjc133
 */
public class AccessTokenResponse {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private int expireTime;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("session_key")
    private String sessionKey;
    @SerializedName("session_secret")
    private String sessionSecret;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSessionSecret() {
        return sessionSecret;
    }

    public void setSessionSecret(String sessionSecret) {
        this.sessionSecret = sessionSecret;
    }

    @Override
    public String toString() {
        return "AccessTokenResponse{" +
                "accessToken='" + accessToken + '\'' +
                ", expireTime=" + expireTime +
                ", refreshToken='" + refreshToken + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", sessionSecret='" + sessionSecret + '\'' +
                '}';
    }
}
