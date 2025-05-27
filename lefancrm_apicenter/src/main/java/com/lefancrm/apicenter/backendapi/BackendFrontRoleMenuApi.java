package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.FrontRoleMenu;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import java.util.List;

/**
 * 后台角色菜单类API
 * 
 * @author Daniel
 */
public interface BackendFrontRoleMenuApi {


	@SuppressWarnings("rawtypes")
	ApiResponse menuListByUserId(ApiRequest apiReq);



}
