package cn.ellacat.tools.alarm.mapper;

import cn.ellacat.tools.alarm.netease.Playlist;
import cn.ellacat.tools.alarm.persist.PlaylistMeta;

import java.util.Date;

/**
 * @author wjc133
 */
public class PlaylistMapper {
    public static final PlaylistMapper INSTANCE = new PlaylistMapper();

    public PlaylistMeta getMeta(Playlist playlist) {
        if (playlist == null) {
            return null;
        }
        PlaylistMeta meta = new PlaylistMeta();
        meta.setOriId(playlist.getId());
        meta.setName(playlist.getName());
        meta.setUpdateTime(new Date());
        return meta;
    }
}
