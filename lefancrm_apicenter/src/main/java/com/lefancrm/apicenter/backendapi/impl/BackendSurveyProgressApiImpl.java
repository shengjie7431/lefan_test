package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyProgressApi;
import com.lefancrm.apicenter.dao.SurveyAssignOrgMapper;
import com.lefancrm.apicenter.dao.SurveyProgressMapper;
import com.lefancrm.apicenter.dao.SurveyRiskCaseInfoMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.SurveyAssignOrg;
import com.lefancrm.apicenter.model.SurveyProgress;
import com.lefancrm.apicenter.model.SurveyRiskCaseInfo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixianfeng on 2019/1/23.
 */
@Service
@ApiService(descript = "狄大人案件进度API")
public class BackendSurveyProgressApiImpl extends BaseServiceImpl implements BackendSurveyProgressApi{
    @Autowired
    private SurveyProgressMapper surveyProgressMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SurveyAssignOrgMapper surveyAssignOrgMapper;
    @ApiMethod(needLogin = false,descript = "进度列表",value = "list-survey-progress")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        Long surveyInfoId = apiRequest.getLong("surveyInfoId");
        Map<String,Long> map =  new HashMap<String,Long>();
        map.put("surveyInfoId",surveyInfoId);
        List<SurveyProgress> list = surveyProgressMapper.list(map);
        return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
    }

    @ApiMethod(needLogin = false,descript = "0610版添加跟踪",value = "add-survey-progress")
    @Override
    public ApiResponse addprogress(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        String content = apiRequest.getString("contents");
        SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(id);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiRequest));
        Map map = new HashMap();
        map.put("surveyOrgId",apiRequest.getLong("surveyOrgId"));
        map.put("surveyInfoId",surveyRiskCaseInfo.getId());
        SurveyAssignOrg surveyAssignOrg = surveyAssignOrgMapper.selectByOne(map);
        surveyAssignOrg.setFollowInformation(content);
        surveyAssignOrg.setFollowTime(new Date());
        surveyAssignOrg.setFollowUserId(userInfo.getUserId());
        surveyAssignOrg.setFollowUserName(userInfo.getUserName());
        surveyAssignOrgMapper.updateByPrimaryKey(surveyAssignOrg);
        int result = this.saveProgress(surveyRiskCaseInfo.getSurveyId(), surveyRiskCaseInfo.getId(), userInfo.getUserId(), userInfo.getUserName(), "添加跟踪", "("+surveyAssignOrg.getSurveyOrgName()+") "+content);
        return new ApiResponse(result>0?ApiMsgEnum.SUCCESS:ApiMsgEnum.FAIL);
    }

    /**
     * 保存进度
     * @param surveyId
     * @param surveyInfoId
     * @param userId
     * @param userName
     * @param progressName
     * @param progressDesc
     * @return
     */
    public int saveProgress(Long surveyId,Long surveyInfoId,Long userId,String userName,String progressName,String progressDesc){
        SurveyProgress surveyProgress = new SurveyProgress();
        surveyProgress.setSurveyId(surveyId);
        surveyProgress.setSurveyInfoId(surveyInfoId);
        surveyProgress.setProgressUserId(userId);
        surveyProgress.setProgressUserName(userName);
        surveyProgress.setProgressName(progressName);
        surveyProgress.setProgressDesc(progressDesc);
        surveyProgress.setProgressTime(new Date());
        surveyProgress.setCreateBy(userName);
        surveyProgress.setCreateTime(new Date());
        surveyProgress.setUpdateBy(null);
        surveyProgress.setUpdateTime(null);
        surveyProgress.setDeleteFlag(0);
        return surveyProgressMapper.insert(surveyProgress);
    }
}
