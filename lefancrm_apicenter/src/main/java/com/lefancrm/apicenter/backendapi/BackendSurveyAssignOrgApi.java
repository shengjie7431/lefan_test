package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by lixianfeng on 2019/2/15.
 */
public interface BackendSurveyAssignOrgApi {
    ApiResponse list(ApiRequest apiRequest);
    ApiResponse info(ApiRequest apiRequest);
    ApiResponse operate(ApiRequest apiRequest);
    ApiResponse tasks(ApiRequest apiRequest);

    ApiResponse getHelpReportData(ApiRequest apiRequest);

    ApiResponse getSurveyOrgs(ApiRequest apiRequest);
    ApiResponse listVisit(ApiRequest apiRequest);
    ApiResponse orgCaseRemindList(ApiRequest apiRequest);
    ApiResponse orgCaseRemindOperate(ApiRequest apiRequest);
}
