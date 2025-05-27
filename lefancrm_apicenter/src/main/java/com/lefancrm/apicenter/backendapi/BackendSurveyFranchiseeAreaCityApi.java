package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendSurveyFranchiseeAreaCityApi {
    ApiResponse getAreaData(ApiRequest apiRequest);
    ApiResponse getCommonAreas(ApiRequest apiRequest);
}
