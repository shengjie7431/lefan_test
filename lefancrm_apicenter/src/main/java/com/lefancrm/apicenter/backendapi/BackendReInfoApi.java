package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendReInfoApi {
    /**
     * 可下发清单
     * @param apiRequest
     * @return
     */
    ApiResponse canBeSued(ApiRequest apiRequest);

    /**
     * 下发接口
     * @param apiRequest
     * @return
     */
    ApiResponse sued(ApiRequest apiRequest);

    /**
     * 预报销下发
     * @param apiRequest
     * @return
     */
    ApiResponse addPre(ApiRequest apiRequest);

    /**
     * 调查员报销清单列表
     * @param apiRequest
     * @return
     */
    ApiResponse surveyUserSuedList(ApiRequest apiRequest);

    /**
     * 调查员予报销清单列表
     * @param apiRequest
     * @return
     */
    ApiResponse surveyUserPreSuedList(ApiRequest apiRequest);

    /**
     * 予报销选择调查员
     * @param apiRequest
     * @return
     */
    ApiResponse surveyUserPreList(ApiRequest apiRequest);

    /**
     * 报销清单管理列表
     * @param apiRequest
     * @return
     */
    ApiResponse suedManagerList(ApiRequest apiRequest);

    /**
     * 报销处理操作
     * @param apiRequest
     * @return
     */
    ApiResponse suedOperate(ApiRequest apiRequest);

    /**
     * 预报销处理操作
     * @param apiRequest
     * @return
     */
    ApiResponse preOperate(ApiRequest apiRequest);

    ApiResponse preUserList(ApiRequest apiRequest);
}
