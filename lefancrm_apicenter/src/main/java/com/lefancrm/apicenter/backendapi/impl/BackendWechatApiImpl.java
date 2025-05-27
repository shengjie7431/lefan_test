package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendWechatApi;
import com.lefancrm.apicenter.dao.UserLoginMapper;
import com.lefancrm.apicenter.model.SurveyRiskCase;
import com.lefancrm.apicenter.model.SurveyRiskCaseInfo;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.model.UserLogin;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.SubscribeMessage;
import com.lefancrm.apicenter.util.WechatTempleMsgUtil;
import com.lefancrm.apicenter.util.WxChatUtil;
import com.lefancrm.base.annotations.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.*;

@Service
@ApiService(descript = "狄大人系统微信通知API")
public class BackendWechatApiImpl extends BaseServiceImpl implements BackendWechatApi {
    @Autowired
    private UserLoginMapper userLoginMapper;

    private static String appId2 = "wx737e361255f28485";
    private static String appSecret2 = "0cc208e330ff2fd1a7602d1363314523";


    @Override
    public Object send(List<UserInfo> toUsers, Map<String, Object> contentMap) {
        for (UserInfo toUser : toUsers) {
            send(toUser.getUserId(),contentMap);
        }
        return null;
    }

    @Override
    public Object send(UserInfo toUser, Map<String, Object> contentMap) {
        return send(Arrays.asList(toUser),contentMap);
    }

    /**
     * 发送微信通知
     * @param userId        接收人
     * @param contentMap    内容(title,content,keyWords)
     * @return
     */
    @Override
    public Object send(Long userId, Map<String, Object> contentMap) {
        if (!validParam(contentMap)){
            return null;
        }
        UserLogin userLogin = userLoginMapper.selectByPrimaryKey(userId);
        if (userLogin != null) {
            String openid2 = userLogin.getLfpcOpenid();
            if (openid2 == null || "".equals(openid2)){
                openid2 = userLogin.getLfpc2Openid();
            }
            if (openid2 != null && !"".equals(openid2)){
                try {
                    String title = (String) contentMap.get("title");
                    String content = (String) contentMap.get("content");
                    String keyWords = (String) contentMap.get("keyWords");
                    String path = "";
                    if(contentMap.containsKey("path")){
                        path = (String) contentMap.get("path");
                    }
                    WechatTempleMsgUtil.sendWechatMsgDdr(openid2,path,"","",title,"#FF0000",content,"#080808",keyWords,"#080808");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                /** //新版本   微信小程序 消息通知 2024年11月18日
                 *
                 SubscribeMessage subscribeMessage = new SubscribeMessage();
                 subscribeMessage.setTouser(openid2);
                 subscribeMessage.setTemplate_id("");
                 subscribeMessage.setPage("跳转页面");
                 Map<String, SubscribeMessage.MessageTemplate> data = new HashMap<>();
                 subscribeMessage.setData(data);
                 subscribeMessage.setMiniprogram_state("dev");
                 WxChatUtil.sendSubscribeMessage(appId2,appSecret2,subscribeMessage);
                 */
            }
        }
        return null;
    }


    private static Boolean validParam(Map<String,Object> contentMap){
        List<String> keys = Arrays.asList("title", "content", "keyWords");
        for (String key : keys) {
            if (!contentMap.keySet().contains(key)) {
                return false;
            }
        }
        return true;
    }
}
