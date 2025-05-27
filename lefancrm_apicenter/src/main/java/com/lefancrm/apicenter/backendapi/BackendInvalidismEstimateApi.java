package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * 代理申请
 * 
 * @author Daniel
 */
public interface BackendInvalidismEstimateApi {
	@SuppressWarnings("rawtypes")
	ApiResponse list(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    ApiResponse report(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
     ApiResponse queryFile(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    ApiResponse del(ApiRequest apiReq);
    @SuppressWarnings("rawtypes")
    ApiResponse toReport(ApiRequest apiReq);
}
