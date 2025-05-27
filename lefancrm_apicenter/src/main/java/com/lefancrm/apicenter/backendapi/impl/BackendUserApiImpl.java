package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONObject;
import com.lefancrm.apicenter.backendapi.BackendUserApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.UserInfoOprDTO;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@ApiService(descript = "后台用户管理API")
public class BackendUserApiImpl extends BaseServiceImpl implements BackendUserApi {

    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "设置机构角色", value = "backend-set-org-admin")
    public ApiResponse setOrgAdmin(ApiRequest apiReq){
        Long userId = apiReq.getLong("userId");
        String bsType = apiReq.getString("bsType");
        //先删除拥有的角色
        int ret = busUserRoleMapper.deleteByUserId(userId);
            //设置机构角色
        String[] arry = bsType.split(",");
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userId",userId);
        for (String str : arry) {
            Long bsId = Long.parseLong(str);
            paramMap.put("roleId",bsId);
            BusUserRole role =  busUserRoleMapper.selectBusRoleInfo(paramMap);
            if(role != null){
                continue;
            }
            role = new BusUserRole();
            role.setRoleId(bsId);
            role.setUserId(userId);
            busUserRoleMapper.insertSelective(role);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /*
    * 查询是CC人员职级职位的用户list
    * */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "CC人员职级职位的用户查询", value = "backend-cc-user-info-list", apiParams = { })
    @Override
    public ApiResponse<List<UserInfo>> getCcUserInfoList(ApiRequest apiReq) {

        this.setBackendPageSize(apiReq);

        List<UserInfo> list = userInfoMapper.selectCcUserInfoList(apiReq);

        return new ApiResponse<List<UserInfo>>(ApiMsgEnum.SUCCESS, null, list);
    }

    /*
    * 根据userId查询用户数据
    * */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "根据userId查询用户数据", value = "backend-user-info-by-userId", apiParams = { })
    @Override
    public ApiResponse getUserInfoByUserId(ApiRequest apiReq) {

        this.setBackendPageSize(apiReq);

        UserInfo list = userInfoMapper.selectUserInfoByUserId(apiReq);

        return new ApiResponse<UserInfo>(ApiMsgEnum.SUCCESS, null, list);
    }

    @ApiMethod(descript = "根据角色ID获取用户列表",value = "backend-user-info-list-by-roleId")
    @Override
    public ApiResponse<List<UserInfo>> selectUserByRoleId(ApiRequest apiReq) {
        Long roleId = apiReq.getLong("roleId");
        List<UserInfo> userInfos = userInfoMapper.selectUserByRoleId(roleId);
        return new ApiResponse<List<UserInfo>>(ApiMsgEnum.SUCCESS,userInfos.size(),userInfos);
    }


    /*
    * 查询用户信息
    * */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "查询用户信息", value = "backend-user-info", apiParams = { })
    @Override
    public ApiResponse searchUserInfo(ApiRequest apiReq) {
        UserInfo userinfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(apiReq));
        return new ApiResponse<UserInfo>(ApiMsgEnum.SUCCESS, null, userinfo);
    }

    /**
    * 非调查员或委托人的用户
    * */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "非调查员或委托人的用户", value = "backend-survey-select-user-info", apiParams = { })
    @Override
    public ApiResponse selectUserInfoForSurvey(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        String surveyCode = apiReq.getString("surveyCode");
        //狄大人-委托人
        if("consignor".equals(surveyCode)){
            apiReq.put("type",1);
        }
        //狄大人-调查员
        else if("franchisee".equals(surveyCode)){
            apiReq.put("type",2);
        }

        List<UserInfo> userInfo = userInfoMapper.selectUserInfoForSurvey(apiReq);
        int count = userInfoMapper.selectUserInfoForSurveysSize(apiReq);
        return new ApiResponse<List<UserInfo>>(ApiMsgEnum.SUCCESS, count, userInfo);
    }


    @ApiMethod(descript = "获取当前登陆人的角色列表", value = "backend-select-cur-user-roles", apiParams = { })
    @Override
    public ApiResponse selectCurUserRoles(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        return new ApiResponse(ApiMsgEnum.SUCCESS,userRoles.size(),userRoles);
    }

    @ApiMethod(descript = "获取当前登陆人的角色列表", value = "backend-select-cur-user-roles-have-menu", apiParams = { })
    @Override
    public ApiResponse selectCurUserRolesHaveMenu(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("menuName",apiRequest.getString("menuName"));
        paramMap.put("currentUserId",currentUserId);
        Boolean haveMenu = busUserRoleMapper.selectUserHaveMenu(paramMap);
        JSONObject result = new JSONObject();
        result.put("haveMenu",haveMenu);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,result);
    }

    @ApiMethod(descript = "获取复审人员列表", value = "backend-select-opr-user", apiParams = { })
    @Override
    public ApiResponse selectOprUser(ApiRequest apiRequest) {
        Long oprUserType = apiRequest.getLong("oprUserType");//1互助  2保司
        Map<String,Object> paramMap =  new HashMap<String,Object>();
        if (oprUserType.intValue() == 1){
            List<UserInfoOprDTO> oprUsers = userInfoMapper.selectUserInfoOprHelp(paramMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,oprUsers.size(),oprUsers);
        }else{
            List<UserInfoOprDTO> oprUsers = userInfoMapper.selectUserInfoOprSafe(paramMap);
            return new ApiResponse(ApiMsgEnum.SUCCESS,oprUsers.size(),oprUsers);
        }
    }
}
