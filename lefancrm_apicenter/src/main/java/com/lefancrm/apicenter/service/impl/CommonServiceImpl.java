package com.lefancrm.apicenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.lefancrm.apicenter.exception.BizException;
import com.lefancrm.apicenter.service.CommonService;
import com.lefancrm.apicenter.util.pinganfu.AESUtils;
import com.lefancrm.apicenter.util.pinganfu.HashFuncUtils;
import com.lefancrm.apicenter.util.pinganfu.RsaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service("commonService")
public class CommonServiceImpl<T> implements CommonService<T> {

    private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);


    @Override
    public String hashFunc(T t, String hashFunc, String salt, String charset) throws BizException {

        String jsonString = JSON.toJSONString(t);
        String content = jsonString + salt;
        String hashFuncStr = null;

        logger.info("hashFunc接口token值为:{}", content);

        try {
            hashFuncStr = HashFuncUtils.encryptPwd(content, hashFunc, charset);
        } catch (UnsupportedEncodingException e) {
            throw new BizException(e);
        }

        return hashFuncStr;
    }

    @Override
    public String aes(T t, String key) throws BizException {

        String jsonString = JSON.toJSONString(t);

        logger.info("AES接口加密原文：{}",jsonString);
        String aesStr = null;

        try {
            aesStr = AESUtils.encrypt4Aes(jsonString, key);
        } catch (Exception e) {
            throw new BizException(e);
        }

        return aesStr;
    }

    @Override
    public String sign(String content, String privateKey) throws BizException {

        String sign= null;
        try {
            sign = RsaUtils.sign(privateKey, content);
            logger.info("sign签名结果：{}", sign);

        } catch (Exception e) {
            throw new BizException(e);
        }

        return sign;
    }
}
