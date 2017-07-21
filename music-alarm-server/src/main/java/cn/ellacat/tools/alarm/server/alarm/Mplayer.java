package cn.ellacat.tools.alarm.server.alarm;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wjc133
 */
public class Mplayer implements Player {
    private static final Logger LOGGER = LoggerFactory.getLogger(Mplayer.class);

    @Override
    public void play(SongBo songBo) {
        String url = songBo.getUrl();
        if (url == null || url.length() <= 0) {
            LOGGER.warn("invalid url. songBo={}", songBo);
            return;
        }
        try {
            CommandLine cmd = new CommandLine("mplayer");
            cmd.addArgument(url);
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValues(new int[]{0, 1});
            int exitValue = executor.execute(cmd);
            LOGGER.info("end of file. exitValue={}", exitValue);
        } catch (Exception e) {
            LOGGER.error("play music with mplayer failed. url={}", url, e);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void addVol() {

    }

    @Override
    public void subVol() {

    }
}
