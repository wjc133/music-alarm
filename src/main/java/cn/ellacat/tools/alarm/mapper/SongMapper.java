package cn.ellacat.tools.alarm.mapper;

import cn.ellacat.tools.alarm.alarm.SongBo;
import cn.ellacat.tools.alarm.netease.Album;
import cn.ellacat.tools.alarm.netease.Artist;
import cn.ellacat.tools.alarm.netease.Track;
import cn.ellacat.tools.alarm.persist.SongMeta;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author wjc133
 */
public class SongMapper {
    public static final SongMapper INSTANCE = new SongMapper();

    public SongMeta getMeta(Track track, int listId) {
        if (track == null) {
            return null;
        }
        SongMeta meta = new SongMeta();
        meta.setName(track.getName());
        meta.setDuration(track.getDuration());
        meta.setOriId(track.getId());
        meta.setUpdateTime(new Date());
        meta.setPlaylistId(listId);
        Album album = track.getAlbum();
        if (album != null) {
            meta.setAlbum(album.getName());
        }
        List<Artist> artists = track.getArtists();
        if (artists != null) {
            StringBuilder arts = new StringBuilder();
            Iterator<Artist> iterator = artists.iterator();
            while (iterator.hasNext()) {
                Artist artist = iterator.next();
                String name = artist.getName();
                if (name != null && name.length() > 0) {
                    arts.append(name);
                }
                if (iterator.hasNext()) {
                    arts.append(",");
                }
            }
            meta.setArtists(arts.toString());
        }
        return meta;
    }

    public SongBo getSongBo(SongMeta meta, String url) {
        if (meta == null || url == null || url.length() == 0) {
            return null;
        }
        SongBo bo = new SongBo();
        bo.setUrl(url);
        bo.setAlbum(meta.getAlbum());
        bo.setArtists(meta.getArtists());
        bo.setName(meta.getName());
        return bo;
    }
}
