package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by lixianfeng on 2018/5/10.
 */
public interface BackendCaseCenterInfoNewInfo {
    ApiResponse list(ApiRequest request);
    ApiResponse manager(ApiRequest request);
    ApiResponse getInfo(ApiRequest request);
    ApiResponse getCaseUserRole(ApiRequest request);
    ApiResponse operate(ApiRequest request);
    ApiResponse getCaseEnumState(ApiRequest request);
    ApiResponse update(ApiRequest request);
}
