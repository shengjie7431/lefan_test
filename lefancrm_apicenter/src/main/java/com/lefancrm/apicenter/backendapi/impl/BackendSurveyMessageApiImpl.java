package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyMessageApi;
import com.lefancrm.apicenter.dao.SurveyMessageMapper;
import com.lefancrm.apicenter.model.SurveyMessage;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@ApiService(descript = "发送消息API")
public class BackendSurveyMessageApiImpl extends BaseServiceImpl implements BackendSurveyMessageApi {

    @ApiMethod(needLogin = false,descript = "进度列表",value = "get-survey-message-size")
    @Override
    public ApiResponse getSurveyMessageSize(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        apiRequest.put("toUserId",currentUserId);//当前登录人
        apiRequest.put("msgType",4);//狄大人消息
        apiRequest.put("isRead",0);//未读
        int size = surveyMessageMapper.listSize(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,size);
    }

    @Autowired
    private SurveyMessageMapper surveyMessageMapper;
    @Override
    public Boolean sendSurveyMessage(Long formUserId, String formUserName, List<UserInfo> toUsers, int msgType, String title, String content, String url) {
        for (UserInfo toUser : toUsers) {
            SurveyMessage surveyMessage = new SurveyMessage();
            surveyMessage.setFromUserId(formUserId);
            surveyMessage.setFromUserName(formUserName);
            surveyMessage.setToUserId(toUser.getUserId());
            surveyMessage.setToUserName(toUser.getUserName());
            surveyMessage.setMsgType(msgType);
            surveyMessage.setFromTime(new Date());
            surveyMessage.setIsRead(0);
            surveyMessage.setMsgTitle(title);
            surveyMessage.setMsgContent(content);
            surveyMessage.setMsgUrl(url);
            surveyMessageMapper.insert(surveyMessage);
        }
        return true;
    }

    @Override
    public Boolean sendSurveyMessage(Long formUserId, String formUserName, Long toUserId, String toUserName, int msgType, String title, String content, String url) {
        SurveyMessage surveyMessage = new SurveyMessage();
        surveyMessage.setFromUserId(formUserId);
        surveyMessage.setFromUserName(formUserName);
        surveyMessage.setToUserId(toUserId);
        surveyMessage.setToUserName(toUserName);
        surveyMessage.setMsgType(msgType);
        surveyMessage.setFromTime(new Date());
        surveyMessage.setIsRead(0);
        surveyMessage.setMsgTitle(title);
        surveyMessage.setMsgContent(content);
        surveyMessage.setMsgUrl(url);
        surveyMessageMapper.insert(surveyMessage);
        return true;
    }

    @Override
    public Boolean readSurveyMessage(Long id) {
        SurveyMessage surveyMessage = surveyMessageMapper.selectByPrimaryKey(id);
        surveyMessage.setIsRead(1);
        surveyMessage.setToTime(new Date());
        surveyMessageMapper.updateByPrimaryKey(surveyMessage);
        return true;
    }
}
