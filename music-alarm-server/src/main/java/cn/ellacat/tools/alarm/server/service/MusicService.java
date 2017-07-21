package cn.ellacat.tools.alarm.server.service;

import cn.ellacat.tools.alarm.server.mapper.SongMapper;
import cn.ellacat.tools.alarm.server.mapper.PlaylistMapper;
import cn.ellacat.tools.alarm.netease.NeteaseClient;
import cn.ellacat.tools.alarm.netease.Playlist;
import cn.ellacat.tools.alarm.netease.Track;
import cn.ellacat.tools.alarm.persist.PlaylistMeta;
import cn.ellacat.tools.alarm.persist.SongMeta;
import cn.ellacat.tools.alarm.persist.dao.PlaylistDao;
import cn.ellacat.tools.alarm.persist.dao.SongDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wjc133
 */
@Service
public class MusicService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MusicService.class);

    @Autowired
    private NeteaseClient client;
    @Autowired
    private PlaylistDao playlistDao;
    @Autowired
    private SongDao songDao;

    private static final long DEFAULT_PLAYLIST_ID = 3236554;

    public void updateSongLibrary() {
        client.getPlaylist(DEFAULT_PLAYLIST_ID, new NeteaseClient.Callback<Playlist>() {
            @Override
            public void onSuccess(Playlist data) {
                PlaylistMeta playlist = PlaylistMapper.INSTANCE.getMeta(data);
                playlistDao.insertIfNotExist(playlist);
                List<Track> tracks = data.getTracks();
                if (tracks == null) {
                    return;
                }
                for (Track track : tracks) {
                    SongMeta meta = SongMapper.INSTANCE.getMeta(track, playlist.getId());
                    songDao.insertIfNotExist(meta);
                }
            }

            @Override
            public void onFailed() {
                LOGGER.warn("no data to record");
            }
        });
    }
}
