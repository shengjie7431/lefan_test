package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.UserInfo;

import java.util.List;
import java.util.Map;

public interface BackendWechatApi {
    Object send(List<UserInfo> sendUsers, Map<String,Object> contentMap);
    Object send(UserInfo sendUser, Map<String,Object> contentMap);
    Object send(Long userId, Map<String,Object> contentMap);
}
