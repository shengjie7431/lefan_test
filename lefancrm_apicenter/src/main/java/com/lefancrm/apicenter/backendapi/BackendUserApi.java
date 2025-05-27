package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;
import java.util.Map;

/**
 * 后台用户管理API
 * 
 * @author Daniel
 */
public interface BackendUserApi {


    @SuppressWarnings("rawtypes")
    ApiResponse setOrgAdmin(ApiRequest apiReq);

    //查询是CC人员职级职位的用户list
    ApiResponse<List<UserInfo>> getCcUserInfoList(ApiRequest apiReq);

    ApiResponse getUserInfoByUserId(ApiRequest apiReq);

    ApiResponse<List<UserInfo>> selectUserByRoleId(ApiRequest apiReq);

    ApiResponse searchUserInfo(ApiRequest apiReq);

    //非调查员或委托人的用户
    ApiResponse selectUserInfoForSurvey(ApiRequest apiReq);

    ApiResponse selectCurUserRoles(ApiRequest apiRequest);

    ApiResponse selectCurUserRolesHaveMenu(ApiRequest apiRequest);


    ApiResponse selectOprUser(ApiRequest apiRequest);
}
