package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by wangwei on 2020年3月17日13:49:04
 */
public interface BackendStaffApi {
    ApiResponse list(ApiRequest apiReq);
    ApiResponse info(ApiRequest apiReq);
    ApiResponse update(ApiRequest apiReq);
    ApiResponse selectStaffInfoByRelationId(ApiRequest apiReq);
    ApiResponse operate(ApiRequest apiReq);
}
