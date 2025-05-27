package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendSurveyReportApi {
    ApiResponse getData(ApiRequest apiRequest);
    ApiResponse getAccList(ApiRequest apiRequest);
    ApiResponse reportScoreExport(ApiRequest apiRequest);
    ApiResponse reportScore(ApiRequest apiRequest);


    ApiResponse getSurveyOrgData(ApiRequest apiRequest);

    ApiResponse getSurveyOrgDetailData(ApiRequest apiRequest);
    ApiResponse getExpenseReimbursementInfo(ApiRequest apiRequest);
}
