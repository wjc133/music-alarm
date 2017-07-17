package cn.ellacat.tools.alarm.alarm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wjc133
 */
@Component
public class Alarm extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(Alarm.class);
    private BlockingQueue<SongBo> musicQueue = new LinkedBlockingQueue<>();
    private Player player = new Mplayer();
    private SongBo currentSong;
    private boolean playing = false;

    @PostConstruct
    public void init() {
        start();
    }

    @Override
    public void run() {
        startPlay();
    }

    public void add(SongBo songBo) {
        if (songBo == null) {
            return;
        }
        musicQueue.add(songBo);
    }

    public void startPlay() {
        LOGGER.info("music player starting...");
        this.playing = true;
        while (true) {
            if (!playing) {
                break;
            }
            try {
                currentSong = musicQueue.poll(1, TimeUnit.MINUTES);
                if (currentSong != null) {
                    player.play(currentSong);
                }
            } catch (InterruptedException e) {
                LOGGER.info("no music to play, retry");
            }
        }
    }

    public void stopPlay() {
        this.playing = false;
    }

    public void clear() {
        this.musicQueue.clear();
    }

    public boolean isPlaying() {
        return playing && musicQueue.size() > 0;
    }

    public SongBo getCurrentSong() {
        return currentSong;
    }
}
