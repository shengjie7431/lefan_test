package com.lefancrm.apicenter.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lefancrm.apicenter.model.SendSms;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jani on 2019/5/17.
 */
public class WechatTempleMsgUtil {
    @Value("${redis.host}")
    private String redisIp;
    @Value("${redis.port}")
    private Integer redisPort;
    @Value("${redis.password}")
    private String redisPassword;

    private static String appId2 = "wx737e361255f28485";
    private static String appSecret2 = "0cc208e330ff2fd1a7602d1363314523";

    public static Object sendWechatMsg(String openid2,String pagePath,String firstStr,String firstStrColor,String keyword1Str,String keyword1StrColor,String keyword2Str,String keyword2StrColor,String remarkStr ,String remarkStrColor,String appId2,String appSecret2) throws Exception {
        WxMssVo wx=new WxMssVo();//发送模板消息请求参数封装对象
        wx.setTouser(openid2);
        wx.setTemplate_id("i4Xm_FLuyJxFgyXtrx6ApNlwGaDdtku7cXfqZY8-hm8");
        Map<String,String> miniprogram=new HashMap<>();
        miniprogram.put("appid","wx698f1593ad128635");
        miniprogram.put("pagepath",pagePath);
        wx.setMiniprogram(miniprogram);
        Map<String,String> first=new HashMap<>();
        first.put("value",firstStr);
        first.put("color",firstStrColor);
        Map<String,String> keyword1=new HashMap<>();
        keyword1.put("value",keyword1Str);
        keyword1.put("color",keyword1StrColor);
        Map<String,String> keyword2=new HashMap<>();
        keyword2.put("value",keyword2Str);
        keyword2.put("color",keyword2StrColor);

        Map<String,String> remark=new HashMap<>();
        remark.put("value",remarkStr);
        remark.put("color",remarkStrColor);

        Map<String, Map<String,String>> map= new HashMap<>();
        map.put("first",first);
        map.put("keyword1",keyword1);
        map.put("keyword2",keyword2);
        map.put("remark",remark);
        wx.setData(map);
        String jsonString = JSON.toJSONString(wx);
        String access_token=getAccess_token(appId2,appSecret2);
        String data= HttpClientUtils.postJson1("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token, jsonString, "utf8");
        System.out.println(data);
        return null;
    }

    public static Object sendWechatMsg(String openid2,String pagePath,String firstStr,String firstStrColor,String keyword1Str,String keyword1StrColor,String keyword2Str,String keyword2StrColor,String remarkStr ,String remarkStrColor) throws Exception {
        WxMssVo wx=new WxMssVo();//发送模板消息请求参数封装对象
        wx.setTouser(openid2);
        wx.setTemplate_id("i4Xm_FLuyJxFgyXtrx6ApNlwGaDdtku7cXfqZY8-hm8");
        Map<String,String> miniprogram=new HashMap<>();
        miniprogram.put("appid","wx698f1593ad128635");
        miniprogram.put("pagepath",pagePath);
        wx.setMiniprogram(miniprogram);
        Map<String,String> first=new HashMap<>();
        first.put("value",firstStr);
        first.put("color",firstStrColor);
        Map<String,String> keyword1=new HashMap<>();
        keyword1.put("value",keyword1Str);
        keyword1.put("color",keyword1StrColor);
        Map<String,String> keyword2=new HashMap<>();
        keyword2.put("value",keyword2Str);
        keyword2.put("color",keyword2StrColor);

        Map<String,String> remark=new HashMap<>();
        remark.put("value",remarkStr);
        remark.put("color",remarkStrColor);

        Map<String, Map<String,String>> map= new HashMap<>();
        map.put("first",first);
        map.put("keyword1",keyword1);
        map.put("keyword2",keyword2);
        map.put("remark",remark);
        wx.setData(map);
        String jsonString = JSON.toJSONString(wx);
        String access_token=getAccess_token(appId2,appSecret2);
        String data= HttpClientUtils.postJson1("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token, jsonString, "utf8");
        System.out.println(data);
        return null;
    }

    private  static String getAccess_token(String appId2,String appSecret2)  throws IOException  {//获得公众号access_token
       //从数据库中获取access_token
        String access_token="";
        if(StringUtils.isEmpty(access_token)){
            String access_token_json="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId2+"&secret="+appSecret2+"";
            Map<String,String> map=new HashMap<String,String>();
            String str = HttpClientUtils.httpPost(access_token_json,map);
            JSONObject accessTokenJsonObj = JSON.parseObject(str);
            access_token = accessTokenJsonObj.get("access_token").toString();
         }
        return access_token;
    }



    /**
     * 狄大人小程序关联的公众号消息openid2，openid2取登录用户的：lfpc2_openid
     * @param openid2
     * @param pagePath
     * @param firstStr
     * @param firstStrColor
     * @param keyword1Str
     * @param keyword1StrColor
     * @param keyword2Str
     * @param keyword2StrColor
     * @param remarkStr
     * @param remarkStrColor
     * @return
     * @throws Exception
     */
    public static SendSms sendWechatMsgDdr(String openid2, String pagePath, String firstStr, String firstStrColor, String keyword1Str, String keyword1StrColor, String keyword2Str, String keyword2StrColor, String remarkStr , String remarkStrColor) throws Exception {
        WxMssVo wx=new WxMssVo();//发送模板消息请求参数封装对象
        wx.setTouser(openid2);
        wx.setTemplate_id("i4Xm_FLuyJxFgyXtrx6ApNlwGaDdtku7cXfqZY8-hm8");
        Map<String,String> miniprogram=new HashMap<>();
        miniprogram.put("appid","wx5c814b8ea29cc21c");//wx698f1593ad128635 //wx51963ff5aed75bec
        miniprogram.put("pagepath",pagePath);
        wx.setMiniprogram(miniprogram);
        Map<String,String> first=new HashMap<>();
        first.put("value",firstStr);
        first.put("color",firstStrColor);
        Map<String,String> keyword1=new HashMap<>();
        keyword1.put("value",keyword1Str);
        keyword1.put("color",keyword1StrColor);
        Map<String,String> keyword2=new HashMap<>();
        keyword2.put("value",keyword2Str);
        keyword2.put("color",keyword2StrColor);

        Map<String,String> remark=new HashMap<>();
        remark.put("value",remarkStr);
        remark.put("color",remarkStrColor);

        Map<String, Map<String,String>> map= new HashMap<>();
        map.put("first",first);
        map.put("keyword1",keyword1);
        map.put("keyword2",keyword2);
        map.put("remark",remark);
        wx.setData(map);
        String jsonString = JSON.toJSONString(wx);
        String access_token = getAccess_token();
        System.out.println(access_token);
        String data= HttpClientUtils.postJson1("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token, jsonString, "utf8");
        System.out.println(data);
        return null;
    }

    private static String getAccess_token() throws Exception{
        WechatTempleMsgUtil wechatTempleMsgUtil = new WechatTempleMsgUtil();
        System.out.println(wechatTempleMsgUtil.redisIp);
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("shlefan.com123");
        if (!jedis.exists("weChatToken")) {
            String access_token_json="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId2+"&secret="+appSecret2+"";
            Map<String,String> map=new HashMap<String,String>();
            String str = HttpClientUtils.httpPost(access_token_json,map);
            JSONObject accessTokenJsonObj = JSON.parseObject(str);
            String accessToken = accessTokenJsonObj.get("access_token").toString();
            jedis.set("weChatToken",accessToken);
            jedis.expire("weChatToken",60 * 4);//token缓存五分钟
            return accessToken;
        }else{
            return jedis.get("weChatToken");
        }
    }
}
