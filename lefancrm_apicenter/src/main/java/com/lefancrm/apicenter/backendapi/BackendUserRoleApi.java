package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.dto.TreeData;
import com.lefancrm.apicenter.model.CommonArea;
import com.lefancrm.apicenter.model.OrgInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * 后台管理员API
 * 
 * @author Daniel
 */
public interface BackendUserRoleApi {
	@SuppressWarnings("rawtypes")
	ApiResponse<List<OrgInfo>> queryOrgList(ApiRequest apiReq);

    @SuppressWarnings("rawtypes")
    ApiResponse<List<CommonArea>> selectArea(ApiRequest apiReq);
//    @SuppressWarnings("rawtypes")
//    ApiResponse add(ApiRequest apiReq);
//    @SuppressWarnings("rawtypes")
//    ApiResponse edit(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
   ApiResponse getById(ApiRequest apiReq);
//    @SuppressWarnings("rawtypes")
//    ApiResponse editState(ApiRequest apiReq);
//    @SuppressWarnings("rawtypes")
//    ApiResponse queryCase(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    ApiResponse queryUser(ApiRequest apiReq);
//    @SuppressWarnings("rawtypes")
//    ApiResponse disUser(ApiRequest apiReq);
//    @SuppressWarnings("rawtypes")
//    ApiResponse toDisUser(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    ApiResponse businessRoleList(ApiRequest apiReq);
//    @SuppressWarnings("rawtypes")
//    ApiResponse businessRoleAdd(ApiRequest apiReq);
//    @SuppressWarnings("rawtypes")
//    ApiResponse businessRoleUpdateTo(ApiRequest apiReq);
//    @SuppressWarnings("rawtypes")
//    ApiResponse businessRoleUpdate(ApiRequest apiReq);
//    @SuppressWarnings("rawtypes")
//    ApiResponse businessRoleDel(ApiRequest apiReq);
//    @SuppressWarnings("rawtypes")
//    ApiResponse delOrgUser(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    ApiResponse orgUserRole(ApiRequest apiReq);
//    @SuppressWarnings("rawtypes")
//    ApiResponse queryOrgByParentId(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    ApiResponse orgTreeData(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    ApiResponse<List<OrgInfo>> queryOrgListTo(ApiRequest apiReq);
//    @SuppressWarnings("rawtypes")
//    ApiResponse delFile(ApiRequest apiReq);
//
//    @SuppressWarnings("rawtypes")
//    ApiResponse isOrgRole(ApiRequest apiReq);
//
//    ApiResponse<List<TreeData>> selectAreaAll(ApiRequest apiReq);

    ApiResponse roleList(ApiRequest apiReq);

    ApiResponse selectByAreaId(ApiRequest apiReq);

}
