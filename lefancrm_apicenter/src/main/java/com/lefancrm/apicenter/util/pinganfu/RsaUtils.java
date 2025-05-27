package com.lefancrm.apicenter.util.pinganfu;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaUtils {

    //秘钥算法
    private static final String KEY_ALGORITHM = "RSA"; //RSA 最大加密明文大小
    private static final int MAX_ENCRYPT_BLOCK = 117; //RSA 最大解密密文大小
    private static final int MAX_DECRYPT_BLOCK = 128; //秘钥长度
    private static final int RSA_KEY_SIZE = 1024;

    /**
     * 用私钥分段加密
     *
     * @param data       明文数据
     * @param privateKey 对应加密秘钥(<span style=color:red>秘钥存储使用
     *                   base64 加密<span/>)
     * @return 密文数据(<span style=color:red>需使用 base64 加密<span/>)
     * 13
     * <p>
     * 平安壹钱包-科技中心
     * @throws Exception 加密异常
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
//使用 base64 对加密秘钥进行解码
        byte[] keyBytes = DigestUtils.decryptBASE64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new
                PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
// 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }


    /**
     * 用私钥分段解密
     *
     * @param data       加密数据
     * @param privateKey 对应加密秘钥(<span style=color:red>秘钥存储使用
     *                   base64 加密<span/>)
     * @return 明文数据(<span style=color:red>需使用 base64 加密<span/>) * @throws Exception 加密异常
     */
    public static byte[] decryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        privateKey = formatString(privateKey);
//使用 base64 对加密秘钥进行解码
        byte[] keyBytes = DigestUtils.decryptBASE64(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
//Cipher cipher = Cipher.getInstance(RSA_PADDING_KEY);
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;

        int i = 0;
// 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);

            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }


            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * 公钥分段加密
     * 加密支持的最大字节数=公钥/8 - 11
     *
     * @param data      加密数据
     * @param publicKey 公钥
     * @param encode    字符编码(UTF-8)
     * @return 使用 base64 编码之后的字符串
     */
    public static String encryptByPublicKey(String data, String publicKey, String encode) {
        try {
            publicKey = formatString(publicKey);
            byte[] kb = DigestUtils.decryptBASE64(publicKey);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(kb);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicK = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicK);

            byte[] b = data.getBytes(encode);
            byte[] encrypt;
            int encryptMaxSiz = RSA_KEY_SIZE / 8 - 11;


            int inputLen = b.length;
            if (inputLen > encryptMaxSiz) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] cache;
                int offSet = 0, i = 0;
                while (inputLen - offSet > 0) {
                    if (inputLen - offSet > encryptMaxSiz) {
                        cache = cipher.doFinal(b, offSet, encryptMaxSiz);
                    } else {
                        cache = cipher.doFinal(b, offSet, inputLen - offSet);
                    }
                    out.write(cache, 0, cache.length);
                    i++;
                    offSet = i * encryptMaxSiz;
                }
                encrypt = out.toByteArray();
                out.close();
            } else {
                encrypt = cipher.doFinal(b);
            }
            return DigestUtils.encryptBASE64(encrypt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用公钥分段解密
     *
     * @param data      加密数据
     * @param publicKey 公钥
     * @return 解密数据(<span style=color:red>需使用 base64 进行解密
     * <span/>)
     * @throws Exception
     */

    public static byte[] decryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = DigestUtils.decryptBASE64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
// 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /**
     * 格式化秘钥中出现的换行字符 * @param key 秘钥串
     *
     * @return 格式化之后的秘钥
     * 平安壹钱包-科技中心
     * 18
     */
    private static String formatString(String key) {
        if (key == null) {
            return null;
        }
        return key.replaceAll("\\r", "").replaceAll("\\n", "");
    }


    /**
     * RSA 私钥加签
     *
     * @param priKeyText 经过 base64 处理后的私钥 * @param plainText 明文内容
     * @return 十六进制的签名字符串
     */
    public static String sign(String priKeyText, String plainText) {
        try {
            PKCS8EncodedKeySpec priPKCS8 =
                    new PKCS8EncodedKeySpec(Base64.decodeBase64(priKeyText));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey prikey = keyf.generatePrivate(priPKCS8);
// 用私钥对信息生成数字签名
            java.security.Signature signet = java.security.Signature.getInstance("SHA256withRSA");
            signet.initSign(prikey);
            signet.update(plainText.getBytes("UTF-8"));
            return DigestUtils.bytes2Hex(signet.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥验签
     *
     * @param pubKeyText 经过 base64 处理后的公钥
     * @param plainText  明文内容
     * @param signText   十六进制的签名字符串
     * @return 验签结果 true 验证一致 false 验证不一致
     */
    public static boolean verify(String pubKeyText, String plainText, String signText) {
        try {
            // 解密由 base64 编码的公钥,并构造 X509EncodedKeySpec 对象
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(
                    Base64.decodeBase64(pubKeyText));

            // RSA 算法
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            // 取公钥匙对象
            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec); // 十六进制数字签名转为字节
            byte[] signed = DigestUtils.hex2byte(signText.getBytes("UTF-8"));
            java.security.Signature signatureChecker = java.security.Signature
                    .getInstance("SHA256withRSA");
            signatureChecker.initVerify(pubKey);
            signatureChecker.update(plainText.getBytes("UTF-8")); // 验证签名是否正常

            return signatureChecker.verify(signed);
        } catch (Throwable e) {
            return false;
        }
    }


    public static void main(String[] args) {
        String testData = "what' your name?";
        // String privateKey =
        //         "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAPAdB Tvu2wwE1sqfNV345l4g2zus+AZ1XyjCtq7U7H1rlrGsnadqTd0NCIP1m39R+Ry RmGoViEObPFg6CRgOag/h9Mx6ojBjh7Sbj5YNMXoR/vi0RbZJ43BRWoG1w oP4MYTH1BNpaM4u77AjDh/iBG66ujbuhJy3I0l+InrbkLfZAgMBAAECgYEA 2+hYQNmzeEB+T7icceJhadgBsZfq2E9qxbP/CAQuS3fb3gHPqeKsSUWEhQbO UT9MPaQCyTXLRM/J5qvQZF3fN8KAOxJUXxu8iy2qb2XPv/9/1dZf0i2uCDiv jaCc8/PviXMZs0a0P4HDHsHHffn6vcYIiEnhCf2eMMIdV3nZxeECQQD4jr4qK MVVMtOme11COJgBnlHVv6CmTLeTGv8rz4LMzeBSJJfMVjfLpHf8Ivo4mVd x7VBlbNIVi7jwBVPLE4CtAkEA902M6tJ8BahnEX6oP1p6W2A2ChA6leE7wW nrtzDjiCG9nNExUFHhdCE5EceV8nfLIcX9XE/t6voEyVJU5pD9XQJBAJqBEJ BgW5nESHA6SxQ43bRT14bI4XG+SnZ0151CFop8hy5IdNud1H0PtU3T6Dp6h zLYU5tYc5bVDZaVmSqo6tkCQFzNJDFGVTYGUM8W2WoUuM+rVfwGxQV TZQoahlLTLL778lxzf+7lGxZqFTFf1RwM6hQ9aOsIL3663aryk1uGUx0CQAp0 fGJM/Y5Ic+rrZA46kV5+BtXJulkIRTVBTMW1vv714iCk6selXIzQo2sU0gD0B UVlx8V1yoKileuksRbuU5k=";
        // String publicKey =
        //         "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDwHQU77tsMBNb KnzVd+OZeINs7rPgGdV8owrau1Ox9a5axrJ2nak3dDQiD9Zt/UfkckZhqFYhD mzxYOgkYDmoP4fTMeqIwY4e0m4+WDTF6Ef74tEW2SeNwUVqBtcKD+DGE x9QTaWjOLu+wIw4f4gRuuro27oSctyNJfiJ625C32QIDAQAB";

        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUpwpvEfOyrer42X/N0DVq6gtl18fC5nNy44N7jAWilwcXXvcC6b/0AHbxtAwPk3XIj7iRq/mCN6Af1WoGCNH/pJVW7TOGf2esqQzz25naSoTqfvQVk8MhqKM8TAu+E8uY3saRQMRW6AJ7JB1L1rGm5oRPRQFMmOrJZNQDuhxCvwIDAQAB";


        String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANSnCm8R87Kt6vjZf83QNWrqC2XXx8Lmc3Ljg3uMBaKXBxde9wLpv/QAdvG0DA+TdciPuJGr+YI3oB/VagYI0f+klVbtM4Z/Z6ypDPPbmdpKhOp+9BWTwyGoozxMC74Ty5jexpFAxFboAnskHUvWsabmhE9FAUyY6slk1AO6HEK/AgMBAAECgYEArQcBsWBNBy0GDNeEayWn4UdwYLyTeN1kQK4olMVY7HOwvG5z1DX4DwS1w3M5gQ79/mQazMS+rxqtulk+/Iv+uWUtYqZYAZjpf+3ojrpTl9qz+DAqdc1leTe1FBaKlyWcpAGZ2KKp308Vw7H7uMxm4qFMXSuJWsaGDuR3NZHqzokCQQDrqk1a4Xinf/03vfH9PGVBPWp025wzyZMssflqsB80lIV9YjbFmssZgbi8NlyqrLFT8mXcHmz9UclO4wxlAaZDAkEA5wBmzHMcwUEugcSfZDGsaBv/DCR0+mT5p7z93dXvg5MboIDdLJ1iMGxrb8ricJSVSa4iFAfLZut5qEqpxpUP1QJAQWYaX20QuZL4J17r57W597fech17pJd84ztklunXTxUkUl5OCtphIGvyYzDG2IpiD4TGJaa/4jCvJ9g0WVdy1wJBAMKJvAkCCH1X/2qZxAIFM0SaDziSm1EkKHvy1Xk06fjMsr+AxsMD5+UP6YrEOd7FwtPCKkDkL5AFTvdDqabrceECQQDDiI7UrRdW4NJUO9qZ76EQuOQoZ3RMLAugSjd79fjYOdxtrI8ASu9T/CpkZ9TvqSSZOtHDBGYp2i7ERRJwvg5B";

        try {
//             testData = DigestUtils.encryptBASE64(
//                     (encryptByPrivateKey(testData.getBytes("UTF-8"), privateKey)));
//
//             System.out.println("Test data Encrypted by privateKey: " + testData);
//
//             testData = new String(decryptByPublicKey(DigestUtils.decryptBASE64(testData), publicKey), "UTF-8");
//
//             System.out.println("Test data Decrypted by publicKey: " + testData);
// //注意:该方法已经包含 base64 的转码，故不需要再次转码
//             testData = encryptByPublicKey(testData, publicKey, "UTF-8");
//
//             System.out.println("Test data Encrypted by publicKey: " + testData);

            // testData="0Oq3n7jDu8Kh9 cw/l3MiD/JibWPVCBVI/8MC1Fm02Ji/3wd7gbiAABFKveJkwHEh0/SkdbteJDuCwq3 kb9kxhsMX0A7 YoYSr8hWTF EXU45Xud72minbr6o5qVV4ZfH8P69V3f8rM95qvtF9SBq7cdHNtcd9tJc06lmJFf9t4RpxoL7FgvYAJs656q9Kj5VY7iE/VCgLyCGH3enmambb7jFcm/z7zrpXa2ru4E4wnt3umGx4Z0yV/AbD5sqthAmxUmpxN1fqJAn1nDXq t5CsyOvfQ/hvlU0URceK8yQJbv5r6XmHm9OVr0U9DPAp1DvkHcPe781cik2U1tNtSg==";

            testData = "04RbOE3CW9mokbHsgD6r6A5erRwN8dHBgLC7VpYykze+t3YusAMlGEz20jFdT3h+M6dSigb2TnjFvcIc6vuULiiCWAb4pl08P1fzKO8iilqO4dbbRMlJnbmEmsPrpib0zDvmeBWyJMgpeVStCEn4vanb4A30lUMGsw9Q13BkFBJ60d57TZbF3kBo8kH7DM/6XqbnqOS/jeC1GS/Kx9wA5zQAcyjSId21peXUiQy65AxDdzPA7ZgYNKtMntYvpxeR+JUs4Gz7kbBcnV477JlVBtIC+pknUy3tSf8ocaL9I10zS/KuyGdfx0XW9sLoO/mszNZq9ctIC0WxNfMfV70yKg==";

            testData = new String(decryptByPrivateKey(DigestUtils.decryptBASE64(testData), privateKey), "UTF-8");

            System.out.println("Test data Decrypted by privateKey:" + testData);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
