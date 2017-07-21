package cn.ellacat.tools.alarm.netease;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * @author wjc133
 */
public interface SongApi {
    @GET("/api/playlist/detail")
    Call<PlaylistResponse> getPlayList(@Query("id") long id);

    @POST("/weapi/song/enhance/player/url")
    @FormUrlEncoded
    Call<MusicResponse> getMusic(@Query("csrf_token") String token, @HeaderMap Map<String, String> headers, @Field("params") String params, @Field("encSecKey") String encSecKey);

    @POST("/api/search/get")
    @FormUrlEncoded
    Call<SearchResponse> searchMusic(@Field("s") String keyword, @Field("type") Short type, @Field("limit") int limit, @Field("offset") int offset);
}
