package cn.ellacat.tools.alarm.netease;

import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author wjc133
 */
public interface SongApi {
    @GET("/api/playlist/detail")
    Call<PlaylistResponse> getPlayList(@Query("id") long id);

    @POST("/weapi/song/enhance/player/url")
    @FormUrlEncoded
    Call<MusicResponse> getMusic(@Field("params") String params, @Field("encSecKey") String encSecKey);
}
