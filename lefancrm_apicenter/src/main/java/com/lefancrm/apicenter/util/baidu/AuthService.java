package com.lefancrm.apicenter.util.baidu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.nlp.AipNlp;
import com.lefancrm.apicenter.util.HttpClientUtils;
import com.lefancrm.apicenter.util.wechatPay.util.HttpClientUtil;
import com.squareup.okhttp.OkHttpClient;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AuthService {

    //设置APPID/AK/SK
    @Value("${direction.text.error.baidu.appid}")
    public static final String APP_ID = "22974667";
    @Value("${direction.text.error.baidu.appkey}")
    public static final String API_KEY = "TBr6Q99EQgcoaK3VYs2lX2rV";
    @Value("${direction.text.error.baidu.secretkey}")
    public static final String SECRET_KEY = "3Ksd4c4lSiDGLFrwWWSbHPppUTrxn5O3";

    public String token() throws Exception{
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("shlefan.com123");
        if (!jedis.exists("chineseToken")) {
            Map<String,String> paramMap =  new HashMap<String,String>();
            paramMap.put("grant_type","client_credentials");
            paramMap.put("client_id",API_KEY);
            paramMap.put("client_secret",SECRET_KEY);
            String res = HttpClientUtils.httpPost("https://aip.baidubce.com/oauth/2.0/token",paramMap);
            JSONObject accessTokenJsonObj = JSON.parseObject(res);
            String accessToken = accessTokenJsonObj.get("access_token").toString();
            jedis.set("chineseToken",accessToken);
            jedis.expire("chineseToken",60 * 24 * 15);//缓存15天
            return accessToken;
        }else{
            return jedis.get("chineseToken");
        }

//        Map<String,String> paramMap =  new HashMap<String,String>();
//        paramMap.put("grant_type","client_credentials");
//        paramMap.put("client_id",API_KEY);
//        paramMap.put("client_secret",SECRET_KEY);
//        String res = HttpClientUtils.httpPost("https://aip.baidubce.com/oauth/2.0/token",paramMap);
//        JSONObject accessTokenJsonObj = JSON.parseObject(res);
//        String accessToken = accessTokenJsonObj.get("access_token").toString();
//        return accessToken;
    }

    public static void main(String[] args) throws Exception{
        AuthService authService = new AuthService();
        String token = authService.token();
        System.out.println("token:" + token);
        String url = "https://aip.baidubce.com/rpc/2.0/nlp/v1/ecnet?charset=UTF-8&access_token=" + token;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("text","她眼睛里坦荡清明的笑意像一百只毛绒绒的猫爪子");
        String res = authService.sendPost(url,jsonObject);
        System.out.println(res);
    }

    public static String valid(String text) throws Exception{
        AuthService authService = new AuthService();
        String token = authService.token();
        String url = "https://aip.baidubce.com/rpc/2.0/nlp/v1/ecnet?charset=UTF-8&access_token=" + token;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("text",text);
        String res = authService.sendPost(url,jsonObject);
        return res;
    }



    public String sendPost(String url, JSONObject param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        HttpURLConnection conn = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 设置连接超时时间
            conn.setConnectTimeout(3000);
            conn.setRequestProperty("Content-type", "application/json;charset=utf-8");
            conn.setRequestProperty("Charset", "UTF-8");

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (ConnectException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

}

