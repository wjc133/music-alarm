package cn.ellacat.tools.alarm.speaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.*;
import java.io.File;
import java.io.OutputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 声卡操作工具类
 *
 * @author wjc133
 */
public class AudioCard {
    private static final Logger LOGGER = LoggerFactory.getLogger(AudioCard.class);

    private AudioFormat audioFormat;
    private DataLine.Info dataLineInfo;
    private TargetDataLine targetDataLine;
    private final Executor captureExecutor = Executors.newSingleThreadExecutor();

    private static final AudioCard INSTANCE = new AudioCard();

    private AudioCard() {
        try {
            audioFormat = new AudioFormat(8000.0f, 16, 1, true, false);
            dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
        } catch (Exception e) {
            LOGGER.error("init AudioCard failed");
        }
    }

    public static AudioCard getInstance() {
        return INSTANCE;
    }

    public void startCapture(File out) {
        try {
            AudioSystem.getLine(dataLineInfo);
            captureExecutor.execute(() -> {
                try {
                    targetDataLine.open(audioFormat);
                    targetDataLine.start();
                    AudioSystem.write(new AudioInputStream(targetDataLine), AudioFileFormat.Type.WAVE, out);
                } catch (Exception e) {
                    LOGGER.error("capture audio failed.", e);
                }
            });
        } catch (LineUnavailableException e) {
            LOGGER.error("cannot find line.", e);
        }
    }

    public void stopCapture() {
        targetDataLine.stop();
//        targetDataLine.close();
    }

}
