package com.lefancrm.apicenter.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lefancrm.apicenter.util.spring.SpringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信请求工具类
 */

public class WxChatUtil {


    //获取openId
    public static final String WX_GET_OPENID_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    /**
     * 获取token
     */
    public static final String WX_GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={}&secret={}";

    public static final String WX_GET_STABLE_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/stable_token";

    /**
     * 获取用户授权手机号
     */
    public static final String WX_GET_PHONE_URL = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token={}";

    /**
     * 发送订阅消息
     */
    public static final String WX_SEND_SUBSCRIBE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token={}";

    /**
     * 获取ticket
     */
    public static final String WX_GET_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={}&type=jsapi";

    private static final Object accTokenLock = new Object();

    private static final Object ticketLock = new Object();


    public static String getToken(String appId,String appSecret) {
        RestTemplate restTemplate = getRestTemplate();

        Map<String, Object> body = new HashMap<>();
        body.put("grant_type", "client_credential");
        body.put("appid", appId);
        body.put("secret", appSecret);
        ResponseEntity<String> response = restTemplate.postForEntity(WX_GET_STABLE_TOKEN_URL,body, String.class);
        JSONObject tokenResponse = JSON.parseObject(response.getBody());
        String accessToken = tokenResponse.getString("access_token");
        return accessToken;
    }

    /**
     * 发送订阅消息
     * @param appId
     * @param appSecret
     * @param subscribeMessage
     */
    public static void sendSubscribeMessage(String appId, String appSecret,SubscribeMessage subscribeMessage) {
        RestTemplate restTemplate = getRestTemplate();
        String accessToken = getToken(appId, appSecret);
        ResponseEntity<String> response = restTemplate.postForEntity(String.format(WX_SEND_SUBSCRIBE_MESSAGE_URL, accessToken), subscribeMessage, String.class, new HashMap<>());

        JSONObject messageResponse = JSON.parseObject(response.getBody());
        String errcode1 = messageResponse.getString("errcode");
        if (!"0".equals(errcode1)) {
            System.out.println("发送消息失败，原因：" + messageResponse.getString("errmsg"));
        }
    }


    public static RestTemplate getRestTemplate() {
        return SpringUtils.getBean(RestTemplate.class);
    }
}
