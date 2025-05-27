package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendSurveyHzReportApi {
    ApiResponse getData(ApiRequest apiRequest);
    ApiResponse getHZCaseList(ApiRequest apiRequest);
    ApiResponse getDataRole(ApiRequest apiRequest);
    ApiResponse getAssessmentIndexCaseList(ApiRequest apiRequest);
}
