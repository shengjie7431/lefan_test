package com.lefancrm.apicenter.util.wechatPay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	//private static final Log logger = Logs.get();  
    private final static int CONNECT_TIMEOUT = 5000; // in milliseconds  
    private final static String DEFAULT_ENCODING = "UTF-8";  
      
    public static String postData(String urlStr, String data){  
        return postData(urlStr, data, null);  
    }  
      
    public static String postData(String urlStr, String data, String contentType){  
        BufferedReader reader = null;  
        try {
        	byte[] entity = data.toString().getBytes();
        	HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
        	conn.setConnectTimeout(5000);
    		conn.setRequestMethod("POST");
    		conn.setDoOutput(true);
    		//conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);  
            if(data == null)  
                data = "";  
            writer.write(data);   
            writer.flush();  
            writer.close();
            /*URL url = new URL(urlStr);  
            URLConnection conn = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)conn;
            httpURLConnection.setDoOutput(true);  
            httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);  
            //httpURLConnection.setReadTimeout(CONNECT_TIMEOUT);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();
            httpURLConnection.getOutputStream();
            System.out.println(httpURLConnection.getResponseCode()+"weqeqweq");
            if(contentType != null)  
            	httpURLConnection.setRequestProperty("content-type", contentType);
            System.out.println("11111111111111");
            OutputStream writer = httpURLConnection.getOutputStream();
            System.out.println("22222222222222222");
            if(data == null)  
                data = "";
            byte[] entity = data.toString().getBytes();
            writer.write(entity);   
            writer.flush();  
            writer.close();    
            System.out.println("333333333333");*/
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));  
            StringBuilder sb = new StringBuilder();  
            String line = null;  
            while ((line = reader.readLine()) != null) {  
                sb.append(line);  
                sb.append("\r\n");  
            }
            System.out.println(sb.toString());
            return sb.toString();  
        } catch (IOException e) {  
            //logger.error("Error connecting to " + urlStr + ": " + e.getMessage()); 
        	System.out.println("Error connecting to " + urlStr + ": " + e.getMessage());
        } finally {  
            try {  
                if (reader != null)  
                    reader.close();  
            } catch (IOException e) {  
            }  
        }  
        return null;  
    }  

}
