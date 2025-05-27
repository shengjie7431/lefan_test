package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * 认领明细表
 * @author EDZ
 */
public interface BillingReceiveInfoApi {

    /**
     * 查询所有数据
     * @param request
     * @return
     */
    ApiResponse selectByMap(ApiRequest request);

    ApiResponse selectByMapRefund(ApiRequest request);

    ApiResponse receiveBillUpd(ApiRequest request);
    ApiResponse receiveBillRefundUpd(ApiRequest request);
    ApiResponse receiveBillRefundAdd(ApiRequest request);
}
