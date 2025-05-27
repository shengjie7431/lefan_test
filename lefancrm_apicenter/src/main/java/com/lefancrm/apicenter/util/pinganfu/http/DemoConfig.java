package com.lefancrm.apicenter.util.pinganfu.http;


public class DemoConfig {
	
	public static String charset = "UTF-8";//字符编码
	
	public static String signMethod = "SHA-256";//加签方法
	
	public static String merchantKey = "9286ed7a54e94c5e96820896d02c412d";//与平安付约定的密钥
	
	public static String merchantId = "900000000009";
	
	public static String version = "1.0.0";//消息版本
	
	public static String success = "S";//成功常量
	
	public static String fail = "N";//失败常量
	
	//测试环境地址
	public static String postUrl = "https://test-www.1qianbao.com/caps/request.do";
	
//	public static String postUrl = "http://localhost:8081/ffastpay";
//	public static String postUrl = "http://114.80.125.116:2191/ffastpay";
//	public static String postUrl = "http://localhost:8080/ffastpay";
//	public static String postUrl = "https://test-www.1qianbao.com/ffastpay";
	
//	"STABLE：
//	http://test-stable-www.stg.1qianbao.com:8109/ffastpay
//	公网：
//	http://119.18.227.191:8109/ffastpay
//	http://114.80.87.55:8109/ffastpay
//	内网：
//	http://10.59.9.133:8443/ffastpay
//	http://10.59.9.133:8080/ffastpay"
	
	//静默注册
//	"STABLE：
//	http://119.18.227.193:9080/caps/request.do
//	https://119.18.227.193:9443/caps/request.do"
//	STG1：
//	https://test-www.1qianbao.com/caps/request.do



	
}
