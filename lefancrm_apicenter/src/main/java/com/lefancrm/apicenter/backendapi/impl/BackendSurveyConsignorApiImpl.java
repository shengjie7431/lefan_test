package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyConsignorApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyLevelApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
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
import java.util.stream.Collectors;

/**
 * Created by wangwei on 2018/12/17.
 * 委托人机构
 */
@Service
@ApiService(descript = "委托人机构API")
public class BackendSurveyConsignorApiImpl extends BaseServiceImpl implements BackendSurveyConsignorApi {

    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;
    @Autowired
    private SurveyConsignerMapper surveyConsignerMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private BillingApplyCompanyMapper billingApplyCompanyMapper;


    /**
     * 委托人机构(非分页数据) list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "委托人机构(非分页数据)list", value = "backend-survey-consignor-list", apiParams = { })
    @Override
    public ApiResponse list(ApiRequest apiReq) {
        //非分页数据 -- 仅返回数个字段
        List<SurveyConsignor> list = surveyConsignorMapper.selectInfo(apiReq);
        return new ApiResponse<List<SurveyConsignor>>(ApiMsgEnum.SUCCESS, list.size(), list);

    }

    @ApiMethod(descript = "委托人机构(非分页数据)info", value = "backend-survey-consignor-info", apiParams = { })
    @Override
    public ApiResponse info(ApiRequest apiReq) {
        SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(apiReq.getLong("entrustOrgId"));
        if (surveyConsignor != null){
            ApiRequest paramMap =  new ApiRequest();
            paramMap.put("entrustOrgId",surveyConsignor.getId());
            paramMap.put("state",0);
            List<BillingApplyCompany> companies = billingApplyCompanyMapper.selectList(paramMap);
            surveyConsignor.setCompanys(companies);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyConsignor);
    }

    /**
     * 委托人机构(非分页数据) list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "根据角色信息查询相关用户list", value = "backend-survey-user-list-by-role", apiParams = { })
    @Override
    public ApiResponse userListByRole(ApiRequest apiReq) {
        //非分页数据 -- 仅返回数个字段
        String roleIds=apiReq.getString("roleId");
        List<BusUserRole> list =busUserRoleMapper.selectUserByRoleIds(roleIds);
        return new ApiResponse<List<BusUserRole>>(ApiMsgEnum.SUCCESS, list.size(), list);
    }
    /**
     * 获取符合条件的委托人
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "获取符合条件的委托人", value = "backend-survey-select-consigner", apiParams = { })
    @Override
    public ApiResponse selectConsigner(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);

        String btnCode = apiReq.getString("btnCode");
        Map<String,Object> map = new HashMap<>();
        //名下人员信息
        if("1300".equals(btnCode)){
            apiReq.put("userName",apiReq.getString("userName"));
            apiReq.put("tel",apiReq.getString("tel"));
            apiReq.put("entrustOrgId",apiReq.getLong("id"));
        }
        List<SurveyConsigner> list = surveyConsignerMapper.list(apiReq);
        for (SurveyConsigner surveyConsigner : list) {
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("consignorOrgId",surveyConsigner.getEntrustOrgId());
            paramMap.put("consignerUserId",surveyConsigner.getId());
            List<SurveyConsignerDepartment> consignerDepartments = surveyConsignerDepartmentMapper.list(paramMap);
            if (consignerDepartments.size() > 0) {
                String names = consignerDepartments.stream().map(SurveyConsignerDepartment::getConsignorDepartmentName).collect(Collectors.joining(" "));
                surveyConsigner.setDepartmentNames(names);
            }
        }
        int count = surveyConsignerMapper.listSize(apiReq);
        return new ApiResponse<List<SurveyConsigner>>(ApiMsgEnum.SUCCESS, count, list);

    }
    @Autowired
    private SurveyConsignerDepartmentMapper surveyConsignerDepartmentMapper;

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "“平台终审人员”对应的“委托机构”", value = "backend-survey-consignor-for-final-user")
    @Override
    public ApiResponse selectConsignorListForFinalUser(ApiRequest apiReq) {
        Long userId = apiReq.getLong("operatorId");
        Map<String,Object> map =new HashMap<>();
        map.put("userId",userId);
        map.put("oprTypeValue",apiReq.getString("oprType"));
        map.put("surveyOrgIds",apiReq.getString("surveyOrgIds"));
        map.put("surveyStates",apiReq.getString("surveyStates"));
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
        String menuCode = apiReq.getString("menuCode");
        if ("caseItem".equals(menuCode)){//如果是从报表查询的委托方机构。则不需要userId 2020年11月2日 lxf修改
            map.remove("userId");
            map.remove("startDate");
            map.remove("endDate");
        }
        List<SurveyConsignor> surveyConsignors = null;
        if ("time-track-list".equals(apiReq)){
            surveyConsignors =  surveyConsignorMapper.selectConsignorListForFinalUser(map);
        }else{
            surveyConsignors =  surveyConsignorMapper.selectConsignorListForFinalUserNew(map);
        }
        if (!StringUtils.isEmpty(apiReq.getString("orgSurveyStates"))){
            List<String> list = Arrays.asList(apiReq.getString("orgSurveyStates").split(","));
            if (list.contains("1") && !list.contains("2")){
                surveyConsignors.forEach(e->e.setCheckNum(0));
            }
            if (list.contains("2") && !list.contains("1")){
                surveyConsignors.forEach(e->e.setSurveyNum(0));
            }
            if (!list.contains("2") && !list.contains("1")){
                surveyConsignors.forEach(e->{e.setSurveyNum(0);e.setCheckNum(0);});
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,surveyConsignors==null?0:surveyConsignors.size(),surveyConsignors);
    }

    @ApiMethod(descript = "“查询平台机构”", value = "backend-survey-consignor-selectByMap")
    @Override
    public ApiResponse selectByMap(ApiRequest apiReq) {
        Map map=new HashMap();
        String orgAttr=apiReq.getString("orgAttr");
        map.put("orgAttr",orgAttr);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyConsignorMapper.selectByMap(map));
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "配置了相同委托方的“复审人员”", value = "backend-survey-same-final-user")
    @Override
    public ApiResponse selectSameFinalUser(ApiRequest apiReq) {
        Long userId = apiReq.getLong("operatorId");
        Map<String,Object> map =new HashMap<>();
        map.put("userId",userId);
        map.put("oprTypeValue",apiReq.getString("oprType"));
        List<SurveyConsignor> surveyConsignorList =  surveyConsignorMapper.selectConsignorListForFinalUser(map);

        map =new HashMap<>();
        map.put("surveyConsignorList",surveyConsignorList);
        List<UserInfo> userInfos = userInfoMapper.selectUserBySurveyOrg(map);

        return new ApiResponse(ApiMsgEnum.SUCCESS,userInfos==null?0:userInfos.size(),userInfos);
    }

    @ApiMethod(descript = "查询委托方机构数据", value = "backend-survey-consignor-data")
    @Override
    public ApiResponse selectConsignorData(ApiRequest apiReq) {
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        paramMap.put("surveyOrgId",apiReq.getLong("surveyOrgId"));
        paramMap.put("entrustOrgIds",apiReq.getString("entrustOrgIds"));
        List<SurveyConsignor> consignors = surveyConsignorMapper.selectConsignorDataByParam(paramMap);
        return new ApiResponse(ApiMsgEnum.SUCCESS,consignors.size(),consignors);
    }
}
