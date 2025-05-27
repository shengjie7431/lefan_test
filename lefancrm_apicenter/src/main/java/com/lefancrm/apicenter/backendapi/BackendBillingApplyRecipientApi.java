package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.BillingApplyRecipient;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/10/29.
 */
public interface BackendBillingApplyRecipientApi {

    /**
     * 开票收件地址list
     * @param apiRequest
     * @return
     */
    ApiResponse<List<BillingApplyRecipient>> billingApplyRecipientList(ApiRequest apiRequest);

    ApiResponse info(ApiRequest apiRequest);

}
