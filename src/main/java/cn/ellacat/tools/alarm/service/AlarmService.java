package cn.ellacat.tools.alarm.service;

import cn.ellacat.tools.alarm.alarm.Alarm;
import cn.ellacat.tools.alarm.alarm.SongBo;
import cn.ellacat.tools.alarm.mapper.SongMapper;
import cn.ellacat.tools.alarm.netease.Music;
import cn.ellacat.tools.alarm.netease.NeteaseClient;
import cn.ellacat.tools.alarm.persist.PlaylistMeta;
import cn.ellacat.tools.alarm.persist.SongMeta;
import cn.ellacat.tools.alarm.persist.dao.PlaylistDao;
import cn.ellacat.tools.alarm.persist.dao.SongDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author wjc133
 */
@Service
public class AlarmService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmService.class);
    @Autowired
    private NeteaseClient client;
    @Autowired
    private PlaylistDao playlistDao;
    @Autowired
    private SongDao songDao;
    @Autowired
    private Alarm alarm;

    public void addMusicToAlarm() {
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
