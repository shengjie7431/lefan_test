package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendSurveyZhaAssessApi {
    ApiResponse list(ApiRequest apiRequest);
    ApiResponse operate(ApiRequest apiRequest);
    ApiResponse info(ApiRequest apiRequest);
    ApiResponse ajaxData(ApiRequest apiRequest);
}
