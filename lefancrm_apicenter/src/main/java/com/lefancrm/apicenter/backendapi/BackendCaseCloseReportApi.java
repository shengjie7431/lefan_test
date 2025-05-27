package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendCaseCloseReportApi {
    ApiResponse addCaseCloseReport(ApiRequest apiReq);
    ApiResponse updCaseCloseReport(ApiRequest apiReq);
    ApiResponse getCaseCloseReport(ApiRequest apiReq);


    ApiResponse updCaseCloseObjReport(ApiRequest apiReq);
    ApiResponse getCaseCloseObjReport(ApiRequest apiReq);

//    ApiResponse createCaseCloseReportPDF(ApiRequest apiReq);
}
