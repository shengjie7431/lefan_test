package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendStaffPaySlipApi {
    /**
     * 列表
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

    ApiResponse infoKey(ApiRequest apiRequest);

    /**
     * 操作
     * @param apiRequest
     * @return
     */
    ApiResponse operate(ApiRequest apiRequest);

    /**
     * 导入钉钉数据 级 缴税数据
     * @param apiRequest
     * @return
     */
    ApiResponse exportData(ApiRequest apiRequest);

    /**
     * 导入员工数据
     * @param apiRequest
     * @return
     */
    ApiResponse exportPersonnelInfoData(ApiRequest apiRequest);
}
