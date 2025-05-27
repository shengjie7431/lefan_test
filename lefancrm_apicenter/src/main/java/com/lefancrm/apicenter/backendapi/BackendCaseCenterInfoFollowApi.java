package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.CaseCenterInfo;
import com.lefancrm.apicenter.model.CaseCenterInfoFollow;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/5/15.
 */
public interface BackendCaseCenterInfoFollowApi {
    /**
     * 跟踪信息列表
     * @param apiRequest
     * @return
     */
    ApiResponse<List<CaseCenterInfoFollow>> caseCenterInfoFollowList(ApiRequest apiRequest);

    /**
     * 通过‘案件编号’查询跟踪信息
     * @param apiRequest
     * @return
     */
    ApiResponse<List<CaseCenterInfoFollow>> searchCaseCenterInfoFollowByCaseId(ApiRequest apiRequest);

    /**
     * 通过‘id’查询案件信息
     * @param apiRequest
     * @return
     */
    ApiResponse<CaseCenterInfo> searchCaseCenterInfoById(ApiRequest apiRequest);

    /**
     * 保存案件跟踪记录
     * @param apiReq
     * @return
     */
    ApiResponse caseCenterInfoFollowSave(ApiRequest apiReq);
}
