package com.lefancrm.apicenter.util.nwUtils;


import java.io.Serializable;


/**
 * 网关请求参数基础类
 * 
 * @author CHENQIMING
 */
public class RequestBase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 应用授权key
	 */
	private String appKey;

	/**
	 * 请求业务数据
	 */
	private Object bizContent;

	/**
	 * 签名
	 */
	private String signValue;

	/**
	 * 时间戳
	 */
	private String timestamp;

	/**
	 * 版本号
	 */
	private String version;

	
	
	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public Object getBizContent() {
		return bizContent;
	}

	public void setBizContent(Object bizContent) {
		this.bizContent = bizContent;
	}

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
