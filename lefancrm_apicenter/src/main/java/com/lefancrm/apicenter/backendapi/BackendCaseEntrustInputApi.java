package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by wangwei on 2019-05-08
 */
public interface BackendCaseEntrustInputApi {
    ApiResponse list(ApiRequest apiReq);
    ApiResponse info(ApiRequest apiReq);
    ApiResponse operate(ApiRequest apiReq);

}
