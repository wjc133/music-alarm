package cn.ellacat.tools.alarm.alarm;

/**
 * @author wjc133
 */
public interface Player {
    void play(SongBo songBo);

    void pause();

    void resume();

    void stop();

    void addVol();

    void subVol();
}
