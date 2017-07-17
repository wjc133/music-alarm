package cn.ellacat.tools.alarm.job;

import cn.ellacat.tools.alarm.alarm.Alarm;
import cn.ellacat.tools.alarm.alarm.SongBo;
import cn.ellacat.tools.alarm.mapper.PlaylistMapper;
import cn.ellacat.tools.alarm.mapper.SongMapper;
import cn.ellacat.tools.alarm.netease.Music;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author wjc133
 */
@Lazy(false)
@EnableScheduling
@Service
public class AlarmJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmJob.class);

    private static final long DEFAULT_PLAYLIST_ID = 3236554;

    @Autowired
    private NeteaseClient client;
    @Autowired
    private PlaylistDao playlistDao;
    @Autowired
    private SongDao songDao;
    @Autowired
    private Alarm alarm;

    @Scheduled(cron = "0 30 2 * * ?")
//    @Scheduled(fixedRate = 60000)
    public void recordMusic() {
        LOGGER.info("ready for song");
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

    @Scheduled(cron = "0 0 8 * * ?")
    public void alarm() {
        LOGGER.info("start alarming!");
        List<PlaylistMeta> playlistMetas = playlistDao.queryAll();
        for (PlaylistMeta playlistMeta : playlistMetas) {
            List<SongMeta> metas = songDao.queryByPlaylistId(playlistMeta.getId());
            Collections.shuffle(metas);
            if (metas.size() > 5) {
                metas = metas.subList(0, 5);
            }
            for (SongMeta meta : metas) {
                client.getMusic(meta.getOriId(), new NeteaseClient.Callback<Music>() {
                    @Override
                    public void onSuccess(Music data) {
                        SongBo songBo = SongMapper.INSTANCE.getSongBo(meta, data.getUrl());
                        if (songBo == null) {
                            return;
                        }
                        alarm.add(songBo);
                    }

                    @Override
                    public void onFailed() {
                        LOGGER.warn("get Music detail failed...");
                    }
                });
            }
        }
    }
}
