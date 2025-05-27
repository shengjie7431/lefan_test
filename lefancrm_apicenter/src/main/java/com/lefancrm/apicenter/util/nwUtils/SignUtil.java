package com.lefancrm.apicenter.util.nwUtils;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by CHENQIMING.
 * 加签/验签工具类
 */
public class SignUtil {
	private static final Logger log = LoggerFactory.getLogger(SignUtil.class);


	/**
	 * 加签
	 * @param map 请求map
	 * @param secret 加签验值
	 * @return
	 */
	public static String sign(Map<String, Object> bizContent, String timestamp, String secret) {
		String genSign = "";
		try {
			Map<String, Object> map = new HashMap<String, Object>(bizContent); 
			StringBuilder stringBuilder = new StringBuilder("");
			map.put("timestamp", timestamp);
			//将map放入treeMap进行生序排序
			TreeMap<String, Object> treeMap = sortMap(map);
			String treeMapStr = JSONObject.toJSONString(treeMap);
			stringBuilder.append(treeMapStr);
			//将私钥加在排好序的map字符串后
			String orgStr = stringBuilder.append(secret).toString();
			log.info("加签原文：{}", orgStr);
			genSign = DigestUtils.md5Hex(orgStr.getBytes("UTF-8")).toLowerCase();//指定编码格式
			log.info("加签密文：{}", genSign);
		} catch (Exception e) {
			log.error("加签失败", e);
		}
		return genSign;
	}

	/**
	 * 验签方法
	 * @param map 请求map
	 * @param secret 加签验值
	 * @param sign 签名密文
	 * @return
	 */
	public static boolean verify(Map<String, Object> map, String secret, String sign) {
		boolean flag = false;
		try {
			StringBuilder stringBuilder = new StringBuilder("");
			//将map放入treeMap进行生序排序
			TreeMap<String, Object> treeMap = sortMap(map);
			String treeMapStr = JSONObject.toJSONString(treeMap);
			stringBuilder.append(treeMapStr);
			//将私钥加在排好序的map字符串后
			String orgStr = stringBuilder.append(secret).toString();
			log.info("加签原文：{}", orgStr);
			String genSign = DigestUtils.md5Hex(orgStr.getBytes("UTF-8")).toLowerCase();//指定编码格式
			log.info("加签密文：{}", genSign);
			if(genSign.equals(sign)){
				flag = true;
			}
		} catch (Exception e) {
			log.error("验签失败", e);
		}
		return flag;
	}


	@SuppressWarnings("unchecked")
	private static TreeMap<String, Object> sortMap(Map<String, Object> map) {
		TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
		for(Map.Entry<String, Object> entry: map.entrySet()) {
			if(entry.getValue() instanceof Map) {
				TreeMap<String, Object> subTreeMap = sortMap((Map<String, Object>)entry.getValue());
				treeMap.put(entry.getKey(), subTreeMap);
			} else {
				treeMap.put(entry.getKey(), entry.getValue());
			}
		}
		return treeMap;
	}
}
