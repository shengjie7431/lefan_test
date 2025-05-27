package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.dto.CaseEstimateInfoDto;
import com.lefancrm.apicenter.model.InvalidismEstimate;
import com.lefancrm.apicenter.model.PaymentEstimateApply;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/5/30.
 */
public interface BackendCaseEstimateInfoApi {
    /**
     * 伤残测算列表
     * @param apiRequest
     * @return
     */
    ApiResponse<List<CaseEstimateInfoDto>> caseEstimateInfoList(ApiRequest apiRequest);

    /**
     * 伤残预估详情
     * @param
     * @return
     */
    ApiResponse<InvalidismEstimate> searchInvalidismEstimateById(ApiRequest apiRequest);


    /**
     * 赔付测算详情
     * @param
     * @return
     */
    ApiResponse<PaymentEstimateApply> searchPaymentEstimateApplyById(ApiRequest apiRequest);


}
