package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by wangwei on 2019-04-23
 */
public interface BackendBillingRoleApi {
    ApiResponse list(ApiRequest apiReq);
    ApiResponse list1(ApiRequest apiRequest);
    ApiResponse save(ApiRequest apiRequest);
    ApiResponse saveTwo(ApiRequest apiRequest);


}
