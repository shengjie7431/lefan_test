package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendStaffPerformanceApi {
    /**
     * 详情
     * @param apiRequest
     * @return
     */
    ApiResponse info(ApiRequest apiRequest);

    ApiResponse infoKey(ApiRequest apiRequest);

    /**
     * 操作
     * @param apiRequest
     * @return
     */
    ApiResponse operate(ApiRequest apiRequest);

    /**
     * 积分案件数据
     * @param apiRequest
     * @return
     */
    ApiResponse scoreCaseList(ApiRequest apiRequest);

}
