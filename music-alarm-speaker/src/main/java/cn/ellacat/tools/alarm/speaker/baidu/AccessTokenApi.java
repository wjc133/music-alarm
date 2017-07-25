package cn.ellacat.tools.alarm.speaker.baidu;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 访问Token生成API
 * https://openapi.baidu.com/oauth/2.0/token
 *
 * @author wjc133
 */
public interface AccessTokenApi {
    @POST("/oauth/2.0/token")
    @FormUrlEncoded
    Call<AccessTokenResponse> getAccessToken(@Field("grant_type") String grantType, @Field("client_id") String apiKey, @Field("client_secret") String secretKey);
}
