package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.dto.CasePayInfoDto;
import com.lefancrm.apicenter.model.CasePayInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/6/19.
 */
public interface BackendCasePayInfoApi {
    /**
     * 待支付项目列表
     * @param apiRequest
     * @return
     */
    ApiResponse<List<CasePayInfoDto>> casePayInfoList(ApiRequest apiRequest);

    /**
     * 通过‘id’查询案件信息
     * @param apiRequest
     * @return
     */
    ApiResponse<CasePayInfo> searchCasePayInfoById(ApiRequest apiRequest);

    /**
     * 确认上传开票信息
     * @param apiRequest
     * @return
     */
    ApiResponse<CasePayInfo> casePayInfoUpd(ApiRequest apiRequest);
}