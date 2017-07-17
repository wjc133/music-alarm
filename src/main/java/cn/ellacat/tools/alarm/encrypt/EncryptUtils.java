package cn.ellacat.tools.alarm.encrypt;

/**
 * Created by wjc133 on 2015/11/26.
 * Time: 22:45
 */
public class EncryptUtils {
    public static String stringToHexString(String strPart) {
        String hexString = "";
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int) strPart.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString = hexString + strHex;
        }
        return hexString;
    }
}
