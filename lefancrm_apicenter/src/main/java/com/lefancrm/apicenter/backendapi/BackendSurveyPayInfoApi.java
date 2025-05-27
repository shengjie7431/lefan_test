package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendSurveyPayInfoApi {
    /**
     * 操作
     * @param apiRequest
     * @return
     */
    ApiResponse operate(ApiRequest apiRequest);

    /**
     * 申请列表
     * @param apiRequest
     * @return
     */
    ApiResponse list(ApiRequest apiRequest);

    /**
     * 详情
     * @param apiRequest
     * @return
     */
    ApiResponse info(ApiRequest apiRequest);

    ApiResponse ajaxFinancialData(ApiRequest apiRequest);

}
