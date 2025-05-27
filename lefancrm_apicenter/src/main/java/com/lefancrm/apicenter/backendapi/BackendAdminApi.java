package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * 后台管理员API
 * 
 * @author Daniel
 */
public interface BackendAdminApi {
	@SuppressWarnings("rawtypes")
	ApiResponse login(ApiRequest apiReq);

	@SuppressWarnings("rawtypes")
	ApiResponse updatePassword(ApiRequest apiReq);

	/*@SuppressWarnings("rawtypes")
	ApiResponse list(ApiRequest apiReq);

	@SuppressWarnings("rawtypes")
	ApiResponse add(ApiRequest apiReq);

	@SuppressWarnings("rawtypes")
	ApiResponse getById(ApiRequest apiReq);

	@SuppressWarnings("rawtypes")
	ApiResponse update(ApiRequest apiReq);

	@SuppressWarnings("rawtypes")
	ApiResponse delete(ApiRequest apiReq);

	@SuppressWarnings("rawtypes")
	ApiResponse disable(ApiRequest apiReq);

	@SuppressWarnings("rawtypes")
	ApiResponse enable(ApiRequest apiReq);*/

}
