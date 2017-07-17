package cn.ellacat.tools.alarm.encrypt;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;

/**
 * Created by wjc133
 * Date: 2015/11/26
 * Time: 18:22
 */
public class Encrypt {
    public static EncryptResult aesRsaEncrypt(String text, String pubKey, String modulus, String nonce) {
        EncryptResult result = new EncryptResult();
        String secKey = createSecretKey(16);
        String encText = aesEncrypt(text, nonce);
        encText = aesEncrypt(encText, secKey);
        String encSecKey = rsaEncrypt(secKey, pubKey, modulus);
        result.setEncText(encText);
        result.setEncSecKey(encSecKey);
        return result;
    }

    private static String createSecretKey(int bit) {
        String keys = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < bit; i = i + 1) {
            double pos = Math.random() * keys.length();
            pos = Math.floor(pos);
            key.append(keys.charAt((int) pos));
        }
        return key.toString();
    }

    private static String aesEncrypt(String text, String secKey) {
        try {
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
            byte[] raw = secKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            //PKCS5
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            return new BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String rsaEncrypt(String text, String pubKey, String modulus) {
        StringBuilder buffer = new StringBuilder(text);
        buffer.reverse();
        String reversedText = buffer.toString();
        BigInteger biText = new BigInteger(EncryptUtils.stringToHexString(reversedText), 16);
        BigInteger biPubKey = new BigInteger(pubKey, 16);
        BigInteger biModulus = new BigInteger(modulus, 16);
        BigInteger biRet = biText.modPow(biPubKey, biModulus);
        return addPadding(biRet.toString(16), modulus);
    }

    private static String addPadding(String encText, String modulus) {
        int ml = modulus.length();
        for (int i = 0; ml > 0 && modulus.charAt(i) == '0'; i++) ml--;
        int num = ml - encText.length();
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < num; i++) {
            prefix.append('0');
        }
        return prefix.toString() + encText;
    }
}
