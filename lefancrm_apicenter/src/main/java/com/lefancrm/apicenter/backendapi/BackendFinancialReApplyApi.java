package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendFinancialReApplyApi {
    ApiResponse list(ApiRequest apiRequest);
    ApiResponse info(ApiRequest apiRequest);
    ApiResponse operate(ApiRequest apiRequest);
    ApiResponse ajaxData(ApiRequest apiRequest);

}
