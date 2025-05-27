package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendSurveyFranchiseeApi;
import com.lefancrm.apicenter.dao.BusinessRoleMapper;
import com.lefancrm.apicenter.dao.SurveyFranchiseeMapper;
import com.lefancrm.apicenter.dao.SurveyInvestigatorMapper;
import com.lefancrm.apicenter.model.BusUserRole;
import com.lefancrm.apicenter.model.BusinessRole;
import com.lefancrm.apicenter.model.SurveyFranchisee;
import com.lefancrm.apicenter.model.SurveyInvestigator;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by wangwei on 2018/12/17.
 * 调查放机构
 */
@Service
@ApiService(descript = "调查放机构API")
public class BackendSurveyFranchiseeApiImpl extends BaseServiceImpl implements BackendSurveyFranchiseeApi {

    @Autowired
    private SurveyInvestigatorMapper surveyInvestigatorMapper;
    @Autowired
    private BusinessRoleMapper businessRoleMapper;
    @Autowired
    private SurveyFranchiseeMapper surveyFranchiseeMapper;

    /**
     * 获取符合条件的调查人
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "获取符合条件的调查人", value = "backend-survey-select-investigator", apiParams = { })
    @Override
    public ApiResponse selectInvestigator(ApiRequest apiReq) {

        String menuType = apiReq.getString("menuType");
        if(menuType == null){
            this.setBackendPageSize(apiReq);
        }

        String btnCode = apiReq.getString("btnCode");
        Map<String,Object> map = new HashMap<>();
        //名下人员信息
        if("1500".equals(btnCode)){
            apiReq.put("realName",apiReq.getString("realName"));
            apiReq.put("tel",apiReq.getString("tel"));
            apiReq.put("orgId",apiReq.getLong("id"));
        }
        //名下人员信息
        if("1600".equals(btnCode)){
            apiReq.put("orgId",apiReq.getLong("id"));
        }
        List<SurveyInvestigator> list = surveyInvestigatorMapper.list(apiReq);
        int count = surveyInvestigatorMapper.listSize(apiReq);
        if("1600".equals(btnCode)){
            return new ApiResponse<List<SurveyInvestigator>>(ApiMsgEnum.SUCCESS, count, list);
        }
        //调查员权限
        Map findMap=new HashMap();
        findMap.put("roleCode","lordDi");
        for (int i = 0; i < list.size(); i++) {
            findMap.put("userId",list.get(i).getUserId());
            String roles = businessRoleMapper.selectInvestigatorRoles(findMap);
            if(roles!=null){
                apiReq.put("ids",roles);
            }else{
                apiReq.put("ids",50);//默认为调查员
            }
            List<BusinessRole> businessRoles = businessRoleMapper.selectAll(apiReq);
            list.get(i).setBusinessRole(businessRoles);
        }
        return new ApiResponse<List<SurveyInvestigator>>(ApiMsgEnum.SUCCESS, count, list);

    }

    /**
     * 验证角色列表是否有狄大人角色
     * @param
     * @return
     */
    private Boolean isBusUser(String ro){
        if ("50".equals(ro) || "51".equals(ro) || "52".equals(ro) || "53".equals(ro) || "57".equals(ro)
                || "58".equals(ro) || "59".equals(ro)){
            return true;
        }
        return false;
    }


    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "狄大人调查方角色", value = "backend-survey-investigator-business-role-list")
    @Override
    public ApiResponse selectInvestigatorRoleList(ApiRequest apiReq) {
        List<BusinessRole> businessRoles =  new ArrayList<>();
        Long userId = apiReq.getLong("userId");
        Map map=new HashMap();
        map.put("roleCode","lordDi");
        if(userId !=null){
            //个人的狄大人角色
            map.put("userId",userId);
            String roles = businessRoleMapper.selectInvestigatorRoles(map);
            if(roles != null){
                apiReq.put("ids",roles);
            }else{
                apiReq.put("ids",50);//默认调查员
            }
            businessRoles = businessRoleMapper.selectAll(apiReq);
        }else{
            //所有狄大人角色
            apiReq.put("roleCode","lordDi");
            businessRoles = businessRoleMapper.selectInvestigatorRoleList(apiReq);
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS,businessRoles==null?0:businessRoles.size(),businessRoles);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "“平台终审人员”对应的“调查方机构”", value = "backend-survey-franchisee-for-final-user")
    @Override
    public ApiResponse selectFranchiseeListForFinalUser(ApiRequest apiReq) {
        Long userId = apiReq.getLong("operatorId");
        Map<String,Object> map =new HashMap<>();
        map.put("userId",userId);
        map.put("oprTypeValue",apiReq.getString("oprTypeValue"));
        map.put("entrustOrgIds",apiReq.getString("entrustOrgIds"));
        map.put("startDate",apiReq.get("startDate"));
        map.put("dateItem",apiReq.getString("dateItem"));
        map.put("endDate",apiReq.get("endDate"));
        map.put("searchStr",apiReq.getString("searchStr"));
        map.put("agingType",apiReq.get("agingType"));
        map.put("orgSurveyStates", apiReq.getString("orgSurveyStates"));
        String handquery =  apiReq.getString("handquery");
        if (StringUtils.isEmpty(handquery)){//初始化页面设置默认值
            map.put("dateItem", 3);
            if (StringUtils.isEmpty( apiReq.getString("orgSurveyStates"))) {
                map.put("orgSurveyStates", "1,2");
            }
            if (StringUtils.isEmpty( apiReq.getString("startDate"))) {
                map.put("startDate", LocalDate.now().plusDays(1).toString());
                map.put("endDate", LocalDate.now().plusDays(1).toString());
            }
        }


        if ("caseItem".equals(apiReq.getString("menuCode"))){//如果是从报表查询的调查机构。则不需要userId 2020年11月2日 lxf修改
            map.remove("userId");
            map.remove("startDate");
            map.remove("endDate");
        }

        List<SurveyFranchisee> surveyFranchisees = null;
        if ("time-track-list".equals(apiReq)){
            surveyFranchisees =  surveyFranchiseeMapper.selectFranchiseeListForFinalUser(map);
        }else{
            surveyFranchisees =  surveyFranchiseeMapper.selectFranchiseeListForFinalUserNew(map);
        }

        if (!StringUtils.isEmpty(apiReq.getString("orgSurveyStates"))){
            List<String> list = Arrays.asList(apiReq.getString("orgSurveyStates").split(","));
            if (list.contains("1") && !list.contains("2")){
                surveyFranchisees.forEach(e->e.setCheckNum(0));
            }
            if (list.contains("2") && !list.contains("1")){
                surveyFranchisees.forEach(e->e.setSurveyNum(0));
            }
            if (!list.contains("2") && !list.contains("1")){
                surveyFranchisees.forEach(e->{e.setSurveyNum(0);e.setCheckNum(0);});
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,surveyFranchisees==null?0:surveyFranchisees.size(),surveyFranchisees);
    }

    @ApiMethod(descript = "根据调查员查询对应的调查方机构", value = "backend-survey-franchisee-investigator")
    @Override
    public ApiResponse selectMechanism(ApiRequest apiReq) {
        Long currentUserId = getCurrentUserId(apiReq);
        SurveyInvestigator surveyInvestigator=surveyInvestigatorMapper.selectByUserId(currentUserId);
        SurveyFranchisee surveyFranchisee=surveyFranchiseeMapper.selectByParentId(surveyInvestigator.getOrgId());
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyFranchisee);
    }

    @ApiMethod(descript = "根据调查员查询对应的调查方机构", value = "backend-survey-franchisee-data")
    @Override
    public ApiResponse selectFranchiseeData(ApiRequest apiRequest){
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        paramMap.put("entrustOrgId",apiRequest.getLong("entrustOrgId"));
        List<SurveyFranchisee> franchisees = surveyFranchiseeMapper.selectFranchiseeAndDataByParam(paramMap);
        return new ApiResponse(ApiMsgEnum.SUCCESS,franchisees.size(),franchisees);
    }
}
