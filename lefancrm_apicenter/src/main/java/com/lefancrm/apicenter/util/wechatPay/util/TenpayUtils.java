package com.lefancrm.apicenter.util.wechatPay.util;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 微信帮助类
 */
public class TenpayUtils {

	private TenpayUtils() {}

	// 随机字符串因子
	private static final String RANDOM_STRINGS = "abcdefghijklmnopqrstuvwxyz0123456789";
	
	/**
	 * 获取短Timestamp
	 * @return
	 */
	public static String getShortTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	/**
	 * 生成随机字符串
	 * @param count 默认32位
	 * @return
	 */
	public static String random(int... count) {
		if (null != count && count.length > 0) {
			return RandomStringUtils.random(count[0], RANDOM_STRINGS);
		}
		return RandomStringUtils.random(32, RANDOM_STRINGS);
	}

	/**
	 * 组装签名的字段
	 * @param
	 * @param encode
	 * @return
	 */
	private static String packageSign(Map<String, String> params,  boolean encode) {
		// 先将参数以其参数名的字典序升序进行排序
		Map<String, String> sortedParams = new TreeMap<String, String>(params);
		// 去除参与的参数sign
		sortedParams.remove("sign");
		// 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (Entry<String, String> param : sortedParams.entrySet()) {
			if (first) {
				first = false;
			} else {
				sb.append("&");
			}
			sb.append(param.getKey()).append("=");
			String value = param.getValue();
			if (encode) {
				try { value = URLEncoder.encode(value, "UTF-8"); } catch (UnsupportedEncodingException e) {}
			}
			sb.append(value);
		}
		return sb.toString();
	}

	/**
	 * 生成签名
	 * @return
	 */
	public static String createSign(Map<String, String> data) {
		String string1 = packageSign(data, false);
		String stringSignTemp = string1 + "&key=" + WxPayConfig.app_key;
		return DigestUtils.md5Hex(stringSignTemp).toUpperCase();
	}
    /**
     * 生成签名
     * @return
     */
    public static String createJsSign(Map<String, String> data) {
        String string1 = packageSign(data, false);
        String stringSignTemp = string1 + "&key=" +WxPubPayConfig.app_key;
        return DigestUtils.md5Hex(stringSignTemp).toUpperCase();
    }
	/**
	 * 转换返回的数据
	 * @param in
	 * @return
	 * @throws Exception
	 */
	/*@SuppressWarnings("unchecked")*/
	/*public static <T> T toResult(InputStream in, Class<T> clazz) throws Exception{
		XStream xstream = XstreamUtils.getXstream(clazz);
		xstream.alias("xml", clazz);
		return (T) xstream.fromXML(in);
	}

	@SuppressWarnings("unchecked")
	public static <T> T toResult(String xml, Class<T> clazz) throws Exception {
		XStream xstream = XstreamUtils.getXstream(clazz);
		xstream.alias("xml", clazz);
		return (T) xstream.fromXML(xml);
	}

	public static <T> String toXML(Object bean, Class<T> clazz) throws Exception {
		XStream xstream = XstreamUtils.getXstream(clazz);
		xstream.alias("xml", clazz);
		return xstream.toXML(bean);
	}*/
}
