package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendCaseAssessmentReportApi {
    ApiResponse addCaseAssessmentReport(ApiRequest apiReq);
    ApiResponse updCaseAssessmentReport(ApiRequest apiReq);
    ApiResponse getCaseAssessmentReport(ApiRequest apiReq);


    ApiResponse updCaseAssessmentObjReport(ApiRequest apiReq);
    ApiResponse getCaseAssessmentObjReport(ApiRequest apiReq);

    ApiResponse createCaseAssessmentReportPDF(ApiRequest apiReq);
}
