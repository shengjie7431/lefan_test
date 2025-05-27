package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendSurveyReportThinkApi {
    ApiResponse getData(ApiRequest apiRequest);
    ApiResponse getPros(ApiRequest apiRequest);
}
