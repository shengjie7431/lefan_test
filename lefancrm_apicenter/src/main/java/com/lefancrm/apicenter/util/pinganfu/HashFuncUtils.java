package com.lefancrm.apicenter.util.pinganfu;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFuncUtils {

    /**
     * 对字符串计算数字摘要,加密算法使用 MD5,SHA-1,SHA-256,默认使用 SHA-1 *
     * String SHA1Key = ""; --约定为空
     * String token=StringEncrypt.encryptPwd(content+SHA1Key, "SHA-1", "UTF-8");
     * @param strSrc  要加密的字符串
     * @param encName 加密类型
     * @return
     * @throws java.io.UnsupportedEncodingException
     */

    public static String encryptPwd(String strSrc, String encName, String charset) throws
            UnsupportedEncodingException {
        if (strSrc == null) {
            return null;
        }
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes(charset);
        try {

            if (encName == null || encName.equals("")) {
                encName = "SHA-1";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            // to HexString
            strDes = DigestUtils.bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }



}
