package com.lefancrm.apicenter.backendapi;
import com.lefancrm.apicenter.model.StaffPostAppellation;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

public interface BackendStaffPostAppellationApi {

    ApiResponse staffPostAppellationDel(ApiRequest apiReq);

    ApiResponse staffPostAppellationAdd(ApiRequest apiReq);

    ApiResponse staffPostAppellationAddSelective(ApiRequest apiReq);

    ApiResponse queryStaffPostAppellationById(ApiRequest apiReq);

    ApiResponse updateStaffPostAppellationByParam(ApiRequest apiReq);

    ApiResponse updateStaffPostAppellation(ApiRequest apiReq);

    ApiResponse selectStaffPostAppellationList(ApiRequest apiReq);

    ApiResponse selectStaffPostAppellationListAll(ApiRequest apiReq);
}
