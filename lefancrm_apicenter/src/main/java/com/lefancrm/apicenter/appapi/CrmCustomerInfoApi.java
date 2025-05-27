package com.lefancrm.apicenter.appapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by jun on 2017/12/15.
 */
public interface CrmCustomerInfoApi {


    ApiResponse addCustomerInfo(ApiRequest apiReq);

    ApiResponse queryCustomerDetails(ApiRequest apiReq);

    ApiResponse editCustomerInfo(ApiRequest apiReq);

    ApiResponse queryCustomerList(ApiRequest apiReq);

    ApiResponse queryScreenCcIdAndOrgId(ApiRequest apiReq);
    ApiResponse queryScreenCcIdByOrgId(ApiRequest apiReq);
}
