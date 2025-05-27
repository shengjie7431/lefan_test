package com.lefancrm.apicenter.util.nwUtils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class BaseOperator<T> {


	//公钥：MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAILokuj8/WZdFZ1p6mdQBwL7bO9nYi9dPYvR1X31TgA0/8d4OjkjjpdekUYF08lNjgbizYR/jxFDzsZTnQ2lbRkCAwEAAQ==
	//私钥：MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAguiS6Pz9Zl0VnWnqZ1AHAvts72diL109i9HVffVOADT/x3g6OSOOl16RRgXTyU2OBuLNhH+PEUPOxlOdDaVtGQIDAQABAkAFc/66qd88ZmBCIGldSF4iZ6rQBjR22KF5s5H7Hz7Sj1ME/Eqz+KDa7klWzzo7zkDQZzzHS3izyYcHrKYFcYChAiEA4w/cHR5aGTLEQWGlefUqmeWikP5H6Mg0KLC5MgEAdlUCIQCTl5qkfO+j8hfBQSiNTo7sAPz79WiZagwyQYThAc+3tQIhAIInDybpxP94qCJKFI5Jx/ebN4ohF5sJV0yTsDRHerpZAiB9DlX/zcSXm4e5PeaIZOoA2o2BR91LMGoJIy7eIFTKiQIhAL9yrgbgupnmeYhseu7t42SY7r2q3y6s3/C48TTWrxhb
	private final static Logger log = LoggerFactory.getLogger(BaseOperator.class);

	//每一个接口对应唯一的appkey和secretkey。在开通接口权限后，会分配这两个参数
//	public String appkey = "HJfn6rKn5lDuhoXvnmdGU7XSGTWCJy";
//	public String secretkey = "2XclklRqHqe7Rx4S7EV2DIjdgVQItv";

	//己方私钥
//	public String cprivatekey ="MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAguiS6Pz9Zl0VnWnqZ1AHAvts72diL109i9HVffVOADT/x3g6OSOOl16RRgXTyU2OBuLNhH+PEUPOxlOdDaVtGQIDAQABAkAFc/66qd88ZmBCIGldSF4iZ6rQBjR22KF5s5H7Hz7Sj1ME/Eqz+KDa7klWzzo7zkDQZzzHS3izyYcHrKYFcYChAiEA4w/cHR5aGTLEQWGlefUqmeWikP5H6Mg0KLC5MgEAdlUCIQCTl5qkfO+j8hfBQSiNTo7sAPz79WiZagwyQYThAc+3tQIhAIInDybpxP94qCJKFI5Jx/ebN4ohF5sJV0yTsDRHerpZAiB9DlX/zcSXm4e5PeaIZOoA2o2BR91LMGoJIy7eIFTKiQIhAL9yrgbgupnmeYhseu7t42SY7r2q3y6s3/C48TTWrxhb";
	//暖哇公钥
//	public String spublickey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCY8A9gKCjB5kBqxNUQn35ylFJCAv84U7xCsU+9vaKKtJ1t5ytRxUuk0Co6LNM8KB7OoM/ePwQbN5nav6E4SS4AHpXXY2rObHXg8BLM+5eKqHH/iwqQ7m1z4XT2dOsVAynesFUyggaFGeyr/wxlBJmavcK346xh0HHOfRDbc3g+DwIDAQAB";

	//测试环境地址
//	String url  = "https://gateway-test.nuanwa.net/gw/v2/offlineSupplier/fetchExecuteInfo";
	//https://test.zhongan.io/gateway/gw/v2
	//测试：https://gateway-test.nuanwa.net/gw/api/offlineSupplier/fetchExecuteInfo
	//预发：https://gateway-uat.nuanwa.net/gw/api/offlineSupplier/fetchExecuteInfo
	//生产：https://gateway.nuanwa.net/gw/api/offlineSupplier/fetchExecuteInfo



	public String operate(String appkey,String secretkey,String cprivatekey,String spublickey,String url) throws Exception {
		T bz = getBizContent();
		JSONObject bizContent = ZASignUtil.paramSignEncrypt(cprivatekey, spublickey, JSONObject.toJSONString(bz));
		//组装标准参数格式（也可以用Map或者JSONObject）
		RequestBase rb = new RequestBase();
		String timestamp = String.valueOf(System.currentTimeMillis());
		//加签
		String signValue = SignUtil.sign(bizContent, timestamp, secretkey);
		rb.setAppKey(appkey);
		rb.setBizContent(bizContent);
		rb.setSignValue(signValue);
		rb.setTimestamp(timestamp);
		rb.setVersion("1.0.0");
		log.debug("========request===========>"+ JSONObject.toJSONString(rb));
		String resp= HttpUtil.doPost(url, rb);
		log.debug("resp=====》"+resp);

		JSONObject jsonObject = JSON.parseObject(resp);
		String data = jsonObject.getString("data");
		String respStr = ZASignUtil.checkSignDecrypt(data, cprivatekey, spublickey);
		return respStr;
	}

	protected abstract T getBizContent();
}

