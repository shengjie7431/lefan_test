package com.lefancrm.apicenter.fina.api;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendFinaApplicantInfoApi {
    ApiResponse list(ApiRequest apiRequest);
    ApiResponse info(ApiRequest apiRequest);
    ApiResponse operate(ApiRequest apiRequest);
    ApiResponse ajaxData(ApiRequest apiRequest);
    ApiResponse assignation(ApiRequest apiRequest);
}
