package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by lixianfeng on 2018/12/18.
 */
public interface SurveyRiskCaseApi {
    ApiResponse entrustSurveyRiskCase(ApiRequest apiRequest);
    ApiResponse manager(ApiRequest apiRequest);
}
