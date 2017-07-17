package cn.ellacat.tools.alarm.job;

import cn.ellacat.tools.alarm.service.AlarmService;
import cn.ellacat.tools.alarm.service.MusicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author wjc133
 */
@Lazy(false)
@EnableScheduling
@Service
public class AlarmJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmJob.class);

    @Autowired
    private MusicService musicService;
    @Autowired
    private AlarmService alarmService;

    @Scheduled(cron = "0 30 2 * * ?")
//    @Scheduled(fixedRate = 60000)
    public void recordMusic() {
        LOGGER.info("ready for song");
        musicService.updateSongLibrary();
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void alarm() {
        LOGGER.info("start alarming!");
        alarmService.addMusicToAlarm();
    }
}
