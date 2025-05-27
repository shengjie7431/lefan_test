package com.lefancrm.apicenter.util.pinganfu;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESUtils {

    /**
     * @param content 加密前数据
     * @return
     * @description: AES加密算法入口
     */
    public static String encrypt4Aes(String content, String key) {
        try {
            byte[] src = content.getBytes("UTF-8"); //加密
            byte[] bytOut = encryptMode(src, key);
            return DigestUtils.base64encode(bytOut);
        } catch (Exception e3) {
        }
        return null;
    }

    /**
     * AES算法解密入口
     */
    public static String decrypt4Aes2Str(String contentbase64, String key) {
        String Result = null;
        try {
            byte[] dst = decrypt4Aes(contentbase64, key);
            if (null != dst) {
                Result = new String(dst, "UTF-8");
            }
        } catch (Exception e3) {
        }
        return Result;
    }

    private static byte[] decrypt4Aes(String contentbase64, String key) {
        try {
            byte[] src = DigestUtils.base64decode(contentbase64); //解密
            return decryptMode(src, key);
        } catch (Exception e3) {
        }
        return null;
    }

    private static byte[] decryptMode(byte[] src, String key) {
        try {
            Cipher cip = Cipher.getInstance("AES");
            cip.init(Cipher.DECRYPT_MODE, getSecretKey(key));
            return cip.doFinal(src);
        } catch (Exception e3) {
        }
        return null;
    }


    /**
     * @param src 加密前数据字节 * @return
     * @description: AES加密实现
     */
    private static byte[] encryptMode(byte[] src, String key) {
        try {
            Cipher cip = Cipher.getInstance("AES");
            cip.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
            return cip.doFinal(src);
        } catch (Exception e3) {
        }
        return null;
    }

    private static SecretKey getSecretKey(String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        byte[] keybyte = getKeyByStr(key);// toReplace
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(keybyte);
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(secureRandom);
        return keygen.generateKey();
    }

    private static byte[] getKeyByStr(String str) {
        byte[] bRet = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            Integer itg = new Integer(16 * DigestUtils.getChrInt(str.charAt(2 * i)) + DigestUtils.getChrInt(str.charAt(2 * i + 1)));
            bRet[i] = itg.byteValue();
        }
        return bRet;
    }

    public static void main(String[] args) {

        String content="{\"bindMobile\":\"11\",\"birthDate\":\"2018-04-11\",\"channel\":\"3\",\"coOperCode\":\"8\",\"deviceMessage\":\"7\",\"deviceNum\":\"6\",\"identityNumber\":\"14\",\"identityType\":\"13\",\"merchantNo\":\"9\",\"partnerId\":\"10\",\"pcIp\":\"5\",\"realName\":\"12\",\"sex\":\"M\",\"system\":\"4\"}";

        String aes=AESUtils.encrypt4Aes(content, "6173646e636972757177656e636b6a71");

        System.out.println(aes);
        System.out.println(AESUtils.decrypt4Aes2Str(aes, "6173646e636972757177656e636b6a71"));
    }

}
