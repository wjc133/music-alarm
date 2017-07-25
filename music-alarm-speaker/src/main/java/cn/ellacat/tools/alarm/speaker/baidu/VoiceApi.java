package cn.ellacat.tools.alarm.speaker.baidu;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 语音识别与合成API
 *
 * @author wjc133
 */
public interface VoiceApi {

    @POST("/server_api")
    Call<VoiceDistinguishResponse> distinguish(@Body VoiceDistinguishRequest request);
}
