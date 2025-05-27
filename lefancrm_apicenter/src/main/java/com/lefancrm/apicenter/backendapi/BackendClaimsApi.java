package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendClaimsApi {
    ApiResponse list(ApiRequest apiRequest);
    ApiResponse operate(ApiRequest apiRequest);
    ApiResponse ajaxData(ApiRequest apiRequest);
}
