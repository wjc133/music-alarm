package cn.ellacat.tools.alarm;

import cn.ellacat.tools.alarm.encrypt.EncryptGenertor;
import cn.ellacat.tools.alarm.encrypt.EncryptResult;
import org.junit.Test;

/**
 * @author wjc133
 */
public class ParamsTest {
    @Test
    public void testGenerate() {
        Long musicId = 3935139L;
        EncryptResult result = EncryptGenertor.getResult(musicId);
        System.out.println("params=" + result.getEncText());
        System.out.println("sec=" + result.getEncSecKey());
    }
}
