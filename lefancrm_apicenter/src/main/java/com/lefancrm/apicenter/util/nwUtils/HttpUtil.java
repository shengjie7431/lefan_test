package com.lefancrm.apicenter.util.nwUtils;


import com.alibaba.fastjson.JSONObject;
import com.assist4j.http.request.HttpBodyRequest;
import com.assist4j.http.response.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.entity.ContentType;


/**
 * http工具类
 */
public abstract class HttpUtil {
	public static String doPost(String url, RequestBase base) {
		return doPost(url, base, 0, 0);
	}
	public static String doPost(String url, RequestBase base, int connectTimeout, int readTimeout) {
		String content = JSONObject.toJSONString(base);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)
															.setSocketTimeout(readTimeout)
															.build();
		HttpResponse<String> resp = HttpBodyRequest.create().initUrl(url)
																.initContentType(ContentType.APPLICATION_JSON)
																.initContent(content)
																.initRequestConfig(requestConfig)
																.initResponseBodyClass(String.class)
																.execute();
		
		if (!resp.isSuccess()) {
			throw new RuntimeException(resp.getErrorMessage());
		}
		return resp.getBody();
	}
}
