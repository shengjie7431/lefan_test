package com.lefancrm.apicenter.fina.api;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendFinaOperationsManagementApi {
    ApiResponse list(ApiRequest apiRequest);
    ApiResponse operate(ApiRequest apiRequest);
    ApiResponse ajaxData(ApiRequest apiRequest);


    ApiResponse priceList(ApiRequest apiRequest);
    ApiResponse priceOperate(ApiRequest apiRequest);
    ApiResponse priceAjaxData(ApiRequest apiRequest);

    ApiResponse signList(ApiRequest apiRequest);
    ApiResponse signOperate(ApiRequest apiRequest);
    ApiResponse signAjaxData(ApiRequest apiRequest);


    ApiResponse coefficientList(ApiRequest apiRequest);
    ApiResponse coefficientOperate(ApiRequest apiRequest);
    ApiResponse coefficientAjaxData(ApiRequest apiRequest);
}
