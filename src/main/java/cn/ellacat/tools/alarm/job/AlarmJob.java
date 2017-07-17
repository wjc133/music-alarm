package cn.ellacat.tools.alarm.job;

import cn.ellacat.tools.alarm.netease.NeteaseClient;
import cn.ellacat.tools.alarm.netease.Playlist;
import cn.ellacat.tools.alarm.netease.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wjc133
 */
@Lazy(false)
@EnableScheduling
@Service
public class AlarmJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmJob.class);

    private static final long ID_PLAYLIST = 3236554L;

    @Autowired
    private NeteaseClient client;

    @Scheduled(cron = "0 30 2 * * ?")
    public void ready() {
        LOGGER.info("ready for song");
        client.getPlaylist(ID_PLAYLIST, new NeteaseClient.Callback<Playlist>() {
            @Override
            public void onSuccess(Playlist data) {
                List<Track> tracks = data.getTracks();
                if (tracks == null) {
                    return;
                }
                for (Track track : tracks) {
//                    track.get
                }
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
