package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendFinancialBudgetApi {

    ApiResponse budgetList(ApiRequest apiReq);
    ApiResponse budgetInfoList(ApiRequest apiReq);
    ApiResponse budgetInfoOne(ApiRequest apiReq);
    ApiResponse budgetAdd(ApiRequest apiReq);
    ApiResponse budgetDelete(ApiRequest apiReq);
    ApiResponse budgetEdit(ApiRequest apiReq);
    ApiResponse budgetPageData(ApiRequest apiReq);
    ApiResponse budgetRefresh(ApiRequest apiReq);

    ApiResponse budgetImport(ApiRequest apiReq);
    ApiResponse budgetReportData(ApiRequest apiReq);


}
