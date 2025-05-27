package com.lefancrm.apicenter.util.pinganfu.http;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Map.Entry;

public class PostProxy {
	
    private HttpConnectionManager  connectionManager;

    private static PostProxy postProxy  = new PostProxy();

    /**
     * 工厂方法
     * 
     * @return
     */
    public static PostProxy getInstance() {
        return postProxy;
    }

    /**
     * 私有的构造方法
     */
    private PostProxy() {
        // 创建一个线程安全的HTTP连接池
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(30);
        connectionManager.getParams().setMaxTotalConnections(80);

        IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
        ict.addConnectionManager(connectionManager);
        ict.setConnectionTimeout(60000);

        ict.start();
    }
  	
  	public String postPairHttp(Map context,Map<String,String> head, Map<String,String> params) throws HttpException, IOException{
		String url = (String)context.get("hostName");
		NameValuePair[] nameValuePair = mapToNameValuePair(params);
  			
  	    HttpClient httpclient = new HttpClient(connectionManager);
  	
  	    // 设置连接超时
  	    int connectionTimeout = 8000;
  	    httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);
  	
  	    // 设置回应超时
  	    int soTimeout = 60000;
  	    httpclient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
  	
  	    // 设置等待ConnectionManager释放connection的时间
  	    httpclient.getParams().setConnectionManagerTimeout(3 * 1000);
  	
		String charset = (String)context.get("charset");
		charset = charset == null ? "GBK" : charset;
  	    
  	    //post模式
  		HttpMethod method = new PostMethod(url);
  	    ((PostMethod) method).addParameters(nameValuePair);
  	    method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset="+charset);
  	    
  	    
  	    // 设置Http Header中的User-Agent属性
  	    method.addRequestHeader("User-Agent", "Mozilla/4.0");
  	    if(null != head && head.size()>0){
  	    	for(Object o: head.entrySet()) {
				method.addRequestHeader(((Entry) o).getKey().toString(), ((Entry) o).getValue().toString());
			}
  	    }
  	
  			String strResult = null;
  	    try {
  	        httpclient.executeMethod(method);
  	      	byte[] byteResult = method.getResponseBody();
  	  	    strResult = new String(byteResult, charset);
  	    } catch (UnknownHostException ex) {
  	        ex.printStackTrace();
  	        return null;
  	    }finally {
  	        method.releaseConnection();
  	    }
  	    
  	    if (strResult == null) {
  	        return null;
  	    }else{
  	        return strResult;
  	    }
  	}
  	
  	private NameValuePair[] mapToNameValuePair(Map<String, String> map) {
    	NameValuePair[] para = new NameValuePair[map.size()];
        int i = 0;
        for (Entry<String, String> entry : map.entrySet()) {
        	Object value = entry.getValue();
        	para[i++] = new NameValuePair(entry.getKey(), (String)value);
        }
        return para;
    }
}
