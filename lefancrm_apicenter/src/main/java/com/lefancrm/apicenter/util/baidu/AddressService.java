package com.lefancrm.apicenter.util.baidu;

import com.alibaba.fastjson.JSONObject;
import com.lefancrm.apicenter.util.HttpClientUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AddressService {
    private static final String ak = "ovrhA4ffmzssZpyggp4Ks2lfwMtSVMru";
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        String url = "https://api.map.baidu.com/place/v2/suggestion?query=儿童医学&region=上海&city_limit=true&output=json&ak=" + ak;
        String s = sendPost(url, jsonObject);
        JSONObject res = JSONObject.parseObject(s);
        List result = (List)res.get("result");
        for (Object o : result) {
            System.out.println(o);
        }
    }

    public static String sendPost(String url, JSONObject param) {
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
