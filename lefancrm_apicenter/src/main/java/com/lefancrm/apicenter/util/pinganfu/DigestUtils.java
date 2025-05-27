package com.lefancrm.apicenter.util.pinganfu;

import org.apache.commons.codec.binary.Base64;

public class DigestUtils {


    public static int getChrInt(char chr) {
        int iRet = 0;
        if (chr == "0".charAt(0)) {
            iRet = 0;
        }
        if (chr == "1".charAt(0)) {
            iRet = 1;
        }
        if (chr == "2".charAt(0)) {
            iRet = 2;
        }
        if (chr == "3".charAt(0)) {
            iRet = 3;
        }
        if (chr == "4".charAt(0)) {
            iRet = 4;
        }
        if (chr == "5".charAt(0)) {
            iRet = 5;
        }
        if (chr == "6".charAt(0)) {
            iRet = 6;
        }
        if (chr == "7".charAt(0)) {
            iRet = 7;
        }
        if (chr == "8".charAt(0)) {
            iRet = 8;
        }
        if (chr == "9".charAt(0)) {
            iRet = 9;
        }
        if (chr == "A".charAt(0)) {
            iRet = 10;
        }
        if (chr == "B".charAt(0)) {
            iRet = 11;
        }
        if (chr == "C".charAt(0)) {
            iRet = 12;
        }


        if (chr == "D".charAt(0)) {
            iRet = 13;
        }
        if (chr == "E".charAt(0)) {
            iRet = 14;
        }
        if (chr == "F".charAt(0)) {
            iRet = 15;
        }
        return iRet;
    }


    // 将 s 进行 BASE64 编码
    public static String base64encode(byte[] src) {
        if (src == null) {
            return null;
        }

        return (new sun.misc.BASE64Encoder()).encode(src);
    }

    //将 BASE64 编码的字符串 s 进行解码
    public static byte[] base64decode(String s) {
        if (s == null) {
            return null;
        }
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * 十六进制字符转为字节 * @param 十六进制字符 * @return 字节
     * 平安壹钱包-科技中心
     * 59
     */
    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("byte length is not correct");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }


    /**
     * BASE64 解密 *
     *
     * @param key 密文
     * @return 明文字节数组
     * @throws Exception 异常码
     */
    public static byte[] decryptBASE64(String key) throws Exception { /*
 */
        return Base64.decodeBase64(key);
    }

    /**
     * BASE64 加密 *
     *
     * @param key 明文字节数组
     * @return base64 字符串
     * @throws Exception 异常
     */
    public static String encryptBASE64(byte[] key) throws Exception {

        return Base64.encodeBase64String(key);
    }
}
