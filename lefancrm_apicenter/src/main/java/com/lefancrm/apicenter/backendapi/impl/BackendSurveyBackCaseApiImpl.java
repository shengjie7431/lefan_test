package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyBackCaseApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyBackCaseDto;
import com.lefancrm.apicenter.model.BusUserRole;
import com.lefancrm.apicenter.model.SurveyBackCase;
import com.lefancrm.apicenter.model.SurveyInvestigator;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lixianfeng on 2019/2/25.
 */
@Service
@ApiService(descript = "逆向流程案件相关API")
public class BackendSurveyBackCaseApiImpl extends BaseServiceImpl implements BackendSurveyBackCaseApi {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SurveyBackCaseMapper surveyBackCaseMapper;
    @Autowired
    private SurveyRiskCaseMapper surveyRiskCaseMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;

    @ApiMethod(needLogin = false,descript = "协助调查处理列表",value = "list-survey-back-case")
    @Override
    public ApiResponse list(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        String menuCode = apiRequest.getString("menuCode");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);

        Boolean a = true,b = true;

        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        a = isRoleUser(userRoles,53L);//平台终审
        b = isRoleUser(userRoles,58L);//狄大人机构复核
        if (a){//乐凡平台审核
            apiRequest.put("search",1);//back_state = 4
        }
        if (b){//机构复核审核
            //back_state in (1,6) 且 申请机构是当前机构
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(userInfo.getUserId());
            if (surveyInvestigator == null) {
                return new ApiResponse(ApiMsgEnum.SURVEY_INVESTIGATOR);
            }
            apiRequest.put("currentOrgId",surveyInvestigator.getOrgId());
            apiRequest.put("search",2);//back_state = 1,6 and org_id = currentOrgId or back_state = 5 and ass_org_id = currentOrgId
        }
        if (a && b){//机构复核 乐凡平台都有
            SurveyInvestigator surveyInvestigator = surveyInvestigatorMapper.selectByUserId(userInfo.getUserId());
            if (surveyInvestigator == null) {
                return new ApiResponse(ApiMsgEnum.SURVEY_INVESTIGATOR);
            }
            apiRequest.put("currentOrgId",surveyInvestigator.getOrgId());
            apiRequest.put("search",3);//back_state in (1,6,4)
        }
        int count = surveyBackCaseMapper.listSize(apiRequest);
        List<SurveyBackCaseDto> list = surveyBackCaseMapper.list(apiRequest);
        for (SurveyBackCaseDto dto : list) {
            dto.setSurveyRiskCase(surveyRiskCaseMapper.selectByPrimaryKey(dto.getSurveyId()));
            dto.setSurveyRiskCaseInfo(surveyRiskCaseInfoMapper.selectByPrimaryKey(dto.getSurveyInfoId()));
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() == roleId){
                return true;
            }
        }
        return false;
    }

    @Override
    public ApiResponse info(ApiRequest apiRequest) {
        return null;
    }

    @Override
    public ApiResponse operate(ApiRequest apiRequest) {
        return null;
    }
}
