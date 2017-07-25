package cn.ellacat.tools.alarm.speaker.util;

import cn.ellacat.tools.alarm.speaker.baidu.VoiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * @author wjc133
 */
public class MacAddressUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoiceClient.class);

    public static String getMacAddress() {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            System.out.println(ia);
            byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                //字节转换为整数
                int temp = mac[i] & 0xff;
                String str = Integer.toHexString(temp);
                System.out.println("每8位:" + str);
                if (str.length() == 1) {
                    sb.append("0" + str);
                } else {
                    sb.append(str);
                }
            }
            return sb.toString().toUpperCase();
        } catch (Exception e) {
            LOGGER.warn("getMacAddress failed... ", e);
        }
        return "";
    }
}
