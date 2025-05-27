package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

public interface BackendSurveyMessageApi {
    ApiResponse getSurveyMessageSize(ApiRequest apiRequest);
    Boolean sendSurveyMessage(Long formUserId, String formUserName, List<UserInfo> toUsers,int msgType,String title,String content,String url);
    Boolean sendSurveyMessage(Long formUserId, String formUserName, Long toUserId,String toUserName,int msgType,String title,String content,String url);
    Boolean readSurveyMessage(Long id);
}
