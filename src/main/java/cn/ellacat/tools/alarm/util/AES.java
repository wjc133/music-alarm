package cn.ellacat.tools.alarm.util; /**
 * Created by PVer on 2017/3/19.
 */

import cn.ellacat.tools.alarm.netease.MusicParams;
import org.apache.commons.text.RandomStringGenerator;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.Scanner;

public class AES {
    private static String arg1 = "010001";
    private static String arg2 = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17" +
            "a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c938701" +
            "14af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97dde" +
            "f52741d546b8e289dc6935b3ece0462db0a22b8e7";
    private static String arg3 = "0CoJUm6Qyw8W8jud";

    // 加密
    public static String encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());

        return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030405060708"
                    .getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    private static String randomString(int length) {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z').build();
        return generator.generate(16);
    }

    public static String getParams(String str,String text) throws Exception {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z').build();
        String randomLetters = generator.generate(16);
        String r1 = encrypt(text, arg3);
        String r2 = encrypt(r1, randomLetters);

        String first_key = "0CoJUm6Qyw8W8jud";
        String second_key = "FFFFFFFFFFFFFFFF";
        String h_encText = AES.encrypt(text, first_key);
        h_encText = AES.encrypt(h_encText, second_key);
        return h_encText;
    }

    public static String getEncSecKey(String str) {
        String encSecKey = "257348aecb5e556c066de214e531faadd1c55d814f9be95fd06d6bff9f4c7a41f831f6394d5a3fd2e3881736d94a02ca919d952872e7d0a50ebfa1769a7a62d512f5f1ca21aec60bc3819a9c3ffca5eca9a0dba6d6f7249b06f5965ecfff3695b54e1c28f3f624750ed39e7de08fc8493242e26dbc4484a01c76f739e135637c";
        return encSecKey;
    }

    public static MusicParams getMusicParams(long id) throws Exception {
        /*
         * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定
         * 此处使用AES-128-CBC加密模式，key需要为16位。
         */
        MusicParams params = new MusicParams();
        String b1 = "{\"ids\":\"[" + id + "]\",\"br\":320000,\"csrf_token\":\"\"}";
        String str = randomString(16);
        String p1 = getParams(str, b1);
        params.setParams(p1);

        String encSecKey = getEncSecKey(str);
        params.setSecret(encSecKey);

        return params;
    }
}