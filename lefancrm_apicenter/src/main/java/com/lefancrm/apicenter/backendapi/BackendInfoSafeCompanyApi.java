package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by wangwei on 2018/11/28.
 */
public interface BackendInfoSafeCompanyApi {
    ApiResponse list(ApiRequest apiRequest);
    ApiResponse edit(ApiRequest apiRequest);
    ApiResponse save(ApiRequest apiRequest);
    ApiResponse selectAllUser(ApiRequest apiRequest);
    ApiResponse confirmUser(ApiRequest apiRequest);
    ApiResponse companyUser(ApiRequest apiRequest);
    ApiResponse remove(ApiRequest apiRequest);
    ApiResponse delete(ApiRequest apiRequest);
}
