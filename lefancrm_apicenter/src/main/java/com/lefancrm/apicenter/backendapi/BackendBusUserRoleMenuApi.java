package com.lefancrm.apicenter.backendapi;



import com.lefancrm.apicenter.model.BusUserRole;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * 后台角色菜单类API
 * 
 * @author Daniel
 */
public interface BackendBusUserRoleMenuApi {


    @SuppressWarnings("rawtypes")
    ApiResponse isRole(ApiRequest apiReq);

    ApiResponse isSomeRoleFromUser(ApiRequest apiReq);

    List<BusUserRole> getRoleList(ApiRequest apiReq);

    ApiResponse getCurrentUserRoleList(ApiRequest apiReq);
}
