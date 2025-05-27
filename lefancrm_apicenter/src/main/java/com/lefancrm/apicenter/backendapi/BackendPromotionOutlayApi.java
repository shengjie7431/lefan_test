package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.PromotionOutlay;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/6/21.
 */
public interface BackendPromotionOutlayApi {

    /**
     * 根据“caseNo”查询推广费用记录表
     * @param apiRequest
     * @return
     */
    ApiResponse<List<PromotionOutlay>> searchLoanApplicationByLoanNo(ApiRequest apiRequest);

}
