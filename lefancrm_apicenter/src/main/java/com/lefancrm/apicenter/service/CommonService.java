package com.lefancrm.apicenter.service;

import com.lefancrm.apicenter.exception.BizException;

public interface CommonService<T> {

    public String hashFunc(T t, String hashFunc, String salt, String charset) throws BizException;

    public String aes(T t, String key) throws BizException;

    public String sign(String content, String privateKey) throws BizException;

}
