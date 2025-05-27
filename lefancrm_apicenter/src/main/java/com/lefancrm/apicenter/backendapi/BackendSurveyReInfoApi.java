package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendSurveyReInfoApi {

    /**
     * 调查员报销清单列表
     * @param apiRequest
     * @return
     */
    ApiResponse surveyUserSuedList(ApiRequest apiRequest);

    /**
     * 报销处理操作
     * @param apiRequest
     * @return
     */
    ApiResponse suedOperate(ApiRequest apiRequest);

    /**
     * 报销处理操作
     * @param apiRequest
     * @return
     */
    ApiResponse uptUserClockRe(ApiRequest apiRequest);

    /**
     * 报销打卡列表
     * @param apiRequest
     * @return
     */
    ApiResponse getSurveyReClockList(ApiRequest apiRequest);

    /**
     * 报销清单管理列表
     * @param apiRequest
     * @return
     */
    ApiResponse suedManagerList(ApiRequest apiRequest);
}
