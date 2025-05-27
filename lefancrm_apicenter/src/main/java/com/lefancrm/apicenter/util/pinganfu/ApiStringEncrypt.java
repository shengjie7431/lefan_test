package com.lefancrm.apicenter.util.pinganfu;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;


/**
 * 
 *	平安付
 * Copyright (c) 2013-2014 PingAnFu,Inc.All Rights Reserved.
 */

/**
 * 
 * @author niyk
 * @version $Id: DesedeDemo.java, v 0.1 2014-6-26 下午4:22:53 niyk Exp $
 */
public class ApiStringEncrypt {

    /**
     * 密钥算法
     */
    public static final String KEY_ALGORITHM    = "DESede";

    /**
     * 转换密钥
     * @param key 二进制密钥
     * @return KEY 密钥
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception {
        DESedeKeySpec dks = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generateSecret(dks);
    }

    public static String decrypt(String data, String key) throws Exception{
//        byte[] bKey=Hex.decodeHex(key.toCharArray());
        byte[] bSrc=Hex.decodeHex(data.toCharArray());
        byte[] crypt = decrypt(bSrc, key.getBytes());
        return new String(crypt);
    }
    /**
     * 解密
     * @param data  待解密数据
     * @param key   密钥
     * @return byte[]   解密数据
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception{
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    public static String encrypt(String data, String key) throws Exception{
//        byte[] bKey=Hex.decodeHex(key.toCharArray());
//        byte[] bSrc=Hex.decodeHex(data.toCharArray());
        byte[] crypt = encrypt(data.getBytes(), key.getBytes());
        return new String(Hex.encodeHex(crypt)).toUpperCase();
    }
    /**
     * 加密
     * @param data  待加密数据
     * @param key   密钥
     * @return byte[]   加密数据
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception{
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    public static void main(String args[]) throws Exception{
        // String aaa = ApiStringEncrypt.encrypt("123abc", "dc2eb86cfd1e4134bd2368bc28820adf");
        String aaa = ApiStringEncrypt.encrypt("130682199606071234", "2fc2b8db899b4b6b8b7644780731bff8");
        System.out.println(aaa);
        String bbb = ApiStringEncrypt.decrypt(aaa, "2fc2b8db899b4b6b8b7644780731bff8");
        System.out.println(bbb);
    }
    
}
